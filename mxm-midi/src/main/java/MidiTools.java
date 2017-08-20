import com.sun.media.sound.StandardMidiFileReader;

import javax.sound.midi.*;
import javax.sound.midi.spi.MidiFileReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
/*
public abstract class MidiTools
{
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


    public static Sequence load (String fileName) throws IOException, InvalidMidiDataException {
        MidiFileReader reader = new StandardMidiFileReader();
        return reader.getSequence(new FileInputStream(fileName));
    }

    public static List<Sequence> loadAll (List<String> fileNames) throws IOException, InvalidMidiDataException {
        List<Sequence> toReturn = new ArrayList<>();
        // TODO: Parallelize this
        for(String fileName : fileNames) {
            MidiFileReader reader = new StandardMidiFileReader();
            toReturn.add(reader.getSequence(new FileInputStream(fileName)));
        }
        return toReturn;
    }

    public static Sequence download (String url) throws IOException, InvalidMidiDataException {
        MidiFileReader reader = new StandardMidiFileReader();
        return reader.getSequence(new URL(url));
    }

    public static List<Sequence> downloadAll (List<String> urls) throws IOException, InvalidMidiDataException {
        List<Sequence> toReturn = new ArrayList<>();
        // TODO: Parallelize this
        for(String url : urls) {
            MidiFileReader reader = new StandardMidiFileReader();
            toReturn.add(reader.getSequence(new URL(url)));
        }
        return toReturn;
    }

    public static TraditionalScore parse(Sequence sequence) {
        // Spawn off a parser object
        MidiReader midiReader = new MidiReader();
        return midiReader.read(sequence);
    }

    public static Sequence write(TraditionalScore passage) {
        // Spawn off a parser object
        MidiWriter midiWriter = new MidiWriter();
        return midiWriter.run(passage);
    }

    public static void play(Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException {
        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.open();
        sequencer.setSequence(sequence);
        sequencer.start();
    }
}
*/