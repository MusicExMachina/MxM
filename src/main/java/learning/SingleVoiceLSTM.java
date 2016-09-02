package learning;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.weights.WeightInit;

import java.util.ArrayList;
import java.util.List;

/**
 * A single voice-generation LSTM. This is the first
 * foray into generating larger, more complex pieces
 * of music by starting with a single note-after-note
 * line played by a single instrument.
 */
public class SingleVoiceLSTM {

    public static int HIDDEN_LAYER_WIDTH = 50;
    public static int HIDDEN_LAYER_NUM = 50;
    public static List<Integer> POSSIBLE_PITCHES = new ArrayList<Integer>();

    public static void main(String[] args) {

    }

    public void run() {
        initialize();
        train();
    }

    /**
     * A method for initialization of the learning core
     */
    public void initialize() {
        // Establish all possible states
        for(int i = 0; i < 120; i++) {
            POSSIBLE_PITCHES.add(new Integer(i));
        }
        // Put those states into a List
        // Create and initiallize a NeuralNetConfiguration.Builder
        // ^ This establishes all the settings for the Neural network
        // Set up inputs and outputs for each layer.
        // Build and configure the output layer
        // Construct a MultiLayerConfig off of the list
        // Construct a Net off of the conf, then init the net
        // Choose how often to print iterations
    }

    /**
     * An encapsulation method for configuring the neural net
     */
    public void configure() {
        NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder();
        builder.iterations(10);
        builder.learningRate(0.001);
        builder.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT);
        builder.seed(123);
        builder.biasInit(0);
        builder.miniBatch(false);
        builder.updater(Updater.RMSPROP);
        builder.weightInit(WeightInit.XAVIER);
        NeuralNetConfiguration.ListBuilder listBuilder = builder.list();
    }

    /**
     * A method to encapsulate the training process
     */
    public void train() {
        // Set up the training data by:
        // > Setting up input and output for each moment
        // Train the data
    }

    /**
     * A method to save the learned weights
     */
    public void save() {

    }
}
