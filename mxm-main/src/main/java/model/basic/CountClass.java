package model.basic;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.HashMap;

/**
 * CountClass represents a measure-less Count. Note that this is useful for several reasons-
 * First, it allows efficient interning of common values like 1/4 and 1/3. Second, it makes
 * a great deal of sense from a RhythmTree perspective- each RhythmNode is essentially a
 * CountClass duration and a CountClass timing. Notes themselves, of course, use full Counts.
 */
public class CountClass implements Comparator<CountClass>, Comparable<CountClass> {

    /** A zero Count. */
    public static final CountClass ZERO = new CountClass(0,1);

    /** A full measure Count. */
    public static final CountClass ONE = new CountClass(1,1);

    /** The fractional numerator of this Count. */
    private int numerator;

    /** The fractional denominator of this Count. */
    private int denominator;

    /** Stores all CountClasses so that we avoid abusing memory. */
    private static HashMap<Pair<Integer,Integer>,CountClass> ALL = new HashMap<>();

    /**
     * A constructor for CountClass taking in a numerator and
     * denominator. Note that these do not need to be reduced,
     * as the constructor itself will do the reducing.
     * @param numerator The desired numerator.
     * @param denominator The desired denominator.
     */
    public CountClass(int numerator, int denominator) {
        if(numerator > denominator) {
            throw new Error("COUNTCLASS:\tCannot create a CountClass with a length larger than a measure!");
        }
        else if (denominator > 0) {
            this.numerator = numerator;
            this.denominator = denominator;
            reduce();
        } else {
            throw new Error("COUNTCLASS:\tCannot create a CountClass with a denominator less than or equal to zero!");
        }
    }

    /**
     * Gets the beat of this Count, which is
     * essentially which number of a subdivision
     * this Count is in its measure. For example,
     * with a subdivision of 3, a beat of 1 would
     * be the second quarter note in 3/4.
     * @return This Count's beat in the measure.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Gets the subdivision level of this Count,
     * i.e. the denominator of the internal fraction.
     * @return This Count's subdivision level.
     */
    public int getDenominator() {
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
     * @return A nicely-formatted String of this Count.
     */
    public String toString() {
        return getNumerator() + "/" + getDenominator();
    }

    /**
     * An addition function which returns a new CountClass
     * whose value is this CountClass plus other.
     * @param other The other CountClass.
     * @return The new sum CountClass.
     */
    public CountClass plus(CountClass other) {
        int newNumerator    = this.numerator * other.denominator + other.numerator*this.denominator;
        int newDenominator  = this.denominator * other.denominator;
        return new CountClass(newNumerator,newDenominator);
    }

    /**
     * A subtraction function which returns a new CountClass
     * whose value is this CountClass minus other.
     * @param other The other CountClass.
     * @return The new difference CountClass.
     */
    public CountClass minus(CountClass other) {
        int newNumerator    = this.numerator * other.denominator - other.numerator*this.denominator;
        int newDenominator  = this.denominator * other.denominator;
        return new CountClass(newNumerator,newDenominator);
    }

    /**
     * A multiplication function which returns a new CountClass
     * whose value is a scalar times this CountClass.
     * @param scalar The integer scalar to multiply by.
     * @return The new CountClass.
     */
    public CountClass times(int scalar) {
        return new CountClass(numerator*scalar,denominator);
    }

    /**
     * A division function which returns a new CountClass
     * whose value is this CountClass divided by a scalar.
     * @param scalar The integer scalar to divide by.
     * @return The new CountClass.
     */
    public CountClass dividedBy(int scalar) {
        return new CountClass(numerator,denominator*scalar);
    }

    /**
     * Reduces the CountClass's internal fraction, a vital
     * method which is run exactly once- every time a
     * new CountClass is created. This could be lumped in
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
     * Compares this CountClass to another, purely based on size.
     * @param other the other CountClass to compare this one to.
     * @return The comparison between the two.
     */
    @Override
    public int compareTo(CountClass other) {
        return Double.compare(this.toDouble(),other.toDouble());
    }

    /**
     * Compares two CountClasses, purely based on size.
     * @param c1 The first CountClass.
     * @param c2 The second CountClass.
     * @return The comparison between the two.
     */
    @Override
    public int compare(CountClass c1, CountClass c2) {
        return Double.compare(c1.toDouble(),c2.toDouble());
    }

    /**
     * A noteQualities (generated) equals() method for CountClasses.
     * @param o The Object to compare this to.
     * @return If these two Objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountClass count = (CountClass) o;

        if (numerator != count.numerator) return false;
        return denominator == count.denominator;

    }

    /**
     * Gets an instance of a given CountClass size. This method
     * creates the interning design pattern per CountClass.
     * @param numerator The numerator of this CountClass.
     * @param denominator The denominator of this CountClass.
     * @return An CountClass of this value.
     */
    public static CountClass getInstance(int numerator,int denominator) {
        Pair<Integer,Integer> pair = new Pair<>(numerator,denominator);
        if(!ALL.containsKey(pair)) {
            ALL.put(pair,new CountClass(numerator,denominator));
        }
        return ALL.get(pair);
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
