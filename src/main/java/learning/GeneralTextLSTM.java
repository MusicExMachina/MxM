package learning;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.GravesLSTM;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.util.ArrayList;
import java.util.List;

/**
 * This has little-to-nothing to do with MxM,
 * but everything to do with making sure things
 * are working. All this class does is generate
 * output strings based on a given input string.
 */
public class GeneralTextLSTM {

    public static int HIDDEN_LAYER_WIDTH            = 50;
    public static int HIDDEN_LAYER_NUM              = 50;
    public static List<Integer> POSSIBLE_PITCHES    = new ArrayList<Integer>();

    public void run() {
        // Initialize and configure the network
        initialize();
        // Train it on the provided data
        train();
        // Do something with the output
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

        for(int i = 0; i < HIDDEN_LAYER_NUM; i++) {
            GravesLSTM. Builder hiddenLayerBuilder = new GravesLSTM.Builder();
            hiddenLayerBuilder.nIn(HIDDEN_LAYER_WIDTH);
            hiddenLayerBuilder.nOut(HIDDEN_LAYER_WIDTH);
            hiddenLayerBuilder.activation("tanh");
            listBuilder.layer(i, hiddenLayerBuilder.build());
        }

        RnnOutputLayer.Builder outputLayerBuilder = new RnnOutputLayer.Builder(LossFunctions.LossFunction.MCXENT);
        outputLayerBuilder.activation("softmax");
        outputLayerBuilder.nIn(HIDDEN_LAYER_WIDTH);
        outputLayerBuilder.nOut(120);
        listBuilder.layer(HIDDEN_LAYER_NUM, outputLayerBuilder.build());

        // finish builder
        listBuilder.pretrain(false);
        listBuilder.backprop(true);

        /*
        // create network
        MultiLayerConfiguration conf = listBuilder.build();
        MultiLayerNetwork net = new MultiLayerNetwork(conf);
        net.init();
        net.setListeners(new ScoreIterationListener(1));
        */
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
