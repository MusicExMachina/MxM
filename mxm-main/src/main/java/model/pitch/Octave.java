package model.pitch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by celenp on 10/27/2016.
 */
public class Octave {

    /** The lowest possible */
    private static final int MIN_VALUE  = -1;
    /** */
    private static final int MAX_VALUE  = 9;

    /* A bunch of preset Pitches for general use. */
    private static final ArrayList<Octave> ALL = new ArrayList<Octave>();

    private final int value;

    // Initialize all pitches
    static {
        for(int octaveValue = MIN_VALUE; octaveValue < MAX_VALUE; octaveValue++) {
            ALL.add(new Octave(octaveValue));
        }
    }

    ///////////////////////////
    // Public static methods //
    ///////////////////////////

    /**
     * Gets an iterator which enumerates all possible octaves.
     * @return An iterator over all valid octaves.
     */
    public static Iterator<Octave> iterator() {
        return ALL.iterator();
    }

    ////////////////////////////
    // Private member methods //
    ////////////////////////////

    /**
     * A private constructor for Octave that initializes
     * Octaves' internal integer value.
     * @param value The number of this Octave.
     */
    private Octave(int value) {
        this.value = value;
    }

    ////////////////////////////
    // Private member methods //
    ////////////////////////////

    /**
     * Returns a normalized representation of this octave,
     *
     */
    private float normalized() {
        return (float)(value-MIN_VALUE)/(float)(MAX_VALUE-MIN_VALUE);
    }
}
