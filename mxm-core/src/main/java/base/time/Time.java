package base.time;

import org.jetbrains.annotations.NotNull;
import javafx.util.Pair;

public abstract class Time implements Comparable<Time> {
    public static Measure PICKUP_MEASURE    = get(-1);
    public static Measure MEASURE_ONE       = get(0);

    static Pair<Integer,Integer> reduce(int numerator, int denominator) {
        // Euclid's gcd algorithm, everyone's favorite
        int a = numerator, b = denominator;
        if(a == 0){ denominator /= b; return new Pair<>(numerator,denominator); }
        if(b == 0){ numerator /= a; return new Pair<>(numerator,denominator); }
        while (a != b) {
            if (a > b) a -= b; else b -= a;
        }
        numerator   /= a;
        denominator /= a;
        return new Pair<>(numerator,denominator);
    }

    protected abstract int getNumerator();
    protected abstract int getDenominator();

    public final @NotNull Time plus(Time other) {
        int newNumerator = this.getNumerator() * other.getDenominator() + other.getNumerator() * this.getDenominator();
        int newDenominator = this.getDenominator() * other.getDenominator();
        return get(newNumerator, newDenominator);
    }
    public final @NotNull Time minus(Time other) {
        int newNumerator = this.getNumerator() * other.getDenominator() - other.getNumerator() * this.getDenominator();
        int newDenominator = this.getDenominator() * other.getDenominator();
        return get(newNumerator, newDenominator);
    }
    public final @NotNull Time times(int factor) {
        int newNumerator = this.getNumerator() * factor;
        return get(newNumerator, getDenominator());
    }
    public final @NotNull Time divBy(int factor) {
        int newDenominator = this.getDenominator() * factor;
        return get(getNumerator(), newDenominator);
    }


    public static Measure get(int measureNum) {
        return Measure.get(measureNum);
    }
    public static Time get(int numerator, int denominator) {
        Pair<Integer,Integer> pair = reduce(numerator,denominator);
        if(pair.getValue() == 1) return Measure.get(pair.getKey());
        else return new Count(pair.getKey(),pair.getValue());
    }
    public static Time get(int numerator, int denominator, int measureNum) {
        int newNumerator = numerator + denominator*measureNum;
        return get(newNumerator,denominator);
    }

    public abstract @NotNull Beat getBeat();
    public abstract @NotNull Measure getMeasure();

    @Override
    public abstract @NotNull String toString();

    @Override
    public final int compareTo(Time other) {
        if(this.equals(other)) return 0;
        if(this.getMeasure().getNum() > other.getMeasure().getNum()) return 1;
        if(this.getMeasure().getNum() > other.getMeasure().getNum()) return -1;
        return this.getBeat().compareTo(other.getBeat());
    }
    public final boolean equals(Time other) {
        // @@@@@@
        return this == other;
    }
    @Override
    public final int hashCode() {
        return getBeat().hashCode() + 31*getMeasure().hashCode();
    }
}

class Count extends Time {
    private final int numerator;
    private final int denominator;
    private final Beat beat;
    private final Measure measure;

    Count(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.beat = Beat.get(numerator % denominator, denominator);
        this.measure = Measure.get(this.numerator/this.denominator);
    }

    @Override
    protected final int getNumerator() { return numerator; }
    @Override
    protected final int getDenominator() { return denominator; }

    @Override
    public @NotNull Beat getBeat() { return beat; }
    @Override
    public @NotNull Measure getMeasure() { return measure; }
    @Override
    public String toString() { return measure.toString() + " & " + beat.toString(); }
}