package base.sound;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Pitch class uses the interning design pattern to represent twelve different values: C, C#, D, D#, E, F, G, G#, A, A#,
 * and B. These are usually found on a sound. Take note that there should never be more than these 12 PitchClasses, and
 * that an iterator() has been provided for easy access.
 */
public class PitchClass implements Comparator<PitchClass>, Comparable<PitchClass> {

    /** The lowest basic class, better known as "C." */
    private static final int MIN_PITCHCLASS = 0;

    /** The highest basic class, better known as "B." */
    private static final int MAX_PITCHCLASS = 11;

    // A number of public static pre-defined sound classes,
    // representing all possible sound classes using their
    // standard music theory names. Note enharmonic classes.
    // TODO: C_FLAT might not work nicely with octave assumptions
    // TODO: B_SHARP might not as well. Ill-defined goals here
    public static PitchClass C_FLAT     = getInstance(11);
    public static PitchClass C_NATURAL  = getInstance(0);
    public static PitchClass C_SHARP    = getInstance(1);
    public static PitchClass D_FLAT     = getInstance(1);
    public static PitchClass D_NATURAL  = getInstance(2);
    public static PitchClass D_SHARP    = getInstance(3);
    public static PitchClass E_FLAT     = getInstance(3);
    public static PitchClass E_NATURAL  = getInstance(4);
    public static PitchClass E_SHARP    = getInstance(5);
    public static PitchClass F_FLAT     = getInstance(4);
    public static PitchClass F_NATURAL  = getInstance(5);
    public static PitchClass F_SHARP    = getInstance(6);
    public static PitchClass G_FLAT     = getInstance(6);
    public static PitchClass G_NATURAL  = getInstance(7);
    public static PitchClass G_SHARP    = getInstance(8);
    public static PitchClass A_FLAT     = getInstance(8);
    public static PitchClass A_NATURAL  = getInstance(9);
    public static PitchClass A_SHARP    = getInstance(10);
    public static PitchClass B_FLAT     = getInstance(10);
    public static PitchClass B_NATURAL  = getInstance(11);
    public static PitchClass B_SHARP    = getInstance(0);

    /** All possible PitchClasses */
    private static final ArrayList<PitchClass> ALL = new ArrayList<>();

    // Initialize the "ALL" collection
    static {
        for(int pitchClassValue = MIN_PITCHCLASS; pitchClassValue <= MAX_PITCHCLASS; pitchClassValue++) {
            ALL.add(new PitchClass(pitchClassValue));
        }
    }

    /**
     * Gets an iterator which enumerates all valid PitchClasses.
     * @return An iterator over all valid Pitches.
     */
    public static Iterator<PitchClass> iterator() {
        return ALL.iterator();
    }

    /**
     * Gets an instance of a given base.sound.PitchClass size. This method
     * creates the interning design pattern per base.sound.PitchClass.
     * @param value The value of this base.sound.PitchClass.
     * @return An base.sound.PitchClass of this value.
     */
    public static PitchClass getInstance(int value) {
        if(value >= MIN_PITCHCLASS && value <= MAX_PITCHCLASS) {
            return ALL.get(value);
        }
        else {
            throw new Error("PITCH CLASS:\tbase.sound.Pitch class out of range.");
        }
    }

    /** Stores the midi value of this base.sound.Pitch. */
    private int value;

    /**
     * The private constructor for base.sound.PitchClass.
     * @param value The value of this base.sound.PitchClass.
     */
    private PitchClass(int value) {
        this.value = (byte)value;
    }

    /**
     * Transposes a base.sound.PitchClass by a given interval.
     * @param intervalClass The base.sound.Interval to transpose by.
     * @return The new, resulting base.sound.PitchClass.
     */
    public PitchClass transpose(IntervalClass intervalClass) {
        return PitchClass.getInstance((value + intervalClass.getSize() % 12));
    }

    /**
     * Gets the base.sound.Interval between this base.sound.PitchClass and another.
     * @param other The other base.sound.Pitch to subtract from this one.
     * @return THe base.sound.Interval between this base.sound.Pitch and another.
     */
    public PitchClass minus(PitchClass other) {
        return new PitchClass(other.value - this.value);
    }

    /**
     * Normalizes this base.sound.PitchClass between 0 and 1.
     * @return This base.sound.PitchClass in the range [0,1).
     */
    public float normalized() {
        return (float)(value - MIN_PITCHCLASS)/(float)(MAX_PITCHCLASS - MIN_PITCHCLASS);
    }

    /**
     * Returns a nicely-formatted String of this base.sound.PitchClass (for debug).
     * @return A nicely-formatted String of this base.sound.PitchClass.
     */
    public String toString() {
        switch (value) {
            case 0:     return "C";     case 1:     return "Db";
            case 2:     return "D";     case 3:     return "Eb";
            case 4:     return "E";     case 5:     return "F";
            case 6:     return "Gb";    case 7:     return "G";
            case 8:     return "Ab";    case 9:     return "A";
            case 10:    return "Bb";    case 11:    return "B";
            default:    return "ERROR";
        }
    }

    /**
     * Compares this base.sound.PitchClass to another base.sound.PitchClass.
     * @param other The other base.sound.PitchClass.
     * @return The comparison.
     */
    @Override
    public int compareTo(PitchClass other) {
        return (new Integer(value)).compareTo(other.value);
    }

    /**
     * Compares two PitchClasses.
     * @param pc1 The first base.sound.PitchClass.
     * @param pc2 The second base.sound.PitchClass.
     * @return The comparison.
     */
    @Override
    public int compare(PitchClass pc1, PitchClass pc2) {
        return new Integer(pc1.value).compareTo(pc2.value);
    }

    /**
     * Checks if this base.sound.PitchClass equals another Object.
     * @param o The other Object.
     * @return If this base.sound.PitchClass is equal to the Object.
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    /**
     * A simple hash code for storage of PitchClasses in special Collections.
     * @return The hash code for this base.sound.PitchClass.
     */
    @Override
    public int hashCode() {
        return value;
    }
}
