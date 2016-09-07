package model.time_ordered;

import model.Player;
import model.time_insensitive.Note;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * RE-IMPLEMENT AS A *NAVIGABLE* LINKED LIST?
 */




/**
 * A passage is a relatively straightforward
 * collection of frames which, perhaps most
 * importantly, allows time-based lookup. For
 * instance, you can find the last frame before
 * a given time.
 *
 * Notes:
 *  There can never be more than one frame at a given time point
 *  There can never be a frame past the end or before time 0
 */
public class Passage {

    TreeSet<Frame> frames;          // A time-ordered set of all the frames in this passage
    ArrayList<Player> players;      // A list of all instruments playing in this passage

    /**
     * We do not add Frames directly to a Passage, but
     * instead add Notes themselves, which automatically fill
     * up frames.
     */
    public void addNote(Note note, long time) {

    }

    /**
     * The only way to access the frames held in a
     * passage is through an iterator, both for
     * performance and encapsulation considerations
     * Note: This is, of course, not edit safe
     * @return an iterator to all the Frames
     */
    public Iterator<Frame> getFrameIterator() {
        return frames.iterator();
    }
}
