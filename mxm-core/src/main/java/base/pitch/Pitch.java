package base.pitch;

import base.ISound;
import io.MxmLog;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * <p> <b>Class Overview:</b>
 * Pitch is a simple class which represents a pitch in the traditional Western music sense: equal-temperament, A440,
 * with octave equivalence and so forth. To use a pitch, simply call Pitch.get() with the midi value of the desired
 * pitch (C4 = 60) or with the desired {@link PitchClass} and octave number. (i.e. Pitch.get(C,2)) To easily enumerate
 * all pitches, use Pitch.allItr() which starts at the lowest possible pitch, and runs to the highest possible. </p>
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements the <b>flyweight design pattern</b>- there is exactly one instance for
 * each value such that two ADTs (Abstract Data Types) with the same value are, in fact, the same instance. This
 * simplifies equality checks and can prevent memory waste. Unlike the <b>interning design pattern</b>, all possible
 * instances are created upfront during static initialization.
 *
 * @author Patrick Celentano
 */
public final class Pitch implements ISound, Comparator<Pitch>, Comparable<Pitch> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    /** The minimum pitch value, C-1. */
    public static final int MIN_VALUE = 0;
    /** The maximum pitch value, B9. */
    public static final int MAX_VALUE = 120;
    /** The total number of pitches */
    public static final int TOTAL_NUM = (MAX_VALUE - MIN_VALUE) + 1;

    /** A static array of all possible pitches, stored to implement the flyweight pattern */
    private static final Pitch[] ALL;
    // Static initialization block
    static {
        // Keep track of the start time to know how long initialization takes
        long startTime = System.nanoTime();

        // Initialize all pitches
        ALL = new Pitch[TOTAL_NUM];
        for(int val = MIN_VALUE; val <= MAX_VALUE; val++) {
            ALL[val] = new Pitch(val);
        }

        // Log the initialization
        MxmLog.logStaticInit("Pitch", Arrays.asList(ALL),System.nanoTime() - startTime);
    }

    /** The lowest possible pitch */
    public static final Pitch MIN = get(MIN_VALUE);
    /** The highest possible pitch */
    public static final Pitch MAX = get(MAX_VALUE);

    //////////////////////////////
    // Static Methods           //
    //////////////////////////////

    /**
     * Gets an iterator which enumerates all valid Pitches.
     * @return An iterator over all valid Pitches.
     */
    public static Iterator<Pitch> allItr() {
        return Arrays.asList(ALL).iterator();
    }
    /**
     * Gets an instance of a given pitch.
     * @param value The value of this pitch.
     * @return An pitch of this value
     */
    public static Pitch get(int value) {
        if(value >= MIN_VALUE && value <= MAX_VALUE) {
            return ALL[value - MIN_VALUE];
        }
        else throw new Error("PITCH:\tInterval out of range.");
    }
    /**
     * Gets an instance of a given pitch.
     * @param pitchClass The pitch class of this pitch
     * @param octave The octave of this pitch
     * @return A pitch of this pitch class and octave
     */
    public static Pitch get(@NotNull PitchClass pitchClass, int octave) {
        // Remember that we must add one to the octave to support the lowest octave, -1
        int value = pitchClass.getValue()+ (octave+1)*12;
        if(value >= MIN_VALUE && value <= MAX_VALUE) {
            return ALL[value - MIN_VALUE];
        }
        else throw new Error("PITCH:\tPitch out of range.");
    }

    //////////////////////////////
    // Member Variables         //
    //////////////////////////////

    /** The octave of this pitch */
    private final int octave;
    /** The midi value of this pitch, between MIN_VALUE and MAX_VALUE */
    private final int value;
    /** The pitch class of this pitch */
    private final PitchClass pitchClass;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * The pitch constructor, which is private to enforce the flyweight design pattern
     * @param value The value of this pitch
     */
    private Pitch(int value) {
        this.value = value;
        this.pitchClass = PitchClass.get(value%12);
        this.octave = value/12;
    }
    /**
     * Gets the value of this pitch.
     * @return The value of this pitch
     */
    public final int getValue() {
        return value;
    }
    /**
     * Gets the octave of this pitch.
     * @return The octave of this pitch
     */
    public final int getOctave() {
        return octave;
    }
    /**
     * Gets the pitch class of this pitch.
     * @return The pitch class of this pitch
     */
    public final @NotNull PitchClass getPitchClass() {
        return pitchClass;
    }
    /**
     * Returns another pitch which is transposed by a given interval
     * @param interval The interval to transpose by
     * @return The new, resulting pitch
     */
    public final @NotNull Pitch plus(@NotNull Interval interval) {
        return Pitch.get(value + interval.getSize());
    }
    /**
     * Returns another pitch which is transposed by a given interval
     * @param interval The interval to transpose by
     * @return The new, resulting pitch
     */
    public final @NotNull Pitch minus(@NotNull Interval interval) {
        return Pitch.get(value - interval.getSize());
    }
    /**
     * Gets the interval between this pitch and another.
     * @param other The other pitch to subtract from this one
     * @return The interval between this pitch and another
     */
    public final @NotNull Interval minus(@NotNull Pitch other) {
        return Interval.get(other.value - this.value);
    }
    /**
     * Returns a string representation of this pitch.
     * @return A string representation of this pitch
     */
    @Override
    public final @NotNull String toString() {
        return pitchClass.toString() + (octave - 1);
    }
    /**
     * Compares this pitch to another based on perceived height.
     * @param other The other pitch
     * @return The comparison between these two pitches
     */
    @Override
    public final int compareTo(@NotNull Pitch other) {
        return Integer.compare(value, other.value);
    }
    /**
     * Compares two pitches based on perceived height.
     * @param p1 The first pitch
     * @param p2 The second pitch
     * @return The comparison between these two pitches
     */
    @Override
    public final int compare(@NotNull Pitch p1, @NotNull Pitch p2) {
        return Integer.compare(p1.value, p2.value);
    }
    /**
     * Checks if this pitch is equal to another object. Note that since the flyweight pattern is used, literal
     * (reference) equality is enough to ensure that these objects are actually equal.
     * @param object the object to compare this pitch to
     * @return if this pitch is equal to this object
     */
    @Override
    public final boolean equals(Object object) {
        return this == object;
    }
    /**
     * A simple hash code in order to allow storage in certain collections.
     * @return The hash code for this pitch
     */
    @Override
    public final int hashCode() {
        return value;
    }
}