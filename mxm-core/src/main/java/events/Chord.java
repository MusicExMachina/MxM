package events;

import passage.Frame;
import base.Harmony;

public class Chord extends PlayableEvent<Harmony> {
    public Chord(Frame startIFrame, Frame endIFrame) {
        super(startIFrame, endIFrame);
    }
}
