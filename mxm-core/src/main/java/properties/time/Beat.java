package properties.time;

import properties.AbstractReducedFractProp;
import org.jetbrains.annotations.NotNull;

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
public final class Beat extends AbstractReducedFractProp implements Comparable<Beat> {

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
    // Member methods           //
    //////////////////////////////

    /**
     * A private constructor to enforce the factory design pattern
     * @param num the numerator of this beat
     * @param den the denominator of this beat
     */
    private Beat(int num, int den) {
        super(num,den);
    }
    /**
     * Returns a string representation of this beat
     * @return a string representation of this beat
     */
    public final @NotNull String toString() {
        return numerator + "/" + denominator;
    }
    /**
     * Compares this beat to another to determine which is earlier
     * @param other the other beat
     * @return a comparison of these two beats
     */
    @Override
    public final int compareTo(@NotNull Beat other) {
        int num1 = getNumerator() * other.getDenominator();
        int num2 = other.getNumerator() * getDenominator();
        return Integer.compare(num1,num2);
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
}