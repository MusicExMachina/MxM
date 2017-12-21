package form.time;

import org.jetbrains.annotations.NotNull;
import util.fraction.ReducedFraction;

import java.util.Objects;

public final class Measure implements Comparable<Measure> {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Static variables                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static final Measure PICKUP = Measure.of(0);
    public static final Measure ONE = Measure.of(1);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Static methods                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static @NotNull Measure of(int num) {
        return new Measure(num);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Member variables                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final int number;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Instance methods                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Measure(int number) {
        this.number = number;
    }

    public final int getNumber() {
        return number;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Package-private methods                                                                   //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    final @NotNull ReducedFraction getFraction() {
        return ReducedFraction.of(number,1);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Override methods                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public final @NotNull String toString() {
        return "m." + number;
    }
    @Override
    public final int compareTo(@NotNull Measure other) {
        return Integer.compare(number, other.number);
    }
    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Measure measure = (Measure) object;
        return number == measure.number;
    }
    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
