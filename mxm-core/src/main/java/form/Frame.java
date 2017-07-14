package form;

import events.MusicEvent;
import events.TempoChange;
import events.TimeSigChange;
import time.Count;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * A frame represents
 */
public class Frame implements Iterable<MusicEvent>, Comparator<Frame>, Comparable<Frame> {

    Count start;
    TreeSet<MusicEvent> events;
    TempoChange tempoChange;
    TimeSigChange timeSigChange;

    public Frame(Count timing) {
        this.start = timing;
        this.events = new TreeSet<>();
        this.tempoChange = null;
        this.timeSigChange = null;
    }

    public Count getStart() {
        return start;
    }

    /*
    public Iterator<Note> noteItr() {
        return events.iterator();
    }
    */

    @Override
    public Iterator<MusicEvent> iterator() {
        return events.iterator();
    }

    @Override
    public int compareTo(Frame other) {
        return this.getStart().compareTo(other.getStart());
    }

    @Override
    public int compare(Frame frame1, Frame frame2) {
        return frame1.getStart().compareTo(frame2.getStart());
    }
}
