package passage;

import base.time.Time;
import events.IMusicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

@SuppressWarnings("unchecked")
public class ParallelTimeline <MusicEventType extends IMusicEvent> implements Iterable<Frame<MusicEventType>> {
    private final TreeMap<Time, Frame<MusicEventType>> frames;

    ParallelTimeline() {
        this.frames = new TreeMap<>();
    }

    // PUBLIC GETTERS
    public @NotNull Frame<MusicEventType> getFirstFrame() { return frames.firstEntry().getValue(); }
    public @NotNull Frame<MusicEventType> getLastFrame() { return frames.lastEntry().getValue(); }
    public @NotNull Frame<MusicEventType> getFrameAt(Time time) {
        return frames.get(time);
    }
    public @NotNull Frame<MusicEventType> getFrameBefore(Time time) {
        return frames.floorEntry(time).getValue();
    }
    public @NotNull Frame<MusicEventType> getFrameAfter(Time time) {
        return frames.ceilingEntry(time).getValue();
    }

    @Override
    public @NotNull Iterator<Frame<MusicEventType>> iterator() {
        Collection constValues = java.util.Collections.unmodifiableCollection(frames.values());
        return constValues.iterator();
    }
}
