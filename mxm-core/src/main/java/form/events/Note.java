package form.events;

import time.Time;
import form.passage.Part;
import org.jetbrains.annotations.NotNull;
import sound.attributes.Accent;
import sound.attributes.Dynamic;
import sound.attributes.Technique;


public final class Note<SoundType> extends SpanningEvent implements IEvent {

    // NOTE PROPERTIES
    private final Part part;
    private final SoundType sound;
    private Technique technique;
    private Dynamic dynamic;
    private Accent accent;

    // CONSTRUCTOR
    public Note(@NotNull Part part, @NotNull SoundType sound, @NotNull Time startTime, @NotNull Time endTime) {
        super(startTime, endTime);
        this.part = part;
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


    public @NotNull Part getPart() { return part; }
}
