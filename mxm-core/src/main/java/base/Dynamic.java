package base;

import java.util.Comparator;

/**
 * base.Dynamic is a glorified int wrapper, which allows for a little more dress
 * and prevents problems down the line with arguments to constructors.
 */
public class Dynamic implements Comparator<Dynamic>, Comparable<Dynamic> {

    /* A bunch of preset Dynamics for general use. */
    public static final Dynamic NIENTE        = new Dynamic(0);

    /* Useful variable bounds */
    public static int MIN_DYNAMIC = 0;
    public static int MAX_DYNAMIC = 100;

    /**
     * Stores the loudness of this base.Dynamic
     */
    private final byte value;

    /**
     * Constructor taking in a loudness value.
     * @param value The basic's loudness value.
     */
    public Dynamic(int value) {
        if(value >= MIN_DYNAMIC && value <= MAX_DYNAMIC) {
            this.value = (byte)value;
        }
        else {
            throw new Error("Invalid dynamic range!");
        }
    }

    /**
     * Copy constructor for base.Dynamic.
     * @param other The other base.Dynamic.
     */
    public Dynamic(Dynamic other) {
        this.value = other.value;
    }

    /**
     * Compares this base.Dynamic to another base.Dynamic.
     * @param other The other base.Dynamic.
     * @return The comparison.
     */
    @Override
    public int compareTo(Dynamic other) {
        return new Byte(value).compareTo(new Byte(other.value));
    }

    /**
     * Compares two Dynamics.
     * @param p1 The first base.Dynamic.
     * @param p2 The second base.Dynamic.
     * @return The comparison.
     */
    @Override
    public int compare(Dynamic p1, Dynamic p2) {
        return new Byte(p1.value).compareTo(new Byte(p2.value));
    }

    /**
     * Checks if this base.Dynamic equals another Object.
     * @param o The other Object.
     * @return If this base.Dynamic is equal to the Object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dynamic dynamic = (Dynamic) o;
        return value == dynamic.value;
    }

    /**
     * A simple hash code for storage of Dynamics in special Collections.
     * @return The hash code for this base.Dynamic.
     */
    @Override
    public int hashCode() {
        return (int) value;
    }
}