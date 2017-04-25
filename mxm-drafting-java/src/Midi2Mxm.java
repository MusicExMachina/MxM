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
        Sequence sequence = MidiTools.download("http://www.kunstderfuge.com/-/midi.asp?file=bach/sankey/well-tempered-clavier-i_bwv-846_(c)sankey.mid");
        Passage passage = MidiTools.parse(sequence);
        Writer.write(passage, "mxm-drafting/harmony/test_data/bach_common_time/pf2");
    }


}
