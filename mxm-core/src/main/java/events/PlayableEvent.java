package events;

import base.Count;

/**
 * Created by celenp on 6/9/2017.
 */
public abstract class PlayableEvent extends MusicEvent {

    public Count getEnd() { return end; }

    public Count getDuration() { return length; }
}
