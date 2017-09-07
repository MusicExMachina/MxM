package base;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.*;

// IMMUTABLE;
public class Sonority implements ISound {

    /** The pitches inside this sonority */
    private TreeSet<Pitch> pitches;
    private Harmony harmony;

    public @NotNull Sonority get(@NotNull Pitch... pitches) {
        return new Sonority(Arrays.asList(pitches));
    }
    public @NotNull Sonority get(@NotNull Collection<Pitch> pitches) {
        return new Sonority(pitches);
    }

    /* Sonorities **/
    private Sonority(Collection<Pitch> pitches) {
        this.pitches = new TreeSet<>(pitches);
        Set<PitchClass> pitchClasses = new HashSet<>();
        for(Pitch pitch : pitches) {
            pitchClasses.add(pitch.getPitchClass());
        }
        this.harmony = Harmony.get(pitchClasses);
    }

    private Sonority(@NotNull Sonority s1, @NotNull Sonority s2) {
        this.pitches = new TreeSet<>(s1.pitches);
        this.pitches.addAll(s2.pitches);
        this.harmony = s1.harmony.plus(s2.harmony);
    }
    public @NotNull Sonority plus(Sonority other) {
        return new Sonority(other.pitches);
    }

    public Sonority subSonority(Pitch from, Pitch to) {
        return new Sonority(pitches.subSet(from,to));
    }

    public Harmony getHarmony() {
        return harmony;
    }
}
