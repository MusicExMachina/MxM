package base;

import io.MxmLog;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.*;

import static base.IntervalClass.*;

/**
 * Chord classes follow the naming convention of "?"-classes being a sort of octave-independent variant of "?". ChordNote
 * classes include concepts such as major and minor chords, but ignore inversion and spacing. They are, for all intents
 * and purposes, a collection of interval classes above an understood root note.
 *
 * Note that static chord classes use shorter names than interval classes in order to avoid name clashing, as well as to
 * avoid very long names for chords (think half diminished seventh)
 *
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements the <b>flyweight design pattern</b>- there is exactly one instance for
 * each value such that two ADTs (Abstract Data Types) with the same value are, in fact, the same instance. This
 * simplifies equality checks and can prevent memory waste. Unlike the <b>interning design pattern</b>, all possible
 * instances are created upfront during static initialization.
 */
public final class ChordClass implements Iterable<IntervalClass> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    /** Major chords */
    public static final ChordClass MAJOR = get(MAJOR_THIRD, PERFECT_FIFTH);
    /** Minor chords */
    public static final ChordClass MINOR = get(MINOR_THIRD, PERFECT_FIFTH);
    /** Augmented chords */
    public static final ChordClass AUGMENTED = get(MAJOR_THIRD, MINOR_SIXTH);
    /** Diminished chords */
    public static final ChordClass DIMINISHED = get(MINOR_THIRD, TRITONE);
    /** Dominant seventh chords */
    public static final ChordClass DOM_SEVENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH);
    /** Major seventh chords */
    public static final ChordClass MAJ_SEVENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH);
    /** Minor seventh chords */
    public static final ChordClass MIN_SEVENTH = get(MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH);
    /** Diminished seventh chords */
    public static final ChordClass DIM_SEVENTH = get(MINOR_THIRD, TRITONE, MAJOR_SIXTH);
    /** Augmented seventh chords */
    public static final ChordClass AUG_SEVENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH);
    /** Half-diminished seventh chords */
    public static final ChordClass HALF_DIM_SEVENTH = get(MINOR_THIRD, TRITONE, MINOR_SEVENTH);
    /** Minor-major seventh chords */
    public static final ChordClass MINOR_MAJOR_SEVENTH = get(MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH);
    /** Major sixth chords */
    public static final ChordClass MAJ_SIXTH = get(MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH);
    /** Minor sixth chords */
    public static final ChordClass MIN_SIXTH = get(MINOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH);
    /** Dominant ninth chords */
    public static final ChordClass DOM_NINTH = get(MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND);
    /** Major ninth chord */
    public static final ChordClass MAJOR_NINTH = get(MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH, MAJOR_SECOND);
    /** Minor ninth chords */
    public static final ChordClass MINOR_NINTH = get(MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND);
    /** Dominant eleventh chords */
    public static final ChordClass DOMINANT_ELEVENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH);
    /** Major eleventh chords */
    public static final ChordClass MAJOR_ELEVENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH);
    /** Minor eleventh chords */
    public static final ChordClass MINOR_ELEVENTH = get(MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH);
    /** Dominant thirteenth chords */
    public static final ChordClass DOMINANT_THIRTEENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH, MAJOR_SIXTH);
    /** Major thirteenth chords */
    public static final ChordClass MAJOR_THIRTEENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH, MAJOR_SIXTH);
    /** Minor thirteenth chords */
    public static final ChordClass MINOR_THIRTEENTH = get(MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH, MAJOR_SIXTH);
    /** Suspended second chords */
    public static final ChordClass SUSPENDED_SECOND = get(MAJOR_SECOND, PERFECT_FIFTH);
    /** Suspended fourth chords */
    public static final ChordClass SUSPENDED_FOURTH = get(PERFECT_FOURTH, PERFECT_FIFTH);
    /** Power chords */
    public static final ChordClass POWER_CHORD = get(PERFECT_FIFTH);

    /** The total number of chord classes */
    public static final int TOTAL_NUM = 2 << (PitchClass.TOTAL_NUM - 1);

    /** A static array of all possible interval classes, stored to implement the flyweight pattern */
    private static final ChordClass[] ALL;
    // Initializes the "ALL" array
    static {
        // Keep track of the start time to know how long initialization takes
        long startTime = System.nanoTime();

        // Initialize all interval classes
        ALL = new ChordClass[TOTAL_NUM];
        for(int val = 0; val < TOTAL_NUM; val++) {
            // We use some fun bit-math in order to generate all permutations of interval classes
            ArrayList<IntervalClass> intervalClasses = new ArrayList<>();
            for(int intClass = IntervalClass.MIN_SIZE; intClass < IntervalClass.MAX_SIZE; intClass++) {
                if(((val >> (intClass + IntervalClass.MIN_SIZE)) & 1) == 1)
                    intervalClasses.add(IntervalClass.get(intClass));
            }
            ALL[val] = new ChordClass(val,intervalClasses);
        }

        long endTime = System.nanoTime();
        MxmLog.logInitialization("Chord class", Arrays.asList(ALL),endTime - startTime);
    }

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

    /**
     * Gets an iterator which enumerates all valid chord classes.
     * @return An iterator over all valid chord classes
     */
    public static Iterator<ChordClass> allItr() {
        return Arrays.asList(ALL).iterator();
    }
    /**
     * Gets an instance of a given chord class. This method creates the interning design pattern per chord class.
     * @param id The id of this chord class
     * @return An intervalClass of this value
     */
    public static @NotNull ChordClass get(int id) {
        if(id < TOTAL_NUM) {
            return ALL[id];
        }
        else {
            throw new Error("CHORD CLASS:\tThis class does not exist!");
        }
    }
    /**
     * Gets an chord class with a number of chord factors above the root. This method creates the interning design
     * pattern per chord class.
     * @param factors The factors of this chord class above unison
     * @return An chord class with these factors
     */
    public static @NotNull ChordClass get(@NotNull IntervalClass... factors) {
        return new ChordClass(0,Arrays.asList(factors)); // TODO: Remove this!
    }
    /**
     * Gets an chord class with a number of chord factors above the root. This method creates the interning design
     * pattern per chord class.
     * @param factors The factors of this chord class above unison
     * @return An chord class with these factors
     */
    public static @NotNull ChordClass get(Collection<IntervalClass> factors) {
        return new ChordClass(0,factors); // TODO: Remove this!
    }

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** All factors of this Chord. */
    private final int id;
    /** All factors of this Chord. */
    private final TreeSet<IntervalClass> factors;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * The constructor for a specific type of chord (such as major or minor) which says nothing about spacing, inversion
     * and so forth- only what chord factors it includes. The constructor automatically adds a unison to the mix. Note
     * that we do not have to copy factors as interval classes are immutable.
     * @param factors The intervals in this chord, all shrunk down to one octave (ie, a ninth becomes a second)
     */
    private ChordClass(int id, @NotNull Collection<IntervalClass> factors) {
        this.id = id;
        this.factors = new TreeSet<>();
        this.factors.addAll(factors);
    }
    /**
     * A getter for the id of this chord class, which is a unique identifier for this chord class.
     * @return the unique id of this chord class
     */
    public final int getID() {
        return id;
    }
    /**
     * Returns a string representation of this chord class
     * @return A string representation of this chord class
     */
    public final @NotNull String toString() {
        StringBuilder strBuilder = new StringBuilder();
        int factorNum = 0;
        for(IntervalClass ic : factors) {
            strBuilder.append(ic);
            if(++factorNum < factors.size()-1)
                strBuilder.append(",");
        }
        return strBuilder.toString();
    }
    /**
     * Returns an iterator over all the factors in this chord class.
     * @return an iterator over all the factors in this chord class
     */
    @Override
    public final Iterator<IntervalClass> iterator() {
        return factors.iterator();
    }
}
