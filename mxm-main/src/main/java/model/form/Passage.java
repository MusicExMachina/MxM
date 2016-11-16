package model.form;

import model.basic.Tempo;
import model.basic.Count;
import model.basic.TimeSignature;
import model.trainable.Instrument;

import java.util.*;

/**
 * After we're done creating a RhythmTree, we put all of its frames into a Passage. Passages are essentially
 * great big Frame holders, and provide users with the ability to look up what's happening at any given Count.
 */
public class Passage implements Iterable<Line> {

    /** TimeSignatures throughout this passage. */
    private NavigableMap<Integer,TimeSignature> timeSignatures;
    /** All tempi in this Passage. */
    private NavigableMap<Count,Tempo> tempi;
    /** All lines being played in this Passage. */
    private List<Line> lines;

    /**
     * The Passage constructor.
     */
    public Passage() {
        this.timeSignatures = new TreeMap<>();
        this.tempi = new TreeMap<>();
        this.lines = new ArrayList<>();
    }

    /**
     * Adds a TimeSignature (change) to the Passage at a given time (that must be on beat 1 of a measure).
     * @param timeSignature The TimeSignature to add to this Passage.
     * @param measure The time at which to add this TimeSignature.
     */
    public void addTimeSignature(TimeSignature timeSignature,Integer measure) {
        timeSignatures.put(measure,timeSignature);
    }

    /**
     * Adds a Tempo (change) to this Passage at a given time.
     * @param tempo The Tempo to add to this Passage.
     * @param time The time at which to add this Tempo.
     */
    public void addTempoChange(Tempo tempo, Count time) {
        tempi.put(time,tempo);
    }


    public void add(Line line) {
        lines.add(line);
    }

    /**
     * Gets the TimeSignature at a given time in this Passage.
     * @param time The time at which to sample.
     * @return The TimeSignature at this time.
     */
    public TimeSignature getTimeSignatureAt(Count time) {
        return timeSignatures.floorEntry(time.getMeasure()).getValue();
    }

    /**
     * Gets the Tempo at a given time in this Passage.
     * @param time The time at which to sample.
     * @return The Tempo at this time.
     */
    public Tempo getTempoAt(Count time) {
        return tempi.floorEntry(time).getValue();
    }


    @Override
    public String toString() {
        String toReturn = "";
        for(Line line : lines) {
            toReturn += "Line : " + line.toString() + "\n";
        }
        return toReturn;
    }

    /**
     * Returns an Iterator over all Lines in this Passage.
     * @return An Iterator over all Lines in this Passage.
     */
    @Override
    public Iterator<Line> iterator() {
        return lines.iterator();
    }
}