package base;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * base.Tempo is a glorified int wrapper, which allows for a little more dress
 * and prevents problems down the line with arguments to constructors.
 */
public class Tempo implements Comparator<Tempo>, Comparable<Tempo> {

    //////////////////////////////
    // Static variables         //
    //////////////////////////////

    //////////////////////////////
    // Static methods           //
    //////////////////////////////

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
    public Tempo(int value) {
        if(value > 0)
            this.value = value;
        else
            throw new Error("TEMPO:\tInvalid tempo! (" + value + " bpm)");
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
     * @return The comparison
     */
    @Override
    public int compareTo(@NotNull Tempo other) {
        if(other != null)
            return Integer.compare(value, other.value);
        else
            return 0;
    }
    /**
     * Compares two Tempi.
     * @param p1 The first tempo
     * @param p2 The second tempo
     * @return The comparison
     */
    @Override
    public int compare(@NotNull Tempo p1, @NotNull Tempo p2) {
        if(p1 != null && p2 != null)
            return Integer.compare(p1.value, p2.value);
        else
            return 0;
    }
    /**
     * Checks if this tempo equals another object.
     * @param o The other object.
     * @return If this tempo is equal to the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tempo tempo = (Tempo) o;
        return value == tempo.value;
    }
    /**
     * A simple hash code for storage of tempi in special collections.
     * @return The hash code for this tempo.
     */
    @Override
    public int hashCode() {
        return value;
    }
}