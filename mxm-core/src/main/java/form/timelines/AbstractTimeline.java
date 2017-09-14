package form.timelines;

import base.time.ITime;
import org.jetbrains.annotations.NotNull;
import form.events.IMusicEvent;
import base.time.Time;

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


