package form.timeline;

import events.AbstractInstantEvent;
import events.AbstractSpanningEvent;
import properties.time.ITime;
import form.IFrame;
import events.IMusicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;

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
