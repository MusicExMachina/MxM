package properties;

import org.jetbrains.annotations.NotNull;

/**
 * <p> <b>Class overview:</b>
 * This class wraps a basic property: an integer for use in classes like {@link properties.time.Tempo} or even
 * {@link properties.sound.Pitch}.</p>
 *
 * <p> <b>Design Details:</b>
 * This abstract class represents an underlying property, and provides basic variables and methods for all derived
 * classes to utilize. Essentially, this just prevents unnecessary code duplication. </p>
 *
 * @author Patrick Celentano
 */
public abstract class AbstractIntegerProp {

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** The underlying value of this property */
    protected int value;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * A constructor for a string-based property
     * @param value the value of this property
     */
    protected AbstractIntegerProp(int value) {
        // Set the member variables
        this.value = value;
    }
    /**
     * A getter for the value of this integer prop
     * @return the value of this integer prop
     */
    public final int getValue() {
        return value;
    }
    /**
     * A basic fraction string (to be overwritten by implementations)
     * @return a string representation of this fraction
     */
    @Override
    public @NotNull String toString() {
        return value + "";
    }
    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this integer property
     */
    @Override
    public final int hashCode() {
        return value;
    }
}
