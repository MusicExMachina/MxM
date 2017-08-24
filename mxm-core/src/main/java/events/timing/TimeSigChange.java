package events.timing;

import base.time.Measure;
import events.InstantEvent;
import base.time.TimeSig;

/**
 * Created by celenp on 6/11/2017.
 */
public class TimeSigChange extends InstantEvent {
    private TimeSig timeSig;

    TimeSigChange(Measure timing, TimeSig timeSig) {
        super(timing);
        this.timeSig = timeSig;
    }

    public TimeSig getTimeSig() {
        return timeSig;
    }
}
