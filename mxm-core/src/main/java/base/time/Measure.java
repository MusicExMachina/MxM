package base.time;

import org.jetbrains.annotations.NotNull;

public class Measure extends Time {
    private int measureNumber;

    public static Measure get(int measureNum) { return new Measure(measureNum); }

    private Measure(int measureNumber) {
        this.measureNumber = measureNumber;
    }

    public int getNum() { return measureNumber; }
    @Override
    protected final int getNumerator() { return measureNumber; }
    @Override
    protected final int getDenominator() { return 1; }
    @Override
    public @NotNull Beat getBeat() { return Beat.ZERO; }
    @Override
    public @NotNull Measure getMeasure() { return this; }
    @Override
    public String toString() { return "m." + measureNumber; }
}