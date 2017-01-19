package basic;

import java.util.Comparator;

/**
 * A Count is the fundamental measurement of  musical time. Note that Counts are essentially
 * just fractions. They're not really that special. They are, however, immutable, in order to
 * avoid accidental headaches down the line.
 */
public class Count implements Comparator<Count>, Comparable<Count> {

    /** A zero Count. */
    public static final Count ZERO = new Count(0);

    /** A zero Count. */
    public static final Count ONE = new Count(1);

    /** An "infinite" Count */
    public static final Count INFINITY = new Count(Integer.MAX_VALUE); // TODO: Optimize this? 0/0?

    /** The fractional numerator of this Count. */
    private int numerator;

    /** The fractional denominator of this Count. */
    private int denominator;

    /** The measure of this Count. */
    private int measure;

    /**
     * A constructor for Count taking in just a measure
     * number. Use of this is likely to be rare, but this
     * avoids having annoying 1-denominator constructor calls.
     * @param measure The desired measure.
     */
    public Count(int measure) {
        this.numerator   = measure;
        this.denominator = 1;
        this.measure     = numerator / denominator;
        //this.countClass  = new CountClass(0,1);
    }

    /**
     * A constructor for Count taking in a numerator and
     * denominator. Note that these do not need to be reduced,
     * as the constructor itself will do the reducing.
     * @param numerator The desired numerator.
     * @param denominator The desired denominator.
     */
    public Count(int numerator, int denominator) {
        if(denominator > 0) {
            this.numerator   = numerator;
            this.denominator = denominator;
            reduce();
            this.measure     = numerator / denominator;
            //this.countClass  = new CountClass(numerator,denominator);
        }
        else {
            throw new Error("Cannot create a Count with a denominator less than or equal to zero.");
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
            this.measure     = numerator / denominator;
            //this.countClass  = new CountClass(numerator,denominator);
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
        return measure;
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
     * @return A nicely-formatted String of this Count.
     */
    public String toString() {
        return getMeasure() + " & " + (numerator%denominator) + "/" + denominator;//countClass.toString();
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

        if(a == 0 && b==0) {
            throw new IllegalArgumentException("Undefined Count: (0,0)");
        }else if(a==0){
            denominator /= b;
            return;
        }else if(b == 0){
            numerator /= a;
            return;
        }

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
     * A noteQualities (generated) equals() method for Counts.
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