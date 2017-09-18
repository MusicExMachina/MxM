package events;

import properties.time.ITime;

public abstract class AbstractInstantEvent implements IMusicEvent {
    private final ITime time;

    protected AbstractInstantEvent(ITime time) {
        this.time = time;
    }

    // GETTERS
    @Override
    public final ITime getTime() { return time; }
}
