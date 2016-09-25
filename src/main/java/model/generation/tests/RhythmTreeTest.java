package model.generation.tests;

import model.generative.RhythmTree;
import org.junit.Test;

/**
 * Created by jpatsenker on 9/18/16.
 */
public class RhythmTreeTest {

    @Test
    public void createTreeFromListTest(){
        int[] rtarr = new int[6];
        rtarr[0] = 2;
        rtarr[1] = 1;
        rtarr[2] = 3;
        rtarr[3] = 1;
        rtarr[4] = 1;
        rtarr[5] = 1;
        RhythmTree rt = new RhythmTree(rtarr);
    }


}