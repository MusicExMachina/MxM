package noutputs;

import java.util.HashMap;
import java.util.Random;

/**
 * Creates a Distribution over a type. Elements can be added but not subtracted,
 * and Distributions can be combined in various ways. Note that the most useful
 * "end product" of a Distribution is its ability to choose() a value based on
 * a random seed.
 */
public class Distribution<T> {

    /** The total number of occurences of all types in this Distribution. */
    int totalOccurences;

    /** The occurences of each type in this Distribution. */
    private HashMap<T,Integer> occurences;

    /** The thresholds for each individual item in the Distribution. */
    private HashMap<T,Double> thresholds;

    /**
     * Constructor for a new Distribution of type T.
     */
    public Distribution() {
        totalOccurences = 0;
        occurences = new HashMap<>();
        thresholds = new HashMap<>();
    }

    /**
     * Adds a single instance of a given type to the Distribution.
     * @param toAdd The type to be added (to).
     */
    public void add(T toAdd) {
        if (occurences.containsKey(toAdd)) {
            Integer integer = occurences.get(toAdd);
            occurences.put(toAdd, integer + 1);
        } else {
            occurences.put(toAdd, 1);
        }
        totalOccurences++;

        double runningTotal = 0.0d;
        for (T key : occurences.keySet()) {
            runningTotal += (double) occurences.get(key) / totalOccurences;
            thresholds.put(key, runningTotal);
        }
    }

    /**
     * Adds a single instance of a given type to the Distribution.
     * @param toAdd The type to be added (to).
     * @param number The number of times to add this type.
     */
    public void add(T toAdd, int number) {
        if (occurences.containsKey(toAdd)) {
            Integer integer = occurences.get(toAdd);
            occurences.put(toAdd, integer + number);
        } else {
            occurences.put(toAdd, number);
        }
        totalOccurences += number;

        double runningTotal = 0.0d;
        for (T key : occurences.keySet()) {
            runningTotal += (double) occurences.get(key) / totalOccurences;
            thresholds.put(key, runningTotal);
        }
    }

    /**
     * Combines two Distributions by addition.
     * @return The sum of the two Distributions.
     */
    public Distribution<T> plus(Distribution<T> other) {
        Distribution<T> newDistribution = new Distribution<>();
        for(T key : occurences.keySet()) {
            newDistribution.add(key,occurences.get(key));
        }
        for(T key : other.occurences.keySet()) {
            newDistribution.add(key,other.occurences.get(key));
        }
        return newDistribution;
    }

    /**
     * Combines two Distributions by multiplication.
     * @return The result of the two Distributions multiplied together.
     */
    public Distribution<T> times(Distribution<T> other) {
        Distribution<T> newDistribution = new Distribution<>();
        HashMap<T,Integer> newOccurences = new HashMap<>();

        for(T key : occurences.keySet()) {
            newOccurences.put(key,0);
        }
        for(T key : other.occurences.keySet()) {
            // If this exists in both distributions, then
            // start off with a "1". If it doesn't, then
            // start off with a "0".
            if(this.occurences.containsKey(key)) {
                newOccurences.put(key,1);
            }
            else {
                newOccurences.put(key,0);
            }
        }

        // Multiply everything togerher
        for(T key : occurences.keySet()) {
            newOccurences.put(key,newOccurences.get(key) * occurences.get(key));
        }
        for(T key : other.occurences.keySet()) {
            newOccurences.put(key,newOccurences.get(key) * other.occurences.get(key));
        }

        // Put it in the new Distribution
        for(T key : newOccurences.keySet()) {
            // If the chance of this type is not 0.
            if(!newOccurences.get(key).equals(0)) {
                newDistribution.add(key,newOccurences.get(key));
            }
        }

        return newDistribution;
    }


    /**
     * Chooses type T randomly from this Distribution.
     * @param seed A random seed with which to seed this Distribution.
     * @return The chosen value from this Distribution.
     */
    public T choose(int seed) {
        Random random = new Random(seed);
        Double value = random.nextDouble();

        for(T type : thresholds.keySet()) {
            if(thresholds.get(type) > value) {
                return type;
            }
        }

        return null;
    }

    /**
     * Does Chi-Squared analysis between this Distribution
     * and an ideal Distribution.
     * @param idealDistribution The ideal Distribution.
     * @return The result of this Chi Squared Analysis.
     */
    public float chiSquared(Distribution<T> idealDistribution) {
        float sum = 0;

        for(T type : occurences.keySet()) {
            int ideal = 0;
            int actual = occurences.get(type);
            if(idealDistribution.occurences.containsKey(type)) {
                ideal = idealDistribution.occurences.get(type);
            }
            int differenceSquared = (ideal-actual) * (ideal-actual);
            // Can be infinity!
            sum += (float)differenceSquared/(float)ideal;
        }

        return sum;
    }

    /**
     * Checks what percent of these Distributions are identical, where
     * 0 would mean they are essentially disjoint sets, and 1 would mean
     * they are identical percentage-wise.
     * @param idealDistribution The ideal Distribution.
     * @return The result of this Chi Squared Analysis.
     */
    public float percentDifferent(Distribution<T> idealDistribution) {
        float sum = 0;
        //TODO: finish this
        return sum;
    }
}
