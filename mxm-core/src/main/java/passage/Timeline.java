package passage;

import com.sun.istack.internal.NotNull;
import events.IMusicEvent;
import base.time.Time;
import events.InstantEvent;
import events.SpanningEvent;

import java.util.*;

// mutable
@SuppressWarnings("unchecked")
class Timeline <MusicEventType extends IMusicEvent> implements Iterable<Frame<MusicEventType>> {
    private final TreeMap<Time, Frame<MusicEventType>> frames;

    Timeline() {
        this.frames = new TreeMap<>();
    }

    private @NotNull Frame<MusicEventType> getFrameAtOrAdd(@NotNull Time time) {
        if(frames.containsKey(time)) {
            return frames.get(time);
        }
        else {
            Frame frame = new Frame(time);
            put(frame);
            return frame;
        }
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