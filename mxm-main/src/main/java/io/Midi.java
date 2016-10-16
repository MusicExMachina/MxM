package io;

import com.sun.media.sound.StandardMidiFileReader;
import com.sun.media.sound.StandardMidiFileWriter;
import model.basic.*;
import model.structure.Passage;

import javax.sound.midi.*;
import javax.sound.midi.spi.MidiFileReader;
import javax.sound.midi.spi.MidiFileWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public abstract class Midi
{
    /* A few of the most useful Midi messages */
    private static final int TEMPO_MSG       = 0x51;
    private static final int TIME_SIGN_MSG   = 0x58;
    private static final int TEXT_MSG        = 0x01;

    /**
     * Loads a midi Sequence from a given filename.
     * @param fileName The filename of the file we want.
     * @return A midi Sequence loaded in from this file.
     * @throws IOException If the reading fails for some reason.
     * @throws InvalidMidiDataException  If the midi data is flawed.
     */
    public static Sequence load (String fileName) throws IOException, InvalidMidiDataException {
        MidiFileReader reader = new StandardMidiFileReader();
        return reader.getSequence(new FileInputStream(fileName));
    }

    /**
     * Downloads a midi file from some remote location.
     * @param url The url of the file we want to download.
     * @return A midi Sequence downloaded from this URL.
     * @throws IOException If the reading fails for some reason.
     * @throws InvalidMidiDataException If the midi data is flawed.
     */
    public static Sequence download (String url) throws IOException, InvalidMidiDataException {
        MidiFileReader reader = new StandardMidiFileReader();
        return reader.getSequence(new URL(url));
    }

    /**
     * Saves a given midi sequence to a file. Note that this
     * may need some fine tuning for each of the three midi
     * file types.
     * @param sequence The midi Sequence to write to a file.
     * @param fileName The filename of the file we want.
     * @throws IOException If the writing fails for some reason.
     */
    public static void save(Sequence sequence, String fileName) throws IOException {
        MidiFileWriter writer = new StandardMidiFileWriter();
        int midiType = MidiSystem.getMidiFileTypes(sequence)[0];
        writer.write(sequence,midiType,new File(fileName));
    }

    /**
     * Takes in a midi Sequence and writes a Passage. The
     * Passage may then be parsed in a number of ways, including
     * forming RhythmTrees, etc. as Passages carry *more* useful
     * musical information than Sequences alone.
     * @param sequence
     * @return
     */
    public static Passage parse(Sequence sequence) {

        System.out.println("Reading in Midi sequence...");

        // The Passage we're going to return
        Passage passage = new Passage();

        for(Patch patch : sequence.getPatchList()) {
            System.out.println(patch.toString());
        }

        // For every Midi track
        for (Track track : sequence.getTracks()) {

            System.out.println("MIDI:\tNew track");

            // Creates a HashMap from Pitches to the time they were last
            // played and left "unresolved." Note that if a Pitch has been
            // resolved, we simply remove the key-value pair from the HashMap.
            HashMap<Pitch,Count> currentPitches = new HashMap<Pitch,Count>();

            // For every Midi event
            for (int i = 0; i < track.size(); i++) {

                // Some information about this Midi event
                MidiEvent event     = track.get(i);
                MidiMessage message = event.getMessage();
                Long tick           = event.getTick();

                // If it's a Midi short message
                if (message instanceof ShortMessage) {

                    // Cast it to a Midi short message
                    ShortMessage sm     = (ShortMessage)message;
                    int channel         = sm.getChannel();
                    int pitchValue;
                    int velocityValue;
                    Pitch pitch;
                    Count time;
                    Count lastTime;

                    switch (sm.getCommand()) {

                        // Note on messages for each channel
                        case ShortMessage.NOTE_ON:

                            pitchValue      = sm.getData1();
                            velocityValue   = sm.getData2();            // TODO: Implement this later
                            pitch           = new Pitch(pitchValue);
                            time            = Count.ZERO;
                            lastTime        = currentPitches.get(pitch);

                            // If this isn't a note off in disguise
                            if(velocityValue != 0) {
                                // If the note we're looking at has not been left unresolved
                                if(lastTime == null) {
                                    currentPitches.put(pitch,time);
                                }
                                // If the note we're looking at has been left unended
                                else {
                                    System.out.println("MIDI:\tRearticulating note (" + pitch.toString() + ") that was not ended");
                                }
                            }
                            // If this is really a note off message
                            else {
                                // If the note we're looking at has not been left unresolved
                                if(lastTime != null) {
                                    Note note = new Note(pitch,time.minus(lastTime));
                                    System.out.println("MIDI:\t"+note.toString());
                                }
                                // If the note we're looking at has been left unended
                                else {
                                    System.out.println("MIDI:\tEnding note (" + pitch.toString() + ") that was not ended");
                                }
                            }

                            break;

                        // Note off messages for each channel
                        case ShortMessage.NOTE_OFF:

                            pitchValue      = sm.getData1();
                            velocityValue   = sm.getData2();            // TODO: Implement this later
                            pitch           = new Pitch(pitchValue);
                            time            = Count.ZERO;
                            lastTime        = currentPitches.get(pitch);

                            // If the note we're looking at has not been left unresolved
                            if(lastTime != null) {
                                Note note = new Note(pitch,time.minus(lastTime));
                                System.out.println("MIDI:\t"+note.toString());
                            }
                            // If the note we're looking at has been left unended
                            else {
                                System.out.println("MIDI:\tEnding note (" + pitch.toString() + ") that was not ended");
                            }

                            break;

                        // Control change message
                        case ShortMessage.CONTROL_CHANGE:
                            System.out.println("MIDI:\tSetting controller to " + sm.getData1());
                            break;

                        case ShortMessage.PROGRAM_CHANGE:
                            System.out.println("MIDI:\tSetting instrument to " + sm.getData1());
                            break;

                        // In the case that we don't know what Midi ShortMessage was sent.
                        default:
                            System.out.println("MIDI:\tUnrecognized Midi ShortMessage " + sm.getCommand());
                            break;
                    }
                }
                // If it's a Midi short message
                else if (message instanceof MetaMessage)
                {
                    // Cast it to a Midi short message
                    MetaMessage mm = (MetaMessage)message;
                    byte[] data = mm.getData();
                    int type = mm.getType();

                    switch(type) {

                        //
                        case 0x20:
                            System.out.println("MIDI:\tMidi Channel prefix:" + mm.getData().toString());
                            break;

                        // A tempo message
                        case TEMPO_MSG:
                            int tempoValue = (data[0] & 0xff) << 16 |
                                             (data[1] & 0xff) << 8 |
                                             (data[2] & 0xff);
                            int bpm = 60000000 / tempoValue;
                            Tempo tempo = new Tempo(bpm);
                            System.out.println("MIDI:\tSetting tempo: " + tempo.toString());
                            break;

                        // A time-signature message
                        case TIME_SIGN_MSG:
                            int numerator   = mm.getData()[0];
                            int denominator = 2 << (mm.getData()[1] - 1);
                            TimeSignature timeSignature = new TimeSignature(numerator, denominator);
                            System.out.println("MIDI:\tSetting time signature: " + timeSignature.toString());
                            break;

                        // A tempo message
                        case TEXT_MSG:
                            break;

                        // If we don't know the message
                        default:
                            System.out.println("MIDI:\tUnrecognized Midi MetaMessage " + mm.getData());
                            break;
                    }
                }
                // If it's a System-exclusive message
                else if (message instanceof SysexMessage)
                {
                    SysexMessage sm = (SysexMessage)message;
                    System.out.println("MIDI:\tUnrecognized Midi SysexMessage " + sm.getData());
                }
            }
        }
        /*
        // Print everything out
        for(Long tick : frames.keySet())
        {
            // System.out.println(tick.toString() + " \t: " +frames.get(tick).toString());
        }
        */
        System.out.println("... finished reading Midi sequence.");

        // Finally our passage is done
        return passage;
    }

    /**
     * Takes in a Passage and writes a midi Sequence from
     * it. The Sequence can then be played using Midi.play()
     * or saved to a midi file using Midi.save()
     * @param passage The Passage to make into a Sequence.
     * @return The created Midi Sequence.
     */
    public static Sequence write(Passage passage) {
        try {
            return new Sequence(0.0f,1);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes a midi Sequence and plays it using a
     * device's default sequencer (if possible).
     * @param sequence The midi Sequence to play
     * @throws MidiUnavailableException If there is no midi sequencer.
     * @throws InvalidMidiDataException If the midi Sequence is flawed.
     */
    public static void play(Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException {
        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.open();
        sequencer.setSequence(sequence);
        sequencer.start();
    }
}