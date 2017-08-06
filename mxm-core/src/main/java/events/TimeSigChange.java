package events;

import time.*;

/**
 * Created by celenp on 6/11/2017.
 */
public class TimeSigChange extends InstantEvent {
    private TimeSig timeSig;

    public TimeSigChange(Frame frame, TimeSig timeSig) {
        super(frame);
        this.timeSig = timeSig;
    }

    public TimeSig getTimeSig() {
        return timeSig;
    }
}
