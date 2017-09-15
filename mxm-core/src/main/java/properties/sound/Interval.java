package properties.sound;

import properties.AbstractIntegerProp;
import io.Log;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p> <b>Class Overview:</b>
 * There are two hundred and forty-one intervals which represent the twelve possible differences between pitches.
 * Intervals are usually used for analysis, though they may of course be stored in Collections and manipulated like most
 * of MxM's basic classes. Note that Intervals may be negative, while {@link IntervalClass} may never be.</p>
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements the <b>flyweight design pattern</b>- there is exactly one instance for
 * each value such that two ADTs (Abstract Data Types) with the same value are, in fact, the same instance. This
 * simplifies equality checks and can prevent memory waste. Unlike the <b>interning design pattern</b>, all possible
 * instances are created upfront during static initialization.
 *
 * @author Patrick Celentano
 */
public final class Interval extends AbstractIntegerProp implements Comparator<Interval>, Comparable<Interval> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    /** The largest (absolute value) size an interval can be */
    static final int MAX_SIZE = Pitch.MAX_VALUE - Pitch.MIN_VALUE;
    /** The most negative an interval can be */
    static final int MIN_VALUE = -MAX_SIZE;
    /** The most positive an interval can be */
    static final int MAX_VALUE = MAX_SIZE;
    /** The total number of pitches */
    private static final int TOTAL_NUM = (MAX_VALUE - MIN_VALUE) + 1;

    /** A static array of all possible intervals, stored to implement the flyweight pattern */
    private static final Interval[] ALL;
    // Initialize the "ALL" collection
    static {
        // Keep track of the start time to know how long initialization takes
        long startTime = System.nanoTime();

        // Initialize all interval classes
        ALL = new Interval[TOTAL_NUM];
        for(int size = MIN_VALUE; size <= MAX_VALUE; size++) {
            ALL[size - MIN_VALUE] = new Interval(size);
        }

        long endTime = System.nanoTime();
        Log.logStaticInit("Interval", Arrays.asList(ALL),endTime - startTime);
    }

    /** The largest downward interval */
    public static final Interval MIN = get(MIN_VALUE);
    /** The largest upward interval */
    public static final Interval MAX = get(MAX_VALUE);

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

    /**
     * Gets an instance of a given interval size. This method creates the interning design pattern per interval.
     * @param value The size (in half steps) of this interval
     * @return An interval of this size
     */
    public static Interval get(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE)  throw new Error("INTERVAL:\tInterval out of range.");

        return ALL[value - MIN_VALUE];
    }
    /**
     * Returns an immutable collection of all valid intervals, useful for iteration or streams
     * @return an immutable collection of all valid intervals
     */
    public static @NotNull Collection<Interval> all() {
        return Collections.unmodifiableList(Arrays.asList(ALL));
    }
    /**
     * Returns a random instance of this class
     * @return a random valid interval
     */
    public static @NotNull Interval random() {
        return get(ThreadLocalRandom.current().nextInt(MIN_VALUE, MAX_VALUE + 1));
    }

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

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
       super(size);
        this.octaves = size/12;
        this.intervalClass = IntervalClass.get((Math.abs(size) + MAX_VALUE) % 12);
    }
    /**
     * A getter for the size of this interval, (positive or negative) in half-steps.
     * @return The size of this interval, (positive or negative) in half-steps.
     */
    public final int getSize() {
        return getValue();
    }
    /**
     * A getter for the number of octaves (positive or negative) in this interval.
     * @return The number of octaves (positive or negative) in this interval.
     */
    public final int getOctaves() {
        return octaves;
    }
    /**
     * A getter for the interval class of this interval.
     * @return The interval class of this interval
     */
    public final @NotNull IntervalClass getIntervalClass() {
        return intervalClass;
    }
    /**
     * Creates a new interval that is the sum of these two.
     * @param other The other interval to add to this one
     * @return  The new interval sum
     */
    public final @NotNull Interval plus(@NotNull Interval other) {
        int newSize = getSize() + other.getSize();
        if(newSize >= MIN_VALUE && newSize <= MAX_VALUE)
            return get(newSize);
        else
            throw new Error("INTERVAL:\tResultant interval out of range!");
    }
    /**
     * Creates a new interval that is the difference of these two.
     * @param other The other interval to subtract from this one
     * @return The new interval difference
     */
    public final @NotNull Interval minus(@NotNull Interval other) {
        int newSize = getSize() - other.getSize();
        if(newSize >= MIN_VALUE && newSize <= MAX_VALUE)
            return get(MIN_VALUE);
        else
            throw new Error("INTERVAL:\nResultant interval out of range!");
    }
    /**
     * Returns a string representation of this interval.
     * @return A string representation of this interval
     */
    @Override
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
    public final int compareTo(@NotNull Interval other) {
        return Integer.compare(getSize(), other.getSize());
    }
    /**
     * Compares two Intervals, purely based on size and direction.
     * @param i1 The first interval
     * @param i2 The second interval
     * @return The comparison between the two
     */
    @Override
    public final int compare(@NotNull Interval i1, @NotNull Interval i2) {
        return Integer.compare(i1.getSize(), i2.getSize());
    }
    /**
     * Checks if this interval is equal to another object. Note that since the flyweight pattern is used, literal
     * (reference) equality is enough to ensure that these objects are actually equal.
     * @param object the object to compare to
     * @return if this interval is equal to this object
     * */
    @Override
    public final boolean equals(Object object) {
        return this == object;
    }
}
