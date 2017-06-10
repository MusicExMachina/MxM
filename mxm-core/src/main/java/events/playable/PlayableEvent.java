package events.playable;

import base.time.Count;
import events.MusicEvent;

/**
 * Created by celenp on 6/9/2017.
 */
public interface PlayableEvent extends MusicEvent {

    public Count getEnd() { return end; }

    public Count getDuration() { return length; }
}
