package base;

public abstract class AbstractIntegerProp {

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

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
    public String toString() {
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
