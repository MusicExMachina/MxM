package noutputs;

import java.util.HashMap;
import java.util.Random;

/**
 * Creates a Distribution over a type. Elements can be added but not subtracted,
 * and Distributions can be combined in various ways. NoteEvent that the most useful
 * "end product" of a Distribution is its ability to choose() a value based on
 * a random seed.
 */
public class Distribution<T> {

    /** The total number of occurrences of all types in this distribution. */
    private int totalOccurrences;

    /** The occurrences of each type in this distribution. */
    private HashMap<T,Integer> occurrences;

    /** The thresholds for each individual item in the distribution. */
    private HashMap<T,Double> thresholds;

    /**
     * Constructor for a new Distribution of type T.
     */
    public Distribution() {
        totalOccurrences = 0;
        occurrences = new HashMap<>();
        thresholds = new HashMap<>();
    }

    /**
     * Adds a single instance of a given type to the Distribution.
     * @param toAdd The type to be added (to).
     */
    public void add(T toAdd) {
        if (occurrences.containsKey(toAdd)) {
            Integer integer = occurrences.get(toAdd);
            occurrences.put(toAdd, integer + 1);
        } else {
            occurrences.put(toAdd, 1);
        }
        totalOccurrences++;

        double runningTotal = 0.0d;
        for (T key : occurrences.keySet()) {
            runningTotal += (double) occurrences.get(key) / totalOccurrences;
            thresholds.put(key, runningTotal);
        }
    }

    /**
     * Adds a single instance of a given type to the Distribution.
     * @param toAdd The type to be added (to).
     * @param number The number of times to add this type.
     */
    public void add(T toAdd, int number) {
        if (occurrences.containsKey(toAdd)) {
            Integer integer = occurrences.get(toAdd);
            occurrences.put(toAdd, integer + number);
        } else {
            occurrences.put(toAdd, number);
        }
        totalOccurrences += number;

        double runningTotal = 0.0d;
        for (T key : occurrences.keySet()) {
            runningTotal += (double) occurrences.get(key) / totalOccurrences;
            thresholds.put(key, runningTotal);
        }
    }

    /**
     * Combines two Distributions by addition.
     * @return The sum of the two Distributions.
     */
    public Distribution<T> plus(Distribution<T> other) {
        Distribution<T> newDistribution = new Distribution<>();
        for(T key : occurrences.keySet()) {
            newDistribution.add(key, occurrences.get(key));
        }
        for(T key : other.occurrences.keySet()) {
            newDistribution.add(key,other.occurrences.get(key));
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

        for(T key : occurrences.keySet()) {
            newOccurences.put(key,0);
        }
        for(T key : other.occurrences.keySet()) {
            // If this exists in both distributions, then
            // start off with a "1". If it doesn't, then
            // start off with a "0".
            if(this.occurrences.containsKey(key)) {
                newOccurences.put(key,1);
            }
            else {
                newOccurences.put(key,0);
            }
        }

        // Multiply everything togerher
        for(T key : occurrences.keySet()) {
            newOccurences.put(key,newOccurences.get(key) * occurrences.get(key));
        }
        for(T key : other.occurrences.keySet()) {
            newOccurences.put(key,newOccurences.get(key) * other.occurrences.get(key));
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
     * Chooses the highest-ranked T in this Distribution.
     * @return The chosen value from this Distribution.
     */
    public T pickHighest() {
        T highestT = null;
        Integer highestValue = Integer.MIN_VALUE;
        for(T occurence : occurrences.keySet()) {
            if(occurrences.get(occurence) > highestValue) {
                highestT = occurence;
                highestValue = occurrences.get(occurence);
            }
        }
        return highestT;
    }

    /**
     * Chooses type T randomly from this Distribution.
     * @param seed A random seed with which to seed this Distribution.
     * @return The chosen value from this Distribution.
     */
    public T pickRandom(int seed) {
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

        for(T type : occurrences.keySet()) {
            int ideal = 0;
            int actual = occurrences.get(type);
            if(idealDistribution.occurrences.containsKey(type)) {
                ideal = idealDistribution.occurrences.get(type);
            }
            int differenceSquared = (ideal-actual) * (ideal-actual);
            // Can be infinity!
            sum += (float)differenceSquared/(float)ideal;
        }

        return sum;
    }
}
