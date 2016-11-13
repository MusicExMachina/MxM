package model.form;

import model.trainable.Instrument;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by celenp on 11/12/2016.
 */
public class Line implements Iterable<Note>{

    private Instrument instrument;
    private Passage passage;
    private ArrayList<Note> notes;

    public Line(Instrument instrument, Passage passage) {
        this.instrument = instrument;
        this.passage = passage;
        this.notes = new ArrayList<>();
    }

    public void add(Note note) {
        // If the last note doesn't end in time for this one to start
        if(notes.size() > 0 && notes.get(notes.size()-1).getEnd().compareTo(note.getStart()) > 0) {
            throw new Error("LINE:\tTrying to add a Note which overlaps the others!");
        }
        notes.add(note);
    }

    @Override
    public Iterator<Note> iterator() {
        return notes.iterator();
    }
}
