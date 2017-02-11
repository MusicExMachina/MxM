package form;

import basic.Count;
import rhythmTree.RhythmNode;
import rhythmTree.RhythmTree;
import trainable.Instrument;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Lines are horizontal arrangements of Notes that are heard as a continuous horizontal slice.
 */
public class Part implements Iterable<Note> {

    /** The rhythm that underlies this line. */
    private Rhythm rhythm;

    /** The instrument that plays this line. */
    private Instrument instrument;

    /** The notes played in this line. */
    private TreeMap<Count,Note> notes;

    /**
     * The line constructor starts only with the instrument
     * playing this passage, as measures are added later.
     * @param instrument
     */
    public Part(Instrument instrument) {
        this.rhythm = new Rhythm();
        this.instrument = instrument;
        this.notes = new TreeMap<>();
    }

    public void add(RhythmTree rhythmTree) {
        for(RhythmNode node : rhythmTree) {
            Note note = node.getNote();
            if(note != null) {
                notes.put(note.getStart(),note);
                /*
                // If this is the wrong instrument for this note
                if(note.getInstrument() != instrument) {
                    throw new Error("LINE:\tWrong instrument!");
                }
                */
                /*
                // If this note starts before the last one ends
                if(notes.floorEntry(note.getStart()) != null &&
                        note.getStart().compareTo(notes.floorEntry(note.getStart()).getValue().getEnd()) < 0) {
                    throw new Error("LINE:\tTrying to add a Note which overlaps the others!");
                }
                // If the next note starts befor this new note ends
                if(notes.ceilingEntry(note.getStart()) != null &&
                        note.getEnd().compareTo(notes.ceilingEntry(note.getStart()).getValue().getStart()) < 0) {
                    throw new Error("LINE:\tTrying to add a Note which overlaps the others!");
                }
                */
            }
        }
    }

    public Instrument getInstrument() { return instrument; }

    public Rhythm getRhythm() {
        return rhythm;
    }

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