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
public class PitchClass implements Comparator<PitchClass>, Comparable<PitchClass> {

    /////////////////////////////
    // Public static variables //
    /////////////////////////////

    /** The PitchClass of C*/
    private static final PitchClass C_NATURAL  = new PitchClass(0);
    /** The PitchClass of Db */
    private static final PitchClass D_FLAT     = new PitchClass(1);
    /** The PitchClass of D */
    private static final PitchClass D_NATURAL  = new PitchClass(2);
    /** The PitchClass of Eb */
    private static final PitchClass E_FLAT     = new PitchClass(3);
    /** The PitchClass of E */
    private static final PitchClass E_NATURAL  = new PitchClass(4);
    /** The PitchClass of F */
    private static final PitchClass F_NATURAL  = new PitchClass(5);
    /** The PitchClass of Gb */
    private static final PitchClass G_FLAT     = new PitchClass(6);
    /** The PitchClass of G */
    private static final PitchClass G_NATURAL  = new PitchClass(7);
    /** The PitchClass of Ab */
    private static final PitchClass A_FLAT     = new PitchClass(8);
    /** The PitchClass of A */
    private static final PitchClass A_NATURAL  = new PitchClass(9);
    /** The PitchClass of Bb */
    private static final PitchClass B_FLAT     = new PitchClass(10);
    /** The PitchClass of B */
    private static final PitchClass B_NATURAL  = new PitchClass(11);

    /** All possible PitchClasses */
    public static final Collection<PitchClass> ALL;

    // Initialize the "ALL" collection
    static {
        ALL = new ArrayList<>();
        for(int pitchClassValue = 0; pitchClassValue < 12; pitchClassValue++) {
            ALL.add(new PitchClass(pitchClassValue));
        }
    }

    /**
     * Stores the MidiTools value of this Pitch.
     */
    private final byte value;

    private PitchClass(int value) {
        if(value >= 0 && value < 12) {
            this.value = (byte)value;
        }
        else {
            throw new Error("Invalid pitch range!");
        }
    }

    public PitchClass(PitchClass other) {
        this.value = other.value;
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
     * Gets the Interval between this PitchClass and another.
     * @param other The other Pitch to subtract from this one.
     * @return THe Interval between this Pitch and another.
     */
    public IntervalClass minus(PitchClass other) {
        return new IntervalClass(other.value - this.value);
    }

    /**
     * Normalizes this PitchClass between 0 and 1.
     * @return This PitchClass in the range [0,1).
     */
    public float normalized() {
        return value/12.0f;
    }

    /**
     * Returns a nicely-formatted String
     * of this Pitch (for debug).
     * @return A nicely-formatted String of this Pitch.
     */
    public String toString() {
        switch (value) {
            case 0:     return "C";
            case 1:     return "Db";
            case 2:     return "D";
            case 3:     return "Eb";
            case 4:     return "E";
            case 5:     return "F";
            case 6:     return "Gb";
            case 7:     return "G";
            case 8:     return "Ab";
            case 9:     return "A";
            case 10:    return "Bb";
            case 11:    return "B";
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
        return new Integer(value).compareTo(new Integer(other.value));
    }

    /**
     * Compares two PitchClasses.
     * @param pc1 The first PitchClass.
     * @param pc2 The second PitchClass.
     * @return The comparison.
     */
    @Override
    public int compare(PitchClass pc1, PitchClass pc2) {
        return new Integer(pc1.value).compareTo(new Integer(pc2.value));
    }

    /**
     * Checks if this PitchClass equals another Object.
     * @param o The other Object.
     * @return If this PitchClass is equal to the Object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PitchClass pitch = (PitchClass) o;
        return value == pitch.value;
    }

    /**
     * A simple hash code for storage of PitchClasses in special Collections.
     * @return The hash code for this PitchClass.
     */
    @Override
    public int hashCode() {
        return (int) value;
    }
}
