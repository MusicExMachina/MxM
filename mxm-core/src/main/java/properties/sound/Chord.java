package properties.sound;

import properties.AbstractIntegerProp;
import theory.harmony.Harmony;
import theory.composite.Voicing;
import io.Log;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * <p> <b>Class overview:</b>
 * Chords represent a root pitch class (such as C) and a chord type (such as major), with no suggestion as to voicing
 * or even pitch. Their purpose is purely to represent what information the words "C major" might imply.</p>
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements the <b>interning design pattern</b>- there is exactly one instance for
 * each value such that two ADTs (Abstract Data Types) with the same value are, in fact, the same instance. This
 * simplifies equality checks and can prevent memory waste. Unlike the <b>flyweight design pattern</b>, however, new
 * instances are whenever an unencountered instance is created- that is to say- at runtime.</p>
 *
 * @author Patrick Celentano
 */
public final class Chord extends AbstractIntegerProp implements ISound, Iterable<PitchClass> {

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

        // Initialize all sound classes
        ALL = new Chord[PitchClass.TOTAL_NUM * ChordClass.TOTAL_NUM];
        for(int pcVal = PitchClass.MIN_VALUE; pcVal <= PitchClass.MAX_VALUE; pcVal++) {
            for(int ccVal = 0; ccVal < ChordClass.TOTAL_NUM; ccVal++) {
                int chordVal = (pcVal * ChordClass.TOTAL_NUM) + ccVal;
                ALL[chordVal] = new Chord(chordVal,PitchClass.get(pcVal), ChordClass.get(ccVal));
            }
        }

        // Log the initialization
        Log.logStaticInit("Chord", Arrays.asList(ALL),System.nanoTime() - startTime);
    }

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

    /**
     * Returns a chord with a given root (i.e. C) and chord type (i.e. major)
     * @param root the root pitch class of this chord
     * @param chordClass the type of chord
     * @return a chord of this type with this root
     */
    public static @NotNull Chord get(@NotNull PitchClass root, @NotNull ChordClass chordClass) {
        return ALL[(root.getValue() * ChordClass.TOTAL_NUM) + chordClass.getID()];
    }
    /**
     * Returns an immutable collection of all valid chords, useful for iteration or streams
     * @return an immutable collection of all valid chords
     */
    public static @NotNull Collection<Chord> all() {
        return Collections.unmodifiableList(Arrays.asList(ALL));
    }
    /**
     * Returns a random instance of this class
     * @return a random valid chord
     */
    public static @NotNull Chord random() {
        return get(PitchClass.random(),ChordClass.random());
    }

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** The pitch class which this chord is built on top of. */
    private final PitchClass root;
    /** Chord classes represent the types of intervals (interval classes) above the root sound class. */
    private final ChordClass chordClass;
    /** Each position in this array represents the position of the factors in order from root through thirteenth. */
    private final ArrayList<PitchClass> factorsInOrder;
    /** Harmonies are essentially just sets of sound classes and do not preserve factor order. */
    private final Harmony harmony;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * A private constructor for a Chord which takes in a "chordVal" (its place in the ALL array), root, and chord type
     * @param chordVal a special value used for looking up this chord in the ALL array
     * @param root the root pitch class of this chord
     * @param chordClass the type of this chord
     */
    private Chord(int chordVal, @NotNull PitchClass root, @NotNull ChordClass chordClass) {
        super(chordVal);
        this.root = root;
        this.chordClass = chordClass;

        this.factorsInOrder = new ArrayList<>();
        // For every interval in the ChordClass (which holds all intervals above the root, add a chord factor that is
        // also that high above the root. Note that ninths become seconds,m as to interval classes wrap at the octave
        for(IntervalClass intervalClass : chordClass) {
            factorsInOrder.add(root.transpose(intervalClass));
        }
        this.harmony = Harmony.get(factorsInOrder);
    }
    /**
     * A getter for the root pitch class of this chord
     * @return the root pitch class of this chord
     */
    public final @NotNull PitchClass getRoot() {
        return root;
    }
    /**
     * A getter for the type of this chord (major/minor/augmented/diminished...)
     * @return the type of this chord
     */
    public final @NotNull ChordClass getChordClass() {
        return chordClass;
    }
    /**
     * Getter for this chord's implicit harmony
     * @return the harmony implicit in this chord
     */
    public final @NotNull Harmony getHarmony() {
        return harmony;
    }
    /**
     * Returns a string representation of this class
     * @return a string representing this Chord
     */
    @Override
    public final @NotNull String toString() {
        return root.toString() + " " + chordClass.toString();
    }







    /**
     *
     * @return
     */
    @Override
    public final @NotNull Iterator<PitchClass> iterator() {
        return factorsInOrder.iterator();
    }
}
