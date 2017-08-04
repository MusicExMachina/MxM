package events;

import time.Count;
import time.Frame;

/**
 * A
 *
 * Note that music events cannot be compared, as how could one compare a note to a tempo change? Or two notes ocurring
 * at the same time? It becomes too hazy to define by a simple comparison operator.
 */
public abstract class MusicEvent {
    // Music event timing information
    private Frame startFrame;

    public MusicEvent(Frame startFrame) {
        this.startFrame = startFrame;
    }

    // COUNT TIMING
    public Count getStart() {
        return startFrame.getTiming();
    }

    // FRAME TIMING
    public Frame getStartFrame() {
        return startFrame;
    }
}
