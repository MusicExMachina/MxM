import base.Count;
import base.Tempo;
import base.TimeSignature;
import form.Note;
import form.Part;
import form.Passage;
import io.Writer;

import javax.sound.midi.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * MidiWriter is a class which does exactly what you'd expect.
 * form.Note that each MidiWriter composes exactly *one* midi Sequence.
 * This means that the MidiTools class instantiates one for every
 * single form.Passage to be written. This class could potentially be
 * absorbed into MidiTools, but is separated for the code cleanness.
 */
class MidiWriter {

    private static int resolution = 24;
    private Passage passage = null;
    private Sequence sequence = null;

    private TreeMap<Float,Long> timePoints;

    public Sequence run(Passage passage) {
        try {
            this.passage = passage;
            this.sequence = new Sequence(javax.sound.midi.Sequence.PPQ,resolution);
            this.timePoints = new TreeMap<>();

            // Put a starting point in
            timePoints.put(0f,(long)0);

            // Create and initialize the control track (for tempi and time signatures)
            Track controlTrack = sequence.createTrack();
            initTrack(controlTrack);

            MetaMessage mt;
            MidiEvent me;

            // Set the default time sig

            mt = new MetaMessage();
            byte[] bt = {0x04, 0x04, (byte)resolution, (byte)8}; // Should work... should.
            mt.setMessage(0x58 ,bt, 3);
            me = new MidiEvent(mt,(long)0);
            controlTrack.add(me);


            int lastTimeSigChange = 0;
            // Add all of the timeSignature changes
            Iterator<Integer> timeSigItr = passage.timeSignatureIterator();
            while(timeSigItr.hasNext()) {
                int measure = timeSigItr.next();
                Count count = new Count(measure);
                TimeSignature timeSignature = passage.getTimeSignatureAt(count);

                long ticksPassed = timePoints.lastEntry().getValue();
                //timePoints.put((float)measure,(long)(measure-lastTimeSigChange)*resolution*timeSignature.getNumerator()/timeSignature.getDenominator()*4));

                lastTimeSigChange = measure;
                timePoints.put(0f,(long)0);

                System.out.println("Time sig set to " + timeSignature + " at " + count);
                // Set time sig

                int cc = 1;
                switch(timeSignature.getDenominator()) {
                    case 1:     cc = 0;     break;
                    case 2:     cc = 1;     break;
                    case 4:     cc = 2;     break;
                    case 8:     cc = 3;     break;
                    case 16:    cc = 4;     break;
                }

                mt = new MetaMessage();
                byte[] bytes = {(byte)timeSignature.getNumerator(), (byte)cc, (byte)resolution, (byte)8}; // Should work... should.
                mt.setMessage(0x58 ,bytes, 3);
                me = new MidiEvent(mt,(long)(resolution *count.toFloat()));
                controlTrack.add(me);
            }

            // Add all of the tempo changes
            Iterator<Count> tempoItr = passage.tempoChangeIterator();
            while(tempoItr.hasNext()) {
                Count time = tempoItr.next();
                Tempo tempo = passage.getTempoAt(time);
                int ppqn = 60000000 / tempo.getBPM();

                // Set tempo
                mt = new MetaMessage();
                byte[] bytes = {(byte)(ppqn >> 16), (byte)(ppqn >> 8), (byte)(ppqn >> 0)}; // Should work... should.
                mt.setMessage(0x51, bytes, 3);
                me = new MidiEvent(mt,(long)(resolution *time.toFloat()));
                controlTrack.add(me);
            }

            System.out.println("MIDI READER:\tAdded a new track (from a Part)");
            // For every part in the passage, create a track, and fill it with all the notes in that track
            for(Part line : passage) {
                Track track = sequence.createTrack();
                initTrack(track);
                for(Note note : line) {
                    addNote(note, track);
                }
                //endTrack(track);
            }
        }
        catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return sequence;
    }

    private void init(Track track) throws InvalidMidiDataException {

    }

    private void initTrack(Track track) throws InvalidMidiDataException {
        //****  General MIDI sysex -- turn on General MIDI sound set  ****
        byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
        SysexMessage sm = new SysexMessage();
        sm.setMessage(b, 6);
        MidiEvent me = new MidiEvent(sm,(long)0);
        track.add(me);

        /*
        //****  set track name (meta event)  ****
        MetaMessage mt = new MetaMessage();
        String TrackName = "Control Track";
        mt.setMessage(0x03 ,TrackName.getBytes(), TrackName.length());
        me = new MidiEvent(mt,(long)0);
        track.add(me);

        //****  set omni on  ****
        ShortMessage mm = new ShortMessage();
        mm.setMessage(0xB0, 0x7D,0x00);
        me = new MidiEvent(mm,(long)0);
        track.add(me);

        //****  set poly on  ****
        mm = new ShortMessage();
        mm.setMessage(0xB0, 0x7F,0x00);
        me = new MidiEvent(mm,(long)0);
        track.add(me);
        */

        // Set the instrument to piano
        ShortMessage instChange = new ShortMessage();
        instChange.setMessage(ShortMessage.PROGRAM_CHANGE, 0, 0, 0);
        track.add(new MidiEvent(instChange, 0));
    }

    private void addNote(Note note, Track track) throws InvalidMidiDataException {
        //System.out.println("Added note");

        ShortMessage on = new ShortMessage();
        ShortMessage off = new ShortMessage();
        on.setMessage(ShortMessage.NOTE_ON, 0, note.getPitch().getValue(), 60);
        off.setMessage(ShortMessage.NOTE_OFF, 0, note.getPitch().getValue(), 0);
        track.add(new MidiEvent(on, (long)(resolution * note.getStart().toFloat())));
        track.add(new MidiEvent(off, (long)(resolution * note.getEnd().toFloat())));
    }

    private void endTrack(Track track) throws InvalidMidiDataException {
        System.out.println("Ending track");
        MetaMessage mt = new MetaMessage();
        byte[] bet = {}; // empty array
        mt.setMessage(0x2F,bet,0);
        MidiEvent me = new MidiEvent(mt, (long)10000000); // TEMPORARY
        track.add(me);
    }

    private long interpolate(float time) {
        // Get the time points before and after this tick
        float earlierTime    = timePoints.floorKey(time);
        float laterTime      = timePoints.ceilingKey(time);

        // If we're right on the time point we want
        if(Float.compare(earlierTime,laterTime) == 0) {
            return timePoints.get(earlierTime);
        }
        // If we have to interpolate
        else {
            // Figure out what fractions-of-a-measure those time points
            // represent, and lerp between them to figure out where "tick" is
            long earlierTimePoint  = timePoints.get(earlierTime);
            long laterTimePoint    = timePoints.get(laterTime);
            long relativePosition  = (long)((time - earlierTime) / (laterTime - earlierTime));
            return (relativePosition * (laterTimePoint - earlierTimePoint )) + earlierTimePoint;
        }
    }


    public static void main(String argv[]) throws IOException, InvalidMidiDataException, MidiUnavailableException {
        Sequence sequence = MidiTools.download("https://www.8notes.com/school/midi/violin/bach_bourree.mid");
        Passage passage = MidiTools.parse(sequence);
        Writer.write(passage,"input");

        Sequence out = MidiTools.write(passage);
        Passage outputPassage = MidiTools.parse(out);
        Writer.write(outputPassage,"output");
        MidiTools.play(out);
    }

}
