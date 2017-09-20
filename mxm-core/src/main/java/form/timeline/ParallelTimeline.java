package form.timeline;

import events.AbstractInstantEvent;
import events.AbstractSpanningEvent;
import properties.time.ITime;
import events.IMusicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

/**
 * <p> <b>Interface Overview:</b>
 * The {@link ParallelTimeline} interface represents a timeline in which musical events may occur at any time, even
 * simultaneously. This contrasts starkly with a {@link SerialTimeline} in which many events must happen one after the
 * other, with no overlap. In a {@link ParallelTimeline}, contemporaneous events are stored in a {@link IFrame}, which
 * is simply a vertical stack of events at a given time</p>
 *
 * @param <MusicEventType> The subclass of IMusicEvent which this timeline may hold. It's worth noting that this might
 *                        be IMusicEvent itself- in which case, all music events may be stored within this timeline.
 *
 * @author Patrick Celentano
 */
final class ParallelTimeline <MusicEventType extends IMusicEvent> implements ITimeline<IFrame<MusicEventType>> {

    private final TreeMap<ITime, IFrame<MusicEventType>> frames;

    public ParallelTimeline() {
        this.frames = new TreeMap<>();
    }

    // PUBLIC GETTERS
    @Override
    public final @NotNull IFrame<MusicEventType> getFirst() { return frames.firstEntry().getValue(); }
    @Override
    public final @NotNull IFrame<MusicEventType> getLast() { return frames.lastEntry().getValue(); }
    @Override
    public final @NotNull IFrame<MusicEventType> getAt(@NotNull ITime time) {
        return frames.get(time);
    }
    @Override
    public final @NotNull IFrame<MusicEventType> getBefore(@NotNull ITime time) {
        return frames.floorEntry(time).getValue();
    }
    @Override
    public final @NotNull IFrame<MusicEventType> getAfter(@NotNull ITime time) {
        return frames.ceilingEntry(time).getValue();
    }

    @Override
    public @NotNull Stream<IFrame<MusicEventType>> stream() {
        return java.util.Collections.unmodifiableCollection(frames.values()).stream();
    }

    @Override
    public @NotNull Stream<IFrame<MusicEventType>> parallelStream() {
        return java.util.Collections.unmodifiableCollection(frames.values()).parallelStream();
    }
    @Override
    public final @NotNull Iterator<IFrame<MusicEventType>> iterator() {
        return java.util.Collections.unmodifiableCollection(frames.values()).iterator();
    }

    @Override
    public @NotNull Spliterator<IFrame<MusicEventType>> spliterator() {
        return java.util.Collections.unmodifiableCollection(frames.values()).spliterator();
    }
}

// Frames can
class Frame <MusicEventType extends IMusicEvent> implements IFrame<MusicEventType> {

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** The time at which this frame occurs */
    private final ITime time;
    /** All events which start exactly on this frame */
    private final TreeSet<MusicEventType> startedEvents;
    /** All events which start or continue through this frame */
    private final TreeSet<MusicEventType> ongoingEvents;
    /** All events which continue through this frame */
    private final TreeSet<MusicEventType> continuedEvents;
    /** All events which end exactly on this frame */
    private final TreeSet<MusicEventType> endedEvents;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * A limited-access constructor which takes in a time, and initializes the various event-holding collections
     * @param time the time at which this event occurs
     */
    Frame(@NotNull ITime time) {
        this.time = time;
        this.startedEvents = new TreeSet<>();
        this.ongoingEvents = new TreeSet<>();
        this.continuedEvents = new TreeSet<>();
        this.endedEvents = new TreeSet<>();
    }

    // Package private on purpose- we don't want users adding events, only score.
    void add(@NotNull MusicEventType event) {
        if(event instanceof AbstractInstantEvent) {
            startedEvents.add(event);
            ongoingEvents.add(event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }
    // Package private on purpose- we don't want users adding events, only score.
    void addStart(@NotNull MusicEventType event) {
        if(event instanceof AbstractSpanningEvent) {
            startedEvents.add(event);
            ongoingEvents.add(event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }
    // Package private on purpose- we don't want users adding events, only score.
    void addContinue(@NotNull MusicEventType event) {
        if(event instanceof AbstractSpanningEvent) {
            continuedEvents.add(event);
            ongoingEvents.add(event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }
    // Package private on purpose- we don't want users adding events, only score.
    void addEnd(@NotNull MusicEventType event) {
        if(event instanceof AbstractSpanningEvent) {
            endedEvents.add((MusicEventType)event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }

    public final @NotNull ITime getTime() {
        return time;
    }

    public final @NotNull Collection<MusicEventType> startedEvents() {
        return Collections.unmodifiableCollection(startedEvents);
    }
    public final @NotNull Collection<MusicEventType> ongoingEvents() {
        return Collections.unmodifiableCollection(ongoingEvents);
    }
    public final @NotNull Collection<MusicEventType> continuedEvents() {
        return Collections.unmodifiableCollection(continuedEvents);
    }
    public final @NotNull Collection<MusicEventType> endedEvents() {
        return Collections.unmodifiableCollection(endedEvents);
    }
}
