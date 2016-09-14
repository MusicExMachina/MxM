package model.time_ordered;
import model.time_insensitive.MusicEvent;

import java.util.TreeSet;

/**
 * A frame is a vertical stack of MusicEvents.
 * This may include identical pitches (and, of
 * course, rests in different instruments).
 */
public class Frame {

    /** Ensures no two notes will have the same UID */
    static int lastUID = 0;

    TreeSet<MusicEvent> notes;
}
