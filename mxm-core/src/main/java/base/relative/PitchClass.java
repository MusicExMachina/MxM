package base.relative;

import io.MxmLog;
import base.sounds.Pitch;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Pitch classes represent a collapsing of {@link Pitch} about the octave. To put it another way, a C5 and
 * a C0 are the same pitch class- C. Pitch classes parallel pitches in many respects- they can be transposed, and also
 * follow the flyweight (interning) design pattern. There are only 12 pitch classes, which can be accessed via static
 * variables like {@link #C_NATURAL} or {@link #get(int)}.
 */
public class PitchClass implements IRelative {

    /** The maximum pitch class value, which represents C */
    private static final int MIN_PITCH_CLASS_VALUE = 0;
    /** The maximum pitch class value, which represents B */
    private static final int MAX_PITCH_CLASS_VALUE = 11;
    /** A static array of all possible pitch classes, stored to implement the flyweight pattern */
    private static final PitchClass[] ALL = new PitchClass[MAX_PITCH_CLASS_VALUE - MIN_PITCH_CLASS_VALUE + 1];

    static {
        // A bunch of useful logging of the initialization process
        MxmLog.logHeader("PITCH CLASS");
        MxmLog.log("Initialization started",1);
        ArrayList<String> allInitStr = new ArrayList<>();
        allInitStr.add("Initialized");

        // Initialize all Pitch Classes
        for(int val = MIN_PITCH_CLASS_VALUE; val <= MAX_PITCH_CLASS_VALUE; val++) {
            PitchClass pitchClass = new PitchClass(val);
            ALL[val] = pitchClass;
            allInitStr.add(pitchClass.toString());
        }

        // More useful logging, after the initialization is done
        MxmLog.log(allInitStr,1);
        MxmLog.log("Initialization completed",1);
        MxmLog.logFooter();
    }

    // C //
    /** The C flat pitch class */
    public static final PitchClass C_FLAT = get(11);
    /** The C natural pitch class */
    public static final PitchClass C_NATURAL = get(0);
    /** The C sharp pitch class */
    public static final PitchClass C_SHARP = get(1);
    // D //
    /** The D flat pitch class */
    public static final PitchClass D_FLAT = get(1);
    /** The D natural pitch class */
    public static final PitchClass D_NATURAL = get(2);
    /** The D sharp pitch class */
    public static final PitchClass D_SHARP = get(3);
    // E //
    /** The E flat pitch class */
    public static final PitchClass E_FLAT = get(3);
    /** The E natural pitch class */
    public static final PitchClass E_NATURAL = get(4);
    /** The E sharp pitch class */
    public static final PitchClass E_SHARP = get(5);
    // F //
    /** The F flat pitch class */
    public static final PitchClass F_FLAT = get(4);
    /** The F natural pitch class */
    public static final PitchClass F_NATURAL = get(5);
    /** The F sharp pitch class */
    public static final PitchClass F_SHARP = get(6);
    // G //
    /** The G flat pitch class */
    public static final PitchClass G_FLAT = get(6);
    /** The G natural pitch class */
    public static final PitchClass G_NATURAL = get(7);
    /** The G sharp pitch class */
    public static final PitchClass G_SHARP = get(8);
    // A //
    /** The A flat pitch class */
    public static final PitchClass A_FLAT = get(8);
    /** The A natural pitch class */
    public static final PitchClass A_NATURAL = get(9);
    /** The A sharp pitch class */
    public static final PitchClass A_SHARP = get(10);
    // B //
    /** The A flat pitch class */
    public static final PitchClass B_FLAT = get(10);
    /** The A natural pitch class */
    public static final PitchClass B_NATURAL = get(11);
    /** The A sharp pitch class */
    public static final PitchClass B_SHARP = get(0);
    /**
     * Supports the flyweight design pattern by a factory-eque getter instead of a public constructor
     * @param value The value of this pitch class
     * @return a pitch class of this value
     */
    public static @NotNull PitchClass get(int value) {
        if (value >= MIN_PITCH_CLASS_VALUE && value <= MAX_PITCH_CLASS_VALUE) {
            return ALL[value];
        } else {
            throw new Error("PITCH CLASS: Pitch class of size " + value + " is out of range.");
        }
    }
    /**
     * Gets an iterator which enumerates all valid pitch classes.
     * @return An iterator over all valid pitch classes
     */
    public static @NotNull Iterator<PitchClass> iterator() { return new ArrayList<>(Arrays.asList(ALL)).iterator(); }

    /** The value of this pitch class (between MIN_PITCH_CLASS_VALUE and MAX_PITCH_CLASS_VALUE) */
    private int value;
    /**
     * A private constructor for PitchClass which is hidden by the flyweight design pattern (use get() instead).
     * @param value the value of this pitch class
     */
    protected PitchClass(int value) {
        this.value = (byte)value;
    }
    /**
     * A getter for the value of this pitch class (between MIN_PITCH_CLASS_VALUE and MAX_PITCH_CLASS_VALUE).
     * @return the value of this pitch class
     */
    public int getValue() {
        return value;
    }
    /**
     * Transposes a pitch class by an interval class. Note that this wraps about the octave.
     * @param intervalClass the interval class to transpose this pitch class by
     * @return another pitch class which is "intervalclass" higher than this one
     */
    public @NotNull PitchClass transpose(@NotNull IntervalClass intervalClass) {
        return PitchClass.get((value + intervalClass.getSize()) % 12);
    }
    /**
     * Gets a string representation of this class.
     * @return a string representation of this class
     */
    @Override
    public @NotNull String toString() {
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
     * @return if this pitch class is equal to another
     */
    @Override
    public boolean equals(Object other) {
        return this == other;
    }
    /**
     * A hash function for pitch classes to allow them to be stored various hashed collections.
     * @return a hash of this pitch class
     */
    @Override
    public int hashCode() {
        return value;
    }
}
