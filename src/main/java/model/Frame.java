package model;
import java.util.List;
import java.util.TreeSet;

/**
 * A frame is a conceptualization of
 */
public class Frame implements Comparable<Frame> {

    long time;
    TreeSet<Note> notes;

    /**
     * The constructor for a Frame. Note that Frames
     * must be constructed with a time.
     * @param time The timing of this frame (in ms)
     */
    public Frame(long time) {
        this.time = time;
    }

    /**
     *
     * @return
     */
    public long getTime() {
        return time;
    }

    /**
     * Returns a collection of all notes playing
     * this frame, ordered by pitch- lowest to
     * highest.
     * @return All notes playing this frame
     */
    /*
    public List<Note> getNotes() {
        //return new ArrayList<Note>(notes.toArray());
    }
    */
    /**
     * Frame comparison is useful as a means of
     * @param o
     * @return
     */
    @Override
    public int compareTo(Frame o) {
        return Long.compare(getTime(),o.getTime());
    }
}
