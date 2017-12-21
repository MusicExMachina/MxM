package sound.pitched;

import util.io.Log;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class IntervalClass  implements Comparator<IntervalClass>, Comparable<IntervalClass> {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static final int MIN_SIZE = 0;
    public static final int MAX_SIZE = 11;
    private static final int TOTAL_NUM = (MAX_SIZE - MIN_SIZE) + 1;

    private static final IntervalClass[] ALL;
    static {
        long startTime = System.nanoTime();
        ALL = new IntervalClass[TOTAL_NUM];
        for(int val = MIN_SIZE; val <= MAX_SIZE; val++) {
            ALL[val] = new IntervalClass(val);
        }
        Log.logStaticInit("Interval class", Arrays.asList(ALL),System.nanoTime() - startTime);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static final IntervalClass UNISON = get(0);
    public static final IntervalClass MINOR_SECOND = get(1);
    public static final IntervalClass MAJOR_SECOND = get(2);
    public static final IntervalClass MINOR_THIRD = get(3);
    public static final IntervalClass MAJOR_THIRD = get(4);
    public static final IntervalClass PERFECT_FOURTH = get(5);
    public static final IntervalClass TRITONE = get(6);
    public static final IntervalClass PERFECT_FIFTH = get(7);
    public static final IntervalClass MINOR_SIXTH = get(8);
    public static final IntervalClass MAJOR_SIXTH = get(9);
    public static final IntervalClass MINOR_SEVENTH = get(10);
    public static final IntervalClass MAJOR_SEVENTH = get(11);

    public static IntervalClass get(int size) {
        if(size >= MIN_SIZE && size <= MAX_SIZE) {
            return ALL[size];
        }
        else throw new Error("INTERVAL CLASS:\tInterval class out of range.");
    }

    public static @NotNull Collection<IntervalClass> all() {
        return Collections.unmodifiableList(Arrays.asList(ALL));
    }

    public static @NotNull IntervalClass random() {
        return get(ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE + 1));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final int value;

    private IntervalClass(int size) {
        value = size;
    }

    public final int getSize() {
        return value;
    }

    public final @NotNull IntervalClass plus(@NotNull IntervalClass other) {
        return get((this.getSize() + other.getSize()) % 12);
    }

    public final @NotNull IntervalClass minus(@NotNull IntervalClass other) {
        return get((this.getSize() - other.getSize() + 12) % 12);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public final @NotNull String toString() {
        switch (getSize()) {
            case 0:     return "PU";
            case 1:     return "m2";
            case 2:     return "M2";
            case 3:     return "m3";
            case 4:     return "M3";
            case 5:     return "P4";
            case 6:     return "TT";
            case 7:     return "P5";
            case 8:     return "m6";
            case 9:     return "M6";
            case 10:    return "m7";
            case 11:    return "M7";
            default:    return "ERROR";
        }
    }

    @Override
    public final int compareTo(@NotNull IntervalClass other) {
        return new Integer(getSize()).compareTo(other.getSize());
    }

    @Override
    public final int compare(@NotNull IntervalClass ic1, @NotNull IntervalClass ic2) {
        return new Integer(ic1.getSize()).compareTo(ic2.getSize());
    }

    @Override
    public final boolean equals(Object object) {
        return this == object;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}