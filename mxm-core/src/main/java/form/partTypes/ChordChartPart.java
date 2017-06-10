package form.partTypes;

import base.instrument.Instrument;
import events.playable.Chord;
import form.Part;

/**
 * ChordChartParts do not have notes, but instead, a collection of timed Chords (which function very similarly to
 * Notes, but of course involve Chords instead of Pitches).
 */
public class ChordChartPart extends Part<Chord> {

    /**
     * The line constructor starts only with the instrument
     * playing this passage, as measures are added later.
     *
     * @param instrument
     */
    public ChordChartPart(Instrument instrument) {
        super(instrument);
    }
}
