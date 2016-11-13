package model.trainable;

import model.basic.Pitch;
import model.basic.Technique;

import java.util.Set;

/**
 * A class for storing information about
 * a specific instrument. Note that this
 * is specific enough to be "Horn in F"
 * instead of just "Horn" given that different
 * transpositions of the "same" instrument
 * have different tendencies.
 */
public class Instrument {

    public static final Instrument DEFAULT = new Instrument("Default Instrument");

    private String name;
    private Set<Pitch> possiblePitches;
    private Set<Technique> possibleTechniques;

    public Instrument(String name) {
        this.name = name;
    }

    public static Instrument getGeneralMIDIInstrument(int number) {
        return DEFAULT;
    }

    @Override
    public String toString() {
        return name;
    }
}
