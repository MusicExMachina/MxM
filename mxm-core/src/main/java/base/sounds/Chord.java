package base.sounds;

import base.relative.ChordClass;
import base.relative.IntervalClass;
import base.relative.PitchClass;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Chord implements ISound, Iterable<PitchClass> {

    private HashSet<PitchClass> factors;
    private ChordClass chordClass;

    public static Chord get(PitchClass root, ChordClass chordClass) {
        return new Chord(root,chordClass);
    }

    protected Chord(Set<PitchClass> pitchClasses){
        this.factors = (HashSet<PitchClass>) pitchClasses;
    }

    protected Chord(PitchClass root, ChordClass chordClass) {
        factors = new HashSet<>();
        /* For every interval in the ChordClass (which holds all intervals above
            the root, add a chord factor that is also that high above the root */
        for(IntervalClass intervalClass : chordClass) {
            factors.add(root.transpose(intervalClass));
        }
        this.chordClass = chordClass;
    }

    @Override
    public Iterator<PitchClass> iterator() {
        return factors.iterator();
    }
}
