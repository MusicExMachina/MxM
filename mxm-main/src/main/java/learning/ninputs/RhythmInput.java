package learning.ninputs;


import model.generation.RhythmTree;
import org.nd4j.linalg.dataset.DataSet;

import java.util.ArrayList;
import java.util.List;

public class RhythmInput {


    private List<RhythmTree> rhythms;

    /**
     * Create a blank rhythm input object
     */
    public RhythmInput(){
        rhythms = new ArrayList<RhythmTree>();
    }

    public void addRhythm(RhythmTree tree){
        rhythms.add(tree);
    }

    public DataSet buildDataSet(){
        DataSet d = new DataSet(input, labels);
    }

}
