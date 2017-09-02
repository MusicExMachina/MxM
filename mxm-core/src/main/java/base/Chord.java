package base;

import base.sounds.ISound;
import io.MxmLog;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 *
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements the <b>flyweight design pattern</b>- there is exactly one instance for
 * each value such that two ADTs (Abstract Data Types) with the same value are, in fact, the same instance. This
 * simplifies equality checks and can prevent memory waste. Unlike the <b>interning design pattern</b>, all possible
 * instances are created upfront during static initialization.
 */
public class Chord implements ISound, Iterable<PitchClass> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    /** The total number of chord classes */
    public static final int TOTAL_NUM = ChordClass.TOTAL_NUM * PitchClass.TOTAL_NUM;

    /** A static array of all possible pitches, stored to implement the flyweight pattern */
    private static final Chord[][] ALL;
    // Static initialization block
    static {
        // Keep track of the start time to know how long initialization takes
        long startTime = System.nanoTime();

        // Initialize all pitch classes
        ALL = new Chord[PitchClass.TOTAL_NUM][ChordClass.TOTAL_NUM];
        for(int pcVal = PitchClass.MIN_VALUE; pcVal <= PitchClass.MAX_VALUE; pcVal++) {
            for(int ccVal = 0; ccVal <= ChordClass.TOTAL_NUM; ccVal++) {
                ALL[pcVal][ccVal] = new Chord(PitchClass.get(pcVal),ChordClass.get(ccVal));
            }
        }

        long endTime = System.nanoTime();
        MxmLog.logInitialization("Pitch", Arrays.asList(ALL),endTime - startTime);
    }

    //////////////////////////////
    //      Static methods      //
    //////////////////////////////

    /**
     * Gets an iterator which enumerates all valid chords.
     * @return An iterator over all valid chords
     */
    /*
    public static Iterator<Chord> allItr() {
        return Arrays.asList(ALL).iterator();
    }
    */
    public static @NotNull Chord get(@NotNull PitchClass root, @NotNull ChordClass chordClass) {
        return ALL[root.getValue()][chordClass.getID()];
    }

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** */
    private PitchClass root;
    /** */
    private HashSet<PitchClass> factors;
    /** */
    private ChordClass chordClass;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    protected Chord(@NotNull PitchClass root, @NotNull ChordClass chordClass) {
        factors = new HashSet<>();
        /* For every interval in the ChordClass (which holds all intervals above
            the root, add a chord factor that is also that high above the root */
        for(IntervalClass intervalClass : chordClass) {
            factors.add(root.transpose(intervalClass));
        }
        this.root = root;
        this.chordClass = chordClass;
    }
    /**
     * Gets a string representation of this class.
     * @return a string representation of this class
     */
    @Override
    public @NotNull String toString() {
        return root.toString() + " " + chordClass.toString();
    }
    @Override
    public @NotNull Iterator<PitchClass> iterator() {
        return factors.iterator();
    }
}
