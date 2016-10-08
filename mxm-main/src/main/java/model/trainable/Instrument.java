package model.trainable;

import model.basic.Pitch;

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
    // Store possible pitches
    Set<Pitch> possiblePitches;
}
