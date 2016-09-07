package model.time_ordered;
import model.time_insensitive.MusicEvent;

import java.util.TreeSet;

/**
 * A frame is a conceptualization of
 */
public class Frame {

    /** Ensures no two notes will have the same UID */
    static int lastUID = 0;

    TreeSet<MusicEvent> notes;
}
