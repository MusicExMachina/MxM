package base.analysis;

import base.relative.IntervalClass;
import base.sounds.Pitch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Interval is a simple class which utilizes the interning design pattern to create only two hundred forty different
 * values- all possible MIDI basic differences. Intervals are usually used for analysis, though they may be used in
 * Collections. Note that there should never be more than these 240 Intervals, and that an iterator() has been provided
 * for easy access. Also note that Intervals may be negative, though IntervalClasses never are.
 */
public class Interval implements Comparator<Interval>, Comparable<Interval> {

    //////////////////////////////
    // Private static variables //
    //////////////////////////////

    /** The smallest an interval can be. */
    private static int MIN_INTERVAL_VALUE = -(Pitch.MAX_OCTAVE - Pitch.MIN_OCTAVE);

    /** The largest an interval can be. */
    private static int MAX_INTERVAL_VALUE = Pitch.MAX_OCTAVE - Pitch.MIN_OCTAVE;

    /** The smallest an interval can be. */
    private static int MIN_OCTAVE_VALUE = Pitch.MAX_OCTAVE - Pitch.MIN_OCTAVE;

    /** The largest an interval can be. */
    private static int MAX_OCTAVE_VALUE = Pitch.MAX_OCTAVE - Pitch.MIN_OCTAVE;

    /** All possible IntervalClasses */
    private static final ArrayList<Interval> ALL   = new ArrayList<>();

    // Initialize the "ALL" collection
    static {
        for(int intervalValue = MIN_INTERVAL_VALUE; intervalValue < MAX_INTERVAL_VALUE; intervalValue++)
            ALL.add(new Interval(intervalValue));
    }

    //////////////////////////////
    //      Static methods      //
    //////////////////////////////

    /**
     * Gets an iterator which enumerates all valid intervals.
     * @return An iterator over all valid intervals.
     */
    public static Iterator<Interval> iterator() {
        return ALL.iterator();
    }

    /**
     * Gets an instance of a given interval size. This method creates the interning design pattern per interval.
     * @param value The size (in half steps) of this interval
     * @return An interval of this size
     */
    public static Interval getInstance(int value) {
        if(value >= MIN_INTERVAL_VALUE && value < MAX_INTERVAL_VALUE)
            return ALL.get(value - MIN_INTERVAL_VALUE);
        else
            throw new Error("INTERVAL:\tInterval out of range.");
    }

    //////////////////////////////
    // Private member variables //
    //////////////////////////////

    /** The immutable noteQualities interval class of this interval. */
    private IntervalClass intervalClass;

    /** The octave of this pitch, between OCTAVE_MIN and OCTAVE_MAX */
    private int octaves;

    /** The immutable size of this interval in half-steps. */
    private int size;


    //////////////////////////////
    //   Private constructor    //
    //////////////////////////////

    /**
     * The interval constructor, which is private to enforce the interning design pattern.
     * @param size The size of the interval in half-steps
     */
    private Interval(int size) {
        if(size >= MIN_INTERVAL_VALUE && size <= MAX_INTERVAL_VALUE) {
            this.size = size;
            this.intervalClass = IntervalClass.getInstance((Math.abs(size) + MAX_INTERVAL_VALUE) % 12);
            this.octaves = size/12;
        }
        else {
            throw new Error("INTERVAL:\nInvalid interval size");
        }
    }

    //////////////////////////////
    //   Public member methods  //
    //////////////////////////////
    
    /**
     * A getter for the interval class of this interval.
     * @return The interval class of this interval
     */
    public IntervalClass getIntervalClass() {
        return intervalClass;
    }

    /**
     * A getter for the number of octaves (positive or negative) in this interval.
     * @return The number of octaves (positive or negative) in this interval.
     */
    public int getOctaves() {
        return octaves;
    }

    /**
     * A getter for the size of this interval, (positive or negative) in half-steps.
     * @return The size of this interval, (positive or negative) in half-steps.
     */
    public int getSize() {
        return size;
    }

    /**
     * Creates a new interval that is the sum of these two.
     * @param other The other interval to add to this one
     * @return  The new interval sum
     */
    public Interval plus(Interval other) {
        int newSize = size + other.size;
        if(newSize >= MIN_INTERVAL_VALUE && newSize <= MAX_INTERVAL_VALUE) {
            return ALL.get(newSize + MIN_INTERVAL_VALUE);
        }
        else {
            throw new Error("INTERVAL:\tResultant interval out of range!");
        }
    }

    /**
     * Creates a new interval that is the difference of these two.
     * @param other The other interval to subtract from this one
     * @return The new interval difference
     */
    public Interval minus(Interval other) {
        int newSize = size - other.size;
        if(newSize >= MIN_INTERVAL_VALUE && newSize <= MAX_INTERVAL_VALUE) {
            return ALL.get(newSize + MIN_INTERVAL_VALUE);
        }
        else {
            throw new Error("INTERVAL:\nResultant interval out of range!");
        }
    }

    /**
     * Compares this interval to another, purely based on size and direction.
     * @param other the other interval to compare this one to
     * @return The comparison between the two
     */
    @Override
    public int compareTo(Interval other) {
        return new Integer(size).compareTo(other.size);
    }

    /**
     * Compares two Intervals, purely based on size and direction.
     * @param i1 The first interval
     * @param i2 The second interval
     * @return The comparison between the two
     */
    @Override
    public int compare(Interval i1, Interval i2) {
        return new Integer(i1.size).compareTo(i2.size);
    }

    /**
     * A (generated) equals() method for intervals.
     * @param o The object to compare this to
     * @return If these two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this interval.
     */
    @Override
    public int hashCode() {
        return size;
    }
}
