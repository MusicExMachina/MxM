package base.properties;

import base.ISound;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements the <b>flyweight design pattern</b>- there is exactly one instance for
 * each value such that two ADTs (Abstract Data Types) with the same value are, in fact, the same instance. This
 * simplifies equality checks and can prevent memory waste. Unlike the <b>interning design pattern</b>, all possible
 * instances are created upfront during static initialization.
 *
 * @author Patrick Celentano
 */
public class Noise implements ISound {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    /** A "hit" noise */
    public static final Noise HIT = get("hit");
    /** A "crash" noise */
    public static final Noise CRASH = get("crash");

    /** A map of noise names to the noises themselves */
    private static final HashMap<String,Noise> ALL = new HashMap<>();

    //////////////////////////////
    // Static Methods           //
    //////////////////////////////

    /**
     * Gets an iterator which enumerates all valid noises.
     * @return An iterator over all valid noises
     */
    public static Iterator<Noise> allItr() {
        return ALL.values().iterator();
    }
    /**
     * Gets an instance of a given noise. This method creates the interning design pattern per noise.
     * @param name The name of this noise
     * @return A noise of this name
     */
    public static Noise get(@NotNull String name) {
        // Standardize
        name = name.toLowerCase();
        // If the noise already exists, return that one
        if (ALL.containsKey(name)) {
            return ALL.get(name);
        }
        // If the noise has not already been created, create it now
        Noise newNoise = new Noise(name);
        ALL.put(name,newNoise);
        return newNoise;
    }

    //////////////////////////////
    // Member Variables         //
    //////////////////////////////

    /** The name of this noise */
    private final String name;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * The noise constructor, which is private to enforce the interning design pattern (one instance per value).
     * @param value The (midi) value of this pitch
     */
    private Noise(String value) {
        this.name = value;
    }
    /**
     * Gets the name of this noise.
     * @return The name of this noise
     */
    public final String getName() {
        return name;
    }
    /**
     * Returns a string representation of this noise
     * @return A string representation of this noise
     */
    public final @NotNull String toString() {
        return name;
    }
    /**
     * Checks if this noise is equal to another object. Note that since the interning pattern is used, literal
     * (reference) equality is enough to ensure that these objects are actually equal.
     * @return if this noise is equal to another
     */
    @Override
    public final boolean equals(Object object) {
        return this == object;
    }
    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this noise
     */
    @Override
    public final int hashCode() {
        return name.hashCode();
    }
}