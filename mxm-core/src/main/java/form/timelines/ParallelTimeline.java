package form.timelines;

import properties.time.ITime;
import form.IFrame;
import form.IParallelTimeline;
import events.IMusicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

@SuppressWarnings("unchecked")
final class ParallelTimeline <MusicEventType extends IMusicEvent> implements IParallelTimeline<MusicEventType> {
    private final TreeMap<ITime, Frame<MusicEventType>> frames;

    ParallelTimeline() {
        this.frames = new TreeMap<>();
    }

    // PUBLIC GETTERS
    @Override
    public final @NotNull IFrame<MusicEventType> getFirstFrame() { return frames.firstEntry().getValue(); }
    @Override
    public final @NotNull IFrame<MusicEventType> getLastFrame() { return frames.lastEntry().getValue(); }
    @Override
    public final @NotNull IFrame<MusicEventType> getFrameAt(@NotNull ITime time) {
        return frames.get(time);
    }
    @Override
    public final @NotNull IFrame<MusicEventType> getFrameBefore(@NotNull ITime time) {
        return frames.floorEntry(time).getValue();
    }
    @Override
    public final @NotNull IFrame<MusicEventType> getFrameAfter(@NotNull ITime time) {
        return frames.ceilingEntry(time).getValue();
    }

    @Override
    public final @NotNull Iterator<IFrame<MusicEventType>> iterator() {
        Collection constValues = java.util.Collections.unmodifiableCollection(frames.values());
        return constValues.iterator();
    }
}
