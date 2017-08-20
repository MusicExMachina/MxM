package events;

import form.Frame;
import musicTheory.Harmony;

public class Chord extends PlayableEvent<Harmony> {
    public Chord(Frame startIFrame, Frame endIFrame) {
        super(startIFrame, endIFrame);
    }
}
