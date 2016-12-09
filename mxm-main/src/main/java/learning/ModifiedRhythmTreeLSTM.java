package learning;

import model.rhythmTree.RhythmTree;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.GravesLSTM;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by jpatsenker on 12/9/16.
 */
public class ModifiedRhythmTreeLSTM {

    // Neural network dimensions
    private int hiddenLayerWidth;
    private int hiddenLayerCount;

    // Neural network and its configuration
    private MultiLayerConfiguration configuration;
    private MultiLayerNetwork network;
    private DataSet trainingData;

    // Specific to our text-based example
    private int[][] inputData;
    private List<Integer> possibleDivs;

    /**
     * A constructor for this TextLSTM with various initialization arguments
     * @param inputData The string we're using to train our neural net
     * @param hiddenLayerWidth The width of hidden layers to use
     * @param hiddenLayerNum The number of hidden layers to use
     */
    public ModifiedRhythmTreeLSTM(int[][] inputData, int hiddenLayerWidth, int hiddenLayerNum) {

        // Take in all of the arguments
        this.inputData = Arrays.copyOf(inputData, inputData.length);
        this.hiddenLayerWidth   = hiddenLayerWidth;
        this.hiddenLayerCount   = hiddenLayerNum;

        // Make everything else null
        this.network        = null;
        this.trainingData   = null;
        this.configuration  = null;
        this.possibleDivs  = null;
    }

    /**
     * Runs this neural network by running several private methods
     * which initialize, configure, build, and execute the network.
     */
    public void run() {
        // A way to measure elapsed time
        long startTime;

        // Initialize and configure the network
        startTime = System.nanoTime();
        initialize();
        System.out.println("Time elapsed: " + (System.nanoTime() - startTime)/1000000d + "ms");

        // Configure the network's data.
        startTime = System.nanoTime();
        configure();
        System.out.println("Time elapsed: " + (System.nanoTime() - startTime) / 1000000d + "ms");

        // Create the actual neural network
        startTime = System.nanoTime();
        build();
        System.out.println("Time elapsed: " + (System.nanoTime() - startTime)/1000000d + "ms");

        // Train it on the provided data
        startTime = System.nanoTime();
        train();
        System.out.println("Time elapsed: " + (System.nanoTime() - startTime)/1000000d + "ms");

        // Do something with the output
    }

    /**
     * A method for initialization of our data. In here, we look
     * over our input, extract all possible values, and set them
     * aside for the configuration method.
     */
    private void initialize() {
        System.out.println("Beginning initialization...");
        // Extract all of the possible states
        LinkedHashSet<Integer> possibleDivsSet = new LinkedHashSet<>();
        for (int c=1; c<21; c++) {
            possibleDivsSet.add(c);
        }
        possibleDivs = new ArrayList<Integer>();
        possibleDivs.addAll(possibleDivsSet);

        int maxInputLen = 0;
        for(int[] i : inputData){
            if (i.length > maxInputLen){
                maxInputLen = i.length;
            }
        }

        // Create input and output arrays
        INDArray input  = Nd4j.zeros(inputData.length, possibleDivs.size(), maxInputLen + 1);
        INDArray labels = Nd4j.zeros(inputData.length, possibleDivs.size(), maxInputLen+1);

        for (int c = 0; c < inputData.length; c++) {
            int samplePos = 0;
            for (int currentDiv : inputData[c]) {
                int nextDiv;
                if(samplePos < inputData[c].length-1) {
                    nextDiv = inputData[c][samplePos + 1];
                }else{
                    continue;
                }
                //System.out.println("" + c + ", " + currentDiv + ", " + samplePos);
                input.putScalar(new int[]{c, possibleDivs.indexOf(currentDiv), samplePos}, 1);
                labels.putScalar(new int[]{c, possibleDivs.indexOf(nextDiv), samplePos}, 1);
                samplePos++;
            }

        }
        trainingData = new DataSet(input, labels);
        System.out.println("...completed initialization.");
    }




}
