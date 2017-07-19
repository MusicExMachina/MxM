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
public class Note <SoundType extends ISound> implements IMusicEvent<Count> {

    // Music event timing information
    private Frame<Count> startFrame;
    private Frame<Count> endFrame;
    private Count duration;

    // Note attributes
    private SoundType sound;
    private Technique technique;
    private Dynamic dynamic;
    private Accent accent;

    public Note(Frame<Count> startFrame, Frame<Count> endFrame, SoundType sound) {
        this.startFrame = startFrame;
        this.endFrame = endFrame;
        this.duration = getStart().minus(getEnd());
        this.sound = sound;
    }

    // FRAME TIMING

    @Override
    public Frame<Count> getFrame() {
        return startFrame;
    }

    public Frame<Count> getStartFrame() {
        return startFrame;
    }

    public Frame<Count> getEndFrame() {
        return endFrame;
    }

    // COUNT TIMING

    @Override
    public Count getTiming() {
        return startFrame.getTiming();
    }

    public Count getStart() {
        return startFrame.getTiming();
    }

    public Count getEnd() {
        return endFrame.getTiming();
    }

    public Count getDuration() {
        return duration;
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
