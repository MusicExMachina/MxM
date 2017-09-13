package form.events;

import base.time.Time;

public abstract class AbstractSpanningEvent implements IMusicEvent {
    private final Time startTime;
    private final Time endTime;
    private final Time duration;

    // GETTERS
    protected AbstractSpanningEvent(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = endTime.minus(endTime);
    }

    // GETTERS
    @Override
    public final Time getTiming() { return startTime; }
    public final Time getStart() { return endTime; }
    public final Time getDuration() { return duration; }
}
