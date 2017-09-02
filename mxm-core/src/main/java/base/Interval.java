package base;

import io.MxmLog;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Interval is a simple class which utilizes the interning design pattern to create only two hundred forty different
 * values- all possible MIDI basic differences. Intervals are usually used for analysis, though they may be used in
 * Collections. Note that there should never be more than these 240 Intervals, and that an iterator() has been provided
 * for easy access. Also note that Intervals may be negative, though IntervalClasses never are.
 *
 *
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements the <b>flyweight design pattern</b>- there is exactly one instance for
 * each value such that two ADTs (Abstract Data Types) with the same value are, in fact, the same instance. This
 * simplifies equality checks and can prevent memory waste. Unlike the <b>interning design pattern</b>, all possible
 * instances are created upfront during static initialization.
 */
public class Interval implements Comparator<Interval>, Comparable<Interval> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    /** The most negative an interval can be */
    public static final int MIN_SIZE = -(Pitch.MAX_VALUE - Pitch.MIN_VALUE);
    /** The most positive an interval can be */
    public static final int MAX_SIZE = Pitch.MAX_VALUE - Pitch.MIN_VALUE;
    /** The total number of pitches */
    public static final int TOTAL_NUM = (MAX_SIZE - MIN_SIZE) + 1;

    /** A static array of all possible intervals, stored to implement the flyweight pattern */
    private static final Interval[] ALL;
    // Initialize the "ALL" collection
    static {
        // Keep track of the start time to know how long initialization takes
        long startTime = System.nanoTime();

        // Initialize all interval classes
        ALL = new Interval[TOTAL_NUM];
        for(int size = MIN_SIZE; size <= MAX_SIZE; size++) {
            ALL[size - MIN_SIZE] = new Interval(size);
        }

        long endTime = System.nanoTime();
        MxmLog.logInitialization("Interval", Arrays.asList(ALL),endTime - startTime);
    }

    /** The largest downward interval */
    public static final Interval MIN = get(MIN_SIZE);
    /** The largest upward interval */
    public static final Interval MAX = get(MAX_SIZE);

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

    /**
     * Gets an iterator which enumerates all valid intervals.
     * @return An iterator over all valid intervals.
     */
    public static Iterator<Interval> allItr() {
        return Arrays.asList(ALL).iterator();
    }
    /**
     * Gets an instance of a given interval size. This method creates the interning design pattern per interval.
     * @param value The size (in half steps) of this interval
     * @return An interval of this size
     */
    public static Interval get(int value) {
        if(value >= MIN_SIZE && value <= MAX_SIZE)
            return ALL[value - MIN_SIZE];
        else
            throw new Error("INTERVAL:\tInterval out of range.");
    }

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** The immutable size of this interval in half-steps. */
    private int size;
    /** The number of octaves inside this interval positive or negative. */
    private int octaves;
    /** The immutable noteQualities interval class of this interval. */
    private IntervalClass intervalClass;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * The interval constructor, which is private to enforce the interning design pattern.
     * @param size The size of the interval in half-steps
     */
    private Interval(int size) {
        this.size = size;
        this.intervalClass = IntervalClass.get((Math.abs(size) + MAX_SIZE) % 12);
        this.octaves = size/12;
    }
    /**
     * A getter for the size of this interval, (positive or negative) in half-steps.
     * @return The size of this interval, (positive or negative) in half-steps.
     */
    public int getSize() {
        return size;
    }
    /**
     * A getter for the number of octaves (positive or negative) in this interval.
     * @return The number of octaves (positive or negative) in this interval.
     */
    public int getOctaves() {
        return octaves;
    }
    /**
     * A getter for the interval class of this interval.
     * @return The interval class of this interval
     */
    public @NotNull IntervalClass getIntervalClass() {
        return intervalClass;
    }
    /**
     * Creates a new interval that is the sum of these two.
     * @param other The other interval to add to this one
     * @return  The new interval sum
     */
    public @NotNull Interval plus(@NotNull Interval other) {
        int newSize = size + other.size;
        if(newSize >= MIN_SIZE && newSize <= MAX_SIZE)
            return get(newSize);
        else
            throw new Error("INTERVAL:\tResultant interval out of range!");
    }
    /**
     * Creates a new interval that is the difference of these two.
     * @param other The other interval to subtract from this one
     * @return The new interval difference
     */
    public @NotNull Interval minus(@NotNull Interval other) {
        int newSize = size - other.size;
        if(newSize >= MIN_SIZE && newSize <= MAX_SIZE)
            return get(MIN_SIZE);
        else
            throw new Error("INTERVAL:\nResultant interval out of range!");
    }
    /**
     * Returns a string representation of this class
     * @return A string representation of this pitch
     */
    public final @NotNull String toString() {
        if(octaves == 0)
            return intervalClass.toString();
        else if(octaves > 0)
            return intervalClass.toString() + "+" + octaves + "o";
        else
            return intervalClass.toString() + octaves + "o";
    }
    /**
     * Compares this interval to another, purely based on size and direction.
     * @param other the other interval to compare this one to
     * @return The comparison between the two
     */
    @Override
    public int compareTo(@NotNull Interval other) {
        return Integer.compare(size, other.size);
    }
    /**
     * Compares two Intervals, purely based on size and direction.
     * @param i1 The first interval
     * @param i2 The second interval
     * @return The comparison between the two
     */
    @Override
    public int compare(@NotNull Interval i1, @NotNull Interval i2) {
        return Integer.compare(i1.size, i2.size);
    }
    /**
     * Checks if this interval is equal to another object. Note that since the flyweight pattern is used, literal
     * (reference) equality is enough to ensure that these objects are actually equal.
     * @return if this interval class is equal to another
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }
    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this interval
     */
    @Override
    public int hashCode() {
        return size;
    }
}
