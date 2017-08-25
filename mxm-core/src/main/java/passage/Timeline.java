package passage;

import org.jetbrains.annotations.NotNull;
import events.IMusicEvent;
import base.time.Time;
import events.InstantEvent;
import events.SpanningEvent;
import events.TempoChange;

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


@SuppressWarnings("unchecked")
class SerialTimeline <MusicEventType extends IMusicEvent> implements Iterable<MusicEventType> {
    private final TreeMap<Time, MusicEventType> events;

    SerialTimeline() {
        this.events = new TreeMap<>();
    }

    // ADDER
    public void addEvent(MusicEventType event) {
        if (events.get(event.getTiming()) == null) {
            events.put(event.getTiming(), event);
        } else {
            throw new Error("Serial Timeline: Cannot add one event on top of another!");
        }
    }

    // PUBLIC GETTERS
    public MusicEventType getFirstEvent() { return events.firstEntry().getValue(); }
    public MusicEventType getLastEvent() { return events.lastEntry().getValue(); }
    public MusicEventType getEventAt(Time time) {
        return events.get(time);
    }
    public MusicEventType getEventBefore(Time time) {
        return events.floorEntry(time).getValue();
    }
    public MusicEventType getEventAfter(Time time) {
        return events.ceilingEntry(time).getValue();
    }

    @Override
    public @NotNull Iterator<MusicEventType> iterator() {
        Collection constValues = java.util.Collections.unmodifiableCollection(events.values());
        return constValues.iterator();
    }
}


@SuppressWarnings("unchecked")
class ParallelTimeline <MusicEventType extends IMusicEvent> implements Iterable<Frame<MusicEventType>> {
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








// Frames can
@SuppressWarnings("unchecked")
class Frame <MusicEventType extends IMusicEvent> {
    private final Time time;
    final TreeSet<MusicEventType> eventsStarted;
    final TreeSet<MusicEventType> eventsContinued;
    final TreeSet<MusicEventType> eventsEnded;
    final TreeSet<MusicEventType> eventsNotStarted;
    final TreeSet<MusicEventType> eventsNotEnded;

    Frame(@NotNull Time time) {
        this.time = time;
        this.eventsStarted      = new TreeSet<>();
        this.eventsContinued    = new TreeSet<>();
        this.eventsEnded        = new TreeSet<>();
        this.eventsNotStarted   = new TreeSet<>();
        this.eventsNotEnded     = new TreeSet<>();
    }

    public void add(@NotNull MusicEventType event) {
        if(event instanceof InstantEvent) {
            eventsStarted.add((MusicEventType) event);
            eventsNotEnded.add((MusicEventType) event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }
    public void addStart(@NotNull MusicEventType event) {
        if(event instanceof SpanningEvent) {
            eventsStarted.add((MusicEventType) event);
            eventsNotEnded.add((MusicEventType) event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }
    public void addContinue(@NotNull MusicEventType event) {
        if(event instanceof SpanningEvent) {
            eventsContinued.add((MusicEventType)event);
            eventsNotEnded.add((MusicEventType)event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }
    public void addEnd(@NotNull MusicEventType event) {
        if(event instanceof SpanningEvent) {
            eventsEnded.add((MusicEventType)event);
            eventsNotStarted.add((MusicEventType)event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }

    public final @NotNull Time getTime() {
        return time;
    }
    public final @NotNull Iterator<MusicEventType> eventsStartedItr() {
        Collection constValues = java.util.Collections.unmodifiableCollection(eventsStarted);
        return constValues.iterator();
    }
    public final @NotNull Iterator<MusicEventType> eventsContinuedItr() {
        Collection constValues = java.util.Collections.unmodifiableCollection(eventsContinued);
        return constValues.iterator();
    }
    public final @NotNull Iterator<MusicEventType> eventsEndedItr() {
        Collection constValues = java.util.Collections.unmodifiableCollection(eventsEnded);
        return constValues.iterator();
    }
    public final @NotNull Iterator<MusicEventType> eventsNotStartedItr() {
        Collection constValues = java.util.Collections.unmodifiableCollection(eventsNotStarted);
        return constValues.iterator();
    }
    public final @NotNull Iterator<MusicEventType> eventsNotEndedItr() {
        Collection constValues = java.util.Collections.unmodifiableCollection(eventsNotEnded);
        return constValues.iterator();
    }
}