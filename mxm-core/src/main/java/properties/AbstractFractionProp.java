package properties;

import org.jetbrains.annotations.NotNull;

/**
 * <p> <b>Class overview:</b>
 * This class wraps a basic property: a non-reduced fraction for use in classes like {@link properties.time.Time} or
 * {@link properties.time.TimeSig}.</p>
 *
 * <p> <b>Design Details:</b>
 * This abstract class represents an underlying property, and provides basic variables and methods for all derived
 * classes to utilize. Essentially, this just prevents unnecessary code duplication. </p>
 *
 * @author Patrick Celentano
 */
public abstract class AbstractFractionProp implements IMusicProperty {

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** The numerator of this fraction*/
    protected int numerator;
    /** The denominator of this fraction */
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
    public @NotNull String toString() {
        return numerator + "/" + denominator;
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
