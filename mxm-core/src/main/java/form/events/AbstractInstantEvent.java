package form.events;

import base.time.Time;

public abstract class AbstractInstantEvent implements IMusicEvent {
    private final Time time;

    protected AbstractInstantEvent(Time time) {
        this.time = time;
    }

    // GETTERS
    @Override
    public final Time getTiming() { return time; }
}
