package form.attributes;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Tempo implements Comparable<Tempo> {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Static variables                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static final Tempo DEFAULT = of(120);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Static methods                                                                            //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static @NotNull Tempo of(int bpm) {
        if(bpm <= 0) throw new Error("Tempo:\tInvalid tempo! (" + bpm + " bpm)");

        return new Tempo(bpm);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Member variables                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final int bpm;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Instance methods                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Tempo(int bpm) {
        this.bpm = bpm;
    }

    public final int getBPM() {
        return bpm;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //  Override methods                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public final @NotNull String toString() {
        return bpm + " bpm";
    }
    @Override
    public final int compareTo(@NotNull Tempo other) {
        return Integer.compare(bpm, other.bpm);
    }
    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Tempo tempo = (Tempo) object;
        return bpm == tempo.bpm;
    }
    @Override
    public int hashCode() {
        return Objects.hash(bpm);
    }
}