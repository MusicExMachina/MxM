package events;

import base.Count;

/**
 * A
 */
public abstract class MusicEvent {

    /** The start RhythmNode of this events.eventTypes.NoteEvent. */
    private Count start;

    public Count getStart() { return start; }

    @Override
    public int hashCode() {
        return start.hashCode();
    }
}
