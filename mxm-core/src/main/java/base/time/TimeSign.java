package base.time;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * TimeSignatures are exactly what they sound like in the Western music theory
 * sense. These are useful as they define how RhythmTrees divide on the first few
 * levels (3/4 versus 6/8) and will eventually  be useful in determining how
 * transitions between TimeSignatures work.
 *
 * Note: These are in many ways structurally similar to Counts. The major difference
 * is that there's no reason to ever reduce a base.time.TimeSign from say, 4/4 to 2/2.
 */
public class TimeSign {

    /** The fractional numerator of this time signature */
    private int numerator;

    /** The fractional denominator of this time signature */
    private int denominator;

    /** The preferred number of subdivisions at this subdivision level */
    private int[] preferredSubdivision;

    /** The preferred note size at each subdivision level (in 4/4 counts) */
    private Count[] preferredNoteLength;

    /** An ArrayList of all valid Pitches */
    private static final ArrayList<TimeSign> ALL = new ArrayList<TimeSign>();

    // Initialize all pitches
    static {
        // Duple Time
        ALL.add(new TimeSign(1,1, new int[]{}));
        ALL.add(new TimeSign(2,2, new int[]{}));
        ALL.add(new TimeSign(2,4, new int[]{}));
        ALL.add(new TimeSign(4,4, new int[]{}));
        ALL.add(new TimeSign(8,8, new int[]{}));

        // Triple Time
        ALL.add(new TimeSign(3,2, new int[]{3}));
        ALL.add(new TimeSign(3,4, new int[]{3}));

        // Compound Time
        ALL.add(new TimeSign(3,8, new int[]{3}));
        ALL.add(new TimeSign(6,8, new int[]{2,3}));
        ALL.add(new TimeSign(9,8, new int[]{3,3}));
        ALL.add(new TimeSign(12,8, new int[]{2,2,3}));

        // Complex Time
        ALL.add(new TimeSign(5,4, new int[]{5}));
        ALL.add(new TimeSign(7,4, new int[]{7}));
        ALL.add(new TimeSign(11,4, new int[]{11}));
        ALL.add(new TimeSign(5,8, new int[]{5}));
        ALL.add(new TimeSign(7,8, new int[]{7}));
        ALL.add(new TimeSign(11,8, new int[]{11}));

    }

    /**
     * Gets an iterator which enumerates all valid time signatures.
     * @return An iterator over all valid times signatures
     */
    public static Iterator<TimeSign> iterator() {
        return ALL.iterator();
    }

    /**
     * Gets an instance of a time signature with a given numerator
     * and denominator. Note that
     * @param numerator The numerator of this time signature
     * @param denominator The denominator of this time Signature
     * @return A time signature with this description
     */
    public static TimeSign getInstance(int numerator, int denominator) {
        // TODO: Make this a more effective search, perhaps hash
        Iterator<TimeSign> iterator = iterator();
        while(iterator.hasNext()) {
            TimeSign timeSig = iterator.next();
            if( timeSig.numerator == numerator &&
                timeSig.denominator == denominator)
                    return timeSig;
        }
        throw new Error("TIME SIGNATURE:\tThis time signature (" +
                numerator + "/" + denominator + ") does not exist!");
    }

    /**
     * A constructor for base.time.TimeSign taking in a numerator and
     * denominator. events.playable.Note that these do not need to be reduced,
     * as TimeSignatures can be almost anything you want them
     * to be.
     * @param numerator The desired numerator.
     * @param denominator The desired denominator.
     */
    private TimeSign(int numerator, int denominator, int[] preferredSubdivision) {
        this.numerator              = numerator;
        this.denominator            = denominator;
        this.preferredSubdivision   = preferredSubdivision;
        this.preferredNoteLength    = new Count[preferredSubdivision.length+1];
        this.preferredNoteLength[0] = getMeasureSize();

        for(int i = 1; i < preferredNoteLength.length; i++) {
            this.preferredNoteLength[i] = preferredNoteLength[i - 1].dividedBy(getPreferredSubdivision(i - 1));
        }
    }

    /**
     * Gets the denominator of this base.time.TimeSign.
     * @return This base.time.TimeSign's denominator.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Gets the denominator of this base.time.TimeSign.
     * @return This base.time.TimeSign's denominator.
     */
    public int getDenominator() {
        return denominator;
    }

    public Count getMeasureSize() { return new Count(numerator,denominator); }

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
            return preferredNoteLength[preferredNoteLength.length-1].dividedBy(2 << (level - preferredNoteLength.length));
        else
            return preferredNoteLength[level];
    }

    /**
     * Returns a nicely-formatted String
     * of this base.time.TimeSign (for debug).
     * @return This base.time.TimeSign's String representation.
     */
    public String toString() {
        return getNumerator() + "/" + getDenominator() + " time ";
    }

    /**
     * A noteQualities (generated) equals() method for base.time.TimeSign.
     * @param o The Object to compare this to.
     * @return If these two Objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSign timeSignature = (TimeSign) o;

        return numerator == timeSignature.numerator && denominator == timeSignature.denominator;
    }

    /**
     * A simple hash code in order to allow storage
     * in certain Collections, like HashSets.
     * @return The hash code for this base.time.TimeSign.
     */
    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }
}
