package events;

import com.sun.istack.internal.NotNull;
import time.Count;

import java.util.*;

/**
 * A frame represents
 *
 * Note that since
 */
public abstract class Frame<MusicEventType extends MusicEvent> implements Iterable<MusicEventType>, Comparator<Frame>, Comparable<Frame> {

    private final Count timing;

    public Frame(@NotNull Count timing) {
        this.timing = timing;
    }

    public final Count getTiming() {
        return timing;
    }

    @Override
    public final int compareTo(Frame other) {
        return this.getTiming().compareTo(other.getTiming());
    }

    @Override
    public final int compare(Frame frame1, Frame frame2) {
        return frame1.getTiming().compareTo(frame2.getTiming());
    }

    @Override
    public abstract Iterator<MusicEventType> iterator();
}

class MonoFrame<MusicEventType extends MusicEvent> extends Frame<MusicEventType> {

    private final ArrayList<MusicEventType> event;

    public MonoFrame(Count timing, MusicEventType event) {
        super(timing);
        this.event = new ArrayList<MusicEventType>(1);
        this.event.set(0,event);
    }

    @Override
    public Iterator<MusicEventType> iterator() {
        return event.iterator();
    }
}

class PolyFrame<MusicEventType extends MusicEvent> extends Frame<MusicEventType> {

    /** All events that occur starting on this frame */
    private final TreeSet<MusicEventType> events;

    public PolyFrame(Count timing, Collection<MusicEventType> events) {
        super(timing);
        this.events = new TreeSet<MusicEventType>();
        this.events.addAll(events);
    }

    @Override
    public Iterator<MusicEventType> iterator() {
        return events.iterator();
    }
}