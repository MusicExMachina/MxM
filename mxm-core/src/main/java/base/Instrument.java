package base;

import base.sounds.ISound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * A class for storing information about a specific instrument. Note that this is specific enough to be "Horn in F"
 * instead of just "Horn" given that different transpositions of the "same" instrument have different tendencies.
 */
public class Instrument {

    private static final ArrayList<Instrument> GeneralMIDIInstrumentSet = new ArrayList<>();

    public static final Instrument GRAND_PIANO     = new Instrument("Acoustic Grand Piano");
    public static final Instrument DRUM_SET        = new Instrument("Drum Set");
    public static final Instrument DEFAULT = GRAND_PIANO;

    private String name;
    private Set<ISound> possibleSounds;
    private Set<Technique> possibleTechniques;

    public Instrument(String name) {
        this.name = name;
    }

    /**
     * Returns an iterator over all possible sounds that this instrument can produce.
     * @return An iterator over all possible sounds that this instrument can produce
     */
    public Iterator<ISound> possibleSoundItr() {
        return possibleSounds.iterator();
    }

    public Iterator<Technique> possibleTechniqueItr() {
        return possibleTechniques.iterator();
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument instrument = (Instrument) o;
        return name != null ? name.equals(instrument.name) : instrument.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
