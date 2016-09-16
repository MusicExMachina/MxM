package model.time_free;

import java.util.*;

/**
 * Interval is a glorified byte wrapper,
 * which allows for a little more dress
 * and prevents problems down the line
 * with arguments to constructors.
 */
public class Interval implements Comparator<Interval>, Comparable<Interval> {

    /**
     * The immutable size of this Interval in half-steps
     */
    byte size;

    /**
     * The normal Interval constructor.
     * @param size The size of the interval in half-steps.
     */
    public Interval(int size) {
        if(size >= -Pitch.MAX_PITCH && size <= Pitch.MAX_PITCH) {
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
        return new Interval(size - other.size);
    }

    /**
     * Compares this Interval to another, purely based on size.
     * @param other the other Interval to compare this one to.
     * @return The comparison between the two.
     */
    @Override
    public int compareTo(Interval other) {
        return Byte.compare(size, other.size);
    }

    /**
     * Compares two Intervals, purely based on size.
     * @param i1 The first Interval.
     * @param i2 The second Interval.
     * @return The comparison between the two.
     */
    @Override
    public int compare(Interval i1, Interval i2) {
        return Byte.compare(i1.size, i2.size);
    }

    /**
     * A basic (generated) equals() method for Intervals.
     * @param o The Object to compare this to.
     * @return If these two Objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return size == interval.size;

    }

    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this Interval.
     */
    @Override
    public int hashCode() {
        return (int) size;
    }
}
