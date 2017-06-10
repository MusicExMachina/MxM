package events.eventTypes;

import base.time.Count;
import events.playable.PlayableEvent;

/**
 * Created by celenp on 6/10/2017.
 */
public class UnpitchedNoteEvent extends PlayableEvent {
    @Override
    public Count getStart() {
        return null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
