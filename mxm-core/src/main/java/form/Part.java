package form;

import events.IMusicEvent;
import events.Note;
import instruments.Instrument;
import time.Beat;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Parts represent passages played by a single performer or a number of performers in the same section. To put it
 * another way, Parts are identical to "parts" in a musical context- there can be a first trumpet part, cello part,
 * drum set part, so on and so forth. As a type of IPassage, they can be iterated over for their constituent events.
 */
public class Part {
    private Score score;

    /** The instrument that plays this line. */
    private Instrument instrument;

    private int partNumInSection;



    private TreeMap<Beat, IMusicEvent> allEvents;
    private TreeMap<Beat, Note> allNotes;


    /**
     * The line constructor starts only with the instrument
     * playing this passage, as measures are added later.
     * @param instrument
     */
    public Part(Instrument instrument, int partNumInSection) {
        this.instrument = instrument;
        this.partNumInSection = partNumInSection;
    }

    public Instrument getInstrument() { return instrument; }

    public int getPartNumInSection() { return partNumInSection; }

    public Iterator<IMusicEvent> iterator() {
        return null;
    }
}