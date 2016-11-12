package model.structure;

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

    private String name;
    // Store possible pitches
    private Set<Pitch> possiblePitches;
    // Store possible techniques
    private Set<Technique> possibleTechniques;

    // All articulations are possible on all instruments (as things stand)


    @Override
    public String toString() {
        return name;
    }
}
