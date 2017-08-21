package base.time;

import javafx.util.Pair;

import java.util.Comparator;

public class Beat implements Comparable<Beat>, Comparator<Beat> {
    public static Beat ZERO = Beat.get(0,1);
    public static Beat ONE = Beat.get(1,0);

    private int numerator;
    private int denominator;

    public static Beat get(int numerator, int denominator) {
        return new Beat(numerator,denominator);
    }

    private Beat(int numerator, int denominator) {
        Pair<Integer,Integer> pair = Time.reduce(numerator,denominator);
        this.numerator = pair.getKey();
        this.denominator = pair.getValue();
    }

    public int getNumerator() {
        return numerator;
    }
    public int getDenominator() {
        return denominator;
    }

    public String toString() { return null; }
    public float toFloat() {
        return (float)numerator/denominator;
    }
    public double toDouble() {
        return (double)numerator/denominator;
    }

    @Override
    public int compareTo(Beat other) { return Double.compare(this.toDouble(),other.toDouble()); }
    @Override
    public int compare(Beat b1, Beat b2) { return Double.compare(b1.toDouble(),b2.toDouble()); }
}