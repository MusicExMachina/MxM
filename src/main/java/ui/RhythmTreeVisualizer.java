package ui;
import model.generation.RhythmTree;
import javax.swing.SwingUtilities;
import java.applet.Applet;
import java.awt.*;



public class RhythmTreeVisualizer extends Applet {

    RhythmTree rt;

//    public RhythmTreeVisualizer(RhythmTree rt){
//        this.rt = rt;
//    }


    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    createGUI();
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }

    public void createGUI(){
        int[] rtarr = new int[8];
        rtarr[0] = 2;
        rtarr[1] = 1;
        rtarr[2] = 3;
        rtarr[3] = 1;
        rtarr[4] = 1;
        rtarr[5] = 2;
        rtarr[6] = 1;
        rtarr[7] = 1;
        rt = new RhythmTree(rtarr);
    }

    public void paint(Graphics g) {
        this.rt.paint((Graphics2D) g, 0, 0);
    }

}
