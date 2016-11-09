package model.form;

import model.time.TimeSignature;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by celenp on 10/7/2016.
 */
public class Measure {
    private TimeSignature timeSignature;
    private RhythmTree rhythmTree;
    private ArrayList<Frame> frames;

    /**
     * Returns an iterator over all the Frames in this Measure.
     * @return An iterator over all the Frames in this Measure.
     */
    public Iterator<Frame> iterator() {
        return frames.iterator();
    }
}
