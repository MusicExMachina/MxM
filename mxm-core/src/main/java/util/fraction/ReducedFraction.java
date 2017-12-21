package util.fraction;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ReducedFraction implements Comparable<ReducedFraction> {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static @NotNull ReducedFraction of(int num, int den) {
        return new ReducedFraction(num, den);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final int numerator;
    private final int denominator;

    private @NotNull ReducedFraction(int numerator, int denominator) {
        if (numerator == 0) {
            this.numerator = numerator;
            this.denominator = 1;
            return;
        }
        if (denominator == 0) {
            throw new Error("Cannot create a reduced fraction with a denominator of 0!");
        }

        // Euclid's gcd algorithm, everyone's favorite
        int a = numerator, b = denominator;
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        this.numerator = numerator / a;
        this.denominator = denominator / a;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public final int getNumerator() {
        return numerator;
    }

    public final int getDenominator() {
        return denominator;
    }

    public final @NotNull ReducedFraction plus(@NotNull ReducedFraction other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return ReducedFraction.of(newNumerator, newDenominator);
    }

    public final @NotNull ReducedFraction minus(@NotNull ReducedFraction other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return ReducedFraction.of(newNumerator, newDenominator);
    }
    public final @NotNull ReducedFraction times(@NotNull int factor) {
        int newNumerator = this.numerator * factor;
        return ReducedFraction.of(newNumerator, denominator);
    }

    public final @NotNull ReducedFraction divBy(@NotNull int factor) {
        int newDenominator = this.denominator * factor;
        return ReducedFraction.of(numerator, newDenominator);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public final @NotNull String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public final int compareTo(@NotNull ReducedFraction other) {
        int num1 = numerator * other.denominator;
        int num2 = other.numerator * denominator;
        return Integer.compare(num1,num2);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReducedFraction fraction = (ReducedFraction) o;
        return (numerator == fraction.numerator) && (denominator == fraction.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}
