package events;

import base.time.Time;
import passage.Frame;

public abstract class InstantEvent implements IMusicEvent {
    private final Frame frame;

    InstantEvent(Frame frame) {
        this.frame = frame;
    }

    // GETTERS
    @Override
    public final Time getTiming() { return frame.getTime(); }
    @Override
    public final Frame getFrame() { return frame; }
}
