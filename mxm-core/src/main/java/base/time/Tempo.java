package base.time;

import java.util.Comparator;

/**
 * base.time.Tempo is a glorified int wrapper, which allows for a little more dress
 * and prevents problems down the line with arguments to constructors.
 */
public class Tempo implements Comparator<Tempo>, Comparable<Tempo> {

    /** Stores a tempo value, in BPM. */
    private final int value;

    /**
     * Constructor taking in a base.time.Tempo speed.
     * @param value The base.time.Tempo's speed.
     */
    public Tempo(int value) {
        if(value > 0 ) {
            this.value = value;
        }
        else {
            throw new Error("Invalid tempo! (" + value + " bpm)");
        }
    }

    /**
     * Returns the number of beats per minute this base.time.Tempo represents.
     * @return The BPM of this tempo.
     */
    public int getBPM() {
        return value;
    }

    /**
     * Returns a nicely-formatted String of this base.time.Tempo (for debug).
     * @return This base.time.Tempo's String representation.
     */
    public String toString() {
        return value + " bpm";
    }


    @Override
    public int compareTo(Tempo other) {
        return new Integer(value).compareTo(other.value);
    }

    /**
     * Compares two Tempi.
     * @param p1 The first base.time.Tempo.
     * @param p2 The second base.time.Tempo.
     * @return The comparison.
     */
    @Override
    public int compare(Tempo p1, Tempo p2) {
        return new Integer(p1.value).compareTo(p2.value);
    }

    /**
     * Checks if this base.time.Tempo equals another Object.
     * @param o The other Object.
     * @return If this base.time.Tempo is equal to the Object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tempo tempo = (Tempo) o;
        return value == tempo.value;
    }

    /**
     * A simple hash code for storage of Tempi in special Collections.
     * @return The hash code for this base.time.Tempo.
     */
    @Override
    public int hashCode() {
        return value;
    }
}