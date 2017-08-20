package events;

import form.Frame;
import form.SpanningEvent;
import sound.Pitch;
import events.properties.*;

public class Portamento extends SpanningEvent {

    // NOTE PROPERTIES
    private Pitch startPitch;
    private Pitch endPitch;
    private Technique technique;
    private Dynamic dynamic;
    private Accent accent;

    // CONSTRUCTOR
    public Portamento(Frame startFrame, Frame endFrame) {
        super(startFrame, endFrame);
    }

    // SETTERS
    public Portamento setStart(Pitch pitch) { this.startPitch = pitch; return this; }
    public Portamento setEnd(Pitch pitch) { this.endPitch = pitch; return this; }
    public Portamento set(Technique technique) { this.technique = technique; return this; }
    public Portamento set(Dynamic dynamic) { this.dynamic = dynamic; return this; }
    public Portamento set(Accent accent) { this.accent = accent; return this; }

    // GETTERS
    public Pitch getStartPitch() {
        return startPitch;
    }
    public Pitch getEndPitch() {
        return endPitch;
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
