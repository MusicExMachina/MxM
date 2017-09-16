package properties.note;

import org.jetbrains.annotations.NotNull;
import properties.AbstractStringProp;
import properties.sound.ISound;
import properties.sound.Noise;
import properties.sound.Pitch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * <p> <b>Class overview:</b>
 * A class for storing information about a specific instrument. Note that this is specific enough to be "Horn in F"
 * instead of just "Horn" given that different transpositions of the "same" instrument have different tendencies.</p>
 *
 * <p> <b>Design Details:</b>
 * ASDF</p>
 *
 * @author Patrick Celentano
 */
public class Instrument extends AbstractStringProp {

    private static final ArrayList<Instrument> GeneralMIDIInstrumentSet = new ArrayList<>();

    public static final Instrument GRAND_PIANO     = new Instrument("Acoustic Grand Piano");
    public static final Instrument DRUM_SET        = new Instrument("Drum Set");
    public static final Instrument DEFAULT = GRAND_PIANO;

    private Set<ISound> possibleSounds;
    private Set<Technique> possibleTechniques;

    public Instrument(String name) {
        super(name);
    }

    /**
     * Returns an iterator over all possible sounds that this instrument can produce.
     * @return An iterator over all possible sounds that this instrument can produce
     */
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
        return getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument instrument = (Instrument) o;
        return getValue() != null ? getValue().equals(instrument.getValue()) : instrument.getValue() == null;
    }
}
