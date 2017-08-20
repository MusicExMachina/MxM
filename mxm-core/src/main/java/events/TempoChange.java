package events;

import form.Frame;
import events.properties.Tempo;

public class TempoChange extends InstantEvent implements ITimingEvent {

    // TEMPO CHANGE PROPERTIES
    private Tempo tempo;

    // CONSTRUCTOR
    public TempoChange(Frame Frame, Tempo tempo) {
        super(Frame);
        this.tempo = tempo;
    }

    // GETTERS
    public Tempo getTempo() {
        return tempo;
    }
}
