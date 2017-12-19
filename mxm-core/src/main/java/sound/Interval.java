package sound;

import io.Log;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class Interval implements Comparator<Interval>, Comparable<Interval> {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    static final int MAX_SIZE = Pitch.MAX_VALUE - Pitch.MIN_VALUE;
    static final int MIN_VALUE = -MAX_SIZE;
    static final int MAX_VALUE = MAX_SIZE;
    private static final int TOTAL_NUM = (MAX_VALUE - MIN_VALUE) + 1;

    private static final Interval[] ALL;
    static {
        long startTime = System.nanoTime();
        ALL = new Interval[TOTAL_NUM];
        for(int size = MIN_VALUE; size <= MAX_VALUE; size++) {
            ALL[size - MIN_VALUE] = new Interval(size);
        }
        long endTime = System.nanoTime();
        Log.logStaticInit("Interval", Arrays.asList(ALL),endTime - startTime);
    }

    public static final Interval MIN = get(MIN_VALUE);
    public static final Interval MAX = get(MAX_VALUE);

    public static Interval get(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE)  throw new Error("INTERVAL:\tInterval out of range.");

        return ALL[value - MIN_VALUE];
    }

    public static @NotNull Collection<Interval> all() {
        return Collections.unmodifiableList(Arrays.asList(ALL));
    }

    public static @NotNull Interval random() {
        return get(ThreadLocalRandom.current().nextInt(MIN_VALUE, MAX_VALUE + 1));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private int value;
    private int octaves;
    private IntervalClass intervalClass;

    private Interval(int size) {
        this.value = size;
        this.octaves = size/12;
        this.intervalClass = IntervalClass.get((Math.abs(size) + MAX_VALUE) % 12);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public final int getSize() {
        return value;
    }

    public final int getOctaves() {
        return octaves;
    }

    public final @NotNull IntervalClass getIntervalClass() {
        return intervalClass;
    }

    public final @NotNull Interval plus(@NotNull Interval other) {
        int newSize = getSize() + other.getSize();
        if(newSize >= MIN_VALUE && newSize <= MAX_VALUE)
            return get(newSize);
        else
            throw new Error("INTERVAL:\tResultant interval out of range!");
    }

    public final @NotNull Interval minus(@NotNull Interval other) {
        int newSize = getSize() - other.getSize();
        if(newSize >= MIN_VALUE && newSize <= MAX_VALUE)
            return get(MIN_VALUE);
        else
            throw new Error("INTERVAL:\nResultant interval out of range!");
    }

    @Override
    public final @NotNull String toString() {
        if(octaves == 0)
            return intervalClass.toString();
        else if(octaves > 0)
            return intervalClass.toString() + "+" + octaves + "o";
        else
            return intervalClass.toString() + octaves + "o";
    }

    @Override
    public final int compareTo(@NotNull Interval other) {
        return Integer.compare(getSize(), other.getSize());
    }

    @Override
    public final int compare(@NotNull Interval i1, @NotNull Interval i2) {
        return Integer.compare(i1.getSize(), i2.getSize());
    }

    @Override
    public final boolean equals(Object object) {
        return this == object;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}
