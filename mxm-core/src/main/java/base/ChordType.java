package base;

import io.MxmLog;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static base.IntervalClass.*;

/**
 * <p> <b>Class overview:</b>
 * A chord class is MxM's representation of a fundamental chord type- major, minor, diminished, augmented and so on.
 * The importance of
 *
 *  Note that static chord classes use shorter names than interval classes in order to avoid name clashing, as well as to
 * avoid very long names for chords (think half diminished seventh)
 * </p>
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements the <b>flyweight design pattern</b>- there is exactly one instance for
 * each value such that two ADTs (Abstract Data Types) with the same value are, in fact, the same instance. This
 * simplifies equality checks and can prevent memory waste. Unlike the <b>interning design pattern</b>, all possible
 * instances are created upfront during static initialization.
 *
 * @author Patrick Celentano
 */
public final class ChordType implements Iterable<IntervalClass> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    /** The total number of chord classes */
    public static final int TOTAL_NUM = 2 << (PitchClass.TOTAL_NUM - 1);
    /** A static array of all possible interval classes, stored to implement the flyweight pattern */
    private static final ChordType[] ALL;
    // Initializes the "ALL" array
    static {
        // Keep track of the start time to know how long initialization takes
        long startTime = System.nanoTime();

        // Initialize all interval classes
        ALL = new ChordType[TOTAL_NUM];
        for(int val = 0; val < TOTAL_NUM; val++) {
            // We use some fun bit-math in order to generate all permutations of interval classes
            ArrayList<IntervalClass> intervalClasses = new ArrayList<>();
            for(int intClass = IntervalClass.MIN_SIZE; intClass < IntervalClass.MAX_SIZE; intClass++) {
                if(((val >> (intClass + IntervalClass.MIN_SIZE)) & 1) == 1)
                    intervalClasses.add(IntervalClass.get(intClass));
            }
            ALL[val] = new ChordType(val,intervalClasses);
        }

        // Log the initialization
        MxmLog.logInitialization("Chord class", Arrays.asList(ALL),System.nanoTime() - startTime);
    }
    /** Major chords */
    public static final ChordType MAJOR = get(MAJOR_THIRD, PERFECT_FIFTH);
    /** Minor chords */
    public static final ChordType MINOR = get(MINOR_THIRD, PERFECT_FIFTH);
    /** Augmented chords */
    public static final ChordType AUGMENTED = get(MAJOR_THIRD, MINOR_SIXTH);
    /** Diminished chords */
    public static final ChordType DIMINISHED = get(MINOR_THIRD, TRITONE);
    /** Dominant seventh chords */
    public static final ChordType DOM_SEVENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH);
    /** Major seventh chords */
    public static final ChordType MAJ_SEVENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH);
    /** Minor seventh chords */
    public static final ChordType MIN_SEVENTH = get(MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH);
    /** Diminished seventh chords */
    public static final ChordType DIM_SEVENTH = get(MINOR_THIRD, TRITONE, MAJOR_SIXTH);
    /** Augmented seventh chords */
    public static final ChordType AUG_SEVENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH);
    /** Half-diminished seventh chords */
    public static final ChordType HALF_DIM_SEVENTH = get(MINOR_THIRD, TRITONE, MINOR_SEVENTH);
    /** Minor-major seventh chords */
    public static final ChordType MINOR_MAJOR_SEVENTH = get(MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH);
    /** Major sixth chords */
    public static final ChordType MAJ_SIXTH = get(MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH);
    /** Minor sixth chords */
    public static final ChordType MIN_SIXTH = get(MINOR_THIRD, PERFECT_FIFTH, MAJOR_SIXTH);
    /** Dominant ninth chords */
    public static final ChordType DOM_NINTH = get(MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND);
    /** Major ninth chord */
    public static final ChordType MAJOR_NINTH = get(MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH, MAJOR_SECOND);
    /** Minor ninth chords */
    public static final ChordType MINOR_NINTH = get(MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND);
    /** Dominant eleventh chords */
    public static final ChordType DOMINANT_ELEVENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH);
    /** Major eleventh chords */
    public static final ChordType MAJOR_ELEVENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH);
    /** Minor eleventh chords */
    public static final ChordType MINOR_ELEVENTH = get(MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH);
    /** Dominant thirteenth chords */
    public static final ChordType DOMINANT_THIRTEENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH, MAJOR_SIXTH);
    /** Major thirteenth chords */
    public static final ChordType MAJOR_THIRTEENTH = get(MAJOR_THIRD, PERFECT_FIFTH, MAJOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH, MAJOR_SIXTH);
    /** Minor thirteenth chords */
    public static final ChordType MINOR_THIRTEENTH = get(MINOR_THIRD, PERFECT_FIFTH, MINOR_SEVENTH, MAJOR_SECOND, PERFECT_FOURTH, MAJOR_SIXTH);
    /** Suspended second chords */
    public static final ChordType SUSPENDED_SECOND = get(MAJOR_SECOND, PERFECT_FIFTH);
    /** Suspended fourth chords */
    public static final ChordType SUSPENDED_FOURTH = get(PERFECT_FOURTH, PERFECT_FIFTH);
    /** Power chords */
    public static final ChordType POWER_CHORD = get(PERFECT_FIFTH);

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

    public static Iterator<ChordType> allItr() {
        return Arrays.asList(ALL).iterator();
    }

    public static @NotNull ChordType get(int id) {
        if(id < TOTAL_NUM) {
            return ALL[id];
        }
        else throw new Error("CHORD CLASS:\tThis class does not exist!");
    }
    public static @NotNull ChordType get(@NotNull IntervalClass... factors) {
        return new ChordType(0,Arrays.asList(factors)); // TODO: Remove this!
    }
    public static @NotNull ChordType get(Collection<IntervalClass> factors) {
        return new ChordType(0,factors); // TODO: Remove this!
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

    private ChordType(int id, @NotNull Collection<IntervalClass> factors) {
        this.id = id;
        this.factors = new TreeSet<>();
        this.factors.addAll(factors);
    }

    public final int getID() {
        return id;
    }

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
    @Override
    public final Iterator<IntervalClass> iterator() {
        return factors.iterator();
    }
}
