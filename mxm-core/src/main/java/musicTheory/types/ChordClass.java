package musicTheory.types;

import base.types.IntervalClass;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Chord classes follow the naming convention of "?"-classes being a sort of octave-independent variant of "?". Chord
 * classes include concepts such as major and minor chords, but ignore inversion and spacing. They are, for all intents
 * and purposes, a collection of interval classes above an understood root note.
 */
public class ChordClass implements Iterable<IntervalClass> {

    public static ChordClass MAJOR                      = new ChordClass(IntervalClass.MAJOR_THIRD, IntervalClass.PERFECT_FIFTH);
    public static ChordClass MINOR                      = new ChordClass(IntervalClass.MINOR_THIRD, IntervalClass.PERFECT_FIFTH);
    public static ChordClass AUGMENTED                  = new ChordClass(IntervalClass.MAJOR_THIRD, IntervalClass.MINOR_SIXTH);
    public static ChordClass DIMINISHED                 = new ChordClass(IntervalClass.MINOR_THIRD, IntervalClass.TRITONE);
    public static ChordClass DOMINANT_SEVENTH           = new ChordClass(IntervalClass.MAJOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MINOR_SEVENTH);
    public static ChordClass MAJOR_SEVENTH              = new ChordClass(IntervalClass.MAJOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MINOR_SEVENTH);
    public static ChordClass MINOR_SEVENTH              = new ChordClass(IntervalClass.MINOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MINOR_SEVENTH);
    public static ChordClass DIMINISHED_SEVENTH         = new ChordClass(IntervalClass.MINOR_THIRD, IntervalClass.TRITONE, IntervalClass.MAJOR_SIXTH);
    public static ChordClass AUGMENTED_SEVENTH          = new ChordClass(IntervalClass.MAJOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MAJOR_SEVENTH);
    public static ChordClass HALF_DIMINISHED_SEVENTH    = new ChordClass(IntervalClass.MINOR_THIRD, IntervalClass.TRITONE, IntervalClass.MINOR_SEVENTH);
    public static ChordClass MINOR_MAJOR_SEVENTH        = new ChordClass(IntervalClass.MINOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MINOR_SEVENTH);
    public static ChordClass MAJOR_SIXTH                = new ChordClass(IntervalClass.MAJOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MAJOR_SIXTH);
    public static ChordClass MINOR_SIXTH                = new ChordClass(IntervalClass.MINOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MAJOR_SIXTH);
    public static ChordClass DOMINANT_NINTH             = new ChordClass(IntervalClass.MAJOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MINOR_SEVENTH, IntervalClass.MAJOR_SECOND);
    public static ChordClass MAJOR_NINTH                = new ChordClass(IntervalClass.MAJOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MAJOR_SEVENTH, IntervalClass.MAJOR_SECOND);
    public static ChordClass MINOR_NINTH                = new ChordClass(IntervalClass.MINOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MINOR_SEVENTH, IntervalClass.MAJOR_SECOND);
    public static ChordClass DOMINANT_ELEVENTH          = new ChordClass(IntervalClass.MAJOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MINOR_SEVENTH, IntervalClass.MAJOR_SECOND, IntervalClass.PERFECT_FOURTH);
    public static ChordClass MAJOR_ELEVENTH             = new ChordClass(IntervalClass.MAJOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MAJOR_SEVENTH, IntervalClass.MAJOR_SECOND, IntervalClass.PERFECT_FOURTH);
    public static ChordClass MINOR_ELEVENTH             = new ChordClass(IntervalClass.MINOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MINOR_SEVENTH, IntervalClass.MAJOR_SECOND, IntervalClass.PERFECT_FOURTH);
    public static ChordClass DOMINANT_THIRTEENTH        = new ChordClass(IntervalClass.MAJOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MINOR_SEVENTH, IntervalClass.MAJOR_SECOND, IntervalClass.PERFECT_FOURTH, IntervalClass.MAJOR_SIXTH);
    public static ChordClass MAJOR_THIRTEENTH           = new ChordClass(IntervalClass.MAJOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MAJOR_SEVENTH, IntervalClass.MAJOR_SECOND, IntervalClass.PERFECT_FOURTH, IntervalClass.MAJOR_SIXTH);
    public static ChordClass MINOR_THIRTEENTH           = new ChordClass(IntervalClass.MINOR_THIRD, IntervalClass.PERFECT_FIFTH, IntervalClass.MINOR_SEVENTH, IntervalClass.MAJOR_SECOND, IntervalClass.PERFECT_FOURTH, IntervalClass.MAJOR_SIXTH);
    public static ChordClass SUSPENDED_SECOND           = new ChordClass(IntervalClass.MAJOR_SECOND, IntervalClass.PERFECT_FIFTH);
    public static ChordClass SUSPENDED_FOURTH           = new ChordClass(IntervalClass.PERFECT_FOURTH, IntervalClass.PERFECT_FIFTH);
    public static ChordClass POWER_CHORD                = new ChordClass(IntervalClass.PERFECT_FIFTH);

    private TreeSet<IntervalClass> factors;

    /**
     * The constructor for a specific type of chord (such as major or minor) which says nothing about spacing, inversion
     * and so forth- only what chord factors it includes. The constructor automatically adds a unison to the mix. NoteEvent
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
