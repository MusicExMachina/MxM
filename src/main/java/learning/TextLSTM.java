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

import java.util.*;

/**
 * A single voice-generation LSTM. This is the first
 * foray into generating larger, more complex pieces
 * of music by starting with a single note-after-note
 * line played by a single instrument.
 */
public class TextLSTM {

    // Neural network dimensions
    private int hiddenLayerWidth;
    private int hiddenLayerHeight;

    // Neural network and its configuration
    private MultiLayerConfiguration configuration;
    private MultiLayerNetwork network;
    private DataSet trainingData;

    // Specific to our text-based example
    private char[] inputString;
    private List<Character> possibleChars;

    /**
     * A constructor for this TextLSTM with various initialization arguments
     * @param inputString The string we're using to train our neural net
     * @param hiddenLayerWidth The width of hidden layers to use
     * @param hiddenLayerNum The number of hidden layers to use
     */
    public TextLSTM(String inputString, int hiddenLayerWidth, int hiddenLayerNum) {

        // Take in all of the arguments
        this.inputString        = inputString.toCharArray();
        this.hiddenLayerWidth   = hiddenLayerWidth;
        this.hiddenLayerHeight  = hiddenLayerNum;

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
        // Initialize and configure the network
        initialize();
        configure();
        build();
        // Train it on the provided data
        train();
        // Do something with the output
    }

    /**
     * A method for initialization of our data. In here, we look
     * over our input, extract all possible values, and set them
     * aside for the configuration method.
     */
    private void initialize() {
        // Extract all of the possible states
        LinkedHashSet<Character> possibleCharsSet = new LinkedHashSet<Character>();
        for (char c : inputString) {
            possibleCharsSet.add(c);
        }
        possibleChars = new ArrayList<>();
        possibleChars.addAll(possibleCharsSet);

        System.out.println(possibleChars.toString());
        System.out.println(possibleChars.size());
        System.out.println(inputString.length);
        // Create input and output arrays
        INDArray input  = Nd4j.zeros(1, possibleChars.size(), inputString.length);
        INDArray labels = Nd4j.zeros(1, possibleChars.size(), inputString.length);

        // Loop through the input (and, for fun, assume the thing loops around
        // such that the next character after the end of the string is actually
        // the first character of the string).
        int samplePos = 0;
        for (char currentChar : inputString) {
            // Some fancypants math over here to do the above
            char nextChar = inputString[(samplePos + 1) % (inputString.length)];
            // The input neuron for current-char is 1 at "samplePos"
            input.putScalar(new int[] { 0, possibleChars.indexOf(currentChar), samplePos }, 1);
            // The output neuron for next-char is 1 at "samplePos"
            labels.putScalar(new int[] { 0, possibleChars.indexOf(nextChar), samplePos }, 1);
            samplePos++;
        }
        trainingData = new DataSet(input, labels);
    }

    /**
     * An encapsulation method for configuring the neural net.
     * This method is gigantic by necessity as DL4J has individual
     * initialization methods per variable. Note that this method's
     * only tangible outcome is that it creates "configuration."
     */
    private void configure() {
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
        for(int i = 0; i < hiddenLayerHeight; i++) {
            // Create and initialize a GravesLSTM.Builder
            // This establishes each layer's settings
            GravesLSTM.Builder hiddenLayerBuilder = new GravesLSTM.Builder();
            hiddenLayerBuilder.nIn(hiddenLayerWidth);
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
        outputLayerBuilder.nOut(120);
        listBuilder.layer(hiddenLayerHeight, outputLayerBuilder.build());

        // Finish the last two settings for the now-infamous
        // NeuralNetConfiguration.ListBuilder
        listBuilder.pretrain(false);
        listBuilder.backprop(true);

        // Construct our MultiLayerConfig off of the our favorite
        // NeuralNetConfiguration.ListBuilder.
        configuration = listBuilder.build();
    }

    /**
     * Builds the actual network, utilizing the configuration we
     * already have, and initializes it. The last step before any
     * sort of training is done.
     */
    private void build() {
        // Construct a neural network off of the configuration,
        // then initialize it and set how often it should print.
        network = new MultiLayerNetwork(configuration);
        network.init();
        network.setListeners(new ScoreIterationListener(1));
    }

    /**
     * A method to encapsulate the training process
     */
    private void train() {
        // some epochs
        for (int epoch = 0; epoch < 100; epoch++) {

            System.out.println("Epoch " + epoch);

            // train the data
            network.fit(trainingData);

            // clear current stance from the last example
            network.rnnClearPreviousState();

            // put the first caracter into the rrn as an initialisation
            INDArray testInit = Nd4j.zeros(possibleChars.size());
            testInit.putScalar(possibleChars.indexOf(inputString[0]), 1);

            // run one step -> IMPORTANT: rnnTimeStep() must be called, not
            // output()
            // the output shows what the net thinks what should come next
            INDArray output = network.rnnTimeStep(testInit);

            // now the net sould guess LEARNSTRING.length mor characters
            for (int j = 0; j < inputString.length; j++) {

                // first process the last output of the network to a concrete
                // neuron, the neuron with the highest output cas the highest
                // cance to get chosen
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
