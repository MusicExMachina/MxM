import form.Score;
import org.junit.Test;

import javax.sound.midi.Sequence;

public class ScoreVisualizerTest {

    @Test
    public void displayTest(){
        try {
            //MidiTools.printSequencerInfo();
            //Sequence sequence = MidiTools.download("http://www.midiworld.com/download/4522");
            //Sequence sequence = MidiTools.download("http://www.mfiles.co.uk/downloads/edvard-grieg-peer-gynt1-morning-mood.mid");
            //Sequence sequence = MidiTools.download("http://www.classicalmidi.co.uk/music2/Pergynt4.mid");
            //Sequence sequence = MidiTools.load("C:/users/celenp/desktop/test.mid");
            Sequence sequence = MidiTools.load("C:\\Users\\celenp\\Desktop\\GitHub\\MxM\\mxm-midi\\src\\tests\\resources\\midi_schubert_impromptu.mid");
            Score passage = MidiTools.parse(sequence);
            //MidiTools.play(sequence);
            System.out.println(sequence.toString());
            //Thread.sleep(100000);
            PassageVisualizer rtv = new PassageVisualizer(passage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}