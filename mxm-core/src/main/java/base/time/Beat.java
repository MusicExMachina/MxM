package base.time;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.HashMap;

/***
 * <p> <b>Class overview:</b>
 * The {@link Beat} class represents a subdivision of a measure- a fraction which amounts to under 1. Beats may be
 * manipulated like other {@link Time} representations, but may never be larger than 1 (i.e. greater than a measure)</p>
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements the <b>interning design pattern</b>- there is exactly one instance for
 * each value such that two ADTs (Abstract Data Types) with the same value are, in fact, the same instance. This
 * simplifies equality checks and can prevent memory waste. Unlike the <b>flyweight design pattern</b>, these are not
 * created upfront, but generated on demand. </p>
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
    public static final Beat ONE = Beat.get(1,0);

    /** To preserve the */
    //private static final HashMap<Pair<Integer,Integer>>
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

    private Beat(int num, int den) {
        Pair<Integer,Integer> pair = Time.reduce(num,den);
        this.numerator = pair.getKey();
        this.denominator = pair.getValue();
    }

    public int getNumerator() {
        return numerator;
    }
    public int getDenominator() {
        return denominator;
    }

    public String toString() { return numerator + "/" + denominator; }


    public float toFloat() {
        return (float)numerator/denominator;
    }
    public double toDouble() {
        return (double)numerator/denominator;
    }

    @Override
    public int compareTo(Beat other) { return Double.compare(this.toDouble(),other.toDouble()); }
    @Override
    public int compare(Beat b1, Beat b2) { return Double.compare(b1.toDouble(),b2.toDouble()); }
}