package form.timelines;

import base.time.ITime;
import base.time.Time;
import form.IFrame;
import form.events.IMusicEvent;
import form.events.AbstractInstantEvent;
import form.events.AbstractSpanningEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

// Frames can
@SuppressWarnings("unchecked")
class Frame <MusicEventType extends IMusicEvent> implements IFrame<MusicEventType> {
    private final ITime time;
    final TreeSet<MusicEventType> eventsStarted;
    final TreeSet<MusicEventType> eventsContinued;
    final TreeSet<MusicEventType> eventsEnded;
    final TreeSet<MusicEventType> eventsNotStarted;
    final TreeSet<MusicEventType> eventsNotEnded;

    Frame(@NotNull ITime time) {
        this.time = time;
        this.eventsStarted      = new TreeSet<>();
        this.eventsContinued    = new TreeSet<>();
        this.eventsEnded        = new TreeSet<>();
        this.eventsNotStarted   = new TreeSet<>();
        this.eventsNotEnded     = new TreeSet<>();
    }
    // Package private on purpose- we don't want users adding form.events, only scores.
    void add(@NotNull MusicEventType event) {
        if(event instanceof AbstractInstantEvent) {
            eventsStarted.add((MusicEventType) event);
            eventsNotEnded.add((MusicEventType) event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }
    // Package private on purpose- we don't want users adding form.events, only scores.
    void addStart(@NotNull MusicEventType event) {
        if(event instanceof AbstractSpanningEvent) {
            eventsStarted.add((MusicEventType) event);
            eventsNotEnded.add((MusicEventType) event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }
    // Package private on purpose- we don't want users adding form.events, only scores.
    void addContinue(@NotNull MusicEventType event) {
        if(event instanceof AbstractSpanningEvent) {
            eventsContinued.add((MusicEventType)event);
            eventsNotEnded.add((MusicEventType)event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }
    // Package private on purpose- we don't want users adding form.events, only scores.
    void addEnd(@NotNull MusicEventType event) {
        if(event instanceof AbstractSpanningEvent) {
            eventsEnded.add((MusicEventType)event);
            eventsNotStarted.add((MusicEventType)event);
        }
        else throw new Error("Frame: Cannot add an event of type " + event.getClass());
    }

    public final @NotNull ITime getTime() {
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
