package sound;

import musicTheory.Interval;
import musicTheory.PitchClass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * sound.Pitch is a simple class which utilizes the interning design pattern to create only one
 * hundred twenty different values- all possible MIDI pitches. Pitches are usually found on
 * Notes, though they may be used in Collections. events.sounding.Note that there should never be more than
 * these 120 Pitches, and that an iterator() has been provided for easy access.
 */
public class Pitch implements Sound, Comparator<Pitch>, Comparable<Pitch> {

    //////////////////////////////
    // Private static variables //
    //////////////////////////////

    /** The minimum midi sound value, C-1. */
    private static final int MIN_PITCH = 0;

    /** The maximum midi sound value, B9. */
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
     * Gets an instance of a given sound.Pitch size. This method
     * creates the interning design pattern per sound.Pitch.
     * @param value The (midi) value of this sound.Pitch
     * @return An sound.Pitch of this size.
     */
    public static Pitch getInstance(int value) {
        if(value >= MIN_PITCH&& value <= MAX_PITCH) {
            return ALL.get(value - MIN_PITCH);
        }
        else {
            throw new Error("INTERVAL:\tmusicTheory.Interval out of range.");
        }
    }

    /**
     * Gets an instance of a given sound.Pitch size. This method
     * creates the interning design pattern per sound.Pitch.
     * @param pitchClass The pitch class of this pitch (C, Ab, F#)
     * @param octave The octave of this pitch
     * @return An sound.Pitch of this size.
     */
    public static Pitch getInstance(PitchClass pitchClass, int octave) {
        int value = pitchClass.getValue() + (octave+1)*12;
        if(value >= MIN_PITCH&& value <= MAX_PITCH) {
            return ALL.get(value - MIN_PITCH);
        }
        else {
            throw new Error("INTERVAL:\tmusicTheory.Interval out of range.");
        }
    }

    /** The sound class of this sound. */
    private PitchClass pitchClass;
    
    /** The midi value of this sound. */
    private int value;

    /**
     * The sound
     * @param value
     */
    private Pitch(int value) {
        this.value = value;
        this.pitchClass = PitchClass.getInstance(value%12);
    }

    /**
     * Gets the musicTheory.PitchClass of this sound.Pitch.
     * @return The musicTheory.PitchClass of this sound.Pitch.
     */
    public PitchClass getPitchClass() {
        return pitchClass;
    }

    /**
     * Transposes a sound.Pitch by a given interval.
     * @param interval The musicTheory.Interval to transpose by.
     * @return The new, resulting sound.Pitch.
     */
    public Pitch transpose(Interval interval) {
        return new Pitch(value + interval.getSize());
    }

    /**
     * Gets the musicTheory.Interval between this sound.Pitch and another.
     * @param other The other sound.Pitch to subtract from this one.
     * @return THe musicTheory.Interval between this sound.Pitch and another.
     */
    public Interval minus(Pitch other) {
        return Interval.getInstance(other.value - this.value);
    }

    public int getValue() {
        return value;
    }

    /**
     * Normalizes this musicTheory.PitchClass between 0 and 1.
     * @return This musicTheory.PitchClass in the range [0,1).
     */
    public float normalized() {
        return (float)(value - MIN_PITCH)/(float)(MAX_PITCH - MIN_PITCH);
    }

    /**
     * Returns a nicely-formatted String
     * of this sound.Pitch (for debug).
     * @return A nicely-formatted String of this sound.Pitch.
     */
    public String toString() {
        return pitchClass.toString() + (value/12 - 1);
    }

    /**
     * Compares this sound.Pitch to another sound.Pitch.
     * @param other The other sound.Pitch.
     * @return The comparison.
     */
    @Override
    public int compareTo(Pitch other) {
        return new Integer(value).compareTo(new Integer(other.value));
    }

    /**
     * Compares two Pitches.
     * @param p1 The first sound.Pitch.
     * @param p2 The second sound.Pitch.
     * @return The comparison.
     */
    @Override
    public int compare(Pitch p1, Pitch p2) {
        return new Integer(p1.value).compareTo(new Integer(p2.value));
    }

    /**
     * Checks if this sound.Pitch equals another Object.
     * @param o The other Object.
     * @return If this sound.Pitch is equal to the Object.
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    /**
     * A simple hash code for storage of Pitches in special Collections.
     * @return The hash code for this sound.Pitch.
     */
    @Override
    public int hashCode() {
        return (int) value;
    }
}