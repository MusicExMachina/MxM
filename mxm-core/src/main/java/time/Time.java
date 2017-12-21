package time;

import org.jetbrains.annotations.NotNull;
import util.ReducedFraction;

import java.util.Objects;

public final class Time implements Comparable<Time> {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Static methods                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static @NotNull Time of(@NotNull Measure measure) {
        return of(measure.getFraction());
    }

    public static @NotNull Time of(@NotNull Beat beat, @NotNull Measure measure) {
        return of(measure.getFraction().plus(beat.getFraction()));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Package-private methods                                                                   //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    static @NotNull Time of(@NotNull ReducedFraction fraction) {
        return new Time(fraction);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Member variables                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final ReducedFraction fraction;
    private final Measure measure;
    private final Beat beat;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Instance methods                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Time(ReducedFraction fraction) {
        this.fraction = fraction;
        beat = Beat.of(fraction.getNumerator() % fraction.getDenominator(), fraction.getDenominator());
        measure = Measure.of(fraction.getNumerator() / fraction.getDenominator());
    }

    public final @NotNull Beat getBeat() {
        return beat;
    }
    public final @NotNull Measure getMeasure() {
        return measure;
    }
    public final @NotNull Time plus(Duration duration) {
        return Time.of(fraction.plus(duration.getFraction()));
    }
    public final @NotNull Time minus(Duration duration) {
        return Time.of(fraction.minus(duration.getFraction()));
    }
    public final @NotNull Duration minus(Time duration) {
        return Duration.of(fraction.minus(duration.fraction));
    }
    public final @NotNull Time times(int factor) {
        return Time.of(fraction.times(factor));
    }
    public final @NotNull Time divBy(int factor) {
        return Time.of(fraction.divBy(factor));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Overrides                                                                                 //
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
}