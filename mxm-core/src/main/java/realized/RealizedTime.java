package realized;

import time.Beat;
import time.Measure;
import time.Time;

public class RealizedTime extends Time implements IRealized {

    @Override
    protected int getNumerator() {
        return 0;
    }

    @Override
    protected int getDenominator() {
        return 0;
    }

    @Override
    public Beat getBeat() {
        return null;
    }

    @Override
    public Measure getMeasure() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
