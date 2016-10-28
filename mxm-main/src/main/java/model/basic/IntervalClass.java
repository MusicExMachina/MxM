package model.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * Interval is a glorified byte wrapper,
 * which allows for a little more dress
 * and prevents problems down the line
 * with arguments to constructors.
 */
public class IntervalClass implements Comparator<IntervalClass>, Comparable<IntervalClass> {

    /////////////////////////////
    // Public static variables //
    /////////////////////////////

    /** The IntervalClass of a unison */
    public static final IntervalClass UNISON            = new IntervalClass(0);
    /** The IntervalClass of a minor second */
    public static final IntervalClass MINOR_SECOND      = new IntervalClass(1);
    /** The IntervalClass of a major second */
    public static final IntervalClass MAJOR_SECOND      = new IntervalClass(2);
    /** The IntervalClass of a minor third */
    public static final IntervalClass MINOR_THIRD       = new IntervalClass(3);
    /** The IntervalClass of a major third */
    public static final IntervalClass MAJOR_THIRD       = new IntervalClass(4);
    /** The IntervalClass of a perfect fourth */
    public static final IntervalClass PERFECT_FOURTH    = new IntervalClass(5);
    /** The IntervalClass of a tritone */
    public static final IntervalClass TRITONE           = new IntervalClass(6);
    /** The IntervalClass of a perfect fifth */
    public static final IntervalClass PERFECT_FIFTH     = new IntervalClass(7);
    /** The IntervalClass of a minor sixth */
    public static final IntervalClass MINOR_SIXTH       = new IntervalClass(8);
    /** The IntervalClass of a major sixth */
    public static final IntervalClass MAJOR_SIXTH       = new IntervalClass(9);
    /** The IntervalClass of a minor seventh */
    public static final IntervalClass MINOR_SEVENTH     = new IntervalClass(10);
    /** The IntervalClass of a major seventh */
    public static final IntervalClass MAJOR_SEVENTH     = new IntervalClass(11);

    /** All possible IntervalClasses */
    public static final Collection<IntervalClass> ALL;

    // Initialize the "ALL" collection
    static {
        ALL = new ArrayList<>();
        for(int intervalClassValue = 0; intervalClassValue < 12; intervalClassValue++) {
            ALL.add(new IntervalClass(intervalClassValue));
        }
    }

    //////////////////////////////
    // Private member variables //
    //////////////////////////////

    /** The value of the interval */
    private int value;

    ///////////////////////////
    // Public member methods //
    ///////////////////////////

    /**
     * The normal Interval constructor.
     * @param size The size of the interval in half-steps.
     */
    public IntervalClass(int size) {
        if(size >= MIN_INTERVAL && size <= MAX_INTERVAL) {
            this.size = (byte)size;
        }
        else {
            throw new Error("Invalid interval size");
        }
    }

    /**
     * The Interval copy constructor.
     * @param other The other Interval to copy.
     */
    public Interval(Interval other) {
        this.size = other.size;
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
        return new Interval(size + other.size);
    }

    /**
     * Creates a new Interval that is the difference of these two.
     * @param other The other Interval to subtract from this one.
     * @return The new Interval difference.
     */
    public Interval minus(Interval other) {
        return new IntervalClass(value - other.value);
    }

    /**
     * Compares this IntervalClass to another, purely based on size.
     * @param other the other IntervalClass to compare this one to.
     * @return The comparison between the two.
     */
    @Override
    public int compareTo(IntervalClass other) {
        return new Integer(value).compareTo(new Integer(other.value));
    }

    /**
     * A basic (generated) equals() method for IntervalClasses.
     * @param o The Object to compare this to.
     * @return If these two Objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntervalClass intervalClass = (IntervalClass) o;
        return value == intervalClass.value;
    }

    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this IntervalClass.
     */
    @Override
    public int hashCode() {
        return (int) value;
    }
}
