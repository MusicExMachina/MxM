package gui.tests;

import model.form.RhythmTree;
import org.junit.Test;
import gui.RhythmTreeVisualizer;

/**
 * Created by jpatsenker on 9/26/16.
 */
public class RhythmTreeVisualizerTest {

    @Test
    public void displayTest(){
        int[] rtarr = new int[8];
        rtarr[0] = 2;
        rtarr[1] = 1;
        rtarr[2] = 3;
        rtarr[3] = 1;
        rtarr[4] = 1;
        rtarr[5] = 2;
        rtarr[6] = 1;
        rtarr[7] = 1;
        RhythmTree rt = new RhythmTree(rtarr);



        RhythmTreeVisualizer rtv = new RhythmTreeVisualizer(rt);
    }


}