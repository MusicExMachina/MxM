package model.form;

import model.time.Count;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * A line is a horizontal, time-ordered arrangement of Notes.
 */
public class Line {

    /** A TreeMap that stores all Notes, ordered by time. */
    private TreeMap<Count,Note> notes;

    /** The time at which this Line starts. */
    private Count start;

    /** The time at which this Line ends. */
    private Count end;

    /** The length of this Line in Counts. */
    private Count length;

    /**
     * A Line constructor simply initializes member variables.
     * Lines are in many ways just a dressed-up collection,
     * specifically a dressed-up TreeMap of Notes.
     */
    public Line() {
        notes = new TreeMap<>();
        start = Count.ZERO;
        end = Count.ZERO;
        length = Count.ZERO;
    }

    /**
     * Adds a Note to this Line, if it fits between the existing Notes.
     * If it does not, an error is thrown, because the Notes would overlap.
     * @param note The Note to add to this Line.
     */
    public void add(Note note) {

        Note previousNote = notes.floorEntry(note.getStart()).getValue();
        Note followingNote = notes.ceilingEntry(note.getStart()).getValue();

        Count endOfPreviousNote = Count.ZERO;
        Count startOfFollowingNote = Count.INFINITY;

        if(previousNote != null) {
            endOfPreviousNote = previousNote.getEnd();
        }

        if(followingNote != null) {
            startOfFollowingNote = followingNote.getStart();
        }

        if(note.getStart().toFloat() > endOfPreviousNote.toFloat() &&
                note.getEnd().toFloat() > startOfFollowingNote.toFloat()) {
            notes.put(note.getStart(),note);
            start = notes.firstEntry().getValue().getStart();
            end = notes.lastEntry().getValue().getEnd();
            length = end.minus(start);
        }
        else {
            throw new Error("LINE\t Note does not fit in line!");
        }
    }

    /**
     * Returns the start Count of this Line (the start of the first Note).
     * @return
     */
    public Count getStart() {
        return start;
    }

    /**
     * Returns the end Count of this Line (the end of the last Note).
     * @return
     */
    public Count getEnd() {
        return end;
    }

    /**
     * Returns the length of this Line (from the start of the first
     * Note to the end of the last Note).
     * @return
     */
    public Count getLength() {
        return length;
    }

    /**
     * Gets the Note at a given Count in this Line. Note that this
     * is likely to return a Note that is in the middle of being played.
     * @param count The Count to check.
     * @return The Note played during that Count, if there is one.
     */
    public Note noteAt(Count count) {
        if(count.toFloat() < start.toFloat()) {
            throw new Error("LINE:\tTrying to access note before line even starts!");
        }
        else if(count.toFloat() > end.toFloat()) {
            throw new Error("LINE:\tTrying to access note before line even starts!");
        }
        return notes.ceilingEntry(count).getValue();
    }

    /**
     * Returns an iterator over all Notes in this Line, time-ordered.
     * @return An iterator over all Notes in this Line.
     */
    public Iterator<Note> iterator() {
        return notes.values().iterator();
    }
}
