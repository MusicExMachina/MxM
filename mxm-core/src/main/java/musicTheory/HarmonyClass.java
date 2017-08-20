package musicTheory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * ChordNote classes follow the naming convention of "?"-classes being a sort of octave-independent variant of "?". ChordNote
 * classes include concepts such as major and minor chords, but ignore inversion and spacing. They are, for all intents
 * and purposes, a collection of interval classes above an understood root note.
 */
public class HarmonyClass implements Iterable<IntervalClass> {

    /** Major chords */
    public static final HarmonyClass MAJOR = new HarmonyClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH);

    /** Minor chords */
    public static final HarmonyClass MINOR = new HarmonyClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH);

    /** Augmented chords */
    public static final HarmonyClass AUGMENTED = new HarmonyClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.MINOR_SIXTH);

    /** Diminished chords */
    public static final HarmonyClass DIMINISHED = new HarmonyClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.TRITONE);

    public static final HarmonyClass DOMINANT_SEVENTH = new HarmonyClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH);

    /** Major seventh chords */
    public static final HarmonyClass MAJOR_SEVENTH = new HarmonyClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH);

    /** Minor seventh chords */
    public static final HarmonyClass MINOR_SEVENTH = new HarmonyClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH);

    /** Diminished seventh chords */
    public static final HarmonyClass DIMINISHED_SEVENTH = new HarmonyClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.TRITONE,
            IntervalClass.MAJOR_SIXTH);

    /** Augmented seventh chords */
    public static final HarmonyClass AUGMENTED_SEVENTH = new HarmonyClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SEVENTH);

    /** Half-diminished seventh chords */
    public static final HarmonyClass HALF_DIMINISHED_SEVENTH = new HarmonyClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.TRITONE,
            IntervalClass.MINOR_SEVENTH);

    /** Minor-major seventh chords */
    public static final HarmonyClass MINOR_MAJOR_SEVENTH = new HarmonyClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH);

    /** Major sixth chords */
    public static final HarmonyClass MAJOR_SIXTH = new HarmonyClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SIXTH);

    /** Minor sixth chords */
    public static final HarmonyClass MINOR_SIXTH = new HarmonyClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SIXTH);

    /** Dominant ninth chords */
    public static final HarmonyClass DOMINANT_NINTH = new HarmonyClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH,
            IntervalClass.MAJOR_SECOND);

    /** Major ninth chord */
    public static final HarmonyClass MAJOR_NINTH = new HarmonyClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SEVENTH,
            IntervalClass.MAJOR_SECOND);

    /** Minor ninth chords */
    public static final HarmonyClass MINOR_NINTH = new HarmonyClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH,
            IntervalClass.MAJOR_SECOND);

    /** Dominant eleventh chords */
    public static final HarmonyClass DOMINANT_ELEVENTH = new HarmonyClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH,
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FOURTH);

    /** Major eleventh chords */
    public static final HarmonyClass MAJOR_ELEVENTH = new HarmonyClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SEVENTH,
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FOURTH);

    /** Minor eleventh chords */
    public static final HarmonyClass MINOR_ELEVENTH = new HarmonyClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH,
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FOURTH);

    /** Dominant thirteenth chords */
    public static final HarmonyClass DOMINANT_THIRTEENTH = new HarmonyClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH,
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.MAJOR_SIXTH);

    /** Major thirteenth chords */
    public static final HarmonyClass MAJOR_THIRTEENTH = new HarmonyClass(
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SEVENTH,
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.MAJOR_SIXTH);

    /** Minor thirteenth chords */
    public static final HarmonyClass MINOR_THIRTEENTH = new HarmonyClass(
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SEVENTH,
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.MAJOR_SIXTH);

    /** Suspended second chords */
    public static final HarmonyClass SUSPENDED_SECOND = new HarmonyClass(
            IntervalClass.MAJOR_SECOND,
            IntervalClass.PERFECT_FIFTH);

    /** Suspended fourth chords */
    public static final HarmonyClass SUSPENDED_FOURTH = new HarmonyClass(
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.PERFECT_FIFTH);

    /** Power chords */
    public static final HarmonyClass POWER_CHORD = new HarmonyClass(
            IntervalClass.PERFECT_FIFTH);

    /** All factors of this Harmony. */
    private TreeSet<IntervalClass> factors;

    /**
     * The constructor for a specific type of chord (such as major or minor) which says nothing about spacing, inversion
     * and so forth- only what chord factors it includes. The constructor automatically adds a unison to the mix. Note
     * that we do not have to copy factors as interval classes are immutable.
     * @param factors The intervals in this chord, all shrunk down to one octave (ie, a ninth becomes a second)
     */
    public HarmonyClass(IntervalClass... factors) {
        this.factors = new TreeSet<>();
        this.factors.add(IntervalClass.UNISON);
        this.factors.addAll(Arrays.asList(factors));
    }

    @Override
    public Iterator<IntervalClass> iterator() {
        return factors.iterator();
    }
}
