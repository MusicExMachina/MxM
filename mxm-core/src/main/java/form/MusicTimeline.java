package form;

import events.MusicEvent;

import java.util.Iterator;

/**
 * Created by celenp on 6/9/2017.
 */
public interface MusicTimeline<MusicEventType extends MusicEvent> extends Iterable<MusicEventType> {
    @Override
    public Iterator<MusicEventType> iterator();
}
