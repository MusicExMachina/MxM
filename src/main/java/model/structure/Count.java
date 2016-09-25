package model.structure;

import java.util.Comparator;

/**
 * A Count is the fundamental measurement of
 * musical time. Note that Counts are essentially
 * just fractions. They're not really that special.
 * They are, however, immutable, in order to avoid
 * accidental headaches down the line.
 */
public class Count implements Comparator<Count>, Comparable<Count> {

    /* A bunch of preset Count for general use. */
    public static Count ZERO            = new Count(0,1);
    public static Count FULL_MEASURE    = new Count(1,1);
    public static Count HALF_MEASURE    = new Count(1,2);
    public static Count THIRD_MEASURE   = new Count(1,3);
    public static Count QUARTER_MEASURE = new Count(1,4);
    public static Count EIGHTH_MEASURE  = new Count(1,8);

    /** The fractional numerator of this Count */
    private int numerator;

    /** The fractional denominator of this Count */
    private int denominator;


    /**
     * A constructor for Count taking in just a measure
     * number. Use of this is likely to be rare, but this
     * avoids having annoying 1-denominator constructor calls.
     * @param measure The desired measure.
     */
    public Count(int measure) {
        this.numerator   = measure;
        this.denominator = 1;
    }

    /**
     * A constructor for Count taking in a numerator and
     * denominator. Note that these do not need to be reduced,
     * as the constructor itself will do the reducing.
     * @param numerator The desired numerator.
     * @param denominator The desired denominator.
     */
    public Count(int numerator, int denominator) {
        if(denominator != 0) {
            this.numerator   = numerator;
            this.denominator = denominator;
            reduce();
        }
        else {
            throw new Error("Cannot create a Count with a deno");
        }
    }

    /**
     * A constructor for Count taking in a measure, a numerator
     * and a denominator. Note that these do not need to be
     * reduced, as the constructor itself will do the reducing.
     * @param measure The desired measure.
     * @param numerator The desired numerator.
     * @param denominator The desired denominator.
     */
    public Count(int measure, int numerator, int denominator) {
        if(denominator != 0) {
            this.numerator   = numerator + measure*denominator;
            this.denominator = denominator;
            reduce();
        }
        else {
            throw new Error("Cannot create a Count with a deno");
        }
    }

    /**
     * Gets the measure number, i.e., the "whole
     * number" part of the internal fraction.
     * @return This Count's measure number.
     */
    public int getMeasure() {
        return numerator / denominator;
    }

    /**
     * Gets the beat of this Count, which is
     * essentially which number of a subdivision
     * this Count is in its measure. For example,
     * with a subdivision of 3, a beat of 1 would
     * be the second quarter note in 3/4.
     * @return This Count's beat in the measure.
     */
    public int getBeat() {
        return numerator % denominator;
    }

    /**
     * Gets the subdivision level of this Count,
     * i.e. the denominator of the internal fraction.
     * @return This Count's subdivision level.
     */
    public int getSubdivision() {
        return denominator;
    }

    /**
     * Converts this Count to a float.
     * @return This Count's float value.
     */
    public float toFloat() {
        return numerator/denominator;
    }

    /**
     * Converts this Count to a double.
     * @return This Count's double value.
     */
    public double toDouble() {
        return numerator/denominator;
    }

    /**
     * Returns a nicely-formatted String
     * of this Count (for debug).
     * @return This Count's double value.
     */
    public String toString() {
        return "[ M " + getMeasure() + " | " + getBeat() + "/" + getSubdivision() + "]";
    }

    /**
     * An addition function which returns a new Count
     * whose value is this Count plus other.
     * @param other The other Count.
     * @return The new sum Count.
     */
    public Count plus(Count other) {
        int newNumerator    = this.numerator * other.denominator + other.numerator*this.denominator;
        int newDenominator  = this.denominator * other.denominator;
        return new Count(newNumerator,newDenominator);
    }

    /**
     * A subtraction function which returns a new Count
     * whose value is this Count minus other.
     * @param other The other Count.
     * @return The new difference Count.
     */
    public Count minus(Count other) {
        int newNumerator    = this.numerator * other.denominator - other.numerator*this.denominator;
        int newDenominator  = this.denominator * other.denominator;
        return new Count(newNumerator,newDenominator);
    }

    /**
     * A multiplication function which returns a new
     * Count whose value is a scalar times this Count.
     * @param scalar The integer scalar to multiply by.
     * @return The new Count.
     */
    public Count times(int scalar) {
        return new Count(numerator*scalar,denominator);
    }

    /**
     * A division function which returns a new Count
     * whose value is this Count divided by a scalar.
     * @param scalar The integer scalar to divide by.
     * @return The new Count.
     */
    public Count dividedBy(int scalar) {
        return new Count(numerator,denominator*scalar);
    }

    /**
     * Reduces the Count's internal fraction, a vital
     * method which is run exactly once- every time a
     * new Count is created. This could be lumped in
     * the constructor, but why do that?
     */
    private void reduce() {

        // Euclid's gcd algorithm, everyone's favorite
        int a = numerator;
        int b = denominator;

        while (a != b) {
            if (a > b) {
                a -= b;
            }
            else {
                b -= a;
            }
        }

        // Reduce both numerator and denominator
        // by "a," the greatest common factor.
        numerator   /= a;
        denominator /= a;
    }

    /**
     * Compares this Count to another, purely based on size.
     * @param other the other Count to compare this one to.
     * @return The comparison between the two.
     */
    @Override
    public int compareTo(Count other) {
        return Double.compare(this.toDouble(),other.toDouble());
    }

    /**
     * Compares two Counts, purely based on size.
     * @param c1 The first Count.
     * @param c2 The second Count.
     * @return The comparison between the two.
     */
    @Override
    public int compare(Count c1, Count c2) {
        return Double.compare(c1.toDouble(),c2.toDouble());
    }

    /**
     * A basic (generated) equals() method for Counts.
     * @param o The Object to compare this to.
     * @return If these two Objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Count count = (Count) o;

        if (numerator != count.numerator) return false;
        return denominator == count.denominator;

    }

    /**
     * A simple hash code in order to allow storage
     * in certain Collections, like HashSets.
     * @return The hash code for this Count.
     */
    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }
}