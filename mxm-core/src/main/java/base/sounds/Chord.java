package base.sounds;

import org.jetbrains.annotations.NotNull;

import base.relative.ChordClass;
import base.IntervalClass;
import base.PitchClass;

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 */
public class Chord implements ISound, Iterable<PitchClass> {

    public static @NotNull Chord get(@NotNull PitchClass root, @NotNull ChordClass chordClass) {
        return new Chord(root,chordClass);
    }
    /** */
    private PitchClass root;
    /** */
    private HashSet<PitchClass> factors;
    /** */
    private ChordClass chordClass;

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
