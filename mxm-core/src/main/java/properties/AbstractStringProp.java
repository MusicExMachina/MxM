package properties;

import properties.note.Accent;
import properties.note.Instrument;
import properties.note.Technique;

/**
 * There are several types of note (notably {@link Accent}, {@link Technique}, and {@link Instrument}) which are
 */
public abstract class AbstractStringProp {

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
    public String toString() {
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
