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
public class Note<SoundType extends Sound> extends MusicEvent {

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

    public Note(Count start, Count duration, SoundType sound) {
        this.start = start;
        this.duration = duration;
        this.sound = sound;
    }

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

    @Override
    public int compareTo(MusicEvent o) {
        return 0;
    }

    @Override
    public int compare(MusicEvent o1, MusicEvent o2) {
        return 0;
    }
}
