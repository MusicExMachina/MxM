package musicTheory;

import base.PitchClass;

import java.util.Iterator;

/**
 * Chords are unrealized (that is to say, not mapped directly to pitches) collections of pitch classes with a definite
 * root and type. Note that chords will not be used in all applications of MxM, as often, we simply don't have a word
 * for a particular chord type. They are, however, useful in analyzing music where all we get to read is a sequence of
 * chords, such as a lead sheet.
 */
public class Chord implements Iterable<PitchClass> {

    /* The most important pitch class in the chord. */
    private PitchClass root;

    private ChordClass chordClass;

    public Chord(PitchClass root, ChordClass chordClass) {
        this.root = root;
        this.chordClass = chordClass;
    }

    @Override
    public Iterator<PitchClass> iterator() {
        return null;
    }
}
