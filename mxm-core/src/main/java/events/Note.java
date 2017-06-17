package events;

import form.Part;
import sound.Dynamic;
import sound.Lyric;
import sound.Sound;
import sound.Technique;
import time.Count;

/**
 *
 */
public abstract class Note<SoundType extends Sound> extends MusicEvent {

    // Note attributes
    private Part playedBy;
    private SoundType sound;
    private Lyric lyric;
    private Dynamic dynamic;
    private Technique technique;

    // Note timing information
    private Count start;
    private Count end;
    private Count duration;

    public Part getPlayedBy() { return playedBy; }

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
