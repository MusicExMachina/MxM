package properties;

import org.jetbrains.annotations.NotNull;

/**
 * <p> <b>Class overview:</b>
 * This class wraps a basic property: an string for use in classes like {@link properties.note.Accent},
 * {@link properties.note.Technique}, and {@link properties.note.Instrument}.</p>
 *
 * <p> <b>Design Details:</b>
 * This abstract class represents an underlying property, and provides basic variables and methods for all derived
 * classes to utilize. Essentially, this just prevents unnecessary code duplication. </p>
 *
 * @author Patrick Celentano
 */
public abstract class AbstractStringProp implements IMusicProperty {

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    protected String value;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * A constructor for a string-based property
     * @param value the value of this property
     */
    protected AbstractStringProp(String value) {
        // Set the member variables
        this.value = value;
    }
    /**
     * A getter for the value of this string property
     * @return the value of this string property
     */
    public final String getValue() {
        return value;
    }
    /**
     * A basic fraction string (to be overwritten by implementations)
     * @return a string representation of this fraction
     */
    @Override
    public @NotNull String toString() {
        return value;
    }
    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this string property
     */
    @Override
    public final int hashCode() {
        return value.hashCode();
    }
}
