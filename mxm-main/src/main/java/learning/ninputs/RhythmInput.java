package learning.ninputs;


import model.generation.RhythmTree;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

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

        ArrayList<ArrayList<Integer>> rhythmLists = new ArrayList<>();
        int numPossibleStates = 0;
        int maxTreeLength = 0;
        for(RhythmTree rhythm : rhythms){
            ArrayList<Integer> l = rhythm.toList();
            rhythmLists.add(l);
            numPossibleStates+=l.size();
            if(l.size()>maxTreeLength){
                maxTreeLength = l.size();
            }
        }

        INDArray input = Nd4j.zeros(maxTreeLength, numPossibleStates);
        INDArray output = Nd4j.zeros(1, numPossibleStates);

        for (int i = 0; i < rhythmLists.size(); i++) {
            ArrayList<Integer> rhythm = rhythmLists.get(i);
            //Pad the lists with 0s in front (will be removed later)
            for (int j = rhythm.size(); j < maxTreeLength; j++) {
                rhythm.add(0, 0);
            }
            for (int j = 0; j < rhythm.size(); j++) {
                input.putScalar(i, jkasdjfl;s);
            }
        }
        return new DataSet(input, output);
    }

}
