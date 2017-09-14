package base.time;

import org.jetbrains.annotations.NotNull;

public interface ITime extends Comparable<ITime> {

    int getNumerator();
    int getDenominator();

    @NotNull Beat getBeat();
    @NotNull IMeasure getMeasure();

    default @NotNull ITime plus(ITime other) {
        int newNumerator = this.getNumerator() * other.getDenominator() + other.getNumerator() * this.getDenominator();
        int newDenominator = this.getDenominator() * other.getDenominator();
        return Time.get(newNumerator, newDenominator);
    }
    default @NotNull ITime minus(ITime other) {
        int newNumerator = this.getNumerator() * other.getDenominator() - other.getNumerator() * this.getDenominator();
        int newDenominator = this.getDenominator() * other.getDenominator();
        return Time.get(newNumerator, newDenominator);
    }
    default @NotNull ITime times(int factor) {
        int newNumerator = this.getNumerator() * factor;
        return Time.get(newNumerator, getDenominator());
    }
    default @NotNull ITime divBy(int factor) {
        int newDenominator = this.getDenominator() * factor;
        return Time.get(getNumerator(), newDenominator);
    }
    @Override
    default int compareTo(@NotNull ITime other) {
        int num1 = getNumerator() * other.getDenominator();
        int num2 = other.getNumerator() * getDenominator();
        return Integer.compare(num1,num2);
    }
}
