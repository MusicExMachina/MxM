package properties.time;

import properties.AbstractIntegerProp;
import properties.sound.Pitch;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * <p> <b>Class Overview:</b>
 * Tempo is a simple class which represents the speed of the passage of time in beats per minute. Now, beats are a
 * rather abstract concept, but as this is the standard way of measuring the passage of time in traditional Western
 * music, we will borrow it as well.</p>
 *
 * <p> <b>Design Details:</b>
 * This class is <i>immutable</i> and implements a <b>factory design pattern</b>- users cannot construct new instances
 * of this class, but must call get() instead. This is mostly a stylistic choice.</p>
 *
 * @author Patrick Celentano
 */
public final class Tempo extends AbstractIntegerProp implements Comparator<Tempo>, Comparable<Tempo> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    public static final Tempo DEFAULT = get(120);

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

    /**
     * This get method dresses up the Tempo constructor to match the other classes of the note package such as {@link
     * Pitch}, {@link TimeSig}, and so forth.
     * @param bpm The tempo desired, in beats per minute
     * @return a tempo of this speed (in beats per minute)
     */
    public static @NotNull Tempo get(int bpm) {
        if(bpm <= 0) throw new Error("Tempo:\tInvalid tempo! (" + bpm + " bpm)");

        return new Tempo(bpm);
    }

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * Constructor taking in a tempo, in beats per minute.
     * @param bpm The tempo, in beats per minute
     */
    private Tempo(int bpm) {
        super(bpm);
    }
    /**
     * Returns the number of beats per minute this tempo represents.
     * @return The BPM of this tempo
     */
    public final int getBPM() {
        return getValue();
    }
    /**
     * Returns a nicely-formatted string of this tempo (for debug).
     * @return This tempo's string representation
     */
    @Override
    public final @NotNull String toString() {
        return super.toString() + " bpm";
    }
    /**
     * Compares this tempo to another.
     * @param other The other tempo
     * @return The comparison between these two tempi
     */
    @Override
    public final int compareTo(@NotNull Tempo other) {
        return Integer.compare(value, other.value);
    }
    /**
     * Compares two tempi.
     * @param tempo1 The first tempo
     * @param tempo2 The second tempo
     * @return The comparison between these two tempi
     */
    @Override
    public final int compare(@NotNull Tempo tempo1, @NotNull Tempo tempo2) {
        return Integer.compare(tempo1.value, tempo2.value);
    }
    /**
     * Checks if this tempo equals another object.
     * @param object The other object
     * @return If this tempo is equal to the object
     */
    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Tempo tempo = (Tempo) object;
        return value == tempo.value;
    }
}