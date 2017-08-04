package events;

import time.*;

/**
 * Created by celenp on 5/13/2017.
 */
public class TempoChange extends MusicEvent<Count> {
    private Tempo tempo;

    public TempoChange(Frame<Count> startFrame, Tempo tempo) {
        super(startFrame);
        this.tempo = tempo;
    }

    public Tempo getTempo() {
        return tempo;
    }
}
