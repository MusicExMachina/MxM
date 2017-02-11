import basic.Count;
import basic.TimeSignature;
import form.Note;
import form.Part;
import form.Passage;
import io.Writer;

import javax.sound.midi.Sequence;
import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.Iterator;
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

            float hScale = 50f;

            for(int i = 0; i < 100; i++) {
                TimeSignature timeSig = passage.getTimeSignatureAt(new Count(i+1));
                g.setColor(Color.darkGray);
                g.drawLine(Math.round(i*hScale),0,Math.round(i*hScale),800);
                for(int j = 1; j <= timeSig.getNumerator(); j++) {
                    g.setColor(Color.white);
                    int x = Math.round(hScale*((float)j/(float)timeSig.getNumerator() + (float)i));
                    g.drawLine(x,0,x,800);
                }
            }
            for(Part part : passage) {
                Color color = Color.getHSBColor(rand.nextFloat(),1,1);
                for(Note note: part) {
                    g.setColor(color);
                    g.fillRect( Math.round(note.getStart().toFloat()*hScale),
                                Math.round(600f - note.getPitch().normalized()*600f),
                                Math.round(note.getLength().toFloat()*hScale+2),
                                5);
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            //MidiTools.printSequencerInfo();
            //Sequence sequence = MidiTools.download("http://www.midiworld.com/download/4522");
            //Sequence sequence = MidiTools.download("http://www.mfiles.co.uk/downloads/edvard-grieg-peer-gynt1-morning-mood.mid");
            //Sequence sequence = MidiTools.download("http://www.classicalmidi.co.uk/music2/Pergynt4.mid");
            //Sequence sequence = MidiTools.download("http://www.bachcentral.com/WTCBkI/Prelude1.mid");
            //Sequence sequence = MidiTools.load("C:/users/celenp/desktop/test.mid");
            //Sequence sequence = MidiTools.load("C:\\Users\\celenp\\Desktop\\GitHub\\MxM\\mxm-midi\\src\\tests\\resources\\midi_schubertImpromptu.mid");
            //Sequence sequence = MidiTools.download("http://www.midiworld.com/midis/other/bach/lttlef.mid");
            Sequence sequence = MidiTools.download("http://www.midiworld.com/midis/other/bach/bwv159-4.mid");
            Passage passage = MidiTools.parse(sequence);
            Writer.write(passage,"C:\\Users\\celenp\\Desktop\\GitHub\\MxM\\mxm-gui\\src\\tests\\resources\\output");
            MidiTools.play(sequence);
            PassageVisualizer rtv = new PassageVisualizer(passage);
            Thread.sleep(100000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
