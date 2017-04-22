package form;

import base.*;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Lines are horizontal arrangements of Notes that are heard as a
 * continuous horizontal slice.
 */
public class Part implements Iterable<Note> {


    /** The instrument that plays this line. */
    private Instrument instrument;

    /** The notes played in this line. */
    private TreeMap<Count, Note> notes;

    /**
     * The line constructor starts only with the instrument
     * playing this passage, as measures are added later.
     * @param instrument
     */
    public Part(Instrument instrument) {
        this.instrument = instrument;
        this.notes = new TreeMap<>();
    }

    public void add(Note note) {
        notes.put(note.getStart(),note);
    }

    public Instrument getInstrument() { return instrument; }

    public float getLastTime() {
        if(notes.size() > 0) {
            return notes.firstKey().toFloat();
        }
        else {
            return 0f;
        }
    }

    /**
     * Returns a nicely-formatted string representing this line.
     * @return A string representing this line.
     */
    @Override
    public String toString() {
        String toReturn = instrument.toString() + "\n\t\t";
        for(Note note : this) {
            toReturn += note.toString() + " ";
        }
        return toReturn + "\n";
    }

    /**
     * Returns an iterator over all the notes in this line.
     * @return An iterator over all the notes in this line.
     */
    @Override
    public Iterator<Note> iterator() {
        return notes.values().iterator();
    }
}