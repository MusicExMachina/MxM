package io;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by celenp on 11/15/2016.
 */
public class MidiFrame implements Comparable<MidiFrame>, Comparator<MidiFrame>, Iterable<MidiNote> {

    private float timing;
    private TreeSet<MidiNote> notes;

    public MidiFrame(float timing) {
        this.timing = timing;
        notes = new TreeSet<>();
    }

    public void add(MidiNote note) {
        if(Float.compare(note.getStart(),timing) == 0) {
            notes.add(note);
        }
        else {
            throw new Error("Midi note doesn't line up with midi frame!");
        }
    }

    @Override
    public Iterator<MidiNote> iterator() {
        return notes.iterator();
    }

    @Override
    public int compareTo(MidiFrame o) {
        return Float.compare(this.timing,o.timing);
    }

    @Override
    public int compare(MidiFrame o1, MidiFrame o2) {
        return Float.compare(o1.timing,o2.timing);
    }
}
