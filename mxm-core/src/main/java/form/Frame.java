package form;


import com.sun.istack.internal.NotNull;
import events.IMusicEvent;
import time.Time;

import java.util.Iterator;
import java.util.TreeSet;

// Frames can be in multiple timelines... no one score can have two frames at the same time
// mutable
public class Frame implements Iterable<IMusicEvent> {
    private final Time timing;
    private final TreeSet<IMusicEvent> events;

    Frame(@NotNull Time timing) {
        this.timing = timing;
        this.events = new TreeSet<>();
    }

    public void add(@NotNull IMusicEvent event) { events.add(event); }
    public @NotNull Time getTiming() {
        return timing;
    }
    public @NotNull Iterator<IMusicEvent> iterator() {
        return events.iterator();
    }
}