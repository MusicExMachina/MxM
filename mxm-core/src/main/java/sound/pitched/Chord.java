package sound.pitched;

import sound.ISound;
import util.io.Log;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class Chord implements ISound, Iterable<PitchClass> {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static final int TOTAL_NUM = ChordClass.TOTAL_NUM * PitchClass.TOTAL_NUM;
    private static final Chord[] ALL;

    static {
        long startTime = System.nanoTime();
        ALL = new Chord[PitchClass.TOTAL_NUM * ChordClass.TOTAL_NUM];
        for(int pcVal = PitchClass.MIN_VALUE; pcVal <= PitchClass.MAX_VALUE; pcVal++) {
            for(int ccVal = 0; ccVal < ChordClass.TOTAL_NUM; ccVal++) {
                int chordVal = (pcVal * ChordClass.TOTAL_NUM) + ccVal;
                ALL[chordVal] = new Chord(chordVal,PitchClass.get(pcVal), ChordClass.get(ccVal));
            }
        }
        Log.logStaticInit("Chord", Arrays.asList(ALL),System.nanoTime() - startTime);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static @NotNull Chord get(@NotNull PitchClass root, @NotNull ChordClass chordClass) {
        return ALL[(root.value * ChordClass.TOTAL_NUM) + chordClass.getID()];
    }

    public static @NotNull Collection<Chord> all() {
        return Collections.unmodifiableList(Arrays.asList(ALL));
    }

    public static @NotNull Chord random() {
        return get(PitchClass.random(),ChordClass.random());
    }

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    private final int value;
    private final PitchClass root;
    private final ChordClass chordClass;
    private final ArrayList<PitchClass> factorsInOrder;

    private Chord(int chordVal, @NotNull PitchClass root, @NotNull ChordClass chordClass) {
        this.value = chordVal;
        this.root = root;
        this.chordClass = chordClass;

        this.factorsInOrder = new ArrayList<>();
        // For every interval in the ChordClass (which holds all intervals above the root, add a sound factor that is
        // also that high above the root. Sound that ninths become seconds,m as to interval classes wrap at the octave
        for(IntervalClass intervalClass : chordClass) {
            factorsInOrder.add(root.transpose(intervalClass));
        }
        //this.harmony = Harmony.of(factorsInOrder);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public final @NotNull PitchClass getRoot() {
        return root;
    }

    public final @NotNull ChordClass getChordClass() {
        return chordClass;
    }

    @Override
    public final @NotNull String toString() {
        return root.toString() + " " + chordClass.toString();
    }

    @Override
    public final @NotNull Iterator<PitchClass> iterator() {
        return factorsInOrder.iterator();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}
