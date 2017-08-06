package events;

import time.Count;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public interface IFrame<MusicEventType extends IMusicEvent> extends Iterable<MusicEventType> {
    public Count getTiming();
}

class MonoFrame<IMusicEventType extends IMusicEvent> implements IFrame<IMusicEventType> {

    private final Count timing;
    private final ArrayList<IMusicEventType> event;

    public MonoFrame(Count timing, IMusicEventType event) {
        this.timing = timing;
        this.event = new ArrayList<IMusicEventType>(1);
        this.event.set(0,event);
    }

    @Override
    public Count getTiming() {
        return timing;
    }

    @Override
    public Iterator<IMusicEventType> iterator() {
        return event.iterator();
    }
}

class PolyFrame<IMusicEventType extends IMusicEvent> implements IFrame<IMusicEventType> {

    private final Count timing;
    private final TreeSet<IMusicEventType> events;

    public PolyFrame(Count timing, Collection<IMusicEventType> events) {
        this.timing = timing;
        this.events = new TreeSet<IMusicEventType>();
        this.events.addAll(events);
    }

    @Override
    public Count getTiming() {
        return timing;
    }

    @Override
    public Iterator<IMusicEventType> iterator() {
        return events.iterator();
    }
}