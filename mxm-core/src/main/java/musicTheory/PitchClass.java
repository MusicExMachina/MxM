package musicTheory;

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
    /*
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
    */

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
     * Gets an instance of a given musicTheory.PitchClass size. This method
     * creates the interning design pattern per musicTheory.PitchClass.
     * @param value The value of this musicTheory.PitchClass.
     * @return An musicTheory.PitchClass of this value.
     */
    public static PitchClass getInstance(int value) {
        if(value >= MIN_PITCHCLASS && value <= MAX_PITCHCLASS) {
            return ALL.get(value);
        }
        else {
            throw new Error("PITCH CLASS:\tsound.Pitch class out of range.");
        }
    }

    /** Stores the midi value of this sound.Pitch. */
    private int value;

    /**
     * The private constructor for musicTheory.PitchClass.
     * @param value The value of this musicTheory.PitchClass.
     */
    private PitchClass(int value) {
        this.value = (byte)value;
    }

    /**
     * Transposes a musicTheory.PitchClass by a given interval.
     * @param intervalClass The musicTheory.Interval to transpose by.
     * @return The new, resulting musicTheory.PitchClass.
     */
    public PitchClass transpose(IntervalClass intervalClass) {
        return PitchClass.getInstance((value + intervalClass.getSize() % 12));
    }

    /**
     * Gets the musicTheory.Interval between this musicTheory.PitchClass and another.
     * @param other The other sound.Pitch to subtract from this one.
     * @return THe musicTheory.Interval between this sound.Pitch and another.
     */
    public PitchClass minus(PitchClass other) {
        return new PitchClass(other.value - this.value);
    }

    /**
     * Normalizes this musicTheory.PitchClass between 0 and 1.
     * @return This musicTheory.PitchClass in the range [0,1).
     */
    public float normalized() {
        return (float)(value - MIN_PITCHCLASS)/(float)(MAX_PITCHCLASS - MIN_PITCHCLASS);
    }

    /**
     * Returns a nicely-formatted String of this musicTheory.PitchClass (for debug).
     * @return A nicely-formatted String of this musicTheory.PitchClass.
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

    public int getValue() {
        return value;
    }

    /**
     * Compares this musicTheory.PitchClass to another musicTheory.PitchClass.
     * @param other The other musicTheory.PitchClass.
     * @return The comparison.
     */
    @Override
    public int compareTo(PitchClass other) {
        return (new Integer(value)).compareTo(other.value);
    }

    /**
     * Compares two PitchClasses.
     * @param pc1 The first musicTheory.PitchClass.
     * @param pc2 The second musicTheory.PitchClass.
     * @return The comparison.
     */
    @Override
    public int compare(PitchClass pc1, PitchClass pc2) {
        return new Integer(pc1.value).compareTo(pc2.value);
    }

    /**
     * Checks if this musicTheory.PitchClass equals another Object.
     * @param o The other Object.
     * @return If this musicTheory.PitchClass is equal to the Object.
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    /**
     * A simple hash code for storage of PitchClasses in special Collections.
     * @return The hash code for this musicTheory.PitchClass.
     */
    @Override
    public int hashCode() {
        return value;
    }
}
