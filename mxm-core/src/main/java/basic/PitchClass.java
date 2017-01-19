package basic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * PitchClass is a simple class which utilizes the interning design pattern to create only
 * twelve different values: C, C#, D, D#, E, F, G, G#, A, A#, and B. These are usually found
 * on a Pitch, though may be used in Collections. Note that there should never be more than
 * these 12 PitchClasses, and that an iterator() has been provided for easy access.
 */
public class PitchClass implements Comparator<PitchClass>, Comparable<PitchClass> {

    /** The lowest basic class, better known as "C." */
    private static final int MIN_PITCHCLASS = 0;

    /** The highest basic class, better known as "B." */
    private static final int MAX_PITCHCLASS = 11;

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
     * Gets an instance of a given PitchClass size. This method
     * creates the interning design pattern per PitchClass.
     * @param value The value of this PitchClass.
     * @return An PitchClass of this value.
     */
    public static PitchClass getInstance(int value) {
        if(value >= MIN_PITCHCLASS && value <= MAX_PITCHCLASS) {
            return ALL.get(value);
        }
        else {
            throw new Error("PITCH CLASS:\tPitch class out of range.");
        }
    }

    /** Stores the midi value of this Pitch. */
    private int value;

    /**
     * The private constructor for PitchClass.
     * @param value The value of this PitchClass.
     */
    private PitchClass(int value) {
        this.value = (byte)value;
    }

    /**
     * Transposes a PitchClass by a given interval.
     * @param intervalClass The Interval to transpose by.
     * @return The new, resulting PitchClass.
     */
    public PitchClass transpose(IntervalClass intervalClass) {
        return PitchClass.getInstance((value + intervalClass.getSize() % 12));
    }

    /**
     * Gets the Interval between this PitchClass and another.
     * @param other The other Pitch to subtract from this one.
     * @return THe Interval between this Pitch and another.
     */
    public PitchClass minus(PitchClass other) {
        return new PitchClass(other.value - this.value);
    }

    /**
     * Normalizes this PitchClass between 0 and 1.
     * @return This PitchClass in the range [0,1).
     */
    public float normalized() {
        return (float)(value - MIN_PITCHCLASS)/(float)(MAX_PITCHCLASS - MIN_PITCHCLASS);
    }

    /**
     * Returns a nicely-formatted String of this PitchClass (for debug).
     * @return A nicely-formatted String of this PitchClass.
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
     * Compares this PitchClass to another PitchClass.
     * @param other The other PitchClass.
     * @return The comparison.
     */
    @Override
    public int compareTo(PitchClass other) {
        return (new Integer(value)).compareTo(other.value);
    }

    /**
     * Compares two PitchClasses.
     * @param pc1 The first PitchClass.
     * @param pc2 The second PitchClass.
     * @return The comparison.
     */
    @Override
    public int compare(PitchClass pc1, PitchClass pc2) {
        return new Integer(pc1.value).compareTo(pc2.value);
    }

    /**
     * Checks if this PitchClass equals another Object.
     * @param o The other Object.
     * @return If this PitchClass is equal to the Object.
     */
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    /**
     * A simple hash code for storage of PitchClasses in special Collections.
     * @return The hash code for this PitchClass.
     */
    @Override
    public int hashCode() {
        return value;
    }
}
