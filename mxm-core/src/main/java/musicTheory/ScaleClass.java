package musicTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by celenp on 5/20/2017.
 */
public class ScaleClass implements Iterable<IntervalClass> {

    /** Major scale */
    public static final ScaleClass MAJOR = new ScaleClass(
            IntervalClass.MAJOR_SECOND,
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SIXTH,
            IntervalClass.MAJOR_SEVENTH
    );

    /** Natural minor scale */
    public static final ScaleClass NATURAL_MINOR = new ScaleClass(
            IntervalClass.MAJOR_SECOND,
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SIXTH,
            IntervalClass.MINOR_SEVENTH
    );

    /** Harmonic minor scale */
    public static final ScaleClass HARMONIC_MINOR = new ScaleClass(
            IntervalClass.MAJOR_SECOND,
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SIXTH,
            IntervalClass.MAJOR_SEVENTH
    );

    /** Ascending melodic minor scale */
    public static final ScaleClass ASCENDING_MELODIC_MINOR = new ScaleClass(
            IntervalClass.MAJOR_SECOND,
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SIXTH,
            IntervalClass.MAJOR_SEVENTH
    );

    /** Descending melodic minor scale */
    public static final ScaleClass DESCENDING_MELODIC_MINOR = NATURAL_MINOR;

    /** Ionian mode */
    public static final ScaleClass IONIAN = MAJOR;

    /** Dorian mode */
    public static final ScaleClass DORIAN = new ScaleClass(
            IntervalClass.MAJOR_SECOND,
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SIXTH,
            IntervalClass.MINOR_SEVENTH
    );

    /** Phrygian mode */
    public static final ScaleClass PHRYGIAN  = new ScaleClass(
            IntervalClass.MINOR_SECOND,
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MINOR_SIXTH,
            IntervalClass.MINOR_SEVENTH
    );

    /** Lydian mode */
    public static final ScaleClass LYDIAN  = new ScaleClass(
            IntervalClass.MAJOR_SECOND,
            IntervalClass.MAJOR_THIRD,
            IntervalClass.TRITONE,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SIXTH,
            IntervalClass.MAJOR_SEVENTH
    );

    /** Mixolydian mode */
    public static final ScaleClass MIXOLYDIAN  = new ScaleClass(
            IntervalClass.MAJOR_SECOND,
            IntervalClass.MAJOR_THIRD,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.PERFECT_FIFTH,
            IntervalClass.MAJOR_SIXTH,
            IntervalClass.MINOR_SEVENTH
    );

    /** Aeolian mode */
    public static final ScaleClass AEOLIAN = NATURAL_MINOR;

    /** Locrian mode */
    public static ScaleClass LOCRIAN = new ScaleClass(
            IntervalClass.MINOR_SECOND,
            IntervalClass.MINOR_THIRD,
            IntervalClass.PERFECT_FOURTH,
            IntervalClass.TRITONE,
            IntervalClass.MINOR_SIXTH,
            IntervalClass.MINOR_SEVENTH
    );

    /** All factors of this Chord. */
    private ArrayList<IntervalClass> degrees;

    /** A constructor for ScaleClasses */
    public ScaleClass(IntervalClass... degrees) {
        this.degrees = new ArrayList<>();
        this.degrees.add(IntervalClass.UNISON);
        this.degrees.addAll(Arrays.asList(degrees));
    }

    /** Gets the number of degrees */
    public int getNumDegrees() {
        return degrees.size();
    }

    /** Gets an iterator over all the intervals in this scale class*/
    @Override
    public Iterator<IntervalClass> iterator() {
        return degrees.iterator();
    }
}