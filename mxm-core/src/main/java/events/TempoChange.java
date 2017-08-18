package events;

import time.Count;
import time.Tempo;

/**
 * Created by celenp on 5/13/2017.
 */
public class TempoChange extends InstantEvent<Count> {
    private Tempo tempo;

    public TempoChange(form.IFrame IFrame, Tempo tempo) {
        super(IFrame);
        this.tempo = tempo;
    }

    public Tempo getTempo() {
        return tempo;
    }
}
