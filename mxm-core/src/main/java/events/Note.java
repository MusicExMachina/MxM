package events;

import sound.Accent;
import sound.Dynamic;
import sound.ISound;
import sound.Technique;
import time.Count;
import time.Frame;

/**
 * Notes represent a sound over some duration of time. All notes have a set number of attributes, which
 *
 * Note that there can be no inherent comparisons between notes- after all, what would we compare about them? Their
 * start times? Their pitches? What would we do with unpitched notes?
 */
public class Note <SoundType extends ISound> extends MusicEvent<Count> {

    // Music event timing information
    private Frame<Count> endFrame;
    private Count duration;

    // Note attributes
    private SoundType sound;
    private Technique technique;
    private Dynamic dynamic;
    private Accent accent;

    public Note(Frame<Count> startFrame, Frame<Count> endFrame, SoundType sound) {
        super(startFrame);
        this.endFrame = endFrame;
        this.duration = getStart().minus(getEnd());
        this.sound = sound;
    }


    // COUNT TIMING
    public Count getEnd() {
        return endFrame.getTiming();
    }

    public Count getDuration() {
        return duration;
    }

    // FRAME TIMING
    public Frame<Count> getEndFrame() {
        return endFrame;
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
