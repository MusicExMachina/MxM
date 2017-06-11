package events;

import sound.Sound;
import time.Count;

/**
 * Created by celenp on 6/9/2017.
 */
public abstract class Note<SoundType extends Sound> implements MusicEvent {

    private SoundType sound;
    private Count start;
    private Count end;
    private Count duration;


    public SoundType getSound() {
        return sound;
    }

    public Count getStart() {
        return start;
    }

    public Count getEnd() {
        return end;
    }

    public Count getDuration() {
        return duration;
    }
}
