package events;

import sound.ISound;
import sound.Pitch;
import time.Frame;

public class Portamento extends Note {
    private Pitch endPitch;

    public Portamento(Frame startFrame, Frame endFrame, ISound sound) {
        super(startFrame, endFrame, sound);
    }

    public Pitch getEndPitch() {
        return endPitch;
    }
}
