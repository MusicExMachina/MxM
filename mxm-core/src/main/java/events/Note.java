package events;

import base.time.Time;
import com.sun.istack.internal.NotNull;
import base.eventProps.Accent;
import base.eventProps.Dynamic;
import base.eventProps.Technique;
import base.sounds.ISound;


public class Note<SoundType extends ISound> extends SpanningEvent {

    // NOTE PROPERTIES
    private final SoundType sound;
    private Technique technique;
    private Dynamic dynamic;
    private Accent accent;

    // CONSTRUCTOR
    protected Note(@NotNull SoundType sound, @NotNull Time startTime, @NotNull Time endTime) {
        super(startTime, endTime);
        this.sound = sound;
    }

    // EXTRA PROPERTY SETTERS
    public Note<SoundType> set(@NotNull Technique technique) { this.technique = technique; return this; }
    public Note<SoundType> set(@NotNull Dynamic dynamic) { this.dynamic = dynamic; return this; }
    public Note<SoundType> set(@NotNull Accent accent) { this.accent = accent; return this; }

    // GETTERS
    public @NotNull SoundType getSound() { return sound; }
    public @NotNull Technique getTechnique() { return technique; }
    public @NotNull Dynamic getDynamic() { return dynamic; }
    public @NotNull Accent getAccent() { return accent; }

}
