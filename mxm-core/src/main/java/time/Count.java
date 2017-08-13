package time;

import com.sun.istack.internal.NotNull;

/**
 * Counts are the fundamental measurement of  musical time. Also note that counts are essentially just fractions. Note
 * that while their numerator and denominator are an improper fraction, the measure and beat are a proper one.
 *
 * This class is IMMUTABLE!
 */
public class Count implements ITime, Comparable<Count> {

    /** A zero Count */
    public static final Count ZERO = new Count(new Measure(0));
    public static final Count ONE = new Count(new Measure(1));
    /** The numerator of this Count as an IMPROPER fraction!) */
    private int numerator;
    /** The denominator of this Count */
    private int denominator;

    private final Measure measure;
    private final Beat beat;

    public Count(@NotNull Measure measure) {
        this.measure = measure;
        this.beat = Beat.ZERO;
    }

    public Count(@NotNull Measure measure, @NotNull Beat beat) {
        this.measure = measure;
        this.beat = beat;
    }

    public Beat getBeat() {
        return beat;
    }
    public Measure getMeasure() {
        return measure;
    }

    public String toString() { return measure.toString() + " & " + beat.toString(); }

    public Count plus(Count other) {
        Count beatAddition = this.beat.plus(other.beat);
        return new Count(this.measure.plus(beatAddition.measure),beatAddition.getBeat());
    }

    public Count minus(Count other) {
        Count beatSubtraction = this.beat.plus(other.beat);
        return new Count(this.measure.minus(beatSubtraction.measure),beatSubtraction.getBeat());
    }

    public float toFloat() {
        return  measure.toFloat() + beat.toFloat();
    }

    public double toDouble() {
        return measure.toDouble() + beat.toDouble();
    }

    public int compareTo(Count other) {
        return Double.compare(this.toDouble(),other.toDouble());
    }

    public int compare(Count c1, Count c2) {
        return Double.compare(c1.toDouble(),c2.toDouble());
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Count count = (Count) o;
        return numerator == count.numerator && denominator == count.denominator;
    }

    public int hashCode() {
        return beat.hashCode() + 31*measure.hashCode();
    }

    @Override
    public ITime plus(ITime other) {
        return null;
    }

    @Override
    public ITime minus(ITime other) {
        return null;
    }

    @Override
    public ITime times(int amount) {
        return null;
    }

    @Override
    public ITime dividedBy(int amount) {
        return null;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }
}