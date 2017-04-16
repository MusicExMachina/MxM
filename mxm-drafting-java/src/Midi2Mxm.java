import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

import form.Passage;
import io.Writer;

import java.io.IOException;

/**
 * Created by jpatsenker on 4/16/17.
 */

public class Midi2Mxm {

    public static void main(String[] args) throws IOException, InvalidMidiDataException {
        Sequence sequence = MidiTools.load("mxm-drafting/harmony/test_data/bach_common_time/prelude1.mid");
        Passage passage = MidiTools.parse(sequence);
        Writer.write(passage, "input");
    }


}
