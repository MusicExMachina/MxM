package learning.ninputs;


import model.rhythmTree.RhythmTree;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

import java.util.ArrayList;
import java.util.List;

public class RhythmInput {

    /**
     * A list of all rhythm trees in training set (IID for NN processing)
     */
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

    /**
     * Build dl4j DataSet object from this
     * @return DataSet object with all RhythmInput for training
     */
    public DataSet buildDataSet(){

        ArrayList<List<Integer>> rhythmLists = new ArrayList<>();
        int numPossibleStates = 0;
        int maxTreeLength = 0;
        for(RhythmTree rhythm : rhythms){
            List<Integer> l = rhythm.toSubdivisionList();
            rhythmLists.add(l);
            numPossibleStates+=l.size();
            if(l.size()>maxTreeLength){
                maxTreeLength = l.size();
            }
        }

        INDArray input = Nd4j.zeros(maxTreeLength, numPossibleStates);
        INDArray output = Nd4j.zeros(1, numPossibleStates);


        //Create DS from input and output, put into Matrix
        for (int i = 0; i < rhythmLists.size(); i++) {
            List<Integer> rhythm = rhythmLists.get(i);
            //Pad the lists with 0s in front (will be removed later)
            for (int j = rhythm.size(); j < maxTreeLength; j++) {
                rhythm.add(0, 0);
            }
            for (int j = 0; j < rhythm.size(); j++) {
                input.putScalar(i, rhythm.get(j));
            }
        }
        //TODO::List Length Calculation!!!
        return new DataSet(input, output);
    }

}
