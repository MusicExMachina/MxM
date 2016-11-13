package model.basic;

import java.util.Comparator;

/**
 * Tempo is a glorified int wrapper, which allows for a little more dress
 * and prevents problems down the line with arguments to constructors.
 */
public class Tempo implements Comparator<Tempo>, Comparable<Tempo> {

    /** Stores a tempo value, in BPM. */
    private final int value;

    /**
     * Constructor taking in a Tempo speed.
     * @param value The Tempo's speed.
     */
    public Tempo(int value) {
        if(value > 0 ) {
            this.value = value;
        }
        else {
            throw new Error("Invalid tempo range!");
        }
    }

    /**
     * Returns the number of beats per minute this Tempo represents.
     * @return The BPM of this tempo.
     */
    public int getBPM() {
        return value;
    }

    /**
     * Returns a nicely-formatted String of this Tempo (for debug).
     * @return This Tempo's String representation.
     */
    public String toString() {
        return value + " bpm";
    }

    /**
     * Compares this Tempo to another Tempo.
     * @param other The other Tempo.
     * @return The comparison.
     */
    @Override
    public int compareTo(Tempo other) {
        return new Integer(value).compareTo(other.value);
    }

    /**
     * Compares two Tempi.
     * @param p1 The first Tempo.
     * @param p2 The second Tempo.
     * @return The comparison.
     */
    @Override
    public int compare(Tempo p1, Tempo p2) {
        return new Integer(p1.value).compareTo(p2.value);
    }

    /**
     * Checks if this Tempo equals another Object.
     * @param o The other Object.
     * @return If this Tempo is equal to the Object.
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
     * @return The hash code for this Tempo.
     */
    @Override
    public int hashCode() {
        return value;
    }
}