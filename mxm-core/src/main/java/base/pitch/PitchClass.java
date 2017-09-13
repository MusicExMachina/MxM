package base.pitch;

import io.Log;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * <p> <b>Class Overview:</b>
 * Pitch classes represent a collapsing of {@link Pitch} about the octave. To put it another way, a C5 and
 * a C0 are the same pitch class- C. Pitch classes parallel pitches in many respects- they can be transposed, and also
 * follow the flyweight (interning) design pattern. There are only 12 pitch classes, which can be accessed via static
 * variables like {@link #C_NATURAL} or {@link #get(int)}. </p>
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements the <b>flyweight design pattern</b>- there is exactly one instance for
 * each value such that two ADTs (Abstract Data Types) with the same value are, in fact, the same instance. This
 * simplifies equality checks and can prevent memory waste. Unlike the <b>interning design pattern</b>, all possible
 * instances are created upfront during static initialization.
 *
 * @author Patrick Celentano
 */
public class PitchClass {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    /** The maximum pitch class value, which represents C */
    public static final int MIN_VALUE = 0;
    /** The maximum pitch class value, which represents B */
    public static final int MAX_VALUE = 11;
    /** The total number of pitch classes */
    public static final int TOTAL_NUM = (MAX_VALUE - MIN_VALUE) + 1;

    /** A static array of all possible pitch classes, stored to implement the flyweight pattern */
    private static final PitchClass[] ALL;
    // Static initialization block
    static {
        // Keep track of the start time to know how long initialization takes
        long startTime = System.nanoTime();

        // Initialize all pitch classes
        ALL = new PitchClass[TOTAL_NUM];
        for(int val = MIN_VALUE; val <= MAX_VALUE; val++) {
            ALL[val] = new PitchClass(val);
        }

        // Log the initialization
        Log.logStaticInit("Pitch class", Arrays.asList(ALL),System.nanoTime() - startTime);
    }

    /** The C flat pitch class */
    public static final PitchClass C_FLAT = get(11);
    /** The C natural pitch class */
    public static final PitchClass C_NATURAL = get(0);
    /** The C sharp pitch class */
    public static final PitchClass C_SHARP = get(1);
    /** The D flat pitch class */
    public static final PitchClass D_FLAT = get(1);
    /** The D natural pitch class */
    public static final PitchClass D_NATURAL = get(2);
    /** The D sharp pitch class */
    public static final PitchClass D_SHARP = get(3);
    /** The E flat pitch class */
    public static final PitchClass E_FLAT = get(3);
    /** The E natural pitch class */
    public static final PitchClass E_NATURAL = get(4);
    /** The E sharp pitch class */
    public static final PitchClass E_SHARP = get(5);
    /** The F flat pitch class */
    public static final PitchClass F_FLAT = get(4);
    /** The F natural pitch class */
    public static final PitchClass F_NATURAL = get(5);
    /** The F sharp pitch class */
    public static final PitchClass F_SHARP = get(6);
    /** The G flat pitch class */
    public static final PitchClass G_FLAT = get(6);
    /** The G natural pitch class */
    public static final PitchClass G_NATURAL = get(7);
    /** The G sharp pitch class */
    public static final PitchClass G_SHARP = get(8);
    /** The A flat pitch class */
    public static final PitchClass A_FLAT = get(8);
    /** The A natural pitch class */
    public static final PitchClass A_NATURAL = get(9);
    /** The A sharp pitch class */
    public static final PitchClass A_SHARP = get(10);
    /** The A flat pitch class */
    public static final PitchClass B_FLAT = get(10);
    /** The A natural pitch class */
    public static final PitchClass B_NATURAL = get(11);
    /** The A sharp pitch class */
    public static final PitchClass B_SHARP = get(0);

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

    /**
     * Gets an iterator which enumerates all valid pitch classes.
     * @return An iterator over all valid pitch classes
     */
    public static @NotNull Iterator<PitchClass> allItr() { return new ArrayList<>(Arrays.asList(ALL)).iterator(); }
    /**
     * Supports the flyweight design pattern by a factory-eque getter instead of a public constructor
     * @param value The value of this pitch class
     * @return a pitch class of this value
     */
    public static @NotNull PitchClass get(int value) {
        if (value >= MIN_VALUE && value <= MAX_VALUE) {
            return ALL[value];
        }
        else throw new Error("PITCH CLASS: Pitch class of size " + value + " is out of range.");
    }

    //////////////////////////////
    // Member Variables         //
    //////////////////////////////

    /** The value of this pitch class */
    private final int value;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * A private constructor for PitchClass which is hidden by the flyweight design pattern (use get() instead).
     * @param value the value of this pitch class
     */
    private PitchClass(int value) {
        this.value = (byte)value;
    }
    /**
     * A getter for the value of this pitch class.
     * @return the value of this pitch class
     */
    public final int getValue() {
        return value;
    }
    /**
     * Transposes a pitch class by an interval class. Note that this wraps around the octave.
     * @param intervalClass the interval class to transpose this pitch class by
     * @return another pitch class which is an interval class higher than this one
     */
    public final @NotNull PitchClass transpose(@NotNull IntervalClass intervalClass) {
        return PitchClass.get((value + intervalClass.getSize()) % 12);
    }
    /**
     * Returns a string representation of this pitch class.
     * @return A string representation of this pitch class
     */
    @Override
    public final @NotNull String toString() {
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
     * Checks if this pitch class is equal to another object. Note that since the flyweight pattern is used, literal
     * (reference) equality is enough to ensure that these objects are actually equal.
     * @param other the object to compare this pitch class to
     * @return if this pitch class is equal to this object
     */
    @Override
    public final boolean equals(Object other) {
        return this == other;
    }
    /**
     * A simple hash code in order to allow storage in certain collections.
     * @return The hash code for this pitch class
     */
    @Override
    public final int hashCode() {
        return value;
    }
}
