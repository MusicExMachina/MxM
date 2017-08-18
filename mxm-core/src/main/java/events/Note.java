package events;

import form.IFrame;
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
public class Note <SoundType extends ISound> extends SpanningEvent<Count> implements IMusicEvent<Count> {
    // Note attributes
    private SoundType sound;
    private Technique technique;
    private Dynamic dynamic;
    private Accent accent;

    public Note(IFrame startIFrame, IFrame endIFrame, SoundType sound) {
        super(startIFrame, endIFrame);
        this.sound = sound;
    }

    // PROPERTIES
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
}
