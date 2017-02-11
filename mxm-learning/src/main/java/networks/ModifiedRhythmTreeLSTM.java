package networks;

//import rhythmTree.RhythmTree;
//import org.deeplearning4j.nn.api.OptimizationAlgorithm;
//import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
//import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
//import org.deeplearning4j.nn.conf.Updater;
//import org.deeplearning4j.nn.conf.layers.GravesLSTM;
//import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.deeplearning4j.nn.weights.WeightInit;
//import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
//import org.nd4j.linalg.api.ndarray.INDArray;
//import org.nd4j.linalg.dataset.DataSet;
//import org.nd4j.linalg.factory.Nd4j;
//import org.nd4j.linalg.lossfunctions.LossFunctions;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.LinkedHashSet;
//import java.util.List;

/**
 * Created by jpatsenker on 12/9/16.
 */
public class ModifiedRhythmTreeLSTM {
//    // Neural network dimensions
//    private int hiddenLayerWidth;
//    private int hiddenLayerCount;
//
//    // Neural network and its configuration
//    private MultiLayerConfiguration configuration;
//    private MultiLayerNetwork network;
//    private DataSet trainingData;
//
//    // Specific to our text-based example
//    private int[][] inputData;
//    private List<Integer> possibleDivs;
//
//    /**
//     * A constructor for this networks.TextLSTM with various initialization arguments
//     * @param inputData The string we're using to train our neural net
//     * @param hiddenLayerWidth The width of hidden layers to use
//     * @param hiddenLayerNum The number of hidden layers to use
//     */
//    public ModifiedRhythmTreeLSTM(int[][] inputData, int hiddenLayerWidth, int hiddenLayerNum) {
//
//        // Take in all of the arguments
//        this.inputData = Arrays.copyOf(inputData, inputData.length);
//        this.hiddenLayerWidth   = hiddenLayerWidth;
//        this.hiddenLayerCount   = hiddenLayerNum;
//
//        // Make everything else null
//        this.network        = null;
//        this.trainingData   = null;
//        this.configuration  = null;
//        this.possibleDivs  = null;
//    }
//
//    /**
//     * Runs this neural network by running several private methods
//     * which initialize, configure, build, and execute the network.
//     */
//    public void run() {
//        // A way to measure elapsed time
//        long startTime;
//
//        // Initialize and configure the network
//        startTime = System.nanoTime();
//        initialize();
//        System.out.println("Time elapsed: " + (System.nanoTime() - startTime)/1000000d + "ms");
//
//        // Configure the network's data.
//        startTime = System.nanoTime();
//        configure();
//        System.out.println("Time elapsed: " + (System.nanoTime() - startTime) / 1000000d + "ms");
//
//        // Create the actual neural network
//        startTime = System.nanoTime();
//        build();
//        System.out.println("Time elapsed: " + (System.nanoTime() - startTime)/1000000d + "ms");
//
//        // Train it on the provided data
//        startTime = System.nanoTime();
//        train();
//        System.out.println("Time elapsed: " + (System.nanoTime() - startTime)/1000000d + "ms");
//
//        // Do something with the output
//    }
//
//    /**
//     * A method for initialization of our data. In here, we look
//     * over our input, extract all possible values, and set them
//     * aside for the configuration method.
//     */
//    private void initialize() {
//        System.out.println("Beginning initialization...");
//        // Extract all of the possible states
//        LinkedHashSet<Integer> possibleDivsSet = new LinkedHashSet<>();
//        for (int c=1; c<21; c++) {
//            possibleDivsSet.add(c);
//        }
//        possibleDivs = new ArrayList<Integer>();
//        possibleDivs.addAll(possibleDivsSet);
//
//        int maxInputLen = 0;
//        for(int[] i : inputData){
//            if (i.length > maxInputLen){
//                maxInputLen = i.length;
//            }
//        }
//
//        // Create input and output arrays
//        INDArray input  = Nd4j.zeros(inputData.length, possibleDivs.size(), maxInputLen + 1);
//        INDArray labels = Nd4j.zeros(inputData.length, possibleDivs.size(), maxInputLen+1);
//
//        sortIntoDataSet(input, labels);
//        trainingData = new DataSet(input, labels);
//        System.out.println("...completed initialization.");
//    }
//
//    private void sortIntoDataSet(INDArray input, INDArray labels) {
//        for (int c = 0; c < inputData.length; c++) {
//            int samplePos = 0;
//            for (int currentDiv : inputData[c]) {
//                int nextDiv;
//                if(samplePos < inputData[c].length-1) {
//                    nextDiv = inputData[c][samplePos + 1];
//                }else{
//                    continue;
//                }
//                //System.out.println("" + c + ", " + currentDiv + ", " + samplePos);
//                input.putScalar(new int[]{c, possibleDivs.indexOf(currentDiv), samplePos}, 1);
//                labels.putScalar(new int[]{c, possibleDivs.indexOf(nextDiv), samplePos}, 1);
//                samplePos++;
//            }
//
//        }
//    }
//
//    /**
//     * An encapsulation method for configuring the neural net.
//     * This method is gigantic by necessity as DL4J has individual
//     * initialization methods per variable. base.Note that this method's
//     * only tangible outcome is that it creates "configuration."
//     */
//    private void configure() {
//        System.out.println("Beginning configuration...");
//        // Create and initialize a NeuralNetConfiguration.Builder
//        // This establishes all the settings for the neural network
//        NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder();
//        builder.iterations(10);
//        builder.learningRate(0.01);
//        builder.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT);
//        builder.seed(123);
//        builder.biasInit(0);
//        builder.miniBatch(false);
//        builder.updater(Updater.RMSPROP);
//        builder.weightInit(WeightInit.XAVIER);
//
//        // A mouthful of a class that essentially turns the configuration
//        // builder we've initialized into a listBuilder, which is capable
//        // of doing stuff for multi-layered neural networks
//        NeuralNetConfiguration.ListBuilder listBuilder = builder.list();
//
//        // Set up inputs and outputs for each layer.
//        inputSetup(listBuilder);
//
//        // Create and initialize the RnnOutputLayer.Builder,
//        // which does exactly what you'd think it does
//        RnnOutputLayer.Builder outputLayerBuilder = new RnnOutputLayer.Builder(LossFunctions.LossFunction.MCXENT);
//        outputLayerBuilder.activation("softmax");
//        outputLayerBuilder.nIn(hiddenLayerWidth);
//        outputLayerBuilder.nOut(possibleDivs.size());
//        listBuilder.layer(hiddenLayerCount, outputLayerBuilder.build());
//
//        // Finish the last two settings for the now-infamous
//        // NeuralNetConfiguration.ListBuilder
//        listBuilder.pretrain(false);
//        listBuilder.backprop(true);
//
//        // Construct our MultiLayerConfig off of the our favorite
//        // NeuralNetConfiguration.ListBuilder.
//        configuration = listBuilder.build();
//        System.out.println("...completed configuration.");
//    }
//
//    private void inputSetup(NeuralNetConfiguration.ListBuilder listBuilder) {
//        for(int i = 0; i < hiddenLayerCount; i++) {
//            // Create and initialize a GravesLSTM.Builder
//            // This establishes each layer's settings
//            GravesLSTM.Builder hiddenLayerBuilder = new GravesLSTM.Builder();
//            if(i == 0) {
//                hiddenLayerBuilder.nIn(possibleDivs.size());
//            } else {
//                hiddenLayerBuilder.nIn(hiddenLayerWidth);
//            }
//            hiddenLayerBuilder.nOut(hiddenLayerWidth);
//            hiddenLayerBuilder.activation("tanh");
//
//            // Builds and adds this layer with index "i"
//            listBuilder.layer(i, hiddenLayerBuilder.build());
//        }
//    }
//
//    /**
//     * Builds the actual network, utilizing the configuration we
//     * already have, and initializes it. The last step before any
//     * sort of training is done.
//     */
//    private void build() {
//        System.out.println("Beginning building...");
//        // Construct a neural network off of the configuration,
//        // then initialize it and set how often it should print.
//        network = new MultiLayerNetwork(configuration);
//        network.init();
//        network.setListeners(new ScoreIterationListener(1));
//        System.out.println("...completed building.");
//    }
//
//
//    /**
//     * A method to encapsulate the training process
//     */
//    private void train() {
//        System.out.println("Beginning training...");
//        for (int epoch = 0; epoch < 100; epoch++) {
//
//            runEpoch(epoch);
//        }
//    }
//
//    private void runEpoch(int epoch) {
//        System.out.println("Epoch " + epoch);
//
//        // train the data
//        network.fit(trainingData);
//
//        network.rnnClearPreviousState();
//
//        INDArray testInit = Nd4j.zeros(possibleDivs.size());
//        testInit.putScalar(possibleDivs.indexOf(2), 0);
//        int nextIndex = 2;
//
//        INDArray output = network.rnnTimeStep(testInit);
//
//        ArrayList<Integer> treeList = new ArrayList<Integer>();
//        treeList.add(nextIndex);
//
//        boolean validTree = true;
//
//        int[] treeArr = new int[treeList.size()];
//        for(int i = 0; i<treeArr.length; i++){
//            treeArr[i] = treeList.get(i);
//        }
//
//        try{
//            RhythmTree r = new RhythmTree(treeArr);
//        }catch(Exception e){
//            validTree = false;
//        }
//
//        while (!validTree) {
//            GenerateNext generateNext = new GenerateNext(output, treeList).invoke();
//
//
//        }
//        System.out.print("\n");
//        System.out.println("...completed training.");
//        for(Integer i : treeList){
//            System.out.print(i);
//        }
//        System.out.println();
//    }
//
//
//    private class GenerateNext {
//        private INDArray output;
//        private ArrayList<Integer> treeList;
//        private boolean validTree;
//
//        public GenerateNext(INDArray output, ArrayList<Integer> treeList) {
//            this.output = output;
//            this.treeList = treeList;
//        }
//
//        private int findIndexOfHighestValue(double[] distribution) {
//            int maxValueIndex = 0;
//            double maxValue = 0;
//            for (int i = 0; i < distribution.length; i++) {
//                if(distribution[i] > maxValue) {
//                    maxValue = distribution[i];
//                    maxValueIndex = i;
//                }
//            }
//            return maxValueIndex;
//        }
//
//        public INDArray getOutput() {
//            return output;
//        }
//
//        public boolean isValidTree() {
//            return validTree;
//        }
//
//        public GenerateNext invoke() {
//            int nextIndex;
//            int[] treeArr;// first process the last output of the network to a concrete
//            // neuron, the neuron with the highest output cas the highest
//            // chance to get chosen
//            double[] outputProbDistribution = new double[possibleDivs.size()];
//            for (int k = 0; k < outputProbDistribution.length; k++) {
//                outputProbDistribution[k] = output.getDouble(k);
//            }
//            int sampledCharacterIdx = findIndexOfHighestValue(outputProbDistribution);
//
//            // print the chosen output
//            System.out.print(possibleDivs.get(sampledCharacterIdx));
//
//            // use the last output as input
//            INDArray nextInput = Nd4j.zeros(possibleDivs.size());
//            nextInput.putScalar(sampledCharacterIdx, 1);
//            output = network.rnnTimeStep(nextInput);
//            nextIndex = possibleDivs.get(sampledCharacterIdx);
//
//            treeList.add(nextIndex);
//
//            treeArr = new int[treeList.size()];
//            for (int i = 0; i < treeArr.length; i++) {
//                treeArr[i] = treeList.get(i);
//            }
//            try {
//                validTree = true;
//                RhythmTree r = new RhythmTree(treeArr);
//                System.out.println(r);
//            } catch (Exception e) {
//                validTree = false;
//            }
//            return this;
//        }
//    }
}
