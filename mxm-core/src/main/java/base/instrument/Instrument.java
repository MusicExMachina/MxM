package base.instrument;

import base.sound.Technique;
import base.sound.Sound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * A class for storing information about a specific instrument. Note that this is specific enough to be "Horn in F"
 * instead of just "Horn" given that different transpositions of the "same" instrument have different tendencies.
 */
public class Instrument<SoundType extends Sound> {

    private static final ArrayList<Instrument> GeneralMIDIInstrumentSet = new ArrayList<>();

    public static final Instrument ACOUSTIC_GRAND_PIANO     = new Instrument("Acoustic Grand Piano");
    public static final Instrument DEFAULT = ACOUSTIC_GRAND_PIANO;

    private String name;
    private Set<SoundType> possibleActions;
    private Set<Technique> possibleTechniques;
    private Set<Set<Technique>> possibleTechniqueCombos; // It's not possible to mute with two different mutes

    public Instrument(String name) {
        this.name = name;
        GeneralMIDIInstrumentSet.add(this);
    }

    public static Instrument getGeneralMIDIInstrument(int number) {return GeneralMIDIInstrumentSet.get(number);}

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
