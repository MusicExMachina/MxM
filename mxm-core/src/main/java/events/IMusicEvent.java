package events;

import passage.Frame;
import base.time.Time;

public interface IMusicEvent {
    // GETTERS
    public Time getTiming();
    public Frame getFrame();
}

