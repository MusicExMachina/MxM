package events;

import time.Count;

public interface IMusicEvent {

    // COUNT TIMING
    public Count getTiming();

    // FRAME TIMING
    public IFrame getIFrame();
}

abstract class InstantEvent implements IMusicEvent {
    private final IFrame IFrame;

    InstantEvent(IFrame IFrame) {
        this.IFrame = IFrame;
    }

    // COUNT TIMING
    @Override
    public final Count getTiming() { return IFrame.getTiming(); }

    // FRAME TIMING
    @Override
    public final IFrame getIFrame() { return IFrame; }
}

abstract class SpanningEvent implements IMusicEvent {
    private final IFrame startIFrame;
    private final IFrame endIFrame;
    private final Count duration;

    SpanningEvent(IFrame startIFrame, IFrame endIFrame) {
        this.startIFrame = startIFrame;
        this.endIFrame = endIFrame;
        this.duration = endIFrame.getTiming().minus(startIFrame.getTiming());
    }

    // COUNT TIMING
    @Override
    public final Count getTiming() { return startIFrame.getTiming(); }
    public final Count getStart() {
        return startIFrame.getTiming();
    }
    public final Count getEnd() {
        return endIFrame.getTiming();
    }
    public final Count getDuration() {
        return duration;
    }

    // FRAME TIMING
    @Override
    public final IFrame getIFrame() { return startIFrame; }
    public final IFrame getStartIFrame() {
        return startIFrame;
    }
    public final IFrame getEndIFrame() {
        return endIFrame;
    }
}