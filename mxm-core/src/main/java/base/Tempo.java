package base;

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
public class Tempo implements Comparator<Tempo>, Comparable<Tempo> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    public static final Tempo DEFAULT = get(120);

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

    /**
     * This get method dresses up the Tempo constructor to match the other classes of the base package such as {@link
     * Pitch}, {@link TimeSig}, and so forth.
     * @param bpm The tempo desired, in beats per minute
     * @return a tempo of this speed (in beats per minute)
     */
    public static @NotNull Tempo get(int bpm) {
        return new Tempo(bpm);
    }

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** Stores a tempo value, in BPM */
    private final int value;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * Constructor taking in a tempo, in beats per minute.
     * @param value The tempo, in beats per minute
     */
    private Tempo(int value) {
        if(value > 0) {
            this.value = value;
        }
        else throw new Error("TEMPO:\tInvalid tempo! (" + value + " bpm)");
    }
    /**
     * Returns the number of beats per minute this tempo represents.
     * @return The BPM of this tempo
     */
    public int getBPM() {
        return value;
    }
    /**
     * Returns a nicely-formatted string of this tempo (for debug).
     * @return This tempo's string representation
     */
    public String toString() {
        return getBPM() + " bpm";
    }
    /**
     * Compares this tempo to another.
     * @param other The other tempo
     * @return The comparison between these two tempi
     */
    @Override
    public int compareTo(@NotNull Tempo other) {
        return Integer.compare(value, other.value);
    }
    /**
     * Compares two tempi.
     * @param p1 The first tempo
     * @param p2 The second tempo
     * @return The comparison between these two tempi
     */
    @Override
    public int compare(@NotNull Tempo p1, @NotNull Tempo p2) {
        return Integer.compare(p1.value, p2.value);
    }
    /**
     * Checks if this tempo equals another object.
     * @param object The other object
     * @return If this tempo is equal to the object
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Tempo tempo = (Tempo) object;
        return value == tempo.value;
    }
    /**
     * A simple hash code for storage of tempi in special collections.
     * @return The hash code for this tempo
     */
    @Override
    public int hashCode() {
        return value;
    }
}