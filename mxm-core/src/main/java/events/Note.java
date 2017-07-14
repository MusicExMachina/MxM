package events;

import sound.Accent;
import sound.Dynamic;
import sound.ISound;
import sound.Technique;
import time.Count;

/**
 * Notes represent a sound over some duration of time. All notes have a set number of attributes, which
 *
 * Note that there can be no inherent comparisons between notes- after all, what would we compare about them? Their
 * start times? Their pitches? What would we do with unpitched notes?
 */
public class Note<SoundType extends ISound> {

    Count start;

    // Music event timing information
    private Count start;
    private Count end;
    private Count duration;



    // Note attributes
    private SoundType sound;
    private Technique technique;
    private Dynamic dynamic;

    private Accent accent;

    public Note(Count start, Count duration, SoundType sound) {
        this.start = start;
        this.duration = duration;
        this.sound = sound;
    }

    public SoundType getSound() {
        return sound;
    }

    public Technique getTechnique() {
        return technique;
    }

    public Dynamic getDynamic() {
        return dynamic;
    }

    public Accent getAccent() {
        return accent;
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
