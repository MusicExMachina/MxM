package events;

import time.Count;
import time.Frame;
import time.Tempo;

/**
 * Created by celenp on 5/13/2017.
 */
public class TempoChange implements IMusicEvent<Count> {

    /** The tempo we're changing to at this particular time*/
    public Tempo tempo;

    private Frame<Count> frame;

    public TempoChange(Frame<Count> frame, Tempo tempo) {
        this.frame = frame;
        this.tempo = tempo;
    }

    public int compareTo(IMusicEvent o) {
        return 0;
    }

    @Override
    public Frame<Count> getFrame() {
        return frame;
    }

    @Override
    public Count getTiming() {
        return frame.getTiming();
    }
}
