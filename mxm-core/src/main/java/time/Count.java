package time;

/**
 * A Count is the fundamental measurement of  musical time. Also note that counts are essentially just fractions.
 * They're not really that special. They are, however, immutable, in order to avoid accidental headaches down the line.
 */
public class Count implements Time {

    /** A zero Count */
    public static final Count ZERO = new Count(0);

    /** A full measure Count */
    public static final Count ONE = new Count(1);

    /** The fraction of this Count (its position within the measure) */
    private Beat countClass;

    /** The measure this Count is in */
    private Measure measure;

    /**
     * A constructor for time.Count taking in just a measure
     * number. Use of this is likely to be rare, but this
     * avoids having annoying 1-denominator constructor calls.
     * @param measure The desired measure.
     */
    public Count(Measure measure) {
        this.numerator   = measure;
        this.denominator = 1;
        this.measure     = numerator / denominator;
        //this.countClass  = new Beat(0,1);
    }

    /**
     * A constructor for time.Count taking in a numerator and
     * denominator. events.sounding.Note that these do not need to be reduced,
     * as the constructor itself will do the reducing.
     * @param numerator The desired numerator.
     * @param denominator The desired denominator.
     */
    public Count(int numerator, int denominator) {
        if(denominator > 0) {
            this.numerator   = numerator;
            this.denominator = denominator;
            this.measure     = numerator / denominator;
            reduce();
            //this.countClass  = new Beat(numerator,denominator);
        }
        else {
            throw new Error("Cannot create a time.Count with a denominator less than or equal to zero.");
        }
    }

    /**
     * A constructor for time.Count taking in a measure, a numerator
     * and a denominator. events.sounding.Note that these do not need to be
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
        return getMeasure() + " & " + (numerator%denominator) + "/" + denominator;//countClass.toString();
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
     * A subtraction function which returns a new time.Count
     * whose value is this time.Count minus other.
     * @param other The other time.Count.
     * @return The new difference time.Count.
     */
    public Count minus(Count other) {
        int newNumerator    = this.numerator * other.denominator - other.numerator*this.denominator;
        int newDenominator  = this.denominator * other.denominator;
        return new Count(newNumerator,newDenominator);
    }

    /**
     * A multiplication function which returns a new
     * time.Count whose value is a scalar times this time.Count.
     * @param scalar The integer scalar to multiply by.
     * @return The new time.Count.
     */
    public Count times(int scalar) {
        return new Count(numerator*scalar,denominator);
    }

    /**
     * A division function which returns a new time.Count
     * whose value is this time.Count divided by a scalar.
     * @param scalar The integer scalar to divide by.
     * @return The new time.Count.
     */
    public Count dividedBy(int scalar) {
        return new Count(numerator,denominator*scalar);
    }



    /**
     * Compares this time.Count to another, purely based on size.
     * @param other the other time.Count to compare this one to.
     * @return The comparison between the two.
     */
    @Override
    public int compareTo(Count other) {
        return Double.compare(this.toDouble(),other.toDouble());
    }

    /**
     * Compares two Counts, purely based on size.
     * @param c1 The first time.Count.
     * @param c2 The second time.Count.
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

        if (numerator != count.numerator)
            return false;

        return denominator == count.denominator;

    }

    /**
     * A simple hash code in order to allow storage
     * in certain Collections, like HashSets.
     * @return The hash code for this time.Count.
     */
    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

}