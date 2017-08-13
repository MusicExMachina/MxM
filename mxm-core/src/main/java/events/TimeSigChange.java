package events;

import time.*;

/**
 * Created by celenp on 6/11/2017.
 */
public class TimeSigChange extends InstantEvent<Measure> {
    private TimeSig timeSig;

    public TimeSigChange(form.IFrame IFrame, TimeSig timeSig) {
        super(IFrame);
        this.timeSig = timeSig;
    }

    public TimeSig getTimeSig() {
        return timeSig;
    }
}
