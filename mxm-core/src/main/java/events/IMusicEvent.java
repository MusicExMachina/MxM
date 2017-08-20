package events;

import form.Frame;
import time.Time;

public interface IMusicEvent {
    // GETTERS
    public Time getTiming();
    public Frame getFrame();
}

abstract class InstantEvent implements IMusicEvent {
    private final Frame frame;

    InstantEvent(Frame frame) {
        this.frame = frame;
    }

    // GETTERS
    @Override
    public final Time getTiming() { return frame.getTiming(); }
    @Override
    public final Frame getFrame() { return frame; }
}

abstract class SpanningEvent implements IMusicEvent {
    private final Frame startframe;
    private final Frame endframe;
    private final Time duration;

    // GETTERS
    SpanningEvent(Frame startframe, Frame endframe) {
        this.startframe = startframe;
        this.endframe = endframe;
        this.duration = endframe.getTiming().minus(startframe.getTiming());
    }

    // GETTERS
    @Override
    public final Time getTiming() { return startframe.getTiming(); }
    public final Time getStart() {
        return startframe.getTiming();
    }
    public final Time getEnd() {
        return endframe.getTiming();
    }
    public final Time getDuration() {
        return duration;
    }
    @Override
    public final Frame getFrame() { return startframe; }
    public final Frame getStartFrame() {
        return startframe;
    }
    public final Frame getEndFrame() {
        return startframe;
    }
}