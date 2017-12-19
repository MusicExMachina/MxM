package time;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Measure implements Comparable<Measure> {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static @NotNull Measure of(int num) {
        return new Measure(num);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final int number;

    private Measure(int number) {
        this.number = number;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public final int getNumber() {
        return number;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public final @NotNull String toString() {
        return "m." + number;
    }

    @Override
    public final int compareTo(@NotNull Measure other) {
        return Integer.compare(number, other.number);
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Measure measure = (Measure) object;
        return number == measure.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}
