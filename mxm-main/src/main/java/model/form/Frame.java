package model.form;

import model.time.Count;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class for storing information about contemporaneous events in a Passage.
 */
public class Frame implements Iterable<Note> {

    private Count time;
    private Passage passage;
    private ArrayList<Note> notes;

    public Frame(Count time,Passage passage) {
        this.time = time;
        this.passage = passage;
        this.notes = new ArrayList<>();
    }

    public void add(Note note) {
        notes.add(note);
    }

    @Override
    public Iterator<Note> iterator() {
        return notes.iterator();
    }
}
