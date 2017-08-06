package events;

import time.Count;

/**
 * A
 *
 * Note that music events cannot be compared, as how could one compare a note to a tempo change? Or two notes ocurring
 * at the same time? It becomes too hazy to define by a simple comparison operator.
 */
public interface IMusicEvent {

    // COUNT TIMING
    public Count getTiming();

    // FRAME TIMING
    public Frame getFrame();
}

abstract class InstantEvent implements IMusicEvent {
    private final Frame frame;

    InstantEvent(Frame frame) {
        this.frame = frame;
    }

    // COUNT TIMING
    @Override
    public final Count getTiming() { return frame.getTiming(); }

    // FRAME TIMING
    @Override
    public final Frame getFrame() { return frame; }
}

abstract class SpanningEvent implements IMusicEvent {
    private final Frame startFrame;
    private final Frame endFrame;
    private final Count duration;

    SpanningEvent(Frame startFrame, Frame endFrame) {
        this.startFrame = startFrame;
        this.endFrame = endFrame;
        this.duration = endFrame.getTiming().minus(startFrame.getTiming());
    }

    // COUNT TIMING
    @Override
    public final Count getTiming() { return startFrame.getTiming(); }
    public final Count getStart() {
        return startFrame.getTiming();
    }
    public final Count getEnd() {
        return endFrame.getTiming();
    }
    public final Count getDuration() {
        return duration;
    }

    // FRAME TIMING
    @Override
    public final Frame getFrame() { return startFrame; }
    public final Frame getStartFrame() {
        return startFrame;
    }
    public final Frame getEndFrame() {
        return endFrame;
    }
}