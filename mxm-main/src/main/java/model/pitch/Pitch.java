package model.pitch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Pitch is a simple class which utilizes the interning design pattern to create only one
 * hundred twenty different values- all possible MIDI pitches. Pitches are usually found on
 * Notes, though they may be used in Collections. Note that there should never be more than
 * these 120 Pitches, and that an iterator() has been provided for easy access.
 */
public class Pitch implements Comparator<Pitch>, Comparable<Pitch> {

    //////////////////////////////
    // Private static variables //
    //////////////////////////////

    /** The minimum midi value of pitch, C-1. */
    private static final int MIN_PITCH = 0;

    /** The maximum midi value of pitch, B9. */
    private static final int MAX_PITCH = 120;

    /** An ArrayList of all valid Pitches */
    private static final ArrayList<Pitch> ALL = new ArrayList<Pitch>();

    // Initialize all pitches
    static {
        for(int midiValue = MIN_PITCH; midiValue < MAX_PITCH; midiValue++) {
            ALL.add(new Pitch(midiValue));
        }
    }

    /**
     * Gets an iterator which enumerates all valid Pitches.
     * @return An iterator over all valid Pitches.
     */
    public static Iterator<Pitch> iterator() {
        return ALL.iterator();
    }

    /**
     * Gets an instance of a given Pitch size. This method
     * creates the interning design pattern per Pitch.
     * @param value The (midi) value of this Pitch
     * @return An Pitch of this size.
     */
    public static Pitch getInstance(int value) {
        if(value >= MIN_PITCH&& value < MAX_PITCH) {
            return ALL.get(value - MIN_PITCH);
        }
        else {
            throw new Error("INTERVAL:\tInterval out of range.");
        }
    }

    /** The PitchClass of this Pitch. */
    private PitchClass pitchClass;
    
    /** The midi value of this Pitch. */
    private int value;

    private Pitch(int value) {
        this.value = value;
        this.pitchClass = PitchClass.getInstance(value%12);
    }

    /**
     * Gets the PitchClass of this Pitch.
     * @return The PitchClass of this Pitch.
     */
    public PitchClass getPitchClass() {
        return pitchClass;
    }

    /**
     * Transposes a Pitch by a given interval.
     * @param interval The Interval to transpose by.
     * @return The new, resulting Pitch.
     */
    public Pitch transpose(Interval interval) {
        return new Pitch(value + interval.getSize());
    }

    /**
     * Gets the Interval between this Pitch and another.
     * @param other The other Pitch to subtract from this one.
     * @return THe Interval between this Pitch and another.
     */
    public Interval minus(Pitch other) {
        return Interval.getInstance(other.value - this.value);
    }

    /**
     * Normalizes this PitchClass between 0 and 1.
     * @return This PitchClass in the range [0,1).
     */
    public float normalized() {
        return (float)(value - MIN_PITCH)/(float)(MAX_PITCH - MIN_PITCH);
    }

    /**
     * Returns a nicely-formatted String
     * of this Pitch (for debug).
     * @return A nicely-formatted String of this Pitch.
     */
    public String toString() {
        return pitchClass.toString() + (value/12 - 1);
    }

    /**
     * Compares this Pitch to another Pitch.
     * @param other The other Pitch.
     * @return The comparison.
     */
    @Override
    public int compareTo(Pitch other) {
        return new Integer(value).compareTo(new Integer(other.value));
    }

    /**
     * Compares two Pitches.
     * @param p1 The first Pitch.
     * @param p2 The second Pitch.
     * @return The comparison.
     */
    @Override
    public int compare(Pitch p1, Pitch p2) {
        return new Integer(p1.value).compareTo(new Integer(p2.value));
    }

    /**
     * Checks if this Pitch equals another Object.
     * @param o The other Object.
     * @return If this Pitch is equal to the Object.
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    /**
     * A simple hash code for storage of Pitches in special Collections.
     * @return The hash code for this Pitch.
     */
    @Override
    public int hashCode() {
        return (int) value;
    }
}