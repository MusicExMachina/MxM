package base.properties;

import base.AbstractIntegerProp;
import base.INoteProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The dynamic class is a glorified byte wrapper, which allows for a little more dress and avoiding confusion down the
 * line. Note that we still use the MIDI standard's definition of loudness, just as we use midi note values.
 *
 *
 *
 * TODO: this comment
 */
public class Dynamic extends AbstractIntegerProp implements INoteProperty, Comparator<Dynamic>, Comparable<Dynamic> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    /** The highest value of dynamic */
    private final static int MIN_VALUE = 0;
    /** The lowest value of dynamic */
    private final static int MAX_VALUE = 127;

    /** Niente, or silent */
    public static final Dynamic NIENTE = get(0);
    /** Pianississimo, or very, very quiet */
    public static final Dynamic PIANISSISSIMO = get(16);
    /** Pianissimo, or very quiet */
    public static final Dynamic PIANISSIMO = get(32);
    /** Piano, or quiet */
    public static final Dynamic PIANO = get(48);
    /** Mezzo piano, or slightly quiet */
    public static final Dynamic MEZZO_PIANO = get(64);
    /** Mezzo forte, or slightly loud */
    public static final Dynamic MEZZO_FORTE = get(80);
    /** Forte, or loud */
    public static final Dynamic FORTE = get(96);
    /** Fortissimo, or very loud */
    public static final Dynamic FORTISSIMO = get(112);
    /** Fortississimo, or very, very loud */
    public static final Dynamic FORTISSISSIMO = get(127);

    /** The quietest possible dynamic*/
    public static final Dynamic MIN = get(MIN_VALUE);
    /** The loudest possible dynamic */
    public static final Dynamic MAX = get(MAX_VALUE);

    //////////////////////////////
    // Static Methods           //
    //////////////////////////////

    /**
     * Gets an instance of a given dynamic
     * @param value The value of this dynamic
     * @return An sound of this dynamic
     */
    public static @NotNull Dynamic get(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE)
            throw new Error("PITCH:\tInterval out of range.");

        return new Dynamic(value);
    }
    /**
     * Returns a random instance of this class
     * @return a random valid Dynamic
     */
    public static @NotNull Dynamic random() {
        return get(ThreadLocalRandom.current().nextInt(MIN_VALUE, MAX_VALUE + 1));
    }

    //////////////////////////////
    // Member Variables         //
    //////////////////////////////

    /**
     * Constructor taking in a loudness value.
     * @param value The basic's loudness value.
     */
    private Dynamic(int value) {
        super(value);
    }
    /**
     * Compares this base.properties.Dynamic to another base.properties.Dynamic.
     * @param other The other base.properties.Dynamic.
     * @return The comparison.
     */
    @Override
    public int compareTo(@NotNull Dynamic other) {
        return Integer.compare(value, other.value);
    }
    /**
     * Compares two Dynamics.
     * @param p1 The first base.properties.Dynamic.
     * @param p2 The second base.properties.Dynamic.
     * @return The comparison.
     */
    @Override
    public int compare(Dynamic p1, Dynamic p2) {
        return Integer.compare(p1.value, p2.value);
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
}