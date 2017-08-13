package time;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * TimeSignatures are exactly what they sound like in the Western music theory
 * sense. These are useful as they define how RhythmTrees divide on the first few
 * levels (3/4 versus 6/8) and will eventually  be useful in determining how
 * transitions between TimeSignatures work.
 *
 * Note: These are in many ways structurally similar to Counts. The major difference
 * is that there's no reason to ever reduce a time.TimeSig from say, 4/4 to 2/2.
 */
public class TimeSig {

    /** The fractional numerator of this time signature */
    private int numerator;

    /** The fractional denominator of this time signature */
    private int denominator;

    /** The preferred number of subdivisions at this subdivision level */
    private int[] preferredSubdivision;

    /** The preferred note size at each subdivision level (in 4/4 counts) */
    private Count[] preferredNoteLength;

    /** An ArrayList of all valid TimeSignatures */
    private static final ArrayList<TimeSig> ALL = new ArrayList<TimeSig>();

    // Initialize all pitches
    static {
        // Duple Time
        ALL.add(new TimeSig(1,1, new int[]{}));
        ALL.add(new TimeSig(2,2, new int[]{}));
        ALL.add(new TimeSig(2,4, new int[]{}));
        ALL.add(new TimeSig(4,4, new int[]{}));
        ALL.add(new TimeSig(8,8, new int[]{}));

        // Triple Time
        ALL.add(new TimeSig(3,2, new int[]{3}));
        ALL.add(new TimeSig(3,4, new int[]{3}));

        // Compound Time
        ALL.add(new TimeSig(3,8, new int[]{3}));
        ALL.add(new TimeSig(6,8, new int[]{2,3}));
        ALL.add(new TimeSig(9,8, new int[]{3,3}));
        ALL.add(new TimeSig(12,8, new int[]{2,2,3}));

        // Complex Time
        ALL.add(new TimeSig(5,4, new int[]{5}));
        ALL.add(new TimeSig(7,4, new int[]{7}));
        ALL.add(new TimeSig(11,4, new int[]{11}));
        ALL.add(new TimeSig(5,8, new int[]{5}));
        ALL.add(new TimeSig(7,8, new int[]{7}));
        ALL.add(new TimeSig(11,8, new int[]{11}));

    }

    /**
     * Gets an iterator which enumerates all valid time signatures.
     * @return An iterator over all valid times signatures
     */
    public static Iterator<TimeSig> iterator() {
        return ALL.iterator();
    }

    /**
     * Gets an instance of a time signature with a given numerator
     * and denominator. Note that
     * @param numerator The numerator of this time signature
     * @param denominator The denominator of this time Signature
     * @return A time signature with this description
     */
    public static TimeSig getInstance(int numerator, int denominator) {
        // TODO: Make this a more effective search, perhaps hash
        Iterator<TimeSig> iterator = iterator();
        while(iterator.hasNext()) {
            TimeSig timeSig = iterator.next();
            if( timeSig.numerator == numerator &&
                timeSig.denominator == denominator)
                    return timeSig;
        }
        throw new Error("TIME SIGNATURE:\tThis time signature (" +
                numerator + "/" + denominator + ") does not exist!");
    }

    /**
     * A constructor for time.TimeSig taking in a numerator and
     * denominator. events.sounding.Note that these do not need to be reduced,
     * as TimeSignatures can be almost anything you want them
     * to be.
     * @param numerator The desired numerator.
     * @param denominator The desired denominator.
     */
    private TimeSig(int numerator, int denominator, int[] preferredSubdivision) {
        this.numerator              = numerator;
        this.denominator            = denominator;
        this.preferredSubdivision   = preferredSubdivision;
        this.preferredNoteLength    = new Count[preferredSubdivision.length+1];
        this.preferredNoteLength[0] = getMeasureSize();

        for(int i = 1; i < preferredNoteLength.length; i++) {
           // this.preferredNoteLength[i] = preferredNoteLength[i - 1].dividedBy(getPreferredSubdivision(i - 1));
        }
    }

    /**
     * Gets the denominator of this time.TimeSig.
     * @return This time.TimeSig's denominator.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Gets the denominator of this time.TimeSig.
     * @return This time.TimeSig's denominator.
     */
    public int getDenominator() {
        return denominator;
    }

    public Count getMeasureSize() { return new Count(Measure.PICKUP, new Beat(numerator,denominator)); }

    public int getPreferredSubdivision(int level) {
        if(level < 0)
            throw new Error("TIME SIGNATURE:\t cannot access subdivision level " + level);
        if(level >= preferredSubdivision.length)
            return 2;
        else
            return preferredSubdivision[level];
    }

    public Count getPreferredNoteLength(int level) {
        if(level < 0)
            throw new Error("TIME SIGNATURE:\t cannot access subdivision level " + level);
        if(level >= preferredNoteLength.length)
            // Take the last one and keep subdividing by two until we reach the desired level
            return null; //preferredNoteLength[preferredNoteLength.length-1].dividedBy(2 << (level - preferredNoteLength.length));
        else
            return preferredNoteLength[level];
    }

    /**
     * Returns a nicely-formatted String
     * of this time.TimeSig (for debug).
     * @return This time.TimeSig's String representation.
     */
    public String toString() {
        return getNumerator() + "/" + getDenominator() + " time ";
    }

    /**
     * A noteQualities (generated) equals() method for time.TimeSig.
     * @param o The Object to compare this to.
     * @return If these two Objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSig timeSignature = (TimeSig) o;

        return numerator == timeSignature.numerator && denominator == timeSignature.denominator;
    }

    /**
     * A simple hash code in order to allow storage
     * in certain Collections, like HashSets.
     * @return The hash code for this time.TimeSig.
     */
    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }
}
