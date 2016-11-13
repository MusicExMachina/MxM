package model.trainable;

import model.pitch.Pitch;
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

    public static final Instrument DEFAULT = new Instrument();

    private String name;
    private Set<Pitch> possiblePitches;
    private Set<Technique> possibleTechniques;
    private int numVoices;

    public Instrument() {
        numVoices = 1;
    }

    public int getNumVoices() {
        return numVoices;
    }

    @Override
    public String toString() {
        return name;
    }
}
