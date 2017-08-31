package base;

import io.MxmLog;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * There are only twelve interval classes which represent the twelve possible (always positive) vector differences
 * between sound classes. For instance, MINOR_SECOND represents both a movement of a minor second upward, a major
 * seventh downward, a minor ninth upward, so on and so forth such that octaves are always compressed.
 */
public class IntervalClass implements Comparator<IntervalClass>, Comparable<IntervalClass> {

    //////////////////////////////
    // Static Variables         //
    //////////////////////////////

    /** The lowest interval class, better known as "an upward unison" */
    public static final int MIN_SIZE = 0;
    /** The highest interval class, better known as "an upward major seventh" */
    public static final int MAX_SIZE = 11;

    /** A static array of all possible interval classes, stored to implement the flyweight pattern */
    private static final IntervalClass[] ALL;
    // Initializes the "ALL" array
    static {
        // Keep track of the start time to know how long initialization takes
        long startTime = System.nanoTime();
        // Initialize all interval classes
        ALL = new IntervalClass[MAX_SIZE - MIN_SIZE + 1];
        for(int val = MIN_SIZE; val <= MAX_SIZE; val++) {
            ALL[val] = new IntervalClass(val);
        }
        long endTime = System.nanoTime();
        MxmLog.logInitialization("Interval class", Arrays.asList(ALL),endTime - startTime);
    }

    /** The unison interval class */
    public static final IntervalClass UNISON = get(0);
    /** The minor second interval class */
    public static final IntervalClass MINOR_SECOND = get(1);
    /** The major second interval class */
    public static final IntervalClass MAJOR_SECOND = get(2);
    /** The minor third interval class */
    public static final IntervalClass MINOR_THIRD = get(3);
    /** The major third interval class */
    public static final IntervalClass MAJOR_THIRD = get(4);
    /** The perfect fourth interval class */
    public static final IntervalClass PERFECT_FOURTH = get(5);
    /** The tritone interval class */
    public static final IntervalClass TRITONE = get(6);
    /** The perfect fifth interval class */
    public static final IntervalClass PERFECT_FIFTH = get(7);
    /** The minor sixth interval class */
    public static final IntervalClass MINOR_SIXTH = get(8);
    /** The major sixth interval class */
    public static final IntervalClass MAJOR_SIXTH = get(9);
    /** The minor seventh interval class */
    public static final IntervalClass MINOR_SEVENTH = get(10);
    /** The major seventh interval class */
    public static final IntervalClass MAJOR_SEVENTH = get(11);

    //////////////////////////////
    // Static Methods           //
    //////////////////////////////

    /**
     * Gets an instance of a given interval class. This method creates the interning design pattern per interval class.
     * @param size The size (in half steps) of this interval class
     * @return An intervalClass of this value
     */
    public static IntervalClass get(int size) {
        if(size >= MIN_SIZE && size <= MAX_SIZE) {
            return ALL[size];
        }
        else {
            throw new Error("INTERVAL CLASS:\tInterval class out of range.");
        }
    }
    /**
     * Gets an iterator which enumerates all valid interval classes.
     * @return An iterator over all valid interval classes
     */
    public static Iterator<IntervalClass> allItr() {
        return new ArrayList<>(Arrays.asList(ALL)).iterator();
    }

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** The size of the interval class. */
    private final int size;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * A private constructor for IntervalClass which is hidden by the flyweight design pattern (use get() instead).
     * @param size the size of this interval class
     */
    private IntervalClass(int size) {
        this.size = size;
    }

    /**
     * A getter for the size of this interval class, in half-steps.
     * @return the size of this interval class, in half-steps
     */
    public final int getSize() {
        return size;
    }
    /**
     * Returns the interval class that is the summation of this and another. For instance, MAJOR_THIRD.plus(MINOR_THIRD)
     * would equal PERFECT_FIFTH and so forth. Note that this wraps around the octave such that a PERFECT_FIFTH plus a
     * PERFECT_FOURTH would equal UNISON.
     * @param other the other interval class to add to this one
     * @return the new interval class that corresponds to the addition of this interval class and other
     */
    public final @NotNull IntervalClass plus(@NotNull IntervalClass other) {
        return get((this.size + other.size)%12);
    }
    /**
     * Creates a new interval class that is the difference between this and another. For instance, a MAJOR_THIRD minus a
     * MINOR_SECOND would be a MINOR_THIRD and so forth. Note that this wraps around the octave such that a UNISON minus
     * a MAJOR_SECOND equals a MIN_SEVENTH.
     * @param other the other interval class to subtract from this one
     * @return the new interval class that corresponds to the difference between this and other
     */
    public final @NotNull IntervalClass minus(@NotNull IntervalClass other) {
        return get((this.size - other.size + 12)%12);
    }
    /**
     * Returns a nicely-formatted string of this interval class (for debug).
     * @return A nicely-formatted string of this interval class
     */
    @Override
    public final @NotNull String toString() {
        switch (size) {
            case 0:     return "PU";
            case 1:     return "m2";
            case 2:     return "M2";
            case 3:     return "m3";
            case 4:     return "M3";
            case 5:     return "P4";
            case 6:     return "TT";
            case 7:     return "P5";
            case 8:     return "m6";
            case 9:     return "M6";
            case 10:    return "m7";
            case 11:    return "M7";
            default:    return "ERROR";
        }
    }
    /**
     * Compares this interval class to another, purely based on size.
     * @param other the other interval class to compare this one to
     * @return The comparison between the two
     */
    @Override
    public final int compareTo(@NotNull IntervalClass other) {
        return new Integer(size).compareTo(other.size);
    }
    /**
     * Compares two IntervalClasses, purely based on size.
     * @param ic1 The first interval class
     * @param ic2 The second interval class
     * @return The comparison between the two
     */
    @Override
    public final int compare(@NotNull IntervalClass ic1, @NotNull IntervalClass ic2) {
        return new Integer(ic1.size).compareTo(ic2.size);
    }
    /**
     * Checks if this interval class is equal to another object. Note that since the flyweight pattern is used, literal
     * (reference) equality is enough to ensure that these objects are actually equal.
     * @return if this interval class is equal to another
     */
    @Override
    public final boolean equals(Object object) {
        return this == object;
    }
    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this interval class
     */
    @Override
    public final int hashCode() {
        return size;
    }
}