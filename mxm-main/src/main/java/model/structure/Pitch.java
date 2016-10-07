package model.structure;

import java.util.Comparator;

/**
 * Pitch is a glorified byte wrapper,
 * which allows for a little more dress
 * and prevents problems down the line
 * with arguments to constructors.
 */
public class Pitch implements Comparator<Pitch>, Comparable<Pitch> {

    /* A bunch of preset Pitches for general use. */
    public static final Pitch C_FLAT_4    = new Pitch(59);
    public static final Pitch C_NATURAL_4 = new Pitch(60);
    public static final Pitch C_SHARP_4   = new Pitch(61);
    public static final Pitch D_FLAT_4    = new Pitch(61);
    public static final Pitch D_NATURAL_4 = new Pitch(62);
    public static final Pitch D_SHARP_4   = new Pitch(63);
    public static final Pitch E_FLAT_4    = new Pitch(63);
    public static final Pitch E_NATURAL_4 = new Pitch(64);
    public static final Pitch E_SHARP_4   = new Pitch(65);
    public static final Pitch F_FLAT_4    = new Pitch(64);
    public static final Pitch F_NATURAL_4 = new Pitch(65);
    public static final Pitch F_SHARP_4   = new Pitch(66);
    public static final Pitch G_FLAT_4    = new Pitch(66);
    public static final Pitch G_NATURAL_4 = new Pitch(67);
    public static final Pitch G_SHARP_4   = new Pitch(68);
    public static final Pitch A_FLAT_4    = new Pitch(68);
    public static final Pitch A_NATURAL_4 = new Pitch(69);
    public static final Pitch A_SHARP_4   = new Pitch(70);
    public static final Pitch B_FLAT_4    = new Pitch(70);
    public static final Pitch B_NATURAL_4 = new Pitch(71);
    public static final Pitch B_SHARP_4   = new Pitch(72);

    /* Useful variable bounds */
    public static final int MIN_PITCHCLASS  = 0;
    public static final int MAX_PITCHCLASS  = 11;
    public static final int MIN_OCTAVE      = -1;
    public static final int MAX_OCTAVE      = 9;
    public static final int MIN_PITCH       = 0;
    public static final int MAX_PITCH       = 120;

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
