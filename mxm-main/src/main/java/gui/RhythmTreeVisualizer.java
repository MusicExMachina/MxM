package gui;
import model.form.RhythmTree;

import javax.swing.*;
import java.awt.*;

public class RhythmTreeVisualizer extends JFrame {

    RhythmTree rt;

    public RhythmTreeVisualizer(RhythmTree rt){
        super("Rhythm Tree Visualizer");

        this.rt = rt;

        //you can set the content pane of the frame
        //to your custom class.

        setContentPane(new RhythmTreeDrawer());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(600, 600);

        setVisible(true);
    }

    class RhythmTreeDrawer extends JPanel{
        public void paint(Graphics g) {
            rt.paint((Graphics2D) g, 0, 0);
        }
    }




    public static void main(String[] args) {
        //int[] rtarr = new int[]{2,1,3,1,1,2,1,1};
        //int[] rtarr = new int[]{2,1,2,1,2,1,2,1,1};
        int[] rtarr = new int[]{2,2,2,1,1,1,2,1,2,1,1};
        RhythmTree rt = new RhythmTree(rtarr);



        RhythmTreeVisualizer rtv = new RhythmTreeVisualizer(rt);
    }

}