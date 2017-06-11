package events;

import time.Time;

import java.util.Comparator;

/**
 * A
 */
public interface MusicEvent extends Comparable<MusicEvent>, Comparator<MusicEvent> {
    public Time getStart();// { return start; }

    @Override
    public int hashCode();// {  return start.hashCode(); }
}
