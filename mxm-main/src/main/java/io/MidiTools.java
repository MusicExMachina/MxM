package io;

import com.sun.media.sound.StandardMidiFileReader;
import com.sun.media.sound.StandardMidiFileWriter;
import model.structure.Passage;

import javax.sound.midi.*;
import javax.sound.midi.Instrument;
import javax.sound.midi.spi.MidiFileReader;
import javax.sound.midi.spi.MidiFileWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public abstract class MidiTools
{

    /**
     * Prints some useful information about this system's
     * Midi sequencer, synthesizer, and so forth.
     */
    public static void printInfo() {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();

            System.out.println("MIDI:\tReceivers:");
            List<Receiver> receivers = sequencer.getReceivers();
            for(Receiver receiver : receivers) {
                System.out.println("MIDI:\t\t"+receiver.toString());
            }

            System.out.println("MIDI:\tTransmitters:");
            List<Transmitter> transmitters = sequencer.getTransmitters();
            for(Transmitter transmitter : transmitters) {
                System.out.println("MIDI:\t\t"+transmitter.toString());
            }

            Synthesizer synthesizer = MidiSystem.getSynthesizer();

            System.out.println("MIDI:\tInstruments:");
            Instrument[] instruments = synthesizer.getAvailableInstruments();
            for(Instrument instrument : instruments) {
                System.out.println("MIDI:\t\t"+instrument.getSoundbank().getName() + " - " + instrument.getName());
            }
            System.out.println("");

        } catch (MidiUnavailableException e) {
            System.out.println("MIDI:\tThe sequencer is unavailable");
        }
    }

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
        // Spawn off a parser object
        MidiParser midiParser = new MidiParser();
        return midiParser.run(sequence);
    }

    /**
     * Takes in a Passage and writes a midi Sequence from
     * it. The Sequence can then be played using MidiTools.play()
     * or saved to a midi file using MidiTools.save()
     * @param passage The Passage to make into a Sequence.
     * @return The created MidiTools Sequence.
     */
    public static Sequence write(Passage passage) {
        // Spawn off a parser object
        MidiComposer midiComposer = new MidiComposer();
        return midiComposer.run(passage);
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