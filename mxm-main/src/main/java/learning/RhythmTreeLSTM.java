package learning;

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
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by jpatsenker on 11/12/16.
 */
public class RhythmTreeLSTM {

    // Neural network dimensions
    private int hiddenLayerWidth;
    private int hiddenLayerCount;

    // Neural network and its configuration
    private MultiLayerConfiguration configuration;
    private MultiLayerNetwork network;
    private DataSet trainingData;

    // Specific to our text-based example
    private DataSet rhythmDataSet;
    private List<Character> possibleChars;

    /**
     * A constructor for this TextLSTM with various initialization arguments
     * @param inputString The string we're using to train our neural net
     * @param hiddenLayerWidth The width of hidden layers to use
     * @param hiddenLayerNum The number of hidden layers to use
     */
    public RhythmTreeLSTM(DataSet rhythmDataSet, int hiddenLayerWidth, int hiddenLayerNum) {

        // Take in all of the arguments
        this.rhythmDataSet = rhythmDataSet;
        this.hiddenLayerWidth   = hiddenLayerWidth;
        this.hiddenLayerCount   = hiddenLayerNum;

        // Make everything else null
        this.network        = null;
        this.trainingData   = null;
        this.configuration  = null;
        this.possibleChars  = null;
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
        System.out.println("Time elapsed: " + (System.nanoTime() - startTime)/1000000d + "ms");

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

        System.out.println("...completed initialization.");
    }

    /**
     * An encapsulation method for configuring the neural net.
     * This method is gigantic by necessity as DL4J has individual
     * initialization methods per variable. Note that this method's
     * only tangible outcome is that it creates "configuration."
     */
    private void configure() {
        System.out.println("Beginning configuration...");
        // Create and initialize a NeuralNetConfiguration.Builder
        // This establishes all the settings for the neural network
        NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder();
        builder.iterations(10);
        builder.learningRate(0.001);
        builder.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT);
        builder.seed(123);
        builder.biasInit(0);
        builder.miniBatch(false);
        builder.updater(Updater.RMSPROP);
        builder.weightInit(WeightInit.XAVIER);

        // A mouthful of a class that essentially turns the configuration
        // builder we've initialized into a listBuilder, which is capable
        // of doing stuff for multi-layered neural networks
        NeuralNetConfiguration.ListBuilder listBuilder = builder.list();

        // Set up inputs and outputs for each layer.
        for(int i = 0; i < hiddenLayerCount; i++) {
            // Create and initialize a GravesLSTM.Builder
            // This establishes each layer's settings
            GravesLSTM.Builder hiddenLayerBuilder = new GravesLSTM.Builder();
            if(i == 0) {
                hiddenLayerBuilder.nIn(possibleChars.size());
            } else {
                hiddenLayerBuilder.nIn(hiddenLayerWidth);
            }
            hiddenLayerBuilder.nOut(hiddenLayerWidth);
            hiddenLayerBuilder.activation("tanh");

            // Builds and adds this layer with index "i"
            listBuilder.layer(i, hiddenLayerBuilder.build());
        }

        // Create and initialize the RnnOutputLayer.Builder,
        // which does exactly what you'd think it does
        RnnOutputLayer.Builder outputLayerBuilder = new RnnOutputLayer.Builder(LossFunctions.LossFunction.MCXENT);
        outputLayerBuilder.activation("softmax");
        outputLayerBuilder.nIn(hiddenLayerWidth);
        outputLayerBuilder.nOut(possibleChars.size());
        listBuilder.layer(hiddenLayerCount, outputLayerBuilder.build());

        // Finish the last two settings for the now-infamous
        // NeuralNetConfiguration.ListBuilder
        listBuilder.pretrain(false);
        listBuilder.backprop(true);

        // Construct our MultiLayerConfig off of the our favorite
        // NeuralNetConfiguration.ListBuilder.
        configuration = listBuilder.build();
        System.out.println("...completed configuration.");
    }

    /**
     * Builds the actual network, utilizing the configuration we
     * already have, and initializes it. The last step before any
     * sort of training is done.
     */
    private void build() {
        System.out.println("Beginning building...");
        // Construct a neural network off of the configuration,
        // then initialize it and set how often it should print.
        network = new MultiLayerNetwork(configuration);
        network.init();
        network.setListeners(new ScoreIterationListener(1));
        System.out.println("...completed building.");
    }

    /**
     * A method to encapsulate the training process
     */
    private void train() {
        System.out.println("Beginning training...");
        // some epochs
        for (int epoch = 0; epoch < 100; epoch++) {

            System.out.println("Epoch " + epoch);

            // train the data
            network.fit(trainingData);

            // clear current stance from the last example
            network.rnnClearPreviousState();

            // put the first character into the rrn as an initialisation
            INDArray testInit = Nd4j.zeros(possibleChars.size());
            testInit.putScalar(possibleChars.indexOf(inputString[0]), 1);

            // run one step -> IMPORTANT: rnnTimeStep() must be called, not
            // output()
            // the output shows what the net thinks what should come next
            INDArray output = network.rnnTimeStep(testInit);

            // now the net should guess LEARNSTRING.length more characters
            for (int j = 0; j < inputString.length; j++) {

                // first process the last output of the network to a concrete
                // neuron, the neuron with the highest output cas the highest
                // chance to get chosen
                double[] outputProbDistribution = new double[possibleChars.size()];
                for (int k = 0; k < outputProbDistribution.length; k++) {
                    outputProbDistribution[k] = output.getDouble(k);
                }
                int sampledCharacterIdx = findIndexOfHighestValue(outputProbDistribution);

                // print the chosen output
                System.out.print(possibleChars.get(sampledCharacterIdx));

                // use the last output as input
                INDArray nextInput = Nd4j.zeros(possibleChars.size());
                nextInput.putScalar(sampledCharacterIdx, 1);
                output = network.rnnTimeStep(nextInput);

            }
            System.out.print("\n");
            System.out.println("...completed training.");
        }
    }

    private static int findIndexOfHighestValue(double[] distribution) {
        int maxValueIndex = 0;
        double maxValue = 0;
        for (int i = 0; i < distribution.length; i++) {
            if(distribution[i] > maxValue) {
                maxValue = distribution[i];
                maxValueIndex = i;
            }
        }
        return maxValueIndex;
    }
}
