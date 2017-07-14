package events;

import time.Count;

import java.util.Comparator;

/**
 * A
 */
public abstract class MusicEvent implements Comparator<MusicEvent>, Comparable<MusicEvent> {
    Count start;

    public Count getStart() { return start; }

}
