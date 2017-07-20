package musicTheory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * ChordNote classes follow the naming convention of "?"-classes being a sort of octave-independent variant of "?". ChordNote
 * classes include concepts such as major and minor chords, but ignore inversion and spacing. They are, for all intents
 * and purposes, a collection of interval classes above an understood root note.
 */
public class ChordClass implements Iterable<IntervalClass> {

    /** Major chords */
    public static final ChordClass MAJOR = new ChordClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH);

    /** Minor chords */
    public static final ChordClass MINOR = new ChordClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH);

    /** Augmented chords */
    public static final ChordClass AUGMENTED = new ChordClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.MINOR_SIXTH);

    /** Diminished chords */
    public static final ChordClass DIMINISHED = new ChordClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.TRITONE);

    public static final ChordClass DOMINANT_SEVENTH = new ChordClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH);

    /** Major seventh chords */
    public static final ChordClass MAJOR_SEVENTH = new ChordClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH);

    /** Minor seventh chords */
    public static final ChordClass MINOR_SEVENTH = new ChordClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH);

    /** Diminished seventh chords */
    public static final ChordClass DIMINISHED_SEVENTH = new ChordClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.TRITONE,
            IntervalClass.MAJOR_SIXTH);

    /** Augmented seventh chords */
    public static final ChordClass AUGMENTED_SEVENTH = new ChordClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SEVENTH);

    /** Half-diminished seventh chords */
    public static final ChordClass HALF_DIMINISHED_SEVENTH = new ChordClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.TRITONE,
            IntervalClass.MINOR_SEVENTH);

    /** Minor-major seventh chords */
    public static final ChordClass MINOR_MAJOR_SEVENTH = new ChordClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH);

    /** Major sixth chords */
    public static final ChordClass MAJOR_SIXTH = new ChordClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SIXTH);

    /** Minor sixth chords */
    public static final ChordClass MINOR_SIXTH = new ChordClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SIXTH);

    /** Dominant ninth chords */
    public static final ChordClass DOMINANT_NINTH = new ChordClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH,
            IntervalClass.MAJOR_SECOND);

    /** Major ninth chord */
    public static final ChordClass MAJOR_NINTH = new ChordClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SEVENTH,
            IntervalClass.MAJOR_SECOND);

    /** Minor ninth chords */
    public static final ChordClass MINOR_NINTH = new ChordClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH,
            IntervalClass.MAJOR_SECOND);

    /** Dominant eleventh chords */
    public static final ChordClass DOMINANT_ELEVENTH = new ChordClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH,
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FOURTH);

    /** Major eleventh chords */
    public static final ChordClass MAJOR_ELEVENTH = new ChordClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SEVENTH,
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FOURTH);

    /** Minor eleventh chords */
    public static final ChordClass MINOR_ELEVENTH = new ChordClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH,
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FOURTH);

    /** Dominant thirteenth chords */
    public static final ChordClass DOMINANT_THIRTEENTH = new ChordClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH,
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.MAJOR_SIXTH);

    /** Major thirteenth chords */
    public static final ChordClass MAJOR_THIRTEENTH = new ChordClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SEVENTH,
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.MAJOR_SIXTH);

    /** Minor thirteenth chords */
    public static final ChordClass MINOR_THIRTEENTH = new ChordClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH,
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.MAJOR_SIXTH);

    /** Suspended second chords */
    public static final ChordClass SUSPENDED_SECOND = new ChordClass(
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FIFTH);

    /** Suspended fourth chords */
    public static final ChordClass SUSPENDED_FOURTH = new ChordClass(
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.PERFECT_FIFTH);

    /** Power chords */
    public static final ChordClass POWER_CHORD = new ChordClass(
            IntervalClass.PERFECT_FIFTH);

    /** All factors of this Chord. */
    private TreeSet<IntervalClass> factors;

    /**
     * The constructor for a specific type of chord (such as major or minor) which says nothing about spacing, inversion
     * and so forth- only what chord factors it includes. The constructor automatically adds a unison to the mix. Note
     * that we do not have to copy factors as interval classes are immutable.
     * @param factors The intervals in this chord, all shrunk down to one octave (ie, a ninth becomes a second)
     */
    public ChordClass(IntervalClass... factors) {
        this.factors = new TreeSet<>();
        this.factors.add(IntervalClass.UNISON);
        this.factors.addAll(Arrays.asList(factors));
    }

    @Override
    public Iterator<IntervalClass> iterator() {
        return factors.iterator();
    }
}
