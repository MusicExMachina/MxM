package form;

import java.util.Comparator;

/**
 * form.Tempo is a glorified int wrapper, which allows for a little more dress
 * and prevents problems down the line with arguments to constructors.
 */
public class Tempo implements Comparator<Tempo>, Comparable<Tempo> {

    /** Stores a tempo value, in BPM. */
    private final int value;

    /**
     * Constructor taking in a form.Tempo speed.
     * @param value The form.Tempo's speed.
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
     * Returns the number of beats per minute this form.Tempo represents.
     * @return The BPM of this tempo.
     */
    public int getBPM() {
        return value;
    }

    /**
     * Returns a nicely-formatted String of this form.Tempo (for debug).
     * @return This form.Tempo's String representation.
     */
    public String toString() {
        return value + " bpm";
    }

    /**
     * Compares this form.Tempo to another form.Tempo.
     * @param other The other form.Tempo.
     * @return The comparison.
     */
    @Override
    public int compareTo(Tempo other) {
        return new Integer(value).compareTo(other.value);
    }

    /**
     * Compares two Tempi.
     * @param p1 The first form.Tempo.
     * @param p2 The second form.Tempo.
     * @return The comparison.
     */
    @Override
    public int compare(Tempo p1, Tempo p2) {
        return new Integer(p1.value).compareTo(p2.value);
    }

    /**
     * Checks if this form.Tempo equals another Object.
     * @param o The other Object.
     * @return If this form.Tempo is equal to the Object.
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
     * @return The hash code for this form.Tempo.
     */
    @Override
    public int hashCode() {
        return value;
    }
}