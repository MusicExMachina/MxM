package theory.composite;

import properties.sound.ISound;
import theory.harmony.Harmony;
import properties.sound.Pitch;
import properties.sound.PitchClass;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

// IMMUTABLE;
public class Sonority implements Iterable<Pitch> {

    /** The pitches inside this sonority */
    private TreeSet<Pitch> pitches;
    private Harmony harmony;

    public static @NotNull Sonority get(@NotNull Pitch... pitches) {
        return new Sonority(Arrays.asList(pitches));
    }
    public static @NotNull Sonority get(@NotNull Collection<Pitch> pitches) {
        return new Sonority(pitches);
    }

    /* Sonorities **/
    private Sonority(Collection<Pitch> pitches) {
        this.pitches = new TreeSet<>(pitches);
        Set<PitchClass> pitchClasses = new HashSet<>();
        pitches.parallelStream().forEach((Pitch pitch) -> pitchClasses.add(pitch.getPitchClass()));
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

    public @NotNull Sonority subSonority(Pitch from, Pitch to) {
        return new Sonority(pitches.subSet(from,to));
    }

    public @NotNull Harmony getHarmony() {
        return harmony;
    }






    /**
     * Returns a stream of all the pitches in this sonority
     * @return The
     */
    public @NotNull Stream<Pitch> stream() {
        return pitches.stream();
    }
    /**
     * Returns a stream of all the pitches in this sonority which may be accessed in parallel
     * @return The
     */
    public @NotNull Stream<Pitch> parallelStream() {
        return pitches.parallelStream();
    }
    /**
     * Returns an iterator over all
     * @return The
     */
    @Override
    public @NotNull Iterator<Pitch> iterator() {
        return pitches.iterator();
    }
    /**
     * Returns an iterator over all
     * @return The
     */
    @Override
    public @NotNull Spliterator<Pitch> spliterator() {
        return pitches.spliterator();
    }
}
