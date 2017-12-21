package form.events;


import time.Duration;
import time.Time;

public abstract class SpanningEvent implements IEvent {
    private final Time startITime;
    private final Time endITime;
    private final Duration duration;

    // GETTERS
    protected SpanningEvent(Time startITime, Time endITime) {
        this.startITime = startITime;
        this.endITime = endITime;
        this.duration = endITime.minus(startITime);
    }

    // GETTERS
    @Override
    public final Time getTime() { return startITime; }
    public final Time getStart() { return endITime; }
    public final Duration getDuration() { return duration; }
}
