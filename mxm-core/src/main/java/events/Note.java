package events;

import form.Frame;
import sound.Pitch;

public class Note extends PlayableEvent<Pitch> {
    public Note(Frame startIFrame, Frame endIFrame) {
        super(startIFrame, endIFrame);
    }
}
