package base;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * base.Pitch is a simple class which utilizes the interning design pattern to create only one
 * hundred twenty different values- all possible MIDI pitches. Pitches are usually found on
 * Notes, though they may be used in Collections. events.Note that there should never be more than
 * these 120 Pitches, and that an iterator() has been provided for easy access.
 */
public class Pitch implements Comparator<Pitch>, Comparable<Pitch> {

    //////////////////////////////
    // Private static variables //
    //////////////////////////////

    /** The minimum midi value of basic, C-1. */
    private static final int MIN_PITCH = 0;

    /** The maximum midi value of basic, B9. */
    private static final int MAX_PITCH = 120;

    /** An ArrayList of all valid Pitches */
    private static final ArrayList<Pitch> ALL = new ArrayList<Pitch>();

    // Initialize all pitches
    static {
        for(int midiValue = MIN_PITCH; midiValue <= MAX_PITCH; midiValue++) {
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
     * Gets an instance of a given base.Pitch size. This method
     * creates the interning design pattern per base.Pitch.
     * @param value The (midi) value of this base.Pitch
     * @return An base.Pitch of this size.
     */
    public static Pitch getInstance(int value) {
        if(value >= MIN_PITCH&& value <= MAX_PITCH) {
            return ALL.get(value - MIN_PITCH);
        }
        else {
            throw new Error("INTERVAL:\tbase.Interval out of range.");
        }
    }

    /** The base.PitchClass of this base.Pitch. */
    private PitchClass pitchClass;
    
    /** The midi value of this base.Pitch. */
    private int value;

    private Pitch(int value) {
        this.value = value;
        this.pitchClass = PitchClass.getInstance(value%12);
    }

    /**
     * Gets the base.PitchClass of this base.Pitch.
     * @return The base.PitchClass of this base.Pitch.
     */
    public PitchClass getPitchClass() {
        return pitchClass;
    }

    /**
     * Transposes a base.Pitch by a given interval.
     * @param interval The base.Interval to transpose by.
     * @return The new, resulting base.Pitch.
     */
    public Pitch transpose(Interval interval) {
        return new Pitch(value + interval.getSize());
    }

    /**
     * Gets the base.Interval between this base.Pitch and another.
     * @param other The other base.Pitch to subtract from this one.
     * @return THe base.Interval between this base.Pitch and another.
     */
    public Interval minus(Pitch other) {
        return Interval.getInstance(other.value - this.value);
    }

    public int getValue() {
        return value;
    }

    /**
     * Normalizes this base.PitchClass between 0 and 1.
     * @return This base.PitchClass in the range [0,1).
     */
    public float normalized() {
        return (float)(value - MIN_PITCH)/(float)(MAX_PITCH - MIN_PITCH);
    }

    /**
     * Returns a nicely-formatted String
     * of this base.Pitch (for debug).
     * @return A nicely-formatted String of this base.Pitch.
     */
    public String toString() {
        return pitchClass.toString() + (value/12 - 1);
    }

    /**
     * Compares this base.Pitch to another base.Pitch.
     * @param other The other base.Pitch.
     * @return The comparison.
     */
    @Override
    public int compareTo(Pitch other) {
        return new Integer(value).compareTo(new Integer(other.value));
    }

    /**
     * Compares two Pitches.
     * @param p1 The first base.Pitch.
     * @param p2 The second base.Pitch.
     * @return The comparison.
     */
    @Override
    public int compare(Pitch p1, Pitch p2) {
        return new Integer(p1.value).compareTo(new Integer(p2.value));
    }

    /**
     * Checks if this base.Pitch equals another Object.
     * @param o The other Object.
     * @return If this base.Pitch is equal to the Object.
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    /**
     * A simple hash code for storage of Pitches in special Collections.
     * @return The hash code for this base.Pitch.
     */
    @Override
    public int hashCode() {
        return (int) value;
    }
}