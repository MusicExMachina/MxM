package events;


import properties.time.ITime;

public abstract class AbstractSpanningEvent implements IMusicEvent {
    private final ITime startITime;
    private final ITime endITime;
    private final ITime duration;

    // GETTERS
    protected AbstractSpanningEvent(ITime startITime, ITime endITime) {
        this.startITime = startITime;
        this.endITime = endITime;
        this.duration = endITime.minus(endITime);
    }

    // GETTERS
    @Override
    public final ITime getTiming() { return startITime; }
    public final ITime getStart() { return endITime; }
    public final ITime getDuration() { return duration; }
}
