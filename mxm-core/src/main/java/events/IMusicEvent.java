package events;

import com.sun.istack.internal.NotNull;
import passage.Frame;
import base.time.Time;

// An event can be in multiple frames, but only one score
public interface IMusicEvent {
    // GETTERS
    @NotNull Time getTiming();
}

