package events;

import base.Count;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by celenp on 6/9/2017.
 */
public class MusicTimeline<MusicEventType extends MusicEvent> implements Iterable<MusicEventType> {
    TreeMap<Count, MusicEvent> events;

    @Override
    public Iterator<MusicEventType> iterator() {
        return events.values().iterator();
    }
}
