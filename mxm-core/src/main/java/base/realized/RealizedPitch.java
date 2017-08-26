package base.realized;

import base.sounds.Pitch;

public class RealizedPitch extends Pitch implements IRealized {
    /**
     * The pitch constructor, which is private to enforce the interning design pattern (one instance per value).
     *
     * @param value The (midi) value of this pitch
     */
    private RealizedPitch(int value) {
        Pitch.get(value);
    }
}