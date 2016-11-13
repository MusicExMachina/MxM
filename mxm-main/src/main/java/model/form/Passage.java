package model.form;

import model.basic.Tempo;
import model.basic.Count;
import model.basic.TimeSignature;
import model.trainable.Instrument;

import java.util.*;

/**
 * After we're done creating a RhythmTree, we put all of its frames into a Passage. Passages are essentially
 * great big Frame holders, and provide users with the ability to look up what's happening at any given Count.
 */
public class Passage implements Iterable<Frame> {

    /** TimeSignatures throughout this passage. */
    private NavigableMap<Integer,TimeSignature> timeSignatures;
    /** All tempi in this Passage. */
    private NavigableMap<Count,Tempo> tempi;
    /** All lines being played in this Passage. */
    private List<Line> lines;
    /** All lines being played in this Passage by a specific instrument. */
    private HashMap<Instrument,List<Line>> linesByInstrument;
    /** All frames in this Passage. */
    private NavigableMap<Count,Frame> frames;

    /**
     * The Passage constructor.
     */
    public Passage() {
        this.timeSignatures = new TreeMap<>();
        this.tempi = new TreeMap<>();
        this.lines = new ArrayList<>();
        this.linesByInstrument = new HashMap<>();
        this.frames = new TreeMap<>();
    }

    /**
     * Adds a TimeSignature (change) to the Passage at a given time (that must be on beat 1 of a measure).
     * @param timeSignature The TimeSignature to add to this Passage.
     * @param measure The time at which to add this TimeSignature.
     */
    public void addTimeSignature(TimeSignature timeSignature,Integer measure) {
        timeSignatures.put(measure,timeSignature);
    }

    /**
     * Adds a Tempo (change) to this Passage at a given time.
     * @param tempo The Tempo to add to this Passage.
     * @param time The time at which to add this Tempo.
     */
    public void addTempoChange(Tempo tempo, Count time) {
        tempi.put(time,tempo);
    }

    /**
     * Adds a Note to this Passage.
     * @param note The Note to add to this Passage.
     */
    private void add(Note note) {
        Count time = note.getStart();
        Frame frame = getFrameAt(time);
        Instrument instrument = note.getInstrument();

        // If there's no frame available
        if(frame == null) {
            frame = new Frame(time);
            frames.put(time,frame);
        }
        frame.add(note);

        // If we've not had this instrument here before
        if(!linesByInstrument.containsKey(instrument)) {
            linesByInstrument.put(instrument,new ArrayList<Line>());
        }

        // Get all the Lines played by this instrument
        Iterator<Line> itr = linesByInstrument.get(instrument).iterator();
        while(itr.hasNext()) {
            Line line = itr.next();
        }
        //line.add(note);
    }

    /**
     * Gets the TimeSignature at a given time in this Passage.
     * @param time The time at which to sample.
     * @return The TimeSignature at this time.
     */
    public TimeSignature getTimeSignatureAt(Count time) {
        return timeSignatures.floorEntry(time.getMeasure()).getValue();
    }

    /**
     * Gets the Tempo at a given time in this Passage.
     * @param time The time at which to sample.
     * @return The Tempo at this time.
     */
    public Tempo getTempoAt(Count time) {
        return tempi.floorEntry(time).getValue();
    }

    /**
     * Gets the Frame at a given time in this Passage.
     * @param time The time at which to sample.
     * @return The Frame at this time.
     */
    public Frame getFrameAt(Count time) {
        return frames.floorEntry(time).getValue();
    }

    /**
     * Returns an Iterator over all Frames in this Passage.
     * @return An Iterator over all Frames in this Passage.
     */
    @Override
    public Iterator<Frame> iterator() {
        return frames.values().iterator();
    }

    /**
     * Returns an Iterator over all Lines in this Passage.
     * @return An Iterator over all Lines in this Passage.
     */
    public Iterator<Line> lineIterator() { return lines.iterator(); }
}