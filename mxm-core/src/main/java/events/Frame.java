package events;

import time.Count;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by celenp on 6/11/2017.
 */
public class Frame implements Iterable<MusicEvent>, Comparator<Frame>, Comparable<Frame> {

    Count timing;
    TreeSet<MusicEvent> events;
    TempoChange tempoChange;
    TimeSigChange timeSigChange;

    public Frame(Count timing) {
        this.timing = timing;
        this.events = new TreeSet<>();
        this.tempoChange = null;
        this.timeSigChange = null;
    }

    public Count getTiming() {
        return timing;
    }

    public Iterator<Note> noteItr() {
        events.iterator();
    }

    @Override
    public Iterator<MusicEvent> iterator() {
        return events.iterator();
    }

    @Override
    public int compareTo(Frame other) {
        return this.getTiming().compareTo(other.getTiming());
    }

    @Override
    public int compare(Frame frame1, Frame frame2) {
        return frame1.getTiming().compareTo(other.getTiming());
    }
}
