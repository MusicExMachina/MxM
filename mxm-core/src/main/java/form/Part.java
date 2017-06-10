package form;

import base.*;
import events.MusicEvent;
import events.MusicTimeline;
import events.PlayableEvent;
import events.eventTypes.NoteEvent;

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
    private MusicTimeline<MusicEvent> notes;

    private TreeMap<Count, NoteEvent> dynamic;

    private TreeMap<Count, NoteEvent> notes;

    /**
     * The line constructor starts only with the instrument
     * playing this passage, as measures are added later.
     * @param instrument
     */
    public Part(Instrument instrument) {
        this.instrument = instrument;
        this.notes = new TreeMap<>();
    }

    public void add(NoteEvent note) {
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
        for(NoteEvent note : this) {
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