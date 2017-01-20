package form;

import basic.Tempo;
import basic.Count;
import basic.TimeSignature;

import java.util.*;

/**
 * Passages are simply contemporaneous (or not) collections of
 * lines. They are this project's main way of transporting "music"
 * and the internal structures of the music. Note that a passage
 * is unified by its temporal nature- time signatures, tempos, and
 * the like.
 */
public class Passage implements Iterable<Part> {

    /** Time signature (changes) throughout this passage. */
    private NavigableMap<Integer,TimeSignature> timeSigs;

    /** Tempo (changes) throughout this passage. */
    private NavigableMap<Count,Tempo> tempi;

    /** All lines being played in this passage. */
    private List<Part> lines;

    /**
     * The passage constructor, which takes no input and
     * simply initializes the internal containers.
     */
    public Passage() {
        this.timeSigs = new TreeMap<>();
        this.tempi = new TreeMap<>();
        this.lines = new ArrayList<>();
    }

    /**
     * Adds a time signature (change) to the passage at a
     * given measure, or throws an error, if that measure
     * already has a time signature change.
     * @param timeSignature The TimeSignature to add to this Passage.
     * @param measure The time at which to add this TimeSignature.
     */
    public void addTimeSignature(TimeSignature timeSignature,Integer measure) {
        if(!timeSigs.containsKey(measure)) {
            timeSigs.put(measure,timeSignature);
        }
        else {
            throw new Error("PASSAGE:\tTrying to add a time signature change on top of another one!");
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
     * Adds a line to this passage, or throws an error
     * if it is already included.
     * @param line The line to add to this passage.
     */
    public void add(Part line) {
        if(!lines.contains(line)) {
            lines.add(line);
        }
        else {
            throw new Error("PASSAGE:\tTrying to add a redundant line to this passage!");
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

    /**
     * Returns a nicely-formatted string representing this passage.
     * @return A string representing this passage.
     */
    @Override
    public String toString() {
        String toReturn = "";
        for(Part line : this) {
            toReturn += "Part : " + line.toString() + "\n";
        }
        return toReturn;
    }

    /**
     * Returns an iterator over all lines in this passage.
     * @return An iterator over all lines in this passage.
     */
    @Override
    public Iterator<Part> iterator() {
        return lines.iterator();
    }
}