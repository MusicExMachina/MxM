package learning.experiments;

import io.MidiTools;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import java.io.IOException;

/**
 * Created by jpatsenker on 12/1/16.
 */
public class SimpleRhythmGeneration {

    public static void main(String[] args) throws IOException, InvalidMidiDataException, MidiUnavailableException {
        Sequence sequence = MidiTools.download("http://www.classicalmidi.co.uk/music2/Pergynt4.mid");
        MidiTools.parse(sequence);
        MidiTools.play(sequence);
        System.out.println(sequence.toString());
    }
}
