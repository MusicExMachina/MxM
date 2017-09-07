package base;

import base.time.Time;
import org.jetbrains.annotations.NotNull;

/**
 * <p> <b>Class Overview:</b>
 * Time signatures are exactly what they sound like in the Western music theory- representing metrical (that is to say,
 * measure-based) repetitive patterns of rhythm. These are in many ways structurally similar to {@link Time}. The major
 * difference is that there's no reason to ever reduce a time signature from say, 4/4 to 2/2.
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements a <b>factory design pattern</b>- users cannot construct new instances
 * of this class, but must call get() instead. This is mostly a stylistic choice.</p>
 *
 * @author Patrick Celentano
 */
public class TimeSig {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    public static final TimeSig CUT_TIME = get(2,2);
    public static final TimeSig COMMON_TIME = get(4,4);

    public static final TimeSig TWO_FOUR = get(2,4);
    public static final TimeSig THREE_FOUR = get(3,4);
    public static final TimeSig FOUR_FOUR = get(4,4);
    public static final TimeSig FIVE_FOUR = get(5,4);

    public static final TimeSig THREE_EIGHT = get(3,8);
    public static final TimeSig FIVE_EIGHT = get(5,8);
    public static final TimeSig SIX_EIGHT = get(6,8);
    public static final TimeSig SEVEN_EIGHT = get(7,8);
    public static final TimeSig NINE_EIGHT = get(9,8);
    public static final TimeSig ELEVEN_EIGHT = get(11,8);
    public static final TimeSig TWELVE_EIGHT = get(12,8);

    public static final TimeSig DEFAULT = COMMON_TIME;

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

    /**
     * This get method dresses up the TimeSig constructor to match the other classes of the base package such as {@link
     * Pitch}, {@link Tempo}, and so forth.
     * @param num The desired numerator
     * @param den The desired denominator
     * @return a tempo of this speed (in beats per minute)
     */
    public static @NotNull TimeSig get(int num, int den) {
        // Ensure that the numerator is greater than zero
        if(num <= 0) throw new Error("Time Signature: The numerator must greater than 0!");
        // Ensure that the denominator is greater than zero
        if(den <= 0) throw new Error("Time Signature: The denominator must greater than 0!");
        // Do some bit-magic to check if the denominator is a power of two
        if(!((den & (den - 1)) == 0)) throw new Error("Time Signature: The denominator must be a power of two!");
        // Constructor
        return new TimeSig(num, den);
    }

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** The fractional numerator of this time signature */
    private int numerator;
    /** The fractional denominator of this time signature */
    private int denominator;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * A constructor for time signature taking in a numerator and denominator. Note that the denominator must be a power
     * of two, as is standard for Western music notation.
     * @param num The desired numerator
     * @param den The desired denominator
     */
    private TimeSig(int num, int den) {
        this.numerator = num;
        this.denominator = den;
    }
    /**
     * Gets the denominator of this base.TimeSig.
     * @return This base.TimeSig's denominator.
     */
    public int getNumerator() {
        return numerator;
    }
    /**
     * Gets the denominator of this base.TimeSig.
     * @return This base.TimeSig's denominator.
     */
    public int getDenominator() {
        return denominator;
    }
    /**
     * Returns a String representation of this time signature
     * @return a String representation of this time signature
     */
    public String toString() {
        return getNumerator() + "/" + getDenominator();
    }
    /**
     * A noteQualities (generated) equals() method for base.TimeSig.
     * @param o The Object to compare this to.
     * @return If these two Objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSig timeSignature = (TimeSig) o;
        return numerator == timeSignature.numerator && denominator == timeSignature.denominator;
    }
    /**
     * A simple hash code in order to allow storage in certain Collections, like HashSets.
     * @return The hash code for this time signature
     */
    @Override
    public int hashCode() {
        return (31 * numerator) + denominator;
    }
}
