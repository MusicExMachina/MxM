package io;

import com.sun.media.sound.StandardMidiFileReader;
import com.sun.media.sound.StandardMidiFileWriter;
import model.structure.Passage;

import javax.sound.midi.*;
import javax.sound.midi.spi.MidiFileReader;
import javax.sound.midi.spi.MidiFileWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public abstract class Midi
{
    /* A few of the most useful Midi messages */

    /* Note on messages for all 16 Midi channels */
    private static final int NOTE_ON_CH0    = 0x90;
    private static final int NOTE_ON_CH1    = 0x91;
    private static final int NOTE_ON_CH2    = 0x92;
    private static final int NOTE_ON_CH3    = 0x93;
    private static final int NOTE_ON_CH4    = 0x94;
    private static final int NOTE_ON_CH5    = 0x95;
    private static final int NOTE_ON_CH6    = 0x96;
    private static final int NOTE_ON_CH7    = 0x97;
    private static final int NOTE_ON_CH8    = 0x98;
    private static final int NOTE_ON_CH9    = 0x99;
    private static final int NOTE_ON_CH10   = 0x9A;
    private static final int NOTE_ON_CH11   = 0x9B;
    private static final int NOTE_ON_CH12   = 0x9C;
    private static final int NOTE_ON_CH13   = 0x9D;
    private static final int NOTE_ON_CH14   = 0x9E;
    private static final int NOTE_ON_CH15   = 0x9F;

    /* Note off messages for all 16 Midi channels */
    private static final int NOTE_OFF_CH0   = 0x80;
    private static final int NOTE_OFF_CH1   = 0x81;
    private static final int NOTE_OFF_CH2   = 0x82;
    private static final int NOTE_OFF_CH3   = 0x83;
    private static final int NOTE_OFF_CH4   = 0x84;
    private static final int NOTE_OFF_CH5   = 0x85;
    private static final int NOTE_OFF_CH6   = 0x86;
    private static final int NOTE_OFF_CH7   = 0x87;
    private static final int NOTE_OFF_CH8   = 0x88;
    private static final int NOTE_OFF_CH9   = 0x89;
    private static final int NOTE_OFF_CH10  = 0x8A;
    private static final int NOTE_OFF_CH11  = 0x8B;
    private static final int NOTE_OFF_CH12  = 0x8C;
    private static final int NOTE_OFF_CH13  = 0x8D;
    private static final int NOTE_OFF_CH14  = 0x8E;
    private static final int NOTE_OFF_CH15  = 0x8F;

    /**/
    private static final int TEMPO      = 0x51;
    private static final int TIME_SIGN  = 0x58;

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
    public static Passage read(Sequence sequence) {

        System.out.println("Reading in Midi sequence...");

        // The Passage we're going to return
        Passage passage = new Passage();

        for(Patch patch : sequence.getPatchList()) {
            System.out.println(patch.toString());
        }

        // For every Midi track
        for (Track track : sequence.getTracks()) {

            // For every Midi event
            for (int i = 0; i < track.size(); i++) {

                // Some information about this Midi event
                MidiEvent event     = track.get(i);
                MidiMessage message = event.getMessage();
                Long tick           = event.getTick();

                // If it's a Midi short message
                if (message instanceof ShortMessage) {

                    // Cast it to a Midi short message
                    ShortMessage sm = (ShortMessage)message;
                    int channel     = sm.getChannel();

                    switch (sm.getCommand()) {

                        // Note on messages for each channel
                        case NOTE_ON_CH0:   case NOTE_ON_CH1:   case NOTE_ON_CH2:   case NOTE_ON_CH3:
                        case NOTE_ON_CH4:   case NOTE_ON_CH5:   case NOTE_ON_CH6:   case NOTE_ON_CH7:
                        case NOTE_ON_CH8:   case NOTE_ON_CH9:   case NOTE_ON_CH10:  case NOTE_ON_CH11:
                        case NOTE_ON_CH12:  case NOTE_ON_CH13:  case NOTE_ON_CH14:  case NOTE_ON_CH15:

                            int pitch       = sm.getData1();
                            int velocity    = sm.getData2();
                            int voice       = 0;                // Later

                            /*
                            note.setPitch(pitch);
                            note.setVelocity(velocity);
                            note.setAttack(tick);

                            notes.put(note.getUID(),note);

                            if(frames.containsKey(tick)) {
                                frames.get(tick).add(note);
                            }
                            else {
                                frames.put(tick,new ArrayList<Note>());
                                frames.get(tick).add(note);
                            }

                            break;
                            */
                        // Note off messages for each channel
                        case NOTE_OFF_CH0:  case NOTE_OFF_CH1:  case NOTE_OFF_CH2:  case NOTE_OFF_CH3:
                        case NOTE_OFF_CH4:  case NOTE_OFF_CH5:  case NOTE_OFF_CH6:  case NOTE_OFF_CH7:
                        case NOTE_OFF_CH8:  case NOTE_OFF_CH9:  case NOTE_OFF_CH10: case NOTE_OFF_CH11:
                        case NOTE_OFF_CH12: case NOTE_OFF_CH13: case NOTE_OFF_CH14: case NOTE_OFF_CH15:

                            //Pitch pitch     = new Pitch(sm.getData1())

                            break;

                        // In the case that we don't know what Midi ShortMessage was sent.
                        default:
                            System.out.println("Unknown Midi ShortMessage: " + sm.getCommand());
                            break;
                    }
                }
                // If it's a Midi short message
                else if (message instanceof MetaMessage)
                {
                    // Cast it to a Midi short message
                    MetaMessage mm = (MetaMessage)message;
                    int type = mm.getType();

                    switch(type){
                        case 0x20:      System.out.println("Midi Channel prefix:" + mm.getData().toString()); break;
                        case TEMPO:     System.out.println("Tempo message"); break;
                        case TIME_SIGN: System.out.println("Time signature message" + mm.getData().toString()); break;
                        default:        System.out.print("Unrecognized midi message"); break;
                    }
                }
                // If it's a SysEx Message
                else
                {

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