package events;

import sound.ISound;
import time.Time;
import form.AbstractPart;
import org.jetbrains.annotations.NotNull;
import note.Accent;
import note.Dynamic;
import note.Technique;


public class Note<SoundType extends ISound> extends AbstractSpanningEvent implements IPartEvent {

    // NOTE PROPERTIES
    private final AbstractPart part;
    private final SoundType sound;
    private Technique technique;
    private Dynamic dynamic;
    private Accent accent;

    // CONSTRUCTOR
    public Note(@NotNull AbstractPart part, @NotNull SoundType sound, @NotNull Time startTime, @NotNull Time endTime) {
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
    @Override
    public @NotNull AbstractPart getPart() { return part; }
}
