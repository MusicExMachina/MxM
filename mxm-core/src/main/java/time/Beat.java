package time;

import java.util.Comparator;

/**
 * Beats represent a sub-measure measurement of musical time (i.e. "beat three of four" or "the upbeat of 2"). In this
 * sense, they are the proper fraction, which when combined with an integer measure, make up an entire count. In a
 * different sense, beats are like PitchClasses, IntervalClasses, or anything else.
 *
 * This class is IMMUTABLE!
 */
public class Beat implements Comparable<Beat>, Comparator<Beat> {

    public static Beat ZERO = new Beat(0,1);
    public static Beat ONE = new Beat(1,0);

    private int numerator;
    private int denominator;

    public Beat(int numerator, int denominator) {
        if(numerator > denominator) {
            throw new Error("Cannot create a beat larger than ");
        }
        if(denominator > 0) {
            this.numerator = numerator;
            this.denominator = denominator;
            reduce();
            //this.countClass  = new Beat(numerator,denominator);
        }
        else {
            throw new Error("Cannot create a time.Count with a denominator less than or equal to zero.");
        }
    }


    public Count plus(Beat other) {
        int newNumerator = this.numerator + other.numerator;
        int newDenominator = this.denominator + other.denominator;
        int measureDifference = 0;

        if(newNumerator > newDenominator) {
            newNumerator -= newDenominator;
            measureDifference += 1;
        }
        return new Count(new Measure(measureDifference), new Beat(newNumerator, newDenominator));
    }


    public Count minus(Beat other) {
        int newNumerator = this.numerator - other.numerator;
        int newDenominator = this.denominator - other.denominator;
        int measureDifference = 0;

        if(newNumerator < 0) {
            newNumerator += newDenominator;
            measureDifference -= 1;
        }
        return new Count(new Measure(measureDifference), new Beat(newNumerator, newDenominator));
    }

    /**
     * A getter for this time.Count's numerator.
     * @return This time.Count's numerator.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * A getter for this time.Count's denominator.
     * @return This time.Count's denominator.
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Converts this time.Count to a float.
     * @return This time.Count's float value.
     */
    public float toFloat() {
        return (float)numerator/denominator;
    }

    /**
     * Converts this time.Count to a double.
     * @return This time.Count's double value.
     */
    public double toDouble() {
        return (double)numerator/denominator;
    }

    /**
     * Reduces the count's internal fraction, a vital
     * method which is run exactly once- every time a
     * new time.Count is created. This could be lumped in
     * the constructor, but why do that?
     */
    private void reduce() {
        // Euclid's gcd algorithm, everyone's favorite
        int a = numerator;
        int b = denominator;

        if(a == 0 && b == 0) {
            throw new IllegalArgumentException("BEAT:\tUndefined! (0,0)");
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

    @Override
    public int compareTo(Beat other) {
        return Double.compare(this.toDouble(),other.toDouble());
    }

    @Override
    public int compare(Beat b1, Beat b2) {
        return Double.compare(b1.toDouble(),b2.toDouble());
    }
}
