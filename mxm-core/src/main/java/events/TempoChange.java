package events;

import time.*;

/**
 * Created by celenp on 5/13/2017.
 */
public class TempoChange extends InstantEvent {
    private Tempo tempo;

    public TempoChange(IFrame IFrame, Tempo tempo) {
        super(IFrame);
        this.tempo = tempo;
    }

    public Tempo getTempo() {
        return tempo;
    }
}
