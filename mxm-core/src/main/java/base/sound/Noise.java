package base.sound;

import base.AbstractStringProp;
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
public final class Noise extends AbstractStringProp implements ISoundProperty {

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
    // Member methods           //
    //////////////////////////////

    /**
     * The noise constructor, which is private to enforce the interning design pattern (one instance per value).
     * @param name The name of this noise
     */
    private Noise(String name) {
        super(name);
    }
    /**
     * Gets the name of this noise.
     * @return The name of this noise
     */
    public final @NotNull String getName() {
        return getValue();
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
}