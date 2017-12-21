package util.timeline;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Fraction implements Comparable<Fraction> {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static @NotNull Fraction of(int num, int den) {
        return new Fraction(num, den);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final int numerator;
    private final int denominator;

    private Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new Error("Cannot create a fraction with a denominator of 0!");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public final int getNumerator() {
        return numerator;
    }

    public final int getDenominator() {
        return denominator;
    }

    public final @NotNull Fraction plus(@NotNull Fraction other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return Fraction.of(newNumerator, newDenominator);
    }
    
    public final @NotNull Fraction minus(@NotNull Fraction other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return Fraction.of(newNumerator, newDenominator);
    }

    public final @NotNull Fraction times(@NotNull int factor) {
        int newNumerator = this.numerator * factor;
        return Fraction.of(newNumerator, denominator);
    }

    public final @NotNull Fraction divBy(@NotNull int factor) {
        int newDenominator = this.denominator * factor;
        return Fraction.of(numerator, newDenominator);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public final @NotNull String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public final int compareTo(@NotNull Fraction other) {
        int num1 = numerator * other.denominator;
        int num2 = other.numerator * denominator;
        return Integer.compare(num1,num2);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return (numerator == fraction.numerator) && (denominator == fraction.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
