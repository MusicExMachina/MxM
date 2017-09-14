package base;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractFractionProp {

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    protected int numerator;
    protected int denominator;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * A constructor for a fraction-based property
     * @param num the numerator of this fraction
     * @param den the denominator of this fraction
     */
    protected AbstractFractionProp(int num, int den) {
        // Ensure that the denominator is greater than zero
        if(den ==  0) throw new Error("Abstract Fraction Prop: The denominator must greater than 0!");
        // Set the member variables
        this.numerator = num;
        this.denominator = den;
    }

    /**
     * A getter for the numerator of this fraction prop
     * @return the numerator of this fraction prop
     */
    public final int getNumerator() {
        return numerator;
    }
    /**
     * A getter for the denominator of this fraction prop
     * @return the denominator of this fraction prop
     */
    public final int getDenominator() {
        return denominator;
    }
    /**
     * A basic fraction string (to be overwritten by implementations)
     * @return a string representation of this fraction
     */
    @Override
    public String toString() {
        return super.toString()+ " time";
    }
    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this fraction property
     */
    @Override
    public final int hashCode() {
        return (31 * numerator) + denominator;
    }
}
