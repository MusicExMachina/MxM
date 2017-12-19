package time;

import org.jetbrains.annotations.NotNull;
import util.Fraction;
import util.ReducedFraction;

/**
 * <p> <b>Class overview:</b>
 * The {@link Beat} class represents a subdivision of a measure- a fraction which amounts to under 1. Beats may be
 * manipulated like other {@link Time} representations, but may never be larger than 1 (i.e. greater than a measure)
 * and are always positive. </p>
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements a <b>factory design pattern</b>- users cannot construct new instances
 * of this class, but must call of() instead. This is mostly a stylistic choice.</p>
 *
 * @author Patrick Celentano
 */
public final class Beat implements Comparable<Beat> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    public static final Beat ZERO = Beat.of(0,1);
    public static final Beat ONE = Beat.of(1,1);

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

    public static Beat of(int num, int den) {
        return new Beat(num,den);
    }

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    private final ReducedFraction fraction;

    private Beat(int num, int den) {
        this.fraction = ReducedFraction.of(num,den);
    }

    public final @NotNull String toString() {
        return "b" + fraction;
    }

    @Override
    public final int compareTo(@NotNull Beat other) {
        return this.fraction.compareTo(other.fraction);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beat beat = (Beat) o;
        return this.fraction.equals(beat.fraction);
    }
}