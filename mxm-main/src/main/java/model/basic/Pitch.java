package model.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * Pitch is a glorified byte wrapper,
 * which allows for a little more dress
 * and prevents problems down the line
 * with arguments to constructors.
 */
public class Pitch implements Comparator<Pitch>, Comparable<Pitch> {

    /* Useful variable bounds */
    private static final int MIN_PITCHCLASS  = 0;
    private static final int MAX_PITCHCLASS  = 11;
    private static final int MIN_OCTAVE      = -1;
    private static final int MAX_OCTAVE      = 9;
    private static final int MIN_PITCH       = 0;
    private static final int MAX_PITCH       = 120;

    /* Pitch classes */
    private static final int C_NATURAL  = 0;
    private static final int D_FLAT     = 1;
    private static final int D_NATURAL  = 2;
    private static final int E_FLAT     = 3;
    private static final int E_NATURAL  = 4;
    private static final int F_NATURAL  = 5;
    private static final int G_FLAT     = 6;
    private static final int G_NATURAL  = 7;
    private static final int A_FLAT     = 8;
    private static final int A_NATURAL  = 9;
    private static final int B_FLAT     = 10;
    private static final int B_NATURAL  = 11;

    /* A bunch of preset Pitches for general use. */
    public static final Collection<Pitch> ALL_PITCHES;

    // Initialize all pitches
    static {
        ALL_PITCHES = new ArrayList<Pitch>();
        for(int midiValue = MIN_PITCH; midiValue < MAX_PITCH; midiValue++) {
            ALL_PITCHES.add(new Pitch(midiValue));
        }
    }

    /**
     * Stores the MidiTools value of this Pitch.
     */
    private final byte value;

    /**
     * Constructor taking in a MidiTools value.
     * @param value The pitch's MidiTools value.
     */
    public Pitch(int value) {
        if(value >= MIN_PITCH && value <= MAX_PITCH) {
            this.value = (byte)value;
        }
        else {
            throw new Error("Invalid pitch range!");
        }
    }

    /**
     * Copy constructor for Pitch.
     * @param other The other Pitch.
     */
    public Pitch(Pitch other) {
        this.value = other.value;
    }

    /**
     * Gets the pitch class of this Pitch.
     * @return The pitch class of this Pitch.
     */
    public int getPitchClass() {
        return value % 12;
    }

    /**
     * Gets the octave of this Pitch.
     * @return The octave of this Pitch.
     */
    public int getOctave() {
        return value/12 + MIN_OCTAVE;
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
        return new Interval(other.value - this.value);
    }


    /**
     * Returns a nicely-formatted String
     * of this Pitch (for debug).
     * @return A nicely-formatted String of this Pitch.
     */
    public String toString() {
        switch (getPitchClass()) {
            case C_NATURAL:     return "C"  + getOctave();
            case D_FLAT:        return "Db" + getOctave();
            case D_NATURAL:     return "D"  + getOctave();
            case E_FLAT:        return "Eb" + getOctave();
            case E_NATURAL:     return "E"  + getOctave();
            case F_NATURAL:     return "F"  + getOctave();
            case G_FLAT:        return "Gb" + getOctave();
            case G_NATURAL:     return "G"  + getOctave();
            case A_FLAT:        return "Ab" + getOctave();
            case A_NATURAL:     return "A"  + getOctave();
            case B_FLAT:        return "Bb" + getOctave();
            case B_NATURAL:     return "B"  + getOctave();
            default:            return "ERROR";
        }
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch = (Pitch) o;
        return value == pitch.value;
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
