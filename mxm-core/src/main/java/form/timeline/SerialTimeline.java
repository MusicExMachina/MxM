package form.timeline;

import properties.time.ITime;
import events.IMusicEvent;
import form.exceptions.SerialTimelineOverlapException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

@SuppressWarnings("unchecked")
public final class SerialTimeline <MusicEventType extends IMusicEvent> implements ISerialTimeline<MusicEventType> {
    private final TreeMap<ITime, MusicEventType> events;

    SerialTimeline() {
        this.events = new TreeMap<>();
    }

    // ADDER
    // Package private on purpose- we don't want users adding events, only score.
    void addEvent(MusicEventType event) {
        if (events.get(event.getTiming()) == null) {
            events.put(event.getTiming(), event);
        } else {
            try {
                throw new SerialTimelineOverlapException();
            } catch (SerialTimelineOverlapException e) {
                e.printStackTrace();
            }
        }
    }

    // PUBLIC GETTERS
    @Nullable
    public MusicEventType getFirstEvent() { return events.firstEntry().getValue(); }
    @Nullable
    public MusicEventType getLastEvent() { return events.lastEntry().getValue(); }
    @Nullable
    public MusicEventType getEventAt(@NotNull ITime time) {
        return events.get(time);
    }
    @Nullable
    public MusicEventType getEventBefore(@NotNull ITime time) {
        return events.floorEntry(time).getValue();
    }
    @Nullable
    public MusicEventType getEventAfter(@NotNull ITime time) {
        return events.ceilingEntry(time).getValue();
    }

    @Override
    public @NotNull Iterator<MusicEventType> iterator() {
        Collection constValues = java.util.Collections.unmodifiableCollection(events.values());
        return constValues.iterator();
    }
}
