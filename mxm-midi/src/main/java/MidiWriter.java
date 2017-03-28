import base.Count;
import base.Tempo;
import base.TimeSignature;
import form.Note;
import form.Part;
import form.Passage;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * MidiWriter is a class which does exactly what you'd expect.
 * form.Note that each MidiWriter composes exactly *one* midi Sequence.
 * This means that the MidiTools class instantiates one for every
 * single form.Passage to be written. This class could potentially be
 * absorbed into MidiTools, but is separated for the code cleanness.
 */
class MidiWriter {

    private Passage passage;
    private Sequence sequence = null;

    public Sequence run(Passage passage) {
        try {
            this.passage = passage;
            sequence = new Sequence(javax.sound.midi.Sequence.PPQ,24);
            Track controlTrack = sequence.createTrack();

            //****  General MIDI sysex -- turn on General MIDI sound set  ****
            byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
            SysexMessage sm = new SysexMessage();
            sm.setMessage(b, 6);
            MidiEvent me = new MidiEvent(sm,(long)0);
            controlTrack.add(me);

            //****  set track name (meta event)  ****
            MetaMessage mt = new MetaMessage();
            String TrackName = "Control Track";
            mt.setMessage(0x03 ,TrackName.getBytes(), TrackName.length());
            me = new MidiEvent(mt,(long)0);
            controlTrack.add(me);

            //****  set omni on  ****
            ShortMessage mm = new ShortMessage();
            mm.setMessage(0xB0, 0x7D,0x00);
            me = new MidiEvent(mm,(long)0);
            controlTrack.add(me);

            //****  set poly on  ****
            mm = new ShortMessage();
            mm.setMessage(0xB0, 0x7F,0x00);
            me = new MidiEvent(mm,(long)0);
            controlTrack.add(me);

            //****  set instrument to Piano  ****
            mm = new ShortMessage();
            mm.setMessage(0xC0, 0x00, 0x00);
            me = new MidiEvent(mm,(long)0);
            controlTrack.add(me);

            Iterator<Integer> timeSigItr = passage.timeSignatureIterator();
            while(timeSigItr.hasNext()) {
                Count time = new Count(timeSigItr.next());
                Tempo tempo = passage.getTempoAt(time);
                int ppqn = 60000000 * tempo.getBPM();


                byte[] data = message.getData();
                int numerator   = data[0];
                int denominator = 2 << (data[1] - 1);
                TimeSignature timeSignature  = new TimeSignature(4,4);
                if(numerator == 0 || denominator == 0) {
                    System.out.println("MIDI PARSER:\tImproper time signature message, reverting to 4/4");
                }
                else {
                    timeSignature = new TimeSignature(numerator, denominator);
                }
                timeSigsLong.put(tick,timeSignature);

                // Set tempo
                mt = new MetaMessage();
                byte[] bt = {(byte)(ppqn >> 16), (byte)(ppqn >> 8), (byte)(ppqn >> 2)}; // Should work... should.
                mt.setMessage(0x51 ,bt, 3);
                me = new MidiEvent(mt,(long)0);
                controlTrack.add(me);
            }

            Iterator<Count> tempoItr = passage.tempoChangeIterator();
            while(tempoItr.hasNext()) {
                Count time = tempoItr.next();
                Tempo tempo = passage.getTempoAt(time);
                int ppqn = 60000000 * tempo.getBPM();

                // Set tempo
                mt = new MetaMessage();
                byte[] bt = {(byte)(ppqn >> 16), (byte)(ppqn >> 8), (byte)(ppqn >> 2)}; // Should work... should.
                mt.setMessage(0x51 ,bt, 3);
                me = new MidiEvent(mt,(long)0);
                controlTrack.add(me);
            }


            int trackNumber = 0;
            for(Part line : passage) {
                Track track = sequence.createTrack();
                trackNumber++;

                //****  set track name (meta event)  ****
                mt = new MetaMessage();
                TrackName = "Track #" + trackNumber;
                mt.setMessage(0x03 ,TrackName.getBytes(), TrackName.length());
                me = new MidiEvent(mt,(long)0);
                track.add(me);

                //****  set omni on  ****
                mm = new ShortMessage();
                mm.setMessage(0xB0, 0x7D,0x00);
                me = new MidiEvent(mm,(long)0);
                track.add(me);

                //****  set poly on  ****
                mm = new ShortMessage();
                mm.setMessage(0xB0, 0x7F,0x00);
                me = new MidiEvent(mm,(long)0);
                track.add(me);

                //****  set instrument to Piano  ****
                mm = new ShortMessage();
                mm.setMessage(0xC0, 0x00, 0x00);
                me = new MidiEvent(mm,(long)0);
                track.add(me);

                for(Note note : line) {
                    //****  note on - middle C  ****
                    mm = new ShortMessage();
                    mm.setMessage(0x90,(byte)note.getPitch().getValue(),0x60);
                    me = new MidiEvent(mm,(long)note.getStart().toFloat()*100);
                    track.add(me);

                    //****  note off - middle C - 120 ticks later  ****
                    mm = new ShortMessage();
                    mm.setMessage(0x80,(byte)note.getPitch().getValue(),0x40);
                    me = new MidiEvent(mm,(long)note.getEnd().toFloat()*100);
                    track.add(me);
                }

                //****  set end of track (meta event) 19 ticks later  ****
                mt = new MetaMessage();
                byte[] bet = {}; // empty array
                mt.setMessage(0x2F,bet,0);
                me = new MidiEvent(mt, (long)100000); // TEMPORARY
                track.add(me);
            }
        }
        catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return sequence;
    }

    public static void main(String argv[]) throws IOException, InvalidMidiDataException, MidiUnavailableException {
        Sequence sequence = MidiTools.download("http://www.mfiles.co.uk/downloads/edvard-grieg-peer-gynt1-morning-mood.mid");
        Passage passage = MidiTools.parse(sequence);
        Sequence out = MidiTools.write(passage);
        MidiTools.play(out);
    }
}
