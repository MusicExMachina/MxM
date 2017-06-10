package musicTheory;

import base.sound.IntervalClass;
import base.sound.PitchClass;
import musicTheory.types.ChordClass;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Chords are unrealized (that is to say, not mapped directly to pitches) collections of sound classes with a definite
 * root and type. Note that chords will not be used in all applications of MxM, as often, we simply don't have a word
 * for a particular chord type. They are, however, useful in analyzing music where all we get to read is a sequence of
 * chords, such as a lead sheet.
 */
public class Chord implements Iterable<PitchClass> {

    /* A list of the chord factors, starting from the root, and moving upward in thirds, fourths, etc. */
    private ArrayList<PitchClass> factors;

    private ChordClass chordClass;

    /**
     * The constructor for a chord involves a root sound class (say, F#) and a ChordClass (say, ChordClass.MAJOR)
     * @param root The root of this chord in a traditional music theory sense)
     * @param chordClass The type of chord this is (Major? Minor? Sus2?)
     */
    public Chord(PitchClass root, ChordClass chordClass) {
        /* For every interval in the ChordClass (which holds all intervals above
            the root, add a chord factor that is also that high above the root */
        for(IntervalClass intervalClass : chordClass) {
            factors.add(root.transpose(intervalClass));
        }
        this.chordClass = chordClass;
    }

    @Override
    public Iterator<PitchClass> iterator() {
        return null;
    }
}
