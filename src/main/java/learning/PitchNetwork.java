package learning;

import model.structural.Frame;
import model.structural.Passage;

import java.util.List;

/**
 *
 */
public interface PitchNetwork {
    /**
     * Generates a rhythm of a given length given a seed.
     * @param seed
     * @param passage
     * @return
     */
    public List<Frame> colorRhythm(int seed, Passage passage);
}

