package events;

import time.Count;
import time.Time;

import java.util.Comparator;

/**
 * A
 */
public abstract class MusicEvent implements Comparator<MusicEvent>, Comparable<MusicEvent> {
    Count start;

    public Time getStart() { return start; }

}
