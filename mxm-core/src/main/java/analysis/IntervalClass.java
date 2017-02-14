package analysis;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * analysis.IntervalClass is a simple class which utilizes the interning design pattern to create only
 * twelve different values: PU, m2, M2, m3, M3, P4, TT, P5, m6, M6, m7, and M7. These are usually
 * found in Intervals, though may be used in Collections. form.Note that there should never be more than
 * these 12 IntervalClasses, and that an iterator() has been provided for easy access. Also note
 * that IntervalClasses are always positive, as they represent a distance.
 */
public class IntervalClass implements Comparator<IntervalClass>, Comparable<IntervalClass> {

    /** The lowest interval class, better known as "unison." */
    private static final int MIN_INTERVALCLASS = 0;

    /** The highest interval class, better known as "a major seventh." */
    private static final int MAX_INTERVALCLASS = 11;

    /** All possible IntervalClasses */
    private static final ArrayList<IntervalClass> ALL = new ArrayList<>();

    // Initialize the "ALL" collection
    static {
        for(int intervalClassValue = MIN_INTERVALCLASS; intervalClassValue <= MAX_INTERVALCLASS; intervalClassValue++) {
            ALL.add(new IntervalClass(intervalClassValue));
        }
    }

    /**
     * Gets an iterator which enumerates all valid IntervalClasses.
     * @return An iterator over all valid Pitches.
     */
    public static Iterator<IntervalClass> iterator() {
        return ALL.iterator();
    }

    /**
     * Gets an instance of a given analysis.IntervalClass size. This method
     * creates the interning design pattern per analysis.IntervalClass.
     * @param size The size (in half steps) of this analysis.IntervalClass
     * @return An analysis.IntervalClass of this size.
     */
    public static IntervalClass getInstance(int size) {
        if(size >= MIN_INTERVALCLASS && size < MAX_INTERVALCLASS) {
            return ALL.get(size);
        }
        else {
            throw new Error("INTERVAL CLASS:\tbase.Interval class out of range.");
        }
    }

    /** The size of the analysis.IntervalClass. */
    private int size;

    /**
     * A constructor for analysis.IntervalClass which is private.
     * @param size The size (0-11) of this analysis.IntervalClass.
     */
    private IntervalClass(int size) {
        this.size = size;
    }

    /**
     * A getter for the size of this base.Interval, in half-steps.
     * @return The size of this base.Interval, in half-steps.
     */
    public int getSize() {
        return size;
    }

    /**
     * Creates a new analysis.IntervalClass that is the sum of these two.
     * @param other The other analysis.IntervalClass to add to this one.
     * @return  The new analysis.IntervalClass sum. form.Note: This loops.
     */
    public IntervalClass plus(IntervalClass other) {
        return ALL.get((this.size + other.size)%12);
    }

    /**
     * Creates a new analysis.IntervalClass that is the difference of these two.
     * @param other The other analysis.IntervalClass to subtract from this one.
     * @return The new analysis.IntervalClass difference. form.Note: This loops.
     */
    public IntervalClass minus(IntervalClass other) {
        return ALL.get((this.size - other.size + 12)%12);
    }

    /**
     * Returns a nicely-formatted String of this analysis.IntervalClass (for debug).
     * @return A nicely-formatted String of this analysis.IntervalClass.
     */
    public String toString() {
        switch (size) {
            case 0:     return "PU";   case 1:     return "m2";
            case 2:     return "M2";   case 3:     return "m3";
            case 4:     return "M3";   case 5:     return "P4";
            case 6:     return "TT";   case 7:     return "P5";
            case 8:     return "m6";   case 9:     return "M6";
            case 10:    return "m7";   case 11:    return "M7";
            default:    return "ERROR";
        }
    }

    /**
     * Compares this analysis.IntervalClass to another, purely based on size.
     * @param other the other analysis.IntervalClass to compare this one to.
     * @return The comparison between the two.
     */
    @Override
    public int compareTo(IntervalClass other) {
        return new Integer(size).compareTo(other.size);
    }

    /**
     * Compares two IntervalClasses, purely based on size.
     * @param ic1 The first analysis.IntervalClass.
     * @param ic2 The second analysis.IntervalClass.
     * @return The comparison between the two.
     */
    @Override
    public int compare(IntervalClass ic1, IntervalClass ic2) {
        return new Integer(ic1.size).compareTo(ic2.size);
    }

    /**
     * A noteQualities (generated) equals() method for IntervalClasses.
     * @param o The Object to compare this to.
     * @return If these two Objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntervalClass intervalClass = (IntervalClass) o;
        return size == intervalClass.size;
    }

    /**
     * A simple hash code in order to allow storage in certain Collections.
     * @return The hash code for this analysis.IntervalClass.
     */
    @Override
    public int hashCode() {
        return size;
    }
}
