package model.form;

import model.time.Count;

import java.util.*;

/**
 * After we're done creating a RhythmTree, we put all
 * of its frames into a Passage. Passages are essentially
 * great big Frame holders, and provide users with the
 * ability to look up what's happening at any given Count.
 */
public class Passage implements Iterable<Frame> {

    ArrayList<Measure> measures;
    /** A TreeMap of all the frames in this Passage.
     * Note that this data form was chosen for its
     * lookup abilities, which are quite useful in answering
     * questions such as "What is playing at time x?" etc. etc. */
    TreeMap<Count,Frame> frames;

    /**
     * The default constructor for a Passage.
     */
    public Passage() {
        frames = new TreeMap<Count, Frame>();
    }

    /**
     * A constructor which safely copies a frames TreeMap
     * into this Passage's frames. Note that this constructor
     * exists solely for the excerpt() method.
     * @param frames The starting Frames of this Passage.
     */
    private Passage(TreeMap<Count,Frame> frames) {
        this.frames = new TreeMap<Count, Frame>(frames);
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
    public Passage excerpt(Count start, Count end) {
        return new Passage((TreeMap<Count, Frame>) frames.subMap(start,end));
    }

    /**
     * Allows iteration over all the Frames in this Passage,
     * ordered forward chronologically.
     * @return An iterator over all the Frames.
     */
    public Iterator<Frame> iterator() {
        return frames.values().iterator();
    }
}