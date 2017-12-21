package sound.attributes;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class Dynamic implements Comparator<Dynamic>, Comparable<Dynamic> {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final static int MIN_VALUE = 0;
    private final static int MAX_VALUE = 127;

    public static final Dynamic NIENTE = of(0);
    public static final Dynamic PIANISSISSIMO = of(16);
    public static final Dynamic PIANISSIMO = of(32);
    public static final Dynamic PIANO = of(48);
    public static final Dynamic MEZZO_PIANO = of(64);
    public static final Dynamic MEZZO_FORTE = of(80);
    public static final Dynamic FORTE = of(96);
    public static final Dynamic FORTISSIMO = of(112);
    public static final Dynamic FORTISSISSIMO = of(127);

    public static final Dynamic MIN = of(MIN_VALUE);
    public static final Dynamic MAX = of(MAX_VALUE);

    public static @NotNull Dynamic of(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE)
            throw new Error("PITCH:\tInterval out of range.");

        return new Dynamic(value);
    }

    public static @NotNull Dynamic random() {
        return of(ThreadLocalRandom.current().nextInt(MIN_VALUE, MAX_VALUE + 1));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final int value;

    private Dynamic(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(@NotNull Dynamic other) {
        return Integer.compare(value, other.value);
    }

    @Override
    public int compare(Dynamic p1, Dynamic p2) {
        return Integer.compare(p1.value, p2.value);
    }

    @Override
    public String toString() {
        if (value == 0)  return value + " (n)";
        if (value < 16)  return value + " (ppp)";
        if (value < 32)  return value + " (pp)";
        if (value < 48)  return value + " (p)";
        if (value < 64)  return value + " (mp)";
        if (value < 80)  return value + " (mf)";
        if (value < 96)  return value + " (f)";
        if (value < 112) return value + " (ff)";
        return value + " (fff)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dynamic dynamic = (Dynamic) o;
        return value == dynamic.value;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}