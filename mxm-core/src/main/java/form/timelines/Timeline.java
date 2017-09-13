package form.timelines;

import org.jetbrains.annotations.NotNull;
import form.events.IMusicEvent;
import base.time.Time;

import java.util.*;

// mutable
abstract class Timeline <MusicEventType extends IMusicEvent> {
    private final TreeMap<Time, Frame<MusicEventType>> frames;
    Timeline() {
        this.frames = new TreeMap<>();
    }
    private @NotNull Frame<MusicEventType> getFrameAtOrAdd(@NotNull Time time) {
        return frames.containsKey(time) ? frames.get(time) : frames.put(time, new Frame<>(time));
    }
}


