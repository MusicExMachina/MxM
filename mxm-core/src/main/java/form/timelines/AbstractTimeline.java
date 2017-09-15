package form.timelines;

import properties.time.ITime;
import org.jetbrains.annotations.NotNull;
import events.IMusicEvent;

import java.util.*;

// mutable
public abstract class AbstractTimeline<MusicEventType extends IMusicEvent> {
    private final TreeMap<ITime, Frame<MusicEventType>> frames;
    AbstractTimeline() {
        this.frames = new TreeMap<>();
    }
    private @NotNull Frame<MusicEventType> getFrameAtOrAdd(@NotNull ITime time) {
        return frames.containsKey(time) ? frames.get(time) : frames.put(time, new Frame<>(time));
    }
}


