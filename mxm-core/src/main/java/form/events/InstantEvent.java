package form.events;

import time.Time;

public abstract class InstantEvent implements IEvent {
    private final Time time;

    protected InstantEvent(Time time) {
        this.time = time;
    }

    // GETTERS
    @Override
    public final Time getTime() { return time; }
}
