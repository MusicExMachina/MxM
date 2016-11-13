package model.form;

import model.basic.Pitch;
import model.basic.Count;

import java.util.*;

/**
 * Frames are vertical arrangements of Notes that are sounded (at least their starts) all at once.
 */
public class Frame implements Iterable<Note> {

    private Count time;
    private Passage passage;
    private TreeMap<Pitch,Note> notes;

    public Frame(Count time,Passage passage) {
        this.time = time;
        this.passage = passage;
        this.notes = new TreeMap<>();
    }

    public void add(Note note) {
        notes.put(note.getPitch(),note);
    }

    @Override
    public Iterator<Note> iterator() {
        return notes.values().iterator();
    }
}