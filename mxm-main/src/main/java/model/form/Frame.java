package model.form;

import model.time.Count;
import model.basic.Dynamic;
import model.pitch.Pitch;
import model.time.Tempo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * A class for storing information about single events
 * in a Passage.
 */
public class Frame {

    Count time;
    TreeSet<Note> notes;
    Dynamic dynamic;
    Tempo tempo;

    public Iterator<Note> getNotes() {
        return notes.iterator();
    }

    public void addNote(Note note) {
        if(!notes.contains(note)) {
            notes.add(note);
        }
        else {
            throw new Error("FRAME:\nThis frame already contains this note!");
        }
    }

    public Dynamic getDynamic() {
        return dynamic;
    }

    /**
     * A setter for this Frame's Dynamic level.
     * @param dynamic The Dynamic of this Frame.
     */
    public void setDynamic(Dynamic dynamic) {
        this.dynamic = dynamic;
    }

    /**
     * A getter for this Frame's Tempo.
     * @return The Tempo of this Frame.
     */
    public Tempo getTempo() {
        return tempo;
    }

    /**
     * A setter for this Frame's Tempo.
     * @param tempo The Tempo of this Frame.
     */
    public void setTempo(Tempo tempo) {
        this.tempo = tempo;
    }
}
