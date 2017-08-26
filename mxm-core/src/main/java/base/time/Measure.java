package base.time;

import org.jetbrains.annotations.NotNull;

/**
 * Measure class stuff
 */
public class Measure extends Time {

    private int measureNumber;
    /**
     * A factory method for creating a measure with a given measure number.
     * @param measureNum the measure number of this measure
     * @return a new Measure, given this measure number
     */
    public static Measure get(int measureNum) {
        return new Measure(measureNum);
    }
    /**
     * Constructs a measure given only a measure number.
     * @param measureNumber the measure number of this measure
     */
    private Measure(int measureNumber) {
        this.measureNumber = measureNumber;
    }
    /**
     * Gets the numerator of this measure, which is identical to its measure number.
     * @return the numerator of this measure.
     */
    @Override
    protected final int getNumerator() { return measureNumber; }
    /**
     * Gets the denominator of this measure, which will always be 1.
     * @return the denominator of this measure
     */
    @Override
    protected final int getDenominator() { return 1; }
    /**
     * Gets the measure number, which is identical to this measure's numerator.
     * @return the measure number of this measure
     */
    public int getNumber() { return measureNumber; }
    /**
     * Gets the beat associated with this measure, which will always be 0/0, as this will never be in between bar lines.
     * @return the beat associated with this measure (0/0)
     */
    @Override
    public @NotNull Beat getBeat() { return Beat.ZERO; }
    /**
     * Calling getMeasure() on a measure is rather redundant, but as it inherits from {@link Time}, this is necessary.
     * @return this measure
     */
    @Override
    public @NotNull Measure getMeasure() { return this; }
    /**
     * Gets a string representation of this class.
     * @return a string representation of this class
     */
    @Override
    public @NotNull String toString() { return "m." + measureNumber; }
}