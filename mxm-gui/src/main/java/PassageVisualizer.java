import form.Note;
import form.Part;
import form.Passage;
import io.Writer;
import tools.MidiTools;

import javax.sound.midi.Sequence;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by celenp on 2/2/2017.
 */
public class PassageVisualizer extends JFrame {

    private Passage passage;

    public PassageVisualizer(Passage passage){
        super("Rhythm Tree Visualizer");

        this.passage = passage;

        //you can set the content pane of the frame
        //to your custom class.

        setContentPane(new PassageVisualizer.PassageDrawer());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        setVisible(true);
    }

    class PassageDrawer extends JPanel{
        public void paint(Graphics g) {
            /*rt.paint((Graphics2D) g, 0, 0); */
            Random rand = new Random();
            for(int i = 0; i < 100; i++) {
                g.setColor(Color.white);
                g.drawLine(i*50,0,i*50,1000);
            }
            for(Part part : passage) {
                Color color = Color.getHSBColor(rand.nextFloat(),1,1);
                for(Note note: part) {
                    g.setColor(color);
                    g.drawRect(Math.round(note.getStart().toFloat()*50f),
                            Math.round(800f - note.getPitch().normalized()*800f),
                            5,
                            5);
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            //tools.MidiTools.printSequencerInfo();
            //Sequence sequence = tools.MidiTools.download("http://www.midiworld.com/download/4522");
            //Sequence sequence = tools.MidiTools.download("http://www.mfiles.co.uk/downloads/edvard-grieg-peer-gynt1-morning-mood.mid");
            //Sequence sequence = MidiTools.download("http://www.classicalmidi.co.uk/music2/Pergynt4.mid");
            Sequence sequence = MidiTools.download("http://www.bachcentral.com/WTCBkI/Prelude1.mid");
            //Sequence sequence = tools.MidiTools.load("C:/users/celenp/desktop/test.mid");
            //Sequence sequence = tools.MidiTools.load("C:\\Users\\celenp\\Desktop\\GitHub\\MxM\\mxm-midi\\src\\tests\\resources\\midi_schubertImpromptu.mid");
            Passage passage = MidiTools.parse(sequence);
            Writer.write(passage,"C:\\Users\\celenp\\Desktop\\GitHub\\MxM\\mxm-gui\\src\\tests\\resources\\output");
            //tools.MidiTools.play(sequence);
            PassageVisualizer rtv = new PassageVisualizer(passage);
            Thread.sleep(100000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
