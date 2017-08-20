package events;

import events.properties.TimeSig;

/**
 * Created by celenp on 6/11/2017.
 */
public class TimeSigChange extends InstantEvent implements ITimingEvent {
    private TimeSig timeSig;

    public TimeSigChange(form.Frame Frame, TimeSig timeSig) {
        super(Frame);
        this.timeSig = timeSig;
    }

    public TimeSig getTimeSig() {
        return timeSig;
    }
}
