package base.sound;

import base.AbstractIntegerProp;
import io.Log;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p> <b>Class Overview:</b>
 * Pitch is a simple class which represents a sound in the traditional Western music sense: equal-temperament, A440,
 * with octave equivalence and so forth. To use a sound, simply call Pitch.get() with the midi value of the desired
 * sound (C4 = 60) or with the desired {@link PitchClass} and octave number. (i.e. Pitch.get(C,2)) To easily enumerate
 * all pitches, use Pitch.allItr() which starts at the lowest possible sound, and runs to the highest possible. </p>
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements the <b>flyweight design pattern</b>- there is exactly one instance for
 * each value such that two ADTs (Abstract Data Types) with the same value are, in fact, the same instance. This
 * simplifies equality checks and can prevent memory waste. Unlike the <b>interning design pattern</b>, all possible
 * instances are created upfront during static initialization.
 *
 * @author Patrick Celentano
 */
public final class Pitch extends AbstractIntegerProp implements ISoundProperty, Comparator<Pitch>, Comparable<Pitch> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    /** The minimum sound value, C-1. */
    static final int MIN_VALUE = 0;
    /** The maximum sound value, B9. */
    static final int MAX_VALUE = 120;
    /** The total number of pitches */
    private static final int TOTAL_NUM = (MAX_VALUE - MIN_VALUE) + 1;

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
        Log.logStaticInit("Pitch", Arrays.asList(ALL),System.nanoTime() - startTime);
    }

    /** The lowest possible sound */
    public static final Pitch MIN = get(MIN_VALUE);
    /** The highest possible sound */
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
     * Gets an instance of a given sound.
     * @param value The value of this sound.
     * @return An sound of this value
     */
    public static @NotNull Pitch get(int value) {
        if(value >= MIN_VALUE && value <= MAX_VALUE) {
            return ALL[value - MIN_VALUE];
        }
        else throw new Error("PITCH:\tInterval out of range.");
    }
    /**
     * Gets an instance of a given sound.
     * @param pitchClass The sound class of this sound
     * @param octave The octave of this sound
     * @return A sound of this sound class and octave
     */
    public static @NotNull Pitch get(@NotNull PitchClass pitchClass, int octave) {
        // Remember that we must add one to the octave to support the lowest octave, -1
        int value = pitchClass.getValue()+ (octave+1)*12;
        if(value >= MIN_VALUE && value <= MAX_VALUE) {
            return ALL[value - MIN_VALUE];
        }
        else throw new Error("PITCH:\tPitch out of range.");
    }
    /**
     * Returns a random instance of this class
     * @return a random valid Pitch
     */
    public static @NotNull Pitch random() {
        return get(ThreadLocalRandom.current().nextInt(MIN_VALUE, MAX_VALUE + 1));
    }

    //////////////////////////////
    // Member Variables         //
    //////////////////////////////

    /** The octave of this sound */
    private final int octave;
    /** The sound class of this sound */
    private final PitchClass pitchClass;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * The sound constructor, which is private to enforce the flyweight design pattern
     * @param value The value of this sound
     */
    private Pitch(int value) {
        super(value);
        this.octave = value/12;
        this.pitchClass = PitchClass.get(value%12);
    }
    /**
     * Gets the octave of this sound.
     * @return The octave of this sound
     */
    public final int getOctave() {
        return octave;
    }
    /**
     * Gets the sound class of this sound.
     * @return The sound class of this sound
     */
    public final @NotNull PitchClass getPitchClass() {
        return pitchClass;
    }
    /**
     * Returns another sound which is transposed by a given interval
     * @param interval The interval to transpose by
     * @return The new, resulting sound
     */
    public final @NotNull Pitch plus(@NotNull Interval interval) {
        return Pitch.get(value + interval.getSize());
    }
    /**
     * Returns another sound which is transposed by a given interval
     * @param interval The interval to transpose by
     * @return The new, resulting sound
     */
    public final @NotNull Pitch minus(@NotNull Interval interval) {
        return Pitch.get(value - interval.getSize());
    }
    /**
     * Gets the interval between this sound and another.
     * @param other The other sound to subtract from this one
     * @return The interval between this sound and another
     */
    public final @NotNull Interval minus(@NotNull Pitch other) {
        return Interval.get(other.value - this.value);
    }
    /**
     * Returns a string representation of this sound.
     * @return A string representation of this sound
     */
    @Override
    public final @NotNull String toString() {
        return pitchClass.toString() + (octave - 1);
    }
    /**
     * Compares this sound to another based on perceived height.
     * @param other The other sound
     * @return The comparison between these two pitches
     */
    @Override
    public final int compareTo(@NotNull Pitch other) {
        return Integer.compare(value, other.value);
    }
    /**
     * Compares two pitches based on perceived height.
     * @param p1 The first sound
     * @param p2 The second sound
     * @return The comparison between these two pitches
     */
    @Override
    public final int compare(@NotNull Pitch p1, @NotNull Pitch p2) {
        return Integer.compare(p1.value, p2.value);
    }
    /**
     * Checks if this sound is equal to another object. Note that since the flyweight pattern is used, literal
     * (reference) equality is enough to ensure that these objects are actually equal.
     * @param object the object to compare this sound to
     * @return if this sound is equal to this object
     */
    @Override
    public final boolean equals(Object object) {
        return this == object;
    }
}