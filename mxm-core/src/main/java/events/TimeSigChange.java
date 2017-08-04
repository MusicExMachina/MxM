package events;

import time.*;

/**
 * Created by celenp on 6/11/2017.
 */
public class TimeSigChange extends MusicEvent<Measure> {
    private TimeSig timeSig;

    public TimeSigChange(Frame<Measure> startFrame, TimeSig timeSig) {
        super(startFrame);
        this.timeSig = timeSig;
    }

    public TimeSig getTimeSig() {
        return timeSig;
    }
}
