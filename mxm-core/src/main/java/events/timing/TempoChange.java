package events.timing;

import base.time.Time;
import events.InstantEvent;
import base.time.Tempo;


public class TempoChange extends InstantEvent {
    // TEMPO CHANGE PROPERTIES
    private Tempo tempo;

    // CONSTRUCTORS
    public TempoChange(Time timing, Tempo tempo) {
        super(timing);
        this.tempo = tempo;
    }

    // GETTERS
    public Tempo getTempo() {
        return tempo;
    }
}
