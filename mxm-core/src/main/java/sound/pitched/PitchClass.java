package sound.pitched;

import util.io.Log;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class PitchClass {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    static final int MIN_VALUE = 0;
    static final int MAX_VALUE = 11;
    static final int TOTAL_NUM = (MAX_VALUE - MIN_VALUE) + 1;

    private static final PitchClass[] ALL;
    static {
        long startTime = System.nanoTime();
        ALL = new PitchClass[TOTAL_NUM];
        for(int val = MIN_VALUE; val <= MAX_VALUE; val++) {
            ALL[val] = new PitchClass(val);
        }
        Log.logStaticInit("Pitch class", Arrays.asList(ALL),System.nanoTime() - startTime);
    }

    public static final PitchClass C_FLAT = get(11), C_NATURAL = get(0), C_SHARP = get(1);
    public static final PitchClass D_FLAT = get(1), D_NATURAL = get(2), D_SHARP = get(3);
    public static final PitchClass E_FLAT = get(3), E_NATURAL = get(4), E_SHARP = get(5);
    public static final PitchClass F_FLAT = get(4), F_NATURAL = get(5), F_SHARP = get(6);
    public static final PitchClass G_FLAT = get(6), G_NATURAL = get(7), G_SHARP = get(8);
    public static final PitchClass A_FLAT = get(8), A_NATURAL = get(9), A_SHARP = get(10);
    public static final PitchClass B_FLAT = get(10), B_NATURAL = get(11), B_SHARP = get(0);

    static @NotNull PitchClass get(int value) {
        if (value >= MIN_VALUE && value <= MAX_VALUE) {
            return ALL[value];
        }
        else throw new Error("PITCH CLASS: Pitch class of size " + value + " is out of range.");
    }
    public static @NotNull Collection<PitchClass> all() {
        return Collections.unmodifiableList(Arrays.asList(ALL));
    }
    /**
     * Returns a random instance of this class
     * @return a random valid sound.pitched class
     */
    public static @NotNull PitchClass random() {
        return get(ThreadLocalRandom.current().nextInt(MIN_VALUE, MAX_VALUE + 1));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    final int value;

    private PitchClass(int value) {
        this.value = value;
    }

    public final @NotNull PitchClass transpose(@NotNull IntervalClass intervalClass) {
        return PitchClass.get((value + intervalClass.getSize()) % 12);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public final @NotNull String toString() {
        switch (value) {
            case 0:     return "C";
            case 1:     return "Db";
            case 2:     return "D";
            case 3:     return "Eb";
            case 4:     return "E";
            case 5:     return "F";
            case 6:     return "Gb";
            case 7:     return "G";
            case 8:     return "Ab";
            case 9:     return "A";
            case 10:    return "Bb";
            case 11:    return "B";
            default:    return "ERROR";
        }
    }

    @Override
    public final boolean equals(Object other) {
        return this == other;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}
