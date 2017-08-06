package form;

import com.sun.istack.internal.NotNull;
import events.IMusicEvent;
import time.ITime;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public interface IFrame<TimeType extends ITime> {
    TimeType getTiming();
}

class MonoFrame<TimeType extends ITime, MusicEventType extends IMusicEvent<TimeType>>
        implements IFrame<TimeType> {

    private final TimeType timing;
    private final MusicEventType event;

    public MonoFrame(@NotNull TimeType timing, @NotNull MusicEventType event) {
        this.timing = timing;
        this.event = event;
    }

    @Override
    public TimeType getTiming() {
        return timing;
    }
}

class PolyFrame<TimeType extends ITime, MusicEventType extends IMusicEvent<TimeType>>
        implements IFrame<TimeType>, Iterable<MusicEventType> {

    private final TimeType timing;
    private final TreeSet<MusicEventType> events;

    public PolyFrame(@NotNull TimeType timing, @NotNull Collection<MusicEventType> events) {
        this.timing = timing;
        this.events = new TreeSet<>();
        this.events.addAll(events);
    }

    @Override
    public TimeType getTiming() {
        return timing;
    }
    public Iterator<MusicEventType> iterator() {
        return events.iterator();
    }
}