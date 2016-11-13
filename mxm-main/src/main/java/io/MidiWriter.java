package io;

import model.form.Line;
import model.form.Note;
import model.form.Passage;
import model.trainable.*;

import javax.sound.midi.*;
import java.io.File;
import java.util.Iterator;

/**
 * MidiWriter is a class which does exactly what you'd expect.
 * Note that each MidiWriter composes exactly *one* midi Sequence.
 * This means that the MidiTools class instantiates one for every
 * single Passage to be written. This class could potentially be
 * absorbed into MidiTools, but is separated for the code cleanness.
 */
class MidiWriter {

    public Sequence run(Passage passage) {
        try {
            Sequence s = new Sequence(Sequence.PPQ,24);
            Iterator<Line> itr = passage.lineIterator();
            while(itr.hasNext()) {
                Track track = s.createTrack();
                for(Note note : itr.next()) {

                }
            }
        }
        catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String argv[]) {
        System.out.println("midifile begin ");
        try
        {
            //****  Create a new MIDI sequence with 24 ticks per beat  ****
            Sequence s = new Sequence(javax.sound.midi.Sequence.PPQ,24);

            //****  Obtain a MIDI track from the sequence  ****
            Track t = s.createTrack();

            //****  General MIDI sysex -- turn on General MIDI sound set  ****
            byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
            SysexMessage sm = new SysexMessage();
            sm.setMessage(b, 6);
            MidiEvent me = new MidiEvent(sm,(long)0);
            t.add(me);

            //****  set tempo (meta event)  ****
            MetaMessage mt = new MetaMessage();
            byte[] bt = {0x02, (byte)0x00, 0x00};
            mt.setMessage(0x51 ,bt, 3);
            me = new MidiEvent(mt,(long)0);
            t.add(me);

            //****  set track name (meta event)  ****
            mt = new MetaMessage();
            String TrackName = new String("midifile track");
            mt.setMessage(0x03 ,TrackName.getBytes(), TrackName.length());
            me = new MidiEvent(mt,(long)0);
            t.add(me);

            //****  set omni on  ****
            ShortMessage mm = new ShortMessage();
            mm.setMessage(0xB0, 0x7D,0x00);
            me = new MidiEvent(mm,(long)0);
            t.add(me);

            //****  set poly on  ****
            mm = new ShortMessage();
            mm.setMessage(0xB0, 0x7F,0x00);
            me = new MidiEvent(mm,(long)0);
            t.add(me);

            //****  set instrument to Piano  ****
            mm = new ShortMessage();
            mm.setMessage(0xC0, 0x00, 0x00);
            me = new MidiEvent(mm,(long)0);
            t.add(me);

            //****  note on - middle C  ****
            mm = new ShortMessage();
            mm.setMessage(0x90,0x3C,0x60);
            me = new MidiEvent(mm,(long)1);
            t.add(me);

            //****  note off - middle C - 120 ticks later  ****
            mm = new ShortMessage();
            mm.setMessage(0x80,0x3C,0x40);
            me = new MidiEvent(mm,(long)121);
            t.add(me);

            //****  set end of track (meta event) 19 ticks later  ****
            mt = new MetaMessage();
            byte[] bet = {}; // empty array
            mt.setMessage(0x2F,bet,0);
            me = new MidiEvent(mt, (long)140);
            t.add(me);

            MidiTools.play(s);

            //****  write the MIDI sequence to a MIDI file  ****
            File f = new File("midifile.mid");
            MidiSystem.write(s,1,f);

        } //try
        catch(Exception e)
        {
            System.out.println("Exception caught " + e.toString());
        } //catch
        System.out.println("midifile end ");
    } //main
} //midifile
