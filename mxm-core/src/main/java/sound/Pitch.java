package sound;

import io.Log;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class Pitch implements ISound, Comparator<Pitch>, Comparable<Pitch> {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    static final int MIN_VALUE = 0;
    static final int MAX_VALUE = 120;
    private static final int TOTAL_NUM = (MAX_VALUE - MIN_VALUE) + 1;

    private static final Pitch[] ALL;
    static {
        long startTime = System.nanoTime();
        ALL = new Pitch[TOTAL_NUM];
        for(int val = MIN_VALUE; val <= MAX_VALUE; val++) {
            ALL[val] = new Pitch(val);
        }
        Log.logStaticInit("Pitch", Arrays.asList(ALL),System.nanoTime() - startTime);
    }

    public static final Pitch MIN = get(MIN_VALUE);
    public static final Pitch MAX = get(MAX_VALUE);

    private static @NotNull Pitch get(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE)
            throw new Error("PITCH:\tInterval out of range.");
        return ALL[value - MIN_VALUE];
    }

    public static @NotNull Pitch get(@NotNull PitchClass pitchClass, int octave) {
        // Remember that we must add one to the octave to support the lowest octave, -1
        int value = pitchClass.value + (octave+1)*12;
        if(value >= MIN_VALUE && value <= MAX_VALUE) {
            return ALL[value - MIN_VALUE];
        }
        else throw new Error("PITCH:\tPitch out of range.");
    }

    public static @NotNull Collection<Pitch> all() {
        return Collections.unmodifiableList(Arrays.asList(ALL));
    }

    public static @NotNull Pitch random() {
        return get(ThreadLocalRandom.current().nextInt(MIN_VALUE, MAX_VALUE + 1));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final int value;
    private final int octave;
    private final PitchClass pitchClass;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Pitch(int value) {
        this.value = value;
        this.octave = value/12;
        this.pitchClass = PitchClass.get(value%12);
    }

    public final int getOctave() {
        return octave;
    }

    public final @NotNull PitchClass getPitchClass() {
        return pitchClass;
    }

    public final @NotNull Pitch plus(@NotNull Interval interval) {
        return Pitch.get(value + interval.getSize());
    }

    public final @NotNull Pitch minus(@NotNull Interval interval) {
        return Pitch.get(value - interval.getSize());
    }

    public final @NotNull Interval minus(@NotNull Pitch other) {
        return Interval.get(other.value - this.value);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public final @NotNull String toString() {
        return pitchClass.toString() + (octave - 1);
    }

    @Override
    public final int compareTo(@NotNull Pitch other) {
        return Integer.compare(value, other.value);
    }

    @Override
    public final int compare(@NotNull Pitch p1, @NotNull Pitch p2) {
        return Integer.compare(p1.value, p2.value);
    }

    @Override
    public final boolean equals(Object object) {
        return this == object;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}