package base.harmony;

import base.composite.Sonority;
import base.sound.IntervalClass;
import base.sound.Pitch;
import base.sound.PitchClass;
import io.Log;
import org.jetbrains.annotations.NotNull;
import base.patterns.Scale;

import java.util.*;

/**
 * <p> <b>Class Overview:</b>
 * Harmony is one of the most components of many MxM base classes, as it represents a specific subset of all possible
 * sound classes. This may be a vertical consideration (as in {@link Sonority}) or a horizonal one (as in {@link Scale})
 * or a totally abstract one (as in {@link Key}). In all cases, such Harmonies can be compared, added to make new ones,
 * or otherwise modified.</p>
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements the <b>flyweight design pattern</b>- there is exactly one instance for
 * each value such that two ADTs (Abstract Data Types) with the same value are, in fact, the same instance. This
 * simplifies equality checks and can prevent memory waste. Unlike the <b>interning design pattern</b>, all possible
 * instances are created upfront during static initialization.
 *
 * @author Patrick Celentano
 */
public class Harmony {

    /** The total number of chord classes */
    public static final int TOTAL_NUM = 2 << PitchClass.TOTAL_NUM;
    /** A static array of all possible interval classes, stored to implement the flyweight pattern */
    private static final Harmony[] ALL;
    // Initializes the "ALL" array
    static {
        // Keep track of the start time to know how long initialization takes
        long startTime = System.nanoTime();

        // Initialize all interval classes
        ALL = new Harmony[TOTAL_NUM];
        for(int val = 0; val < TOTAL_NUM; val++) {
            ALL[val] = new Harmony(val);
        }

        // Log the initialization
        Log.logStaticInit("Harmony", Arrays.asList(ALL),System.nanoTime() - startTime);
    }
    /** The trivial, empty harmony, with no constituent sound classes. */
    public static final Harmony EMPTY = get(0);
    /** The full harmony, with all sound classes accounted for. */
    public static final Harmony FULL = get(TOTAL_NUM-1);

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

    /**
     * Gets an iterator which enumerates all valid harmonies.
     * @return An iterator over all valid harmonies
     */
    public static Iterator<Harmony> allItr() {
        return Arrays.asList(ALL).iterator();
    }
    /**
     * Gets an instance of a given harmony. This method enforces the flyweight design pattern per noise.
     * @param id The id of this harmony, which is a special bitmask of all the sound classes
     * @return A harmony with this id
     */
    private static @NotNull Harmony get(int id) {
        if(id < TOTAL_NUM) {
            return ALL[id];
        }
        else throw Log.error("Harmony","This id (" + id + ") does not exist!");
    }
    /**
     * Gets an instance of a given harmony. This method enforces the flyweight design pattern per noise.
     * @param pitchClasses All of the sound classes in this harmony
     * @return A harmony with these sound classes
     */
    public static @NotNull Harmony get(@NotNull PitchClass... pitchClasses) {
        // Use our bitmask trick again with an integer id that's really just T/F bits for each possible sound class
        int pitchClassMask = 0;
        for(PitchClass pitchClass : pitchClasses) {
            pitchClassMask = pitchClassMask | (1 << pitchClass.getValue());
        }
        return get(pitchClassMask);
    }
    /**
     * Gets an instance of a given harmony. This method enforces the flyweight design pattern per noise.
     * @param pitchClasses All of the sound classes in this harmony
     * @return A harmony with these sound classes
     */
    public static @NotNull Harmony get(Collection<PitchClass> pitchClasses) {
        // Use our bitmask trick again with an integer id that's really just T/F bits for each possible sound class
        int pitchClassMask = 0;
        for(PitchClass pitchClass : pitchClasses) {
            pitchClassMask = pitchClassMask | (1 << pitchClass.getValue());
        }
        return get(pitchClassMask);
    }

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** All factors of this Chord. */
    private final int id;
    /** All factors of this Harmony. */
    private final HashSet<PitchClass> pitchClasses;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * A private constructor for a harmony which takes only an "id" which is essentially a bitmask of all constituent
     * sound classes such that id bit 0 would be C, id bit 1 would be C#, and so forth
     * @param id The id of this harmony
     */
    private Harmony(int id) {
        this.id = id;
        // We use some fun bit-math in order to generate all permutations of sound classes
        this.pitchClasses = new HashSet<>();
        for(int pcVal = PitchClass.MIN_VALUE; pcVal < PitchClass.MAX_VALUE; pcVal++) {
            if(((id >> (pcVal + IntervalClass.MIN_SIZE)) & 1) == 1)
                pitchClasses.add(PitchClass.get(pcVal));
        }
    }
    /**
     * A getter for the id of this chord class, which is a unique identifier for this harmony.
     * @return the unique id of this harmony
     */
    public final int getID() {
        return id;
    }
    /**
     * Returns a harmony that is the sum of this harmony and another. Note that this simply combines their constituent
     * sound classes into another, with overlapping meaning nothing.
     * @param other the other harmony to add with this one
     * @return a new harmony that is the sum of this and other
     */
    public final @NotNull Harmony plus(@NotNull Harmony other) {
        return get(this.id & other.id);
    }
    /**
     * Returns a harmony that is the difference of this harmony and another. Note that this simply subtracts the
     * constituent sound classes of the second from the first
     * @param other the other harmony to subtract from this one
     * @return a new harmony that is the difference between this and other
     */
    public final @NotNull Harmony minus(@NotNull Harmony other) {
        return get(this.id ^ ~other.id); // TODO: check this logic
    }
    /**
     * Returns a string representation of this harmony
     * @return A string representation of this harmony
     */
    public final @NotNull String toString() {
        StringBuilder toReturn = new StringBuilder();
        PitchClass.all().forEach(pitchClass -> {
            if(pitchClasses.contains(pitchClass)) {
                toReturn.append(pitchClass.toString());
            }
        });
        return toReturn.toString();
    }
    /**
     * Checks if this harmony is equal to another object. Note that since the flyweight pattern is used, literal
     * (reference) equality is enough to ensure that these objects are actually equal.
     * @return if this harmony is equal to another
     */
    @Override
    public final boolean equals(Object object) {
        return this == object;
    }
    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this harmony
     */
    @Override
    public final int hashCode() {
        return id;
    }
}
