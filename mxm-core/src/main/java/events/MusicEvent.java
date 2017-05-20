package events;

import base.Count;

/**
 * Created by celenp on 5/13/2017.
 */
public abstract class MusicEvent {

    /** The start RhythmNode of this events.eventTypes.Note. */
    private Count start;

    /** The end RhythmNode of this events.eventTypes.Note. */
    private Count end;

    /** The end RhythmNode of this events.eventTypes.Note. */
    private Count length;

    public Count getStart() { return start; }

    public Count getEnd() { return end; }

    public Count getDuration() { return length; }

}
