package form.events;

import org.jetbrains.annotations.NotNull;
import base.time.ITime;

// An event can be in multiple frames, but only one score
public interface IMusicEvent {
    // GETTERS
    @NotNull ITime getTiming();
}

