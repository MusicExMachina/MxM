package form.timelines;

import form.IFrame;
import org.jetbrains.annotations.NotNull;
import form.events.IMusicEvent;
import base.time.Time;
import form.events.InstantEvent;
import form.events.SpanningEvent;

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


