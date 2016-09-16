package model;

import java.util.Comparator;

/**
 * Tempo is a glorified byte wrapper,
 * which allows for a little more dress
 * and prevents problems down the line
 * with arguments to constructors.
 */
public class Tempo implements Comparator<Tempo>, Comparable<Tempo> {

    /* Useful variable bounds */
    public static int MIN_TEMPO = 0;
    public static int MAX_TEMPO = 120;

    /**
     * Stores the MIDI value of this Pitch.
     */
    private final byte value;

    /**
     * Constructor taking in a Tempo speed.
     * @param value The Tempo's speed.
     */
    public Tempo(int value) {
        if(value >= MIN_TEMPO && value <= MAX_TEMPO) {
            this.value = (byte)value;
        }
        else {
            throw new Error("Invalid tempo range!");
        }
    }

    /**
     * Copy constructor for Tempo.
     * @param other The other Tempo.
     */
    public Tempo(Tempo other) {
        this.value = other.value;
    }

    /**
     * Compares this Tempo to another Tempo.
     * @param other The other Tempo.
     * @return The comparison.
     */
    @Override
    public int compareTo(Tempo other) {
        return Byte.compare(value,other.value);
    }

    /**
     * Compares two Tempi.
     * @param p1 The first Tempo.
     * @param p2 The second Tempo.
     * @return The comparison.
     */
    @Override
    public int compare(Tempo p1, Tempo p2) {
        return Byte.compare(p1.value,p2.value);
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
        return (int) value;
    }
}