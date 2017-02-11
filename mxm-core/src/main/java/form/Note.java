package form;

import basic.Pitch;
import basic.Count;
import rhythmTree.RhythmNode;

/**
 * The Note class represents (in essence) a Pitch with a given length. Articulation
 * and Technique are also applied, but mostly just for future expansion.
 *
 * It is important to remember that Notes are one-off, and two different notes with
 * the same Articulation, Technique, Pitch, and Count are in fact NOT identical. This
 * is because each "Note" is in fact a conceptualization of a note on a page (though
 * notes in a repeated passage exist as many times as that passage repeats).
 *
 * It is also crucial to remember that Notes CANNOT BE SORTED ON THEIR OWN. This is
 * because of the inherent ambiguity of what a sorted collection of Notes would look
 * like. Lines, for instance, are time-ordered. Frames are Pitch-ordered.
 */
public class Note {

    /** The Pitch of this Note. */
    private Pitch pitch;

    /** The start RhythmNode of this Note. */
    private RhythmNode start;

    /** The end RhythmNode of this Note. */
    private RhythmNode end;

    /**
     * A noteQualities Note constructor utilizing the
     * default Technique and Articulation.
     * @param pitch The Pitch of this Note.
     */
    public Note(Pitch pitch) {
        this.pitch = pitch;
    }

    /**
     * Gets the Pitch of this Note.
     * @return The Pitch of this Note.
     */
    public Pitch getPitch() {
        return pitch;
    }

    /**
     * Gets the start time of this Note.
     * @return The start time of this Note.
     */
    public Count getStart() {
        if(start == null) {
            return Count.ZERO;
        }
        return start.getTiming();
    }

    /**
     * Gets the end time of this Note.
     * @return The end time of this Note.
     */
    public Count getEnd() {
        if(end == null) {
            return start.getTiming();
        }
        return end.getTiming();
    }

    /**
     * Sets the start time of this Note.
     * @return The start time of this Note.
     */
    public void setStart(RhythmNode node) {
        start = node;
    }

    /**
     * Sets the end time of this Note.
     * @return The end time of this Note.
     */
    public void setEnd(RhythmNode node) {
        end = node;
    }


    /**
     * Returns a nicely-formatted String of this Note.
     * @return A String representing this Note.
     */
    @Override
    public String toString() {
        return "(" + pitch.toString() + " @" + getStart().toString() + ")";
    }
}