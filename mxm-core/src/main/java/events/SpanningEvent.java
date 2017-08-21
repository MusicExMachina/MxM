package events;

import base.time.Time;
import passage.Frame;

public abstract class SpanningEvent implements IMusicEvent {
    private final Frame startframe;
    private final Frame endframe;
    private final Time duration;

    // GETTERS
    SpanningEvent(Frame startframe, Frame endframe) {
        this.startframe = startframe;
        this.endframe = endframe;
        this.duration = endframe.getTime().minus(startframe.getTime());
    }

    // GETTERS
    @Override
    public final Time getTiming() { return startframe.getTime(); }
    public final Time getStart() {
        return startframe.getTime();
    }
    public final Time getEnd() {
        return endframe.getTime();
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
