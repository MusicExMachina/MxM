package form.timeline;

import form.ITimeline;
import properties.time.ITime;
import events.IMusicEvent;
import form.exceptions.TimelineOverlapError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * <p> <b>Interface Overview:</b>
 * The {@link SerialTimeline} interface represents a timeline in which all musical events are ordered serially- one
 * after the other, that is. This contrasts starkly with a {@link ParallelTimeline} in which many events may happen
 * simultaneously, and are stored in a {@link IFrame}.</p>
 *
 * @param <MusicEventType> The subclass of IMusicEvent which this timeline may hold. It's worth noting that this might
 *                        be IMusicEvent itself- in which case, all music events may be stored within this timeline.
 *
 * @author Patrick Celentano
 */
public final class SerialTimeline <MusicEventType extends IMusicEvent> implements ITimeline<MusicEventType> {
    private final TreeMap<ITime, MusicEventType> events;

    public SerialTimeline() {
        this.events = new TreeMap<>();
    }

    // ADDER
    // Package private on purpose- we don't want users adding events, only score.
    public void add(MusicEventType event) throws TimelineOverlapError {
        if (events.get(event.getTime()) == null) {
            events.put(event.getTime(), event);
        } else {
            throw new TimelineOverlapError();
        }
    }

    // PUBLIC GETTERS
    @Nullable
    public MusicEventType getFirst() { return events.firstEntry().getValue(); }
    @Nullable
    public MusicEventType getLast() { return events.lastEntry().getValue(); }
    @Nullable
    public MusicEventType getAt(@NotNull ITime time) {
        return events.get(time);
    }
    @Nullable
    public MusicEventType getBefore(@NotNull ITime time) {
        return events.floorEntry(time).getValue();
    }
    @Nullable
    public MusicEventType getAfter(@NotNull ITime time) {
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
