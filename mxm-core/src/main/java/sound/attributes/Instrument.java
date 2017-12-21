package sound.attributes;

import org.jetbrains.annotations.NotNull;
import sound.ISound;
import sound.Noise;
import sound.pitch.Pitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Instrument {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private static final ArrayList<Instrument> GeneralMIDIInstrumentSet = new ArrayList<>();

    public static final Instrument GRAND_PIANO     = new Instrument("Acoustic Grand Piano");
    public static final Instrument DRUM_SET        = new Instrument("Drum Set");
    public static final Instrument DEFAULT = GRAND_PIANO;

    private Set<ISound> possibleSounds;
    private Set<Technique> possibleTechniques;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final String name;

    private Instrument(String name) {
        this.name = name;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public Iterator<ISound> possibleSoundItr() {
        return possibleSounds.iterator();
    }
    public Iterator<Noise> possibleNoiseItr() {
        return null;
    }
    public Iterator<Pitch> possiblePitchItr() {
        return null;
    }
    public Iterator<Technique> possibleTechniqueItr() {
        return possibleTechniques.iterator();
    }

    public @NotNull String getName() {
        return name;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument instrument = (Instrument) o;
        return name != null ? name.equals(instrument.name) : instrument.name == null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}
