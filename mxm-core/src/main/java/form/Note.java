package form;

import base.Count;
import base.Pitch;

/**
 * The form.Note class represents (in essence) a base.Pitch with a given length. Articulation
 * and Technique are also applied, but mostly just for future expansion.
 *
 * It is important to remember that Notes are one-off, and two different notes with
 * the same Articulation, Technique, base.Pitch, and base.Count are in fact NOT identical. This
 * is because each "form.Note" is in fact a conceptualization of a note on a page (though
 * notes in a repeated passage exist as many times as that passage repeats).
 *
 * It is also crucial to remember that Notes CANNOT BE SORTED ON THEIR OWN. This is
 * because of the inherent ambiguity of what a sorted collection of Notes would look
 * like. Lines, for instance, are time-ordered. Frames are base.Pitch-ordered.
 */
public class Note {

    /** The base.Pitch of this form.Note. */
    private Pitch pitch;

    /** The start RhythmNode of this form.Note. */
    private Count start;

    /** The end RhythmNode of this form.Note. */
    private Count end;

    /** The end RhythmNode of this form.Note. */
    private Count length;

    /**
     * A noteQualities form.Note constructor utilizing the
     * default Technique and Articulation.
     * @param pitch The base.Pitch of this form.Note.
     */
    public Note(Count start, Count end, Pitch pitch) {
        if(end == null) {
            end = start;
        }
        this.pitch = pitch;
        this.start = start;
        this.end = end;
        this.length = end.minus(start);
    }

    /**
     * Gets the base.Pitch of this form.Note.
     * @return The base.Pitch of this form.Note.
     */
    public Pitch getPitch() {
        return pitch;
    }

    /**
     * Gets the start time of this form.Note.
     * @return The start time of this form.Note.
     */
    public Count getStart() {
        return start;
    }

    /**
     * Gets the start time of this form.Note.
     * @return The start time of this form.Note.
     */
    public Count getLength() {
        return length;
    }

    /**
     * Gets the end time of this form.Note.
     * @return The end time of this form.Note.
     */
    public Count getEnd() {
        return end;
    }

    /**
     * Returns a nicely-formatted String of this form.Note.
     * @return A String representing this form.Note.
     */
    @Override
    public String toString() {
        return "(" + pitch.toString() + " @" + getStart().toString() + ")";
    }
}