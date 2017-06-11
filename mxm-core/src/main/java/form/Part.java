package form;

import sound.Instrument;

/**
 * Parts represent
 */
public class Part{

    /** The instrument that plays this line. */
    private Instrument instrument;

    private int partNumberInSection;


    /**
     * The line constructor starts only with the instrument
     * playing this passage, as measures are added later.
     * @param instrument
     */
    public Part(Instrument instrument, int partNumberInSection) {
        this.instrument = instrument;
        this.partNumberInSection = partNumberInSection;
    }

    public Instrument getInstrument() { return instrument; }

    public int getPartNumberInSection() { return partNumberInSection; }
}