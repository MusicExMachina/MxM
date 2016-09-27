package learning.interfaces;

import model.structure.Frame;

import java.util.List;

/**
 * A RhythmNetwork is one which generates rhythms (duh).
 */
public interface RhythmNetwork {
    /**
     * Generates a rhythm of a given length given a seed.
     * @param seed
     * @param numMeasures
     * @return
     */
    public List<Frame> generateRhythm(int seed, int numMeasures);
}
