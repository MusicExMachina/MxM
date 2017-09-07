package form.events;

import org.jetbrains.annotations.NotNull;
import base.time.Time;

// An event can be in multiple frames, but only one score
public interface IMusicEvent {
    // GETTERS
    @NotNull Time getTiming();
}

