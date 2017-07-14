package instruments;

import sound.Sound;
import sound.Technique;

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
    private Set<Sound> possibleSounds;
    private Set<Technique> possibleTechniques;
    private Set<Set<Technique>> possibleTechniqueCombos; // It's not possible to mute with two different mutes

    public Instrument(String name) {
        this.name = name;
        GeneralMIDIInstrumentSet.add(this);
    }

    public static Instrument getGeneralMIDIInstrument(int number) {return GeneralMIDIInstrumentSet.get(number);}


    /**
     * Returns an iterator over all possible sounds that this instrument can produce.
     * @returnan An iterator over all possible sounds that this instrument can produce
     */
    public Iterator<Sound> possibleSoundItr() {
        return possibleSounds.iterator();
    }

    public Iterator<Technique> possibleTechniqueItr() {
        return possibleTechniques.iterator();
    }

    public Iterator<Set<Technique>> possibleTechniqueComboItr() {
        return possibleTechniqueCombos.iterator();
    }

    @Override
    public String toString() {
        return name;
    }
}
