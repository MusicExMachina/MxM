package events;

import passage.Frame;
import base.Pitch;

public class Portamento extends PlayableEvent<Pitch> {
    public Portamento(Frame startIFrame, Frame endIFrame) {
        super(startIFrame, endIFrame);
    }
}
