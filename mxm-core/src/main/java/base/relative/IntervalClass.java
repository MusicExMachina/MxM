package base.relative;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * There are only twelve interval classes which represent the twelve possible (always positive) vector differences
 * between sound classes. For instance, MINOR_SECOND represents both a movement of a minor second upward, a major
 * seventh downward, a minor ninth upward, so on and so forth such that octaves are always compressed.
 */
public class IntervalClass implements Comparator<IntervalClass>, Comparable<IntervalClass> {

    //////////////////////////////
    // Private static variables //
    //////////////////////////////

    /** The lowest interval class, better known as "an upward unison" */
    private static final int MIN_INTERVAL_CLASS_VALUE = 0;

    /** The highest interval class, better known as "an upward major seventh" */
    private static final int MAX_INTERVAL_CLASS_VALUE = 11;

    /** All possible interval classes */
    private static final ArrayList<IntervalClass> ALL = new ArrayList<>();

    // Initialize the "ALL" collection
    static {
        for(int intervalValue = MIN_INTERVAL_CLASS_VALUE; intervalValue < MAX_INTERVAL_CLASS_VALUE; intervalValue++)
            ALL.add(getInstance(intervalValue));
    }

    //////////////////////////////
    // Public static variables  //
    //////////////////////////////

    /* These twelve IntervalClasses are all that can exist. */
    public static final IntervalClass UNISON            = getInstance(0);
    public static final IntervalClass MINOR_SECOND      = getInstance(1);
    public static final IntervalClass MAJOR_SECOND      = getInstance(2);
    public static final IntervalClass MINOR_THIRD       = getInstance(3);
    public static final IntervalClass MAJOR_THIRD       = getInstance(4);
    public static final IntervalClass PERFECT_FOURTH    = getInstance(5);
    public static final IntervalClass TRITONE           = getInstance(6);
    public static final IntervalClass PERFECT_FIFTH     = getInstance(7);
    public static final IntervalClass MINOR_SIXTH       = getInstance(8);
    public static final IntervalClass MAJOR_SIXTH       = getInstance(9);
    public static final IntervalClass MINOR_SEVENTH     = getInstance(10);
    public static final IntervalClass MAJOR_SEVENTH     = getInstance(11);

    //////////////////////////////
    //      Static methods      //
    //////////////////////////////
    
    /**
     * Gets an iterator which enumerates all valid interval classes.
     * @return An iterator over all valid interval classes
     */
    public static Iterator<IntervalClass> iterator() {
        return ALL.iterator();
    }

    /**
     * Gets an instance of a given interval class. This method creates the interning design pattern per interval class.
     * @param size The size (in half steps) of this interval class
     * @return An intervalClass of this value
     */
    public static IntervalClass getInstance(int size) {
        if(size >= MIN_INTERVAL_CLASS_VALUE && size <= MAX_INTERVAL_CLASS_VALUE) {
            return ALL.get(size);
        }
        else {
            throw new Error("INTERVAL CLASS:\tInterval class out of range.");
        }
    }

    //////////////////////////////
    // Private member variables //
    //////////////////////////////

    /** The size of the interval class. */
    private int size;

    //////////////////////////////
    //   Private constructor    //
    //////////////////////////////

    /**
     * A constructor for interval class, which is private.
     * @param size The size (0-11) of this interval class
     */
    private IntervalClass(int size) {
        this.size = size;
        ALL.add(this);
    }

    //////////////////////////////
    //   Public member methods  //
    //////////////////////////////

    /**
     * A getter for the size of this interval class, in half-steps.
     * @return The size of this interval class, in half-steps
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the interval class that is the summation of this and another. For instance, MAJOR_THIRD.plus(MINOR_THIRD)
     * would equal PERFECT_FIFTH and so forth. Note that this wraps around the octave such that a PERFECT_FIFTH plus a
     * PERFECT_FOURTH would equal UNISON.
     * @param other The other interval class to add to this one
     * @return  The new interval class that corresponds to the addition of this interval class and other
     */
    public IntervalClass plus(IntervalClass other) {
        return getInstance((this.size + other.size)%12);
    }

    /**
     * Creates a new interval class that is the difference between this and another. For instance, a MAJOR_THIRD minus a
     * MINOR_SECOND would be a MINOR_THIRD and so forth. Note that this wraps around the octave such that a UNISON minus
     * a MAJOR_SECOND equals a MINOR_SEVENTH.
     * @param other The other interval class to subtract from this one
     * @return The new interval class that corresponds to the difference between this and other
     */
    public IntervalClass minus(IntervalClass other) {
        return getInstance((this.size - other.size + 12)%12);
    }

    /**
     * Returns a nicely-formatted string of this interval class (for debug).
     * @return A nicely-formatted string of this interval class
     */
    public String toString() {
        switch (size) {
            case 0:     return "PU";   case 1:     return "m2";     case 2:     return "M2";   case 3:     return "m3";
            case 4:     return "M3";   case 5:     return "P4";     case 6:     return "TT";   case 7:     return "P5";
            case 8:     return "m6";   case 9:     return "M6";     case 10:    return "m7";   case 11:    return "M7";
            default:    return "ERROR";
        }
    }

    /**
     * Compares this interval class to another, purely based on size.
     * @param other the other interval class to compare this one to
     * @return The comparison between the two
     */
    @Override
    public int compareTo(IntervalClass other) {
        return new Integer(size).compareTo(other.size);
    }

    /**
     * Compares two IntervalClasses, purely based on size.
     * @param ic1 The first interval class
     * @param ic2 The second interval class
     * @return The comparison between the two
     */
    @Override
    public int compare(IntervalClass ic1, IntervalClass ic2) {
        return new Integer(ic1.size).compareTo(ic2.size);
    }

    /**
     * A noteQualities (generated) equals() method for IntervalClasses.
     * @param o The Object to compare this to
     * @return If these two Objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntervalClass intervalClass = (IntervalClass) o;
        return size == intervalClass.size;
    }

    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this interval class.
     */
    @Override
    public int hashCode() {
        return size;
    }
}