package model.frameEvents;

import java.util.Comparator;

/**
 * Pitch is a glorified byte wrapper,
 * which allows for a little more dress
 * and prevents problems down the line
 * with arguments to constructors.
 */
public class Pitch implements Comparator<Pitch>, Comparable<Pitch> {

    /* Useful variable bounds */
    public static int MIN_PITCH = 0;
    public static int MAX_PITCH = 120;
    public static int MIN_OCTAVE = -1;
    public static int MAX_OCTAVE = 9;

    /**
     * Stores the MIDI value of this Pitch.
     */
    private final byte value;

    /**
     * Constructor taking in a MIDI value.
     * @param value The pitch's MIDI value.
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
     * Compares this Pitch to another Pitch.
     * @param other The other Pitch.
     * @return The comparison.
     */
    @Override
    public int compareTo(Pitch other) {
        return Byte.compare(value,other.value);
    }

    /**
     * Compares two Pitches.
     * @param p1 The first Pitch.
     * @param p2 The second Pitch.
     * @return The comparison.
     */
    @Override
    public int compare(Pitch p1, Pitch p2) {
        return Byte.compare(p1.value,p2.value);
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
