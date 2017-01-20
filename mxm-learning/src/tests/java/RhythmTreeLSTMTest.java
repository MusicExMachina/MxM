import networks.RhythmTreeLSTM;
import ninputs.RhythmInput;
import rhythmTree.RhythmTree;
import org.junit.Test;

/**
 * Created by jpatsenker on 11/13/16.
 */
public class RhythmTreeLSTMTest {

    @Test
    public void basicTest() {

        RhythmTree a = new RhythmTree(new int[]{3, 2, 1, 1, 1, 1});
        RhythmTree b = new RhythmTree(new int[]{3, 1, 2, 1, 1, 1});

        RhythmInput r = new RhythmInput();
        r.addRhythm(a);
        r.addRhythm(b);

        System.out.print(r);


        RhythmTreeLSTM network = new RhythmTreeLSTM(r.buildDataSet(),20,2);
        network.run();
    }
}