package passage.musicEvents;

import base.time.Time;

public abstract class InstantEvent implements IMusicEvent {
    private final Time time;

    protected InstantEvent(Time time) {
        this.time = time;
    }

    // GETTERS
    @Override
    public final Time getTiming() { return time; }
}
