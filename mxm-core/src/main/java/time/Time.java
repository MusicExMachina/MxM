package time;

import org.jetbrains.annotations.NotNull;
import util.ReducedFraction;

import java.util.Objects;

public final class Time implements Comparable<Time> {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static final Time PICKUP_MEASURE = Time.of(0, 1);
    public static final Time MEASURE_ONE = Time.of(1, 1);

    public static Time of(int measNum) {
        return of(measNum, 1);
    }

    public static Time of(int num, int den, int measNum) {
        return of(num + (den * measNum), den);
    }

    public static Time of(int num, int den) {
        return of(ReducedFraction.of(num, den));
    }

    private static Time of(ReducedFraction fraction) {
        return new Time(fraction);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final ReducedFraction fraction;
    private final Measure measure;
    private final Beat beat;

    private Time(ReducedFraction fraction) {
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

    public @NotNull Time plus(Duration duration) {
        return Time.of(fraction.plus(duration.fraction));
    }
    public @NotNull Time minus(Duration duration) {
        return Time.of(fraction.minus(duration.fraction));
    }
    public @NotNull Duration minus(Time duration) {
        return Duration.of(fraction.minus(duration.fraction));
    }
    public @NotNull Time times(int factor) {
        return Time.of(fraction.times(factor));
    }
    public @NotNull Time divBy(int factor) {
        return Time.of(fraction.divBy(factor));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public final @NotNull String toString() {
        return fraction.toString();
    }

    @Override
    public final int compareTo(@NotNull Time other) {
        return fraction.compareTo(other.fraction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(fraction, time.fraction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fraction);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}