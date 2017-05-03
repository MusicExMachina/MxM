package form;

import base.Count;
import base.Tempo;
import base.TimeSignature;

import java.util.*;

/**
 * Passages are simply contemporaneous (or not) collections of
 * parts. They are this project's main way of transporting "music"
 * and the internal structures of the music. form.Note that a passage
 * is unified by its temporal nature- time signatures, tempos, and
 * the like.
 */
public class Passage implements Iterable<Part> {

    /** The name of this passage. */
    private String name;

    /** The composer of this passage. */
    private String composer;

    /** Time signature (changes) throughout this passage. */
    private NavigableMap<Integer, TimeSignature> timeSigs;

    /** base.Tempo (changes) throughout this passage. */
    private NavigableMap<Count, Tempo> tempi;

    /** All parts being played in this passage. */
    private List<Part> parts;

    /**
     * The passage constructor, which takes no input and
     * simply initializes the internal containers.
     */
    public Passage() {
        this.timeSigs = new TreeMap<>();
        this.tempi = new TreeMap<>();
        this.parts = new ArrayList<>();
    }

    /**
     * Getter for the name of this Passage.
     * @return the name of this Passage.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the composer of this Passage.
     * @return the composer of this Passage.
     */
    public String getComposer() {
        return composer;
    }

    /**
     * Adds a time signature (change) to the passage at a
     * given measure, or throws an error, if that measure
     * already has a time signature change.
     * @param timeSignature The base.TimeSignature to add to this form.Passage.
     * @param measure The time at which to add this base.TimeSignature.
     */
    public void addTimeSignature(TimeSignature timeSignature,int measure) {
        if(!timeSigs.containsKey(measure)) {
            timeSigs.put(measure,timeSignature);
        }
        else {
            //throw new Error("PASSAGE:\tTrying to add a time signature change on top of another one!");
        }
    }

    /**
     * Adds a tempo (change) to this passage at a given time,
     * or throws an error if this overlaps another tempo change.
     * @param tempo The tempo to add to this passage.
     * @param time The time at which to add this tempo.
     */
    public void addTempoChange(Tempo tempo, Count time) {
        if(!tempi.containsKey(time)) {
            tempi.put(time,tempo);
        }
        else {
            throw new Error("PASSAGE:\tTrying to add a tempo change on top of another tempo change!");
        }
    }

    /**
     * Adds a part to this passage, or throws an error
     * if it is already included.
     * @param part The part to add to this passage.
     */
    public void add(Part part) {
        if(!parts.contains(part)) {
            parts.add(part);
        }
        else {
            throw new Error("PASSAGE:\tTrying to add a redundant part to this passage!");
        }
    }

    /**
     * Gets the time signature at a given time in this passage.
     * @param time The time at which to sample.
     * @return The time signature at this time.
     */
    public TimeSignature getTimeSignatureAt(Count time) {
        return timeSigs.floorEntry(time.getMeasure()).getValue();
    }

    /**
     * Gets the tempo at a given time in this passage.
     * @param time The time at which to sample.
     * @return The tempo at this time.
     */
    public Tempo getTempoAt(Count time) {
        return tempi.floorEntry(time).getValue();
    }

    public Iterator<Integer> timeSignatureIterator() {
        return timeSigs.keySet().iterator();
    }

    public Iterator<Count> tempoChangeIterator() {
        return tempi.keySet().iterator();
    }

    /**
     * Returns a nicely-formatted string representing this passage.
     * @return A string representing this passage.
     */
    @Override
    public String toString() {
        String toReturn = "form.Passage :\n";
        for(Part part : this) {
            toReturn += "\tform.Part : \n" + part.toString();
        }
        return toReturn;
    }

    /**
     * Returns an iterator over all parts in this passage.
     * @return An iterator over all parts in this passage.
     */
    @Override
    public Iterator<Part> iterator() {
        return parts.iterator();
    }
}