package time;

import org.jetbrains.annotations.NotNull;
import util.Fraction;

import java.util.Objects;

public final class TimeSig {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static final TimeSig CUT_TIME = get(2,2);
    public static final TimeSig COMMON_TIME = get(4,4);

    public static final TimeSig TWO_FOUR = get(2,4);
    public static final TimeSig THREE_FOUR = get(3,4);
    public static final TimeSig FOUR_FOUR = get(4,4);
    public static final TimeSig FIVE_FOUR = get(5,4);

    public static final TimeSig THREE_EIGHT = get(3,8);
    public static final TimeSig FIVE_EIGHT = get(5,8);
    public static final TimeSig SIX_EIGHT = get(6,8);
    public static final TimeSig SEVEN_EIGHT = get(7,8);
    public static final TimeSig NINE_EIGHT = get(9,8);
    public static final TimeSig ELEVEN_EIGHT = get(11,8);
    public static final TimeSig TWELVE_EIGHT = get(12,8);

    public static final TimeSig DEFAULT = COMMON_TIME;

    public static @NotNull TimeSig get(int num, int den) {
        if(num <= 0) throw new Error("Time Signature: The numerator must greater than 0!");
        if(den <= 0) throw new Error("Time Signature: The denominator must greater than 0!");
        if(!((den & (den - 1)) == 0)) throw new Error("Time Signature: The denominator must be a power of two!");
        return new TimeSig(Fraction.of(num, den));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final Fraction fraction;

    private TimeSig(Fraction fraction) {
        this.fraction = fraction;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public final @NotNull String toString() {
        return fraction.toString()+ " time";
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TimeSig timeSignature = (TimeSig) object;
        return fraction.equals(timeSignature.fraction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fraction);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}
