package util.timeline;

import time.Time;
import form.events.IEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

/**
 * <p> <b>Interface Overview:</b>
 * The {@link ParallelTimeline} interface represents a timeline in which musical form.events may occur at any time, even
 * simultaneously. This contrasts starkly with a {@link SerialTimeline} in which many form.events must happen one after the
 * other, with no overlap. In a {@link ParallelTimeline}, contemporaneous form.events are stored in a {@link Frame}, which
 * is simply a vertical stack of form.events at a given time</p>
 *
 * @param <MusicEventType> The subclass of IEvent which this timeline may hold. It's worth noting that this might
 *                        be IEvent itself- in which case, all music form.events may be stored within this timeline.
 *
 * @author Patrick Celentano
 */
final class ParallelTimeline <MusicEventType extends IEvent> implements ITimeline<Frame<MusicEventType>> {

    private final TreeMap<Time, Frame<MusicEventType>> frames;

    public ParallelTimeline() {
        this.frames = new TreeMap<>();
    }

    // PUBLIC GETTERS
    @Override
    public final @NotNull Frame<MusicEventType> getFirst() { return frames.firstEntry().getValue(); }
    @Override
    public final @NotNull Frame<MusicEventType> getLast() { return frames.lastEntry().getValue(); }
    @Override
    public final @NotNull Frame<MusicEventType> getAt(@NotNull Time time) {
        return frames.get(time);
    }
    @Override
    public final @NotNull Frame<MusicEventType> getBefore(@NotNull Time time) {
        return frames.floorEntry(time).getValue();
    }
    @Override
    public final @NotNull Frame<MusicEventType> getAfter(@NotNull Time time) {
        return frames.ceilingEntry(time).getValue();
    }

    @Override
    public @NotNull Stream<Frame<MusicEventType>> stream() {
        return java.util.Collections.unmodifiableCollection(frames.values()).stream();
    }

    @Override
    public @NotNull Stream<Frame<MusicEventType>> parallelStream() {
        return java.util.Collections.unmodifiableCollection(frames.values()).parallelStream();
    }
    @Override
    public final @NotNull Iterator<Frame<MusicEventType>> iterator() {
        return java.util.Collections.unmodifiableCollection(frames.values()).iterator();
    }

    @Override
    public @NotNull Spliterator<Frame<MusicEventType>> spliterator() {
        return java.util.Collections.unmodifiableCollection(frames.values()).spliterator();
    }
}