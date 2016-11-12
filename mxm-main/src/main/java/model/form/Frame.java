package model.form;

import model.time.Count;
import model.basic.Dynamic;
import model.pitch.Pitch;
import model.time.Tempo;

import java.util.*;

/**
 * A class for storing information about contemporaneous events in a Passage.
 */
public class Frame {

    /** A TreeMap that stores all Notes, ordered by Pitch. */
    private TreeMap<Pitch,Note> notes;

    /** The time at which this Frame occurs (starts). */
    private Count time;

    /** The Tempo of this Frame. */
    private Tempo tempo;

    /** The Dynamic of this Frame. */
    private Dynamic dynamic;

    /**
     * Constructs a frame at a given time.
     * @param time The time this Frame starts.
     */
    public Frame(Count time) {
        this.notes = new TreeMap<>();
        this.time = time;
    }

    /**
     * Adds a Note to this Frame, if the Note's Pitch is not
     * already represented, and the Note's start is exactly the
     * same as this Frame's time.
     * @param note
     */
    public void add(Note note) {
        if(!notes.containsKey(note.getPitch())) {
            if(note.getStart().equals(time)) {
                notes.put(note.getPitch(),note);
            }
            else {
                throw new Error("FRAME:\tThis Note doesn't start on this Frame!");
            }
        }
        else {
            throw new Error("FRAME:\tThis frame already contains this pitch!");
        }
    }

    /**
     * Gets the time at which this Frame occurs.
     * @return The time this Frame starts.
     */
    public Count getTime() {
        return time;
    }

    /**
     * A getter for this Frame's Dynamic.
     * @return The Dynamic of this Frame.
     */
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

    /**
     * Returns an iterator over all Notes in this Frame, Pitch-ordered.
     * @return An iterator over all Notes in this Frame.
     */
    public Iterator<Note> iterator() {
        return notes.values().iterator();
    }
}
