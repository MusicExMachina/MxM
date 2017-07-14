package time;

import java.util.Comparator;

/**
 * Counts are the fundamental measurement of  musical time. Also note that counts are essentially just fractions. Note
 * that while their numerator and denominator are an improper fraction, the measure and beat are a proper one.
 *
 * This class is IMMUTABLE!
 */
public class Count implements Comparable<Count>, Comparator<Count>
{
    /** A zero Count */
    public static final Count ZERO = new Count(0);
    /** A full measure Count */
    public static final Count ONE = new Count(1);
    /** The numerator of this Count as an IMPROPER fraction!) */
    private int numerator;
    /** The denominator of this Count */
    private int denominator;
    /** The measure this Count is in */
    private int measure;
    /** The beat of this Count (its position within the measure) */
    private Beat beat;

    /**
     * A constructor for a count taking in just a measure number.
     * @param measure The desired measure.
     */
    public Count(int measure) {
        this(measure,1);
    }

    /**
     * A constructor for a count taking in just a beat (assuming measure 0).
     * @param beat The desired beat.
     */
    public Count(Beat beat) {
        this(0,beat);
    }

    /**
     * A constructor for count taking in a numerator and a denominator. Note that these do not need to be reduced.
     * @param numerator The desired numerator.
     * @param denominator The desired denominator.
     */
    public Count(int numerator, int denominator) {
        if(denominator > 0) {
            this.numerator   = numerator;
            this.denominator = denominator;
            this.measure     = numerator / denominator;
            reduce();
            this.beat = new Beat(numerator,denominator);
        }
        else {
            throw new Error("Cannot create a count with a denominator less than or equal to zero.");
        }
    }

    /**
     * A constructor for a count taking in a measure and a beat. events.sounding.Note that these do not need to be
     * reduced, as the constructor itself will do the reducing.
     * @param measure The desired measure.
     * @param beat The desired beat.
     */
    public Count(int measure, Beat beat) {
        if(denominator != 0) {
            this.numerator   = numerator + measure*denominator;
            this.denominator = denominator;
            reduce();
            this.measure     = numerator / denominator;
        }
        else {
            throw new Error("Cannot create a time.Count with a deno");
        }
    }

    /**
     * Gets the measure number, i.e., the "whole
     * number" part of the internal fraction.
     * @return This time.Count's measure number.
     */
    public int getMeasure() {
        return measure;
    }

    /**
     * Returns a nicely-formatted String
     * of this time.Count (for debug).
     * @return A nicely-formatted String of this time.Count.
     */
    public String toString() {
        return getMeasure() + " & " + (numerator%denominator) + "/" + denominator;//beat.toString();
    }

    /**
     * An addition function which returns a new time.Count
     * whose value is this time.Count plus other.
     * @param other The other time.Count.
     * @return The new sum time.Count.
     */
    public Count plus(Count other) {
        int newNumerator    = this.numerator * other.denominator + other.numerator*this.denominator;
        int newDenominator  = this.denominator * other.denominator;
        return new Count(newNumerator,newDenominator);
    }

    /**
     * A subtraction function which returns a new count whose value is this count minus other.
     * @param other The other count.
     * @return The new difference count.
     */
    public Count minus(Count other) {
        int newNumerator    = this.numerator * other.denominator - other.numerator*this.denominator;
        int newDenominator  = this.denominator * other.denominator;
        return new Count(newNumerator,newDenominator);
    }

    /**
     * A multiplication function which returns a new count whose value is a scalar times this count
     * @param scalar The integer scalar to multiply by.
     * @return The new count.
     */
    public Count times(int scalar) {
        return new Count(numerator*scalar,denominator);
    }

    /**
     * A division function which returns a new count whose value is this count divided by a scalar.
     * @param scalar The integer scalar to divide by.
     * @return The new count.
     */
    public Count dividedBy(int scalar) {
        return new Count(numerator,denominator*scalar);
    }

    /**
     * Compares this time.Count to another, purely based on size.
     * @param other the other time.Count to compare this one to.
     * @return The comparison between the two.
     */
    public int compareTo(Count other) {
        return Double.compare(this.toDouble(),other.toDouble());
    }

    /**
     * Compares two Counts, purely based on size.
     * @param c1 The first time.Count.
     * @param c2 The second time.Count.
     * @return The comparison between the two.
     */
    public int compare(Count c1, Count c2) {
        return Double.compare(c1.toDouble(),c2.toDouble());
    }

    /**
     * A noteQualities (generated) equals() method for counts.
     * @param o The Object to compare this to.
     * @return If these two Objects are equal.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Count count = (Count) o;
        return numerator == count.numerator && denominator == count.denominator;
    }

    /**
     * A simple hash code in order to allow storage in certain Collections, like HashSets.
     * @return The hash code for this time.Count.
     */
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

    /**
     * A getter for this count's numerator.
     * @return This count's numerator.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * A getter for this count's denominator.
     * @return This count's denominator.
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Converts this count to a float.
     * @return This count's float value.
     */
    public float toFloat() {
        return (float)numerator/denominator;
    }

    /**
     * Converts this count to a double.
     * @return This count's double value.
     */
    public double toDouble() {
        return (double)numerator/denominator;
    }

    /**
     * Reduces the count's internal fraction, a vital method which is run only when a new count is created.
     */
    private void reduce() {
        // Euclid's gcd algorithm, everyone's favorite
        int a = numerator;
        int b = denominator;

        if(a == 0 && b == 0) {
            throw new IllegalArgumentException("Undefined Count: (0,0)");
        }
        else if(a == 0){
            denominator /= b;
            return;
        }
        else if(b == 0){
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
        // Reduce both numerator and denominator by "a," the greatest common factor.
        numerator   /= a;
        denominator /= a;
    }
}