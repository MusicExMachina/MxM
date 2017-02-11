import form.Passage;
import org.junit.Test;
import rhythmTree.RhythmTree;
import tools.MidiTools;

import javax.sound.midi.Sequence;

public class PassageVisualizerTest {

    @Test
    public void displayTest(){
        try {
            //tools.MidiTools.printSequencerInfo();
            //Sequence sequence = tools.MidiTools.download("http://www.midiworld.com/download/4522");
            //Sequence sequence = tools.MidiTools.download("http://www.mfiles.co.uk/downloads/edvard-grieg-peer-gynt1-morning-mood.mid");
            //Sequence sequence = MidiTools.download("http://www.classicalmidi.co.uk/music2/Pergynt4.mid");
            //Sequence sequence = tools.MidiTools.load("C:/users/celenp/desktop/test.mid");
            Sequence sequence = tools.MidiTools.load("C:\\Users\\celenp\\Desktop\\GitHub\\MxM\\mxm-midi\\src\\tests\\resources\\midi_schubertImpromptu.mid");
            Passage passage = MidiTools.parse(sequence);
            //tools.MidiTools.play(sequence);
            System.out.println(sequence.toString());
            //Thread.sleep(100000);
            PassageVisualizer rtv = new PassageVisualizer(passage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}