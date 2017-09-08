package base.harmony;

import base.ISound;
import base.pitch.IntervalClass;
import base.pitch.PitchClass;
import composite.Voicing;
import io.MxmLog;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * <p> <b>Class overview:</b>
 * The {@link Chord} class represents a specific set of pitch classes. Note that every chord as a root note and a
 * series of factors above that root note. These might include a third, a fifth, a seventh, and so on. They might just
 * as well contain a second, a third, a fourth, and a fifth, forming a cluster. All that matters is that the order of
 * these IntervalClasses is preserved. Note that this is not a {@link Voicing}, as voicings involve
 * actual intervals- octaves and all, while chords simply refer to music theory-based relations.</p>
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements the <b>interning design pattern</b>- there is exactly one instance for
 * each value such that two ADTs (Abstract Data Types) with the same value are, in fact, the same instance. This
 * simplifies equality checks and can prevent memory waste. Unlike the <b>flyweight design pattern</b>, however, new
 * instances are whenever an unencountered instance is created- that is to say- at runtime.</p>
 *
 * @author Patrick Celentano
 */
public class Chord implements ISound, Iterable<PitchClass> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    /** The total number of chord classes */
    public static final int TOTAL_NUM = ChordClass.TOTAL_NUM * PitchClass.TOTAL_NUM;

    /** A static array of all possible pitches, stored to implement the flyweight pattern */
    private static final Chord[] ALL;
    // Static initialization block
    static {
        // Keep track of the start time to know how long initialization takes
        long startTime = System.nanoTime();

        // Initialize all pitch classes
        ALL = new Chord[PitchClass.TOTAL_NUM * ChordClass.TOTAL_NUM];
        for(int pcVal = PitchClass.MIN_VALUE; pcVal <= PitchClass.MAX_VALUE; pcVal++) {
            for(int ccVal = 0; ccVal < ChordClass.TOTAL_NUM; ccVal++) {
                ALL[(pcVal * ChordClass.TOTAL_NUM) + ccVal] = new Chord(PitchClass.get(pcVal), ChordClass.get(ccVal));
            }
        }

        // Log the initialization
        MxmLog.logStaticInit("Chord", Arrays.asList(ALL),System.nanoTime() - startTime);
    }

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

    /**
     * Gets an iterator which enumerates all valid chords.
     * @return An iterator over all valid chords
     */
    public static @NotNull Iterator<Chord> allItr() {
        return Arrays.asList(ALL).iterator();
    }
    public static @NotNull Chord get(@NotNull PitchClass root, @NotNull ChordClass chordClass) {
        return ALL[(root.getValue() * ChordClass.TOTAL_NUM) + chordClass.getID()];
    }

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** Each position in this array represents the position of the factors in order from root through thirteenth. */
    private ArrayList<PitchClass> factorsInOrder;
    /** Harmonies are essentially just sets of pitch classes and do not preserve factor order. */
    private Harmony harmony;
    /** Chord classes represent the types of intervals (interval classes) above the root pitch class. */
    private ChordClass chordClass;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    protected Chord(@NotNull PitchClass root, @NotNull ChordClass chordClass) {
        this.factorsInOrder = new ArrayList<>();
        // For every interval in the ChordClass (which holds all intervals above the root, add a chord factor that is
        // also that high above the root. Note that ninths become seconds,m as to interval classes wrap at the octave
        for(IntervalClass intervalClass : chordClass) {
            factorsInOrder.add(root.transpose(intervalClass));
        }
        this.harmony = Harmony.get(factorsInOrder);
        this.chordClass = chordClass;
    }
    public @NotNull ChordClass getChordClass() {
        return chordClass;
    }
    public @NotNull Harmony getHarmony() {
        return harmony;
    }


    @Override
    public @NotNull String toString() {
        return ""; // TODO: return factorsInOrder.get(0).toString() + " " + chordClass.toString();
    }
    @Override
    public @NotNull Iterator<PitchClass> iterator() {
        return factorsInOrder.iterator();
    }
}
