package events;

import passage.Frame;
import events.properties.Accent;
import events.properties.Dynamic;
import events.properties.Technique;
import sound.ISound;

public abstract class PlayableEvent<SoundType extends ISound> extends SpanningEvent implements IPartEvent {

    // NOTE PROPERTIES
    private SoundType sound;
    private Technique technique;
    private Dynamic dynamic;
    private Accent accent;

    // CONSTRUCTOR
    public PlayableEvent(Frame startIFrame, Frame endIFrame) {
        super(startIFrame, endIFrame);
    }

    // SETTERS
    public PlayableEvent<SoundType> set(SoundType sound) { this.sound = sound; return this; }
    public PlayableEvent<SoundType> set(Technique technique) { this.technique = technique; return this; }
    public PlayableEvent<SoundType> set(Dynamic dynamic) { this.dynamic = dynamic; return this; }
    public PlayableEvent<SoundType> set(Accent accent) { this.accent = accent; return this; }

    // GETTERS
    public SoundType getSound() { return sound; }
    public Technique getTechnique() { return technique; }
    public Dynamic getDynamic() { return dynamic; }
    public Accent getAccent() { return accent; }

}
