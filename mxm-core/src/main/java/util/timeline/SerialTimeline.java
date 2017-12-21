package util.timeline;

import form.events.IEvent;
import time.Time;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import util.Frame;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * <p> <b>Interface Overview:</b>
 * The {@link SerialTimeline} interface represents a timeline in which all musical form.events are ordered serially- one
 * after the other, that is. This contrasts starkly with a {@link ParallelTimeline} in which many form.events may happen
 * simultaneously, and are stored in a {@link Frame}.</p>
 *
 * @param <MusicEventType> The subclass of IEvent which this timeline may hold. It's worth noting that this might
 *                        be IEvent itself- in which case, all music form.events may be stored within this timeline.
 *
 * @author Patrick Celentano
 */
public final class SerialTimeline <MusicEventType extends IEvent> implements ITimeline<MusicEventType> {
    private final TreeMap<Time, MusicEventType> events;

    public SerialTimeline() {
        this.events = new TreeMap<>();
    }

    // ADDER
    // Package private on purpose- we don't want users adding form.events, only score.
    public void add(MusicEventType event) {
        if (events.get(event.getTime()) == null) {
            events.put(event.getTime(), event);
        } else {
            throw new Error("Cannot add one event on top of another in a Serial Timeline!");
        }
    }

    // PUBLIC GETTERS
    @Nullable
    public MusicEventType getFirst() { return events.firstEntry().getValue(); }
    @Nullable
    public MusicEventType getLast() { return events.lastEntry().getValue(); }
    @Nullable
    public MusicEventType getAt(@NotNull Time time) {
        return events.get(time);
    }
    @Nullable
    public MusicEventType getBefore(@NotNull Time time) {
        return events.floorEntry(time).getValue();
    }
    @Nullable
    public MusicEventType getAfter(@NotNull Time time) {
        return events.ceilingEntry(time).getValue();
    }

    @NotNull
    @Override
    public Stream<MusicEventType> stream() {
        return java.util.Collections.unmodifiableCollection(events.values()).stream();
    }
    @NotNull
    @Override
    public Stream<MusicEventType> parallelStream() {
        return java.util.Collections.unmodifiableCollection(events.values()).parallelStream();
    }
    @Override
    public @NotNull Iterator<MusicEventType> iterator() {
        return java.util.Collections.unmodifiableCollection(events.values()).iterator();
    }
    @NotNull
    @Override
    public Spliterator<MusicEventType> spliterator() {
        return java.util.Collections.unmodifiableCollection(events.values()).spliterator();
    }
}
