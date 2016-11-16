package io;

import model.trainable.Instrument;

import java.util.Iterator;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by celenp on 11/15/2016.
 */
public class MidiLine implements Iterable<MidiNote> {

    public Instrument getInstrument() {
        return instrument;
    }

    Instrument instrument;
    TreeMap<Float, MidiNote> notes;

    public MidiLine(Instrument instrument) {
        this.instrument = instrument;
        notes = new TreeMap<>();
    }

    public float getStart() {
        return notes.firstEntry().getValue().getStart();
    }

    public float getEnd() {
        return notes.lastEntry().getValue().getEnd();
    }

    public NavigableMap<Float,MidiNote> getMeasure(int measure) {
        return notes.subMap(measure+0f,true,measure+1f,false);
    }

    public void add(MidiNote note) {
        if(canAdd(note)) {
            this.notes.put(note.getStart(),note);
        }
        else {
            throw new Error("Cannot add a note here!");
        }
    }

    public boolean canAdd(MidiNote note) {

        if(instrument != note.getInstrument()) {
            return false;
        }
        if(notes.floorEntry(note.getStart()) != null &&
                notes.floorEntry(note.getStart()).getValue().getEnd() > note.getStart()) {
            return false;
        }
        if(notes.ceilingEntry(note.getStart()) != null &&
                notes.ceilingEntry(note.getStart()).getValue().getStart() > note.getEnd()) {
            return false;
        }
        return true;
    }

    @Override
    public Iterator<MidiNote> iterator() {
        return notes.values().iterator();
    }
}
