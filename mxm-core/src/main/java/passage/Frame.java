package passage;


import com.sun.istack.internal.NotNull;
import events.IMusicEvent;
import base.time.Time;
import events.InstantEvent;
import events.SpanningEvent;

import java.util.Iterator;
import java.util.TreeSet;

// Frames can be in multiple timelines... no one score can have two frames at the same base.time
// mutable
public class Frame implements Iterable<IMusicEvent> {
    private final Time time;

    private final TreeSet<IMusicEvent> eventStarts;
    private final TreeSet<SpanningEvent> eventContinues;
    private final TreeSet<SpanningEvent> eventEnds;
    private final TreeSet<IMusicEvent> eventsNotEnded;

    Frame(@NotNull Time time) {
        this.time = time;
        this.eventStarts = new TreeSet<>();
        this.eventContinues = new TreeSet<>();
        this.eventEnds = new TreeSet<>();
        this.eventsNotEnded = new TreeSet<>();
    }

    public void add(@NotNull InstantEvent event) { eventStarts.add(event); eventsNotEnded.add(event); }
    public void addStart(@NotNull SpanningEvent event) { eventStarts.add(event); eventsNotEnded.add(event); }
    public void addContinue(@NotNull SpanningEvent event) { eventContinues.add(event); eventsNotEnded.add(event); }
    public void addEnd(@NotNull SpanningEvent event) { eventEnds.add(event); }

    public @NotNull Time getTime() {
        return time;
    }
    public @NotNull Iterator<IMusicEvent> iterator() {
        return eventsNotEnded.iterator();
    }
}