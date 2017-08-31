package form;

import base.time.Time;
import form.musicEvents.IMusicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

@SuppressWarnings("unchecked")
public class SerialTimeline <MusicEventType extends IMusicEvent> implements Iterable<MusicEventType> {
    private final TreeMap<Time, MusicEventType> events;

    SerialTimeline() {
        this.events = new TreeMap<>();
    }

    // ADDER
    // Package private on purpose- we don't want users adding form.musicEvents, only scores.
    void addEvent(MusicEventType event) {
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
