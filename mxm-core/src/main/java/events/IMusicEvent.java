package events;

import form.IFrame;
import time.ITime;

public interface IMusicEvent <ITimeType extends ITime> {

    // COUNT TIMING
    public ITimeType getTiming();

    // FRAME TIMING
    public IFrame getIFrame();
}

abstract class InstantEvent<ITimeType extends ITime> implements IMusicEvent<ITimeType> {
    private final IFrame<ITimeType> IFrame;

    InstantEvent(IFrame IFrame) {
        this.IFrame = IFrame;
    }

    @Override
    public final ITimeType getTiming() { return IFrame.getTiming(); }

    @Override
    public final IFrame getIFrame() { return IFrame; }
}

abstract class SpanningEvent<ITimeType extends ITime> implements IMusicEvent<ITimeType> {
    private final IFrame<ITimeType> startIFrame;
    private final IFrame<ITimeType> endIFrame;
    private final ITime duration;

    SpanningEvent(IFrame<ITimeType> startIFrame, IFrame<ITimeType> endIFrame) {
        this.startIFrame = startIFrame;
        this.endIFrame = endIFrame;
        this.duration = endIFrame.getTiming().minus(startIFrame.getTiming());
    }

    // COUNT TIMING
    @Override
    public final ITimeType getTiming() { return startIFrame.getTiming(); }
    public final ITimeType getStart() {
        return startIFrame.getTiming();
    }
    public final ITimeType getEnd() {
        return endIFrame.getTiming();
    }
    public final ITime getDuration() {
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