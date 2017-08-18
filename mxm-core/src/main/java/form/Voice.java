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

    /** Every voice has an instrument */
    private final Instrument instrument;
    /** The part number this voice is in its section- i.e. "#1" or "#4" */
    private final int partNumInSection;

    public Voice(Instrument instrument, int partNumInSection) {
        this.instrument = instrument;
        this.partNumInSection = partNumInSection;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public int getPartNumInSection() {
        return partNumInSection;
    }
}
