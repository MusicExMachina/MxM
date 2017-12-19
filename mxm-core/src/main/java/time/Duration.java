package time;

import org.jetbrains.annotations.NotNull;
import util.ReducedFraction;

import java.util.Objects;

public class Duration implements Comparable<Duration> {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static Duration of(int measNum) {
        return of(measNum, 1);
    }

    public static Duration of(int num, int den, int measNum) {
        return of(num + (den * measNum), den);
    }

    public static Duration of(int num, int den) {
        return of(ReducedFraction.of(num, den));
    }

    static Duration of(ReducedFraction fraction) {
        return new Duration(fraction);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    final ReducedFraction fraction;
    private final Measure measure;
    private final Beat beat;

    private Duration(ReducedFraction fraction) {
        this.fraction = fraction;
        beat = Beat.of(fraction.getNumerator() % fraction.getDenominator(), fraction.getDenominator());
        measure = Measure.of(fraction.getNumerator() / fraction.getDenominator());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public @NotNull Beat getBeat() {
        return beat;
    }

    public @NotNull Measure getMeasure() {
        return measure;
    }

    public @NotNull Duration plus(Duration duration) {
        return Duration.of(fraction.plus(duration.fraction));
    }
    public @NotNull Duration minus(Duration duration) {
        return Duration.of(fraction.minus(duration.fraction));
    }
    public @NotNull Duration times(int factor) {
        return Duration.of(fraction.times(factor));
    }
    public @NotNull Duration divBy(int factor) {
        return Duration.of(fraction.divBy(factor));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public final @NotNull String toString() {
        return fraction.toString();
    }

    @Override
    public final int compareTo(@NotNull Duration other) {
        return fraction.compareTo(other.fraction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duration duration = (Duration) o;
        return Objects.equals(fraction, duration.fraction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fraction);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}
