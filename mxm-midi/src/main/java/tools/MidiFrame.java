package tools;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * MidiFrames store contemporaneous midi notes in a vertical stack. This is essentially
 * a dressed-up tree set, but it makes tools.MidiReader a great deal easier.
 */
class MidiFrame implements Comparable<MidiFrame>, Comparator<MidiFrame>, Iterable<MidiNote> {

    /** The time that this midi frame starts. */
    private float timing;

    /** The midi notes in this midi frame. */
    private TreeSet<MidiNote> notes;

    /**
     * The constructor for midi frame.
     * @param timing The time that this frame starts.
     */
    MidiFrame(float timing) {
        this.timing = timing;
        notes = new TreeSet<>();
    }

    /**
     * Adds a midi note to this midi frame, or throws an error if they don't allign.
     * @param note The midi note to add to this frame.
     */
    public void add(MidiNote note) {
        if(Float.compare(note.getStart(),timing) == 0) {
            notes.add(note);
        }
        else {
            throw new Error("MIDI FRAME:\tMidi note doesn't line up with midi frame!");
        }
    }

    /**
     * Returns an iterator over all the notes in this frame, sorted by pitch.
     * @return An iterator over all the notes in this frame.
     */
    @Override
    public Iterator<MidiNote> iterator() {
        return notes.iterator();
    }

    /**
     * Compares this midi frame to another.
     * @param f The other frame to compare to this frame.
     * @return A comparison between these two midi frames.
     */
    @Override
    public int compareTo(MidiFrame f) {
        return Float.compare(this.timing,f.timing);
    }

    /**
     * Compares two midi frames.
     * @param f1 The first midi frame to compare.
     * @param f2 The second midi frame to compare.
     * @return A comparison between these two midi frames.
     */
    @Override
    public int compare(MidiFrame f1, MidiFrame f2) {
        return Float.compare(f1.timing,f2.timing);
    }
}
