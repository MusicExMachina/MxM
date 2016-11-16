package model.form;

import model.basic.Count;
import model.rhythmTree.RhythmNode;
import model.rhythmTree.RhythmTree;
import model.trainable.Instrument;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Lines are horizontal arrangements of Notes that are heard as a continuous horizontal slice.
 */
public class Line implements Iterable<Note>{

    private Rhythm rhythm;
    private Contour contour;
    private Instrument instrument;
    private TreeMap<Count,Note> notes;

    public Line(Instrument instrument) {
        this.rhythm = new Rhythm();
        this.contour = new Contour();
        this.instrument = instrument;
        this.notes = new TreeMap<>();
    }

    public void add(int measure,RhythmTree tree) {
        for(RhythmNode node : tree) {
            Note note = node.getNote();
            if(note != null) {
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
                notes.put(note.getStart(),note);
            }
        }
        rhythm.add(measure,tree);
    }

    public Instrument getInstrument() { return instrument; }

    public Rhythm getRhythm() {
        return rhythm;
    }

    public Contour getContour() {
        return contour;
    }

    public float getLastTime() {
        if(notes.size() > 0 ) {
            return notes.firstKey().toFloat();
        }
        else {
            return 0f;
        }
    }

    @Override
    public String toString() {
        String toReturn = instrument.toString();
        for(Note note : this) {
            toReturn += note.toString() + " ";
        }
        return toReturn;
    }

    @Override
    public Iterator<Note> iterator() {
        return notes.values().iterator();
    }
}