package events;

import passage.Frame;
import events.properties.Tempo;


public class TempoChange extends InstantEvent implements ITimingEvent {
    // TEMPO CHANGE PROPERTIES
    private Tempo tempo;

    // CONSTRUCTORS
    public TempoChange(Frame Frame, Tempo tempo) {
        super(Frame);
        this.tempo = tempo;
    }

    // GETTERS
    public Tempo getTempo() {
        return tempo;
    }
}
