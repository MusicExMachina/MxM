package events;

import time.*;

/**
 * Created by celenp on 6/11/2017.
 */
public class TimeSigChange extends InstantEvent {
    private TimeSig timeSig;

    public TimeSigChange(IFrame IFrame, TimeSig timeSig) {
        super(IFrame);
        this.timeSig = timeSig;
    }

    public TimeSig getTimeSig() {
        return timeSig;
    }
}
