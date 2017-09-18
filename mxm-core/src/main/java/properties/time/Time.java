package properties.time;

import properties.AbstractFractionProp;
import properties.AbstractIntegerProp;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public abstract class Time implements Comparable<Time> {

    public static final ITime ZERO = Time.get(0);

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    /***/
    public static IMeasure PICKUP_MEASURE = get(-1);
    /***/
    public static IMeasure MEASURE_ONE = get(0);

    //////////////////////////////
    // Static Methods           //
    //////////////////////////////

    public static IMeasure get(int measureNum) {
        return new Measure(measureNum);
    }

    public static ITime get(int num, int den) {
        // If the denominator reduces to 0
        if(num % den == 0) return new Measure(num / den);
        else return new Count(num, den);
    }

    public static ITime get(int num, int den, int measNum) {
        int newNum = num + den * measNum;
        return get(newNum,den);
    }

    @Override
    public abstract @NotNull String toString();


}


final class Count extends AbstractFractionProp implements ITime {

    private Beat beat;
    private IMeasure measure;

    /**
     * A constructor for a count
     *
     * @param num the numerator of this count
     * @param den the denominator of this count
     */
    protected Count(int num, int den) {
        super(num, den);
        beat = Beat.get(num,den);
        measure = Time.get(num/den);
    }

    @Override
    public @NotNull Beat getBeat() {
        return beat;
    }

    @Override
    public @NotNull IMeasure getMeasure() {
        return measure;
    }
}



final class Measure extends AbstractIntegerProp implements IMeasure {

    Measure(int value) {
        super(value);
    }

    @Override
    public final int getNumerator() {
        return getValue();
    }
    @Override
    public final int getDenominator() {
        return 1;
    }
    @Override
    public final @NotNull Beat getBeat() {
        return Beat.ZERO;
    }
    @Override
    public final @NotNull Measure getMeasure() {
        return this;
    }
    @Override
    public final @NotNull String toString() { return "m. " + super.toString(); }
}
