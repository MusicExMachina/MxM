import com.sun.media.sound.StandardMidiFileReader;
import form.Score;

import javax.sound.midi.*;
import javax.sound.midi.spi.MidiFileReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

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
     * Loads a midi Sequence from a given file name.
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
     * Loads a number of midi sequences from a list of file names.
     * @param fileNames The filename of the file we want.
     * @return A midi Sequence loaded in from this file.
     * @throws IOException If the reading fails for some reason.
     * @throws InvalidMidiDataException  If the midi data is flawed.
     */
    public static List<Sequence> loadAll (List<String> fileNames) throws IOException, InvalidMidiDataException {
        List<Sequence> toReturn = new ArrayList<>();
        // TODO: Parallelize this
        for(String fileName : fileNames) {
            MidiFileReader reader = new StandardMidiFileReader();
            toReturn.add(reader.getSequence(new FileInputStream(fileName)));
        }
        return toReturn;
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
     * Downloads a number of midi files from some remote location.
     * @param urls The urls of the files we want to download.
     * @return A midi Sequence downloaded from this URL.
     * @throws IOException If the reading fails for some reason.
     * @throws InvalidMidiDataException If the midi data is flawed.
     */
    public static List<Sequence> downloadAll (List<String> urls) throws IOException, InvalidMidiDataException {
        List<Sequence> toReturn = new ArrayList<>();
        // TODO: Parallelize this
        for(String url : urls) {
            MidiFileReader reader = new StandardMidiFileReader();
            toReturn.add(reader.getSequence(new URL(url)));
        }
        return toReturn;
    }

    /**
     * Takes in a midi Sequence and writes a form.Score. The
     * form.Score may then be parsed in a number of ways, including
     * forming RhythmTrees, etc. as Passages carry *more* useful
     * musical information than Sequences alone.
     * @param sequence
     * @return
     */
    public static Score parse(Sequence sequence) {
        // Spawn off a parser object
        MidiReader midiReader = new MidiReader();
        return midiReader.read(sequence);
    }

    /**
     * Takes in a form.Score and writes a midi Sequence from
     * it. The Sequence can then be played using MidiTools.play()
     * or saved to a midi file using MidiTools.save()
     * @param passage The form.Score to make into a Sequence.
     * @return The created MidiTools Sequence.
     */
    public static Sequence write(Score passage) {
        // Spawn off a parser object
        MidiWriter midiWriter = new MidiWriter();
        return midiWriter.run(passage);
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