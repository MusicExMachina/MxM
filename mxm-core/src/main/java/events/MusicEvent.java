package events;

import base.time.Count;

/**
 * A
 */
public interface MusicEvent {
    public Count getStart();// { return start; }

    @Override
    public int hashCode();// {  return start.hashCode(); }
}
