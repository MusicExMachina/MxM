package basic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Interval is a simple class which utilizes the interning design pattern to create only two
 * hundred forty different values- all possible MIDI basic differences. Intervals are usually
 * used for analysis, though they may be used in Collections. Note that there should never be
 * more than these 240 Intervals, and that an iterator() has been provided for easy access.
 * Also note that Intervals may be negative, though IntervalClasses never are.
 */
public class Interval implements Comparator<Interval>, Comparable<Interval> {

    /** The smallest an interval can be. */
    private static int MIN_INTERVAL = -120;

    /** The largest an interval can be. */
    private static int MAX_INTERVAL = 120;

    /** All possible IntervalClasses */
    private static final ArrayList<Interval> ALL   = new ArrayList<>();

    // Initialize the "ALL" collection
    static {
        for(int intervalValue = MIN_INTERVAL; intervalValue < MAX_INTERVAL; intervalValue++) {
            ALL.add(new Interval(intervalValue));
        }
    }

    /**
     * Gets an iterator which enumerates all valid Intervals.
     * @return An iterator over all valid Intervals.
     */
    public static Iterator<Interval> iterator() {
        return ALL.iterator();
    }

    /** The immutable noteQualities IntervalClass of this Interval. */
    private IntervalClass intervalClass;

    /** The immutable size of this interval in half-steps. */
    private int size;

    /**
     * The normal, private Interval constructor.
     * @param size The size of the interval in half-steps.
     */
    private Interval(int size) {
        if(size >= MIN_INTERVAL && size <= MAX_INTERVAL) {
            this.size = (byte)size;
            this.intervalClass = IntervalClass.getInstance((Math.abs(size) + 1200) % 12);
        }
        else {
            throw new Error("INTERVAL:\nInvalid interval size");
        }
    }

    /**
     * Gets an instance of a given Interval size. This method
     * creates the interning design pattern per Interval.
     * @param value The size (in half steps) of this Interval
     * @return An Interval of this size.
     */
    public static Interval getInstance(int value) {
        if(value >= MIN_INTERVAL&& value < MAX_INTERVAL) {
            return ALL.get(value - MIN_INTERVAL);
        }
        else {
            throw new Error("INTERVAL:\tInterval out of range.");
        }
    }

    /**
     * A getter for the IntervalClass of this Interval.
     * @return The IntervalClass of this Interval.
     */
    public IntervalClass getIntervalClass() {
        return intervalClass;
    }

    /**
     * A getter for the size of this Interval, in half-steps.
     * @return The size of this Interval, in half-steps.
     */
    public int getSize() {
        return size;
    }

    /**
     * Creates a new Interval that is the sum of these two.
     * @param other The other Interval to add to this one.
     * @return  The new Interval sum.
     */
    public Interval plus(Interval other) {
        int newSize = size + other.size;
        if(newSize >= MIN_INTERVAL && newSize <= MAX_INTERVAL) {
            return ALL.get(newSize + MIN_INTERVAL);
        }
        else {
            throw new Error("INTERVAL:\nResultant Interval out of range!");
        }
    }

    /**
     * Creates a new Interval that is the difference of these two.
     * @param other The other Interval to subtract from this one.
     * @return The new Interval difference.
     */
    public Interval minus(Interval other) {
        int newSize = size - other.size;
        if(newSize >= MIN_INTERVAL && newSize <= MAX_INTERVAL) {
            return ALL.get(newSize + MIN_INTERVAL);
        }
        else {
            throw new Error("INTERVAL:\nResultant Interval out of range!");
        }
    }

    /**
     * Compares this Interval to another, purely based on size and direction.
     * @param other the other Interval to compare this one to.
     * @return The comparison between the two.
     */
    @Override
    public int compareTo(Interval other) {
        return new Integer(size).compareTo(new Integer(other.size));
    }

    /**
     * Compares two Intervals, purely based on size and direction.
     * @param i1 The first Interval.
     * @param i2 The second Interval.
     * @return The comparison between the two.
     */
    @Override
    public int compare(Interval i1, Interval i2) {
        return new Integer(i1.size).compareTo(i2.size);
    }

    /**
     * A noteQualities (generated) equals() method for Intervals.
     * @param o The Object to compare this to.
     * @return If these two Objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this Interval.
     */
    @Override
    public int hashCode() {
        return size;
    }
}
