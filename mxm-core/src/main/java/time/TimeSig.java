package time;

import org.jetbrains.annotations.NotNull;
import util.timeline.Fraction;

import java.util.Objects;

public final class TimeSig {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Static variables                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static final TimeSig CUT_TIME = of(2,2);
    public static final TimeSig COMMON_TIME = of(4,4);
    public static final TimeSig TWO_FOUR = of(2,4);
    public static final TimeSig THREE_FOUR = of(3,4);
    public static final TimeSig FOUR_FOUR = of(4,4);
    public static final TimeSig FIVE_FOUR = of(5,4);
    public static final TimeSig THREE_EIGHT = of(3,8);
    public static final TimeSig FIVE_EIGHT = of(5,8);
    public static final TimeSig SIX_EIGHT = of(6,8);
    public static final TimeSig SEVEN_EIGHT = of(7,8);
    public static final TimeSig NINE_EIGHT = of(9,8);
    public static final TimeSig ELEVEN_EIGHT = of(11,8);
    public static final TimeSig TWELVE_EIGHT = of(12,8);
    public static final TimeSig DEFAULT = COMMON_TIME;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Member variables                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static @NotNull TimeSig of(int num, int den) {
        if(num <= 0) throw new Error("Time Signature: The numerator must greater than 0!");
        if(den <= 0) throw new Error("Time Signature: The denominator must greater than 0!");
        if(!((den & (den - 1)) == 0)) throw new Error("Time Signature: The denominator must be a power of two!");
        return new TimeSig(Fraction.of(num, den));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Instance methods                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final Fraction fraction;

    private TimeSig(Fraction fraction) {
        this.fraction = fraction;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Override methods                                                                          //
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
}
