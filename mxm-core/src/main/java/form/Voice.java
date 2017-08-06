package form;

import sound.Instrument;

/**
 * Voices represent the more abstract use of the word "part"- first violin, third horn, snare, etc. Voices are part of
 * instrumentation, and map 1:1 with parts, as each voice will have one part. Note that multiple instruments may const-
 * itute a voice, such as in a full section of violas.
 *
 * This class is immutable, and unique to each passage- first horn on Mozart 40 is not first horn on Mozart 41.
 */
public class Voice {

    /** Every voice belongs to a passage */
    private final IPassage passage;

    /** Every voice has an instrument */
    private final Instrument instrument;

    /** The part number this voice is in its section- i.e. "#1" or "#4" */
    private final int partNumInSection;

    /**
     * The constructor for a voice taking in an instrument, a part number within its section, and a passage
     * @param instrument
     * @param partNumInSection the
     * @param passage the passage in which this voice is playing
     */
    public Voice(Instrument instrument, int partNumInSection, IPassage passage) {
        this.passage = passage;
        this.instrument = instrument;
        this.partNumInSection = partNumInSection;
    }

    public Voice(Voice other, IPassage newPassage) {
        this.passage = newPassage;
        this.instrument = other.instrument;
        this.partNumInSection = other.partNumInSection;

    }

    /**
     * Gets the passage that this voice is playing in.
     * @return The
     */
    public IPassage getPassage() {
        return passage;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public int getPartNumInSection() {

    }
}
