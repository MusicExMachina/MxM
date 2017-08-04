package time;

import com.sun.istack.internal.NotNull;
import events.MusicEvent;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * A frame represents
 *
 * Note that since
 */
public class Frame <MusicEventType extends MusicEvent> implements Iterable<MusicEventType>, Comparator<Frame>, Comparable<Frame> {

    /** The time that the frame starts */
    private Count timing;
    /** All events that occur starting on this frame */
    private TreeSet<MusicEventType> events;

    /** */
    public Frame(@NotNull Count timing) {
        this.timing = timing;
        this.events = new TreeSet<>();
    }

    public Count getTiming() {
        return timing;
    }

    @Override
    public int compareTo(Frame other) {
        return this.getTiming().compareTo(other.getTiming());
    }

    @Override
    public int compare(Frame frame1, Frame frame2) {
        return frame1.getTiming().compareTo(frame2.getTiming());
    }

    @Override
    public Iterator<MusicEventType> iterator() {
        return events.iterator();
    }
}
