package form;

import base.instrument.Instrument;
import base.sound.Dynamic;
import base.sound.Technique;
import base.time.Count;
import events.playable.PlayableEvent;
import events.eventTypes.DynamicEvent;
import events.playable.Note;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Parts represent a collection of
 *
 * A single instrument can play a part
 * A single section can play one part (
 * A single section can play many parts (think of four horn parts).
 */
public abstract class Part<NoteType extends PlayableEvent> implements Iterable<NoteType> {

    /** The instrument that plays this line. */
    private Instrument instrument;

    /** The notes played in this line. */
    private TreeMap<Count, NoteType> notes;

    private TreeMap<Count, DynamicEvent> dynamic;


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


    public void getNoteAt(Count count) {}

    public void getDynamicAt(Count count) {}

    public void getTechniqueAAt(Count count) {}


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
    public Iterator<NoteType> iterator() {
        return notes.values().iterator();
    }

    public Iterator<Dynamic> dynamicItr() { return null; }

    public Iterator<Technique> techniqueItr() { return null; }

}