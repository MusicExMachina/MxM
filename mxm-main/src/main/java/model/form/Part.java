package model.form;

import model.structure.Instrument;
import model.time.Count;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * A single part for a single player. It's the equivalent of
 * a passage for only one musician/percussionist. (Just kidding!)
 */
public class Part {

    /** The type of Instrument playing this part. */
    private Instrument instrument;

    /** The number of a given Instrument this is. */
    private int partNum;

    /** The number of a given Instrument this is. */
    private int sectionSize;

    /** All Measures in this Passage. */
    private ArrayList<Measure> measures;

    /** A TreeMap of all the frames in this Passage.
     * Note that this data form was chosen for its
     * lookup abilities, which are quite useful in answering
     * questions such as "What is playing at time x?" etc. etc. */
    private TreeMap<Count,Frame> frames;


    /**
     *
     * @param instrument
     * @param partNum
     * @param sectionSize
     */
    public Part(Instrument instrument, int partNum, int sectionSize) {
        this.instrument = instrument;
        this.partNum = partNum;
        this.sectionSize = sectionSize;
    }

    /**
     * Returns a nicely-formatted string of this Part.
     * @return A String representing this Part.
     */
    @Override
    public String toString() {
        return instrument.toString() + " (" + partNum + "/" + sectionSize + ")";
    }

    /**
     * Returns the starting time of this Passage.
     * @return The Count this Passage starts on.
     */
    public Count getStart() {
        return frames.firstEntry().getKey();
    }

    /**
     * Returns the ending time of this Passage.
     * @return The Count this Passage ends on.
     */
    public Count getEnd() {
        return frames.lastEntry().getKey().plus(frames.lastEntry().getKey());
    }

    /**
     * Returns the total length of this Passage.
     * @return The Count length of this Passage.
     */
    public Count getLength() {
        return getEnd().minus(getStart());
    }

    /**
     * Returns the currently-playing Frame at a given Count.
     * @param time The time we're looking at.
     * @return The Frame playing at that time.
     */
    public Frame getAt(Count time) {
        return frames.lowerEntry(time).getValue();
    }

    /**
     * Creates a new Passage which is a subset of this one
     * @param start The Count on which to start the excerpt.
     * @param end The Count on which to end the excerpt.
     * @return The new Passage with these bounds.
     */
    /*
    public Passage excerpt(Count start, Count end) {
        return new Passage((TreeMap<Count, Frame>) frames.subMap(start,end));
    }
    */

    /**
     * Allows iteration over all the Frames in this Passage,
     * ordered forward chronologically.
     * @return An iterator over all the Frames.
     */
    public Iterator<Frame> iterator() {
        return frames.values().iterator();
    }
}
