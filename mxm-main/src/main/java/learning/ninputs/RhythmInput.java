package learning.ninputs;


import model.rhythmTree.RhythmTree;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RhythmInput {

    /**
     * A list of all rhythm trees in training set (IID for NN processing)
     */
    private List<RhythmTree> rhythms;

    public static final int DIVISION_TOKEN = -1;

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

        INDArray input = Nd4j.zeros(1, maxTreeLength, numPossibleStates);
        INDArray output = Nd4j.zeros(1, numPossibleStates, numPossibleStates);
        int count = 0;

        //Create DS from input and output, put into Matrix
        for (int i = 0; i < rhythmLists.size(); i++) {
            ArrayList<Integer> rhythm = rhythmLists.get(i);

            for (int j = 0; j < rhythm.size()-1; j++) {
//                int[] partialList = new int[j];
//                for (int k = 0; k<j; k++) {
//                    partialList[k] = rhythm.get(k);
//                }

                input.putScalar(new int[]{0, rhythm.get(j), count}, 1);
                output.putScalar(new int[]{0, rhythm.get(j+1), count}, 1);
                count++;
            }
            input.putScalar(new int[]{0, DIVISION_TOKEN, count}, 1);
            count++;
        }
        return new DataSet(input, output);
    }

}
