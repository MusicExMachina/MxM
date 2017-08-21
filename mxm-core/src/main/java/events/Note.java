package events;

import passage.Frame;
import base.Pitch;

public class Note extends PlayableEvent<Pitch> {
    public Note(Frame startIFrame, Frame endIFrame) {
        super(startIFrame, endIFrame);
    }
}
