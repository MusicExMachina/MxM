package events;

import sound.ISound;
import sound.Pitch;

public class Portamento extends Note {
    private Pitch endPitch;

    public Portamento(IFrame startIFrame, IFrame endIFrame, ISound sound) {
        super(startIFrame, endIFrame, sound);
    }

    public Pitch getEndPitch() {
        return endPitch;
    }
}
