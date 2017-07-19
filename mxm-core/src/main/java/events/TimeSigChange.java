package events;

import time.*;

/**
 * Created by celenp on 6/11/2017.
 */
public class TimeSigChange implements IMusicEvent<Measure> {

    private TimeSig timeSig;
    private Frame<Measure> frame;

    public TimeSigChange(Frame<Measure> frame, TimeSig timeSig) {
        this.frame = frame;
        this.timeSig = timeSig;
    }

    @Override
    public Frame<Measure> getFrame() {
        return frame;
    }

    @Override
    public Measure getTiming() {
        return null;
    }
}
