package time;

import events.IMusicEvent;
import events.TempoChange;
import events.TimeSigChange;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * A frame represents
 *
 * Note that since
 */
public class Frame <TimeType extends ITime> implements Iterable<IMusicEvent>, Comparator<Frame>, Comparable<Frame> {

    private TimeType timing;
    private TreeSet<? extends IMusicEvent<TimeType>> events;
    private TempoChange tempoChange;
    private TimeSigChange timeSigChange;

    public Frame(TimeType timing) {
        this.timing = timing;
        this.events = new TreeSet<>();
        this.tempoChange = null;
        this.timeSigChange = null;
    }

    public TimeType getTiming() {
        return timing;
    }

    @Override
    public Iterator<IMusicEvent> iterator() {
        return (Iterator<IMusicEvent>) events.iterator();
    }

    @Override
    public int compareTo(Frame other) {
        return this.getTiming().compareTo(other.getTiming());
    }

    @Override
    public int compare(Frame frame1, Frame frame2) {
        return frame1.getTiming().compareTo(frame2.getTiming());
    }
}
