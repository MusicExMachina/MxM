package form.partTypes;

import base.instrument.Instrument;
import form.Part;

/**
 * Created by celenp on 6/9/2017.
 */
public class StandardPart extends Part {
    /**
     * The line constructor starts only with the instrument
     * playing this passage, as measures are added later.
     *
     * @param instrument
     */
    public StandardPart(Instrument instrument) {
        super(instrument);
    }
}
