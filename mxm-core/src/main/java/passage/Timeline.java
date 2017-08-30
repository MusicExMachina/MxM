package passage;

import org.jetbrains.annotations.NotNull;
import passage.musicEvents.IMusicEvent;
import base.time.Time;
import passage.musicEvents.InstantEvent;
import passage.musicEvents.SpanningEvent;

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
    // Package private on purpose- we don't want users adding passage.musicEvents, only scores.
    void add(@NotNull MusicEventType event) {
        if(event instanceof InstantEvent) {
            eventsStarted.add((MusicEventType) event);
            eventsNotEnded.add((MusicEventType) event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }
    // Package private on purpose- we don't want users adding passage.musicEvents, only scores.
    void addStart(@NotNull MusicEventType event) {
        if(event instanceof SpanningEvent) {
            eventsStarted.add((MusicEventType) event);
            eventsNotEnded.add((MusicEventType) event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }
    // Package private on purpose- we don't want users adding passage.musicEvents, only scores.
    void addContinue(@NotNull MusicEventType event) {
        if(event instanceof SpanningEvent) {
            eventsContinued.add((MusicEventType)event);
            eventsNotEnded.add((MusicEventType)event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }
    // Package private on purpose- we don't want users adding passage.musicEvents, only scores.
    void addEnd(@NotNull MusicEventType event) {
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