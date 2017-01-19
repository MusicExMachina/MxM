package learning.tests;

import learning.ModifiedTextLSTM;
import learning.ninputs.RhythmInput;
import model.rhythmTree.RhythmTree;
import org.junit.Test;

/**
 * Created by jpatsenker on 11/27/16.
 */
public class ModifiedTextLSTMTest {

    @Test
    public void basicTest() {

        int[][] a = new int[][]{new int[]{3, 2, 1, 1, 1, 1}, new int[]{3, 1, 2, 1, 1, 1}};
        int[][] b = new int[][]{new int[]{3, 1, 1, 1}, new int[]{3, 1, 1, 1}, new int[]{3, 2, 1, 1, 1, 1}, new int[]{3, 1, 2, 1, 1, 1},new int[]{3, 2, 1, 1, 1, 1}, new int[]{3, 2, 2, 1, 1, 1, 1, 1}, new int[]{3, 2, 1, 1, 1, 1}, new int[]{3, 1, 1, 2, 1, 1}, new int[]{3, 2, 2, 2, 1, 1, 1, 1, 1, 1}, new int[]{3, 1, 1, 1}, new int[]{3, 1, 1, 1}};

        ModifiedTextLSTM network = new ModifiedTextLSTM(a,50,2);
        network.run();
    }
}
