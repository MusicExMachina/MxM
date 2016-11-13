package model.form;

import model.time.Count;
import model.trainable.Instrument;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Lines are horizontal arrangements of Notes that are heard as a continuous horizontal slice.
 */
public class Line implements Iterable<Note>{

    private Instrument instrument;
    private Passage passage;
    private TreeMap<Count,Note> notes;

    public Line(Instrument instrument, Passage passage) {
        this.instrument = instrument;
        this.passage = passage;
        this.notes = new TreeMap<>();
    }

    public void add(Note note) {
        // If this is the wrong instrument for this note
        if(note.getInstrument() != instrument) {
            throw new Error("LINE:\tWrong instrument!");
        }

        // Gets the note before and after this note
        Note previousNote = notes.floorEntry(note.getStart()).getValue();
        Note followingNote = notes.ceilingEntry(note.getStart()).getValue();

        // If this note starts before the last one ends
        if(previousNote != null && note.getStart().compareTo(previousNote.getEnd()) < 0) {
            throw new Error("LINE:\tTrying to add a Note which overlaps the others!");
        }
        // If the next note starts befor this new note ends
        if(followingNote != null && note.getEnd().compareTo(followingNote.getStart()) < 0) {
            throw new Error("LINE:\tTrying to add a Note which overlaps the others!");
        }
        notes.put(note.getStart(),note);
    }

    @Override
    public Iterator<Note> iterator() {
        return notes.values().iterator();
    }
}