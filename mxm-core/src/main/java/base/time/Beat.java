package base.time;

import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/***
 * <p> <b>Class overview:</b>
 * The {@link Beat} class represents a subdivision of a measure- a fraction which amounts to under 1. Beats may be
 * manipulated like other {@link Time} representations, but may never be larger than 1 (i.e. greater than a measure)
 * and are always positive. </p>
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements a <b>factory design pattern</b>- users cannot construct new instances
 * of this class, but must call get() instead. This is mostly a stylistic choice.</p>
 *
 * @author Patrick Celentano
 */
public final class Beat implements Comparable<Beat>, Comparator<Beat> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    /** A "0/1" beat. Represents the lowest point in a measure. */
    public static final Beat ZERO = Beat.get(0,1);
    /** A "1/1" beat. Represents exactly one measure. */
    public static final Beat ONE = Beat.get(1,1);

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

    /**
     * To maintain the <b>interning design pattern</b>, this class implements a get() method. To get a beat, provide a
     * numerator and denominator. Note that these need not be reduced, but the denominator <i>may not</i> be 0.
     * @param num The numerator of this beat
     * @param den The denominator of this beat- may not be zero
     * @return a beat with this numerator and denominator, reduced
     */
    public static Beat get(int num, int den) {
        return new Beat(num,den);
    }

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** The numerator of this beat */
    private final int numerator;
    /** The denominator of this beat */
    private final int denominator;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * A private constructor to enforce the factory design pattern
     * @param num the numerator of this beat
     * @param den the denominator of this beat
     */
    private Beat(int num, int den) {
        // Ensure that the numerator is greater than zero
        if(num < 0) throw new Error("Beat: The numerator must greater than or equal to 0!");
        // Ensure that the denominator is greater than zero
        if(den <= 0) throw new Error("Beat: The denominator must greater than 0!");
        // Ensure that this fraction is smaller than or equal to one
        if(num > den) throw new Error("Beat: Beats must be smaller than a measure!");
        // Reduce the fraction
        Pair<Integer,Integer> pair = Time.reduce(num,den);
        this.numerator = pair.getKey();
        this.denominator = pair.getValue();
    }
    /**
     * A getter for the numerator of this beat
     * @return the numerator of this beat
     */
    public final int getNumerator() {
        return numerator;
    }
    /**
     * A getter for the denominator of this beat
     * @return the denominator of this beat
     */
    public final int getDenominator() {
        return denominator;
    }
    /**
     * Returns a string representation of this beat
     * @return a string representation of this beat
     */
    public final @NotNull String toString() {
        return numerator + "/" + denominator;
    }
    /**
     * Converts this beat's internal fraction to a double
     * @return the value of this beat's internal fraction
     */
    private double toDouble() {
        return (double)numerator/denominator;
    }
    /**
     * Compares this beat to another to determine which is earlier
     * @param other the other beat
     * @return a comparison of these two beats
     */
    @Override
    public final int compareTo(@NotNull Beat other) {
        return Double.compare(this.toDouble(),other.toDouble());
    }
    /**
     * Compares two beats to determine which is earlier
     * @param beat1 the first beat
     * @param beat2 the second beat
     * @return a comparison of these two beats
     */
    @Override
    public final int compare(@NotNull Beat beat1, @NotNull Beat beat2) {
        return Double.compare(beat1.toDouble(),beat2.toDouble());
    }
    /**
     * Checks if this beat is equal to another object.
     * @return if this beat is equal to another object
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beat beat = (Beat) o;
        return (numerator == beat.numerator) && (denominator == beat.denominator);
    }
    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this beat
     */
    @Override
    public final int hashCode() {
        return (31 * numerator) + denominator;
    }
}