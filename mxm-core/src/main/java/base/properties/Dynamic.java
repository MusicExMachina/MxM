package base.properties;

import base.INoteProperty;

import java.util.Comparator;

/**
 * The dynamic class is a glorified byte wrapper, which allows for a little more dress and avoiding confusion down the
 * line. Note that we still use the MIDI standard's definition of loudness, just as we use midi note values.
 */
public class Dynamic implements INoteProperty, Comparator<Dynamic>, Comparable<Dynamic> {

    /* Variable bounds */
    private static int MIN_DYNAMIC = 0;
    private static int MAX_DYNAMIC = 127;

    /* A bunch of preset dynamics for general use. Note that niente is silent */
    public static final Dynamic NIENTE              = new Dynamic(0);
    public static final Dynamic PIANISSISSIMO       = new Dynamic(16);
    public static final Dynamic PIANISSIMO          = new Dynamic(32);
    public static final Dynamic PIANO               = new Dynamic(48);
    public static final Dynamic MEZZO_PIANO         = new Dynamic(64);
    public static final Dynamic MEZZO_FORTE         = new Dynamic(80);
    public static final Dynamic FORTE               = new Dynamic(96);
    public static final Dynamic FORTISSIMO          = new Dynamic(112);
    public static final Dynamic FORTISSISSIMO       = new Dynamic(127);

    /** Stores the loudness of this dynamic. */
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
            throw new Error("DYNAMIC:\tInvalid dynamic range!");
        }
    }

    /**
     * Compares this base.properties.Dynamic to another base.properties.Dynamic.
     * @param other The other base.properties.Dynamic.
     * @return The comparison.
     */
    @Override
    public int compareTo(Dynamic other) {
        return new Byte(value).compareTo(new Byte(other.value));
    }

    /**
     * Compares two Dynamics.
     * @param p1 The first base.properties.Dynamic.
     * @param p2 The second base.properties.Dynamic.
     * @return The comparison.
     */
    @Override
    public int compare(Dynamic p1, Dynamic p2) {
        return new Byte(p1.value).compareTo(new Byte(p2.value));
    }

    /**
     * Checks if this base.properties.Dynamic equals another Object.
     * @param o The other Object.
     * @return If this base.properties.Dynamic is equal to the Object.
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
     * @return The hash code for this base.properties.Dynamic.
     */
    @Override
    public int hashCode() {
        return (int) value;
    }

    /**
     * Returns a nicely-formatted string representing this dynamic level.
     * @return A string representing this dynamic level (and its approximate music notation)
     */
    @Override
    public String toString() {
        if(value == 0)  return value + " (n)";
        if(value < 16)  return value + " (ppp)";
        if(value < 32)  return value + " (pp)";
        if(value < 48)  return value + " (p)";
        if(value < 64)  return value + " (mp)";
        if(value < 80)  return value + " (mf)";
        if(value < 96)  return value + " (f)";
        if(value < 112) return value + " (ff)";
        return value + " (fff)";
    }
}