package model.form;

import model.basic.Articulation;
import model.basic.Technique;
import model.pitch.Pitch;
import model.time.Count;

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

    /** The quality of this note's attack. */
    private final Articulation articulation;

    /** The Technique of this note's production. */
    private final Technique technique;

    /** The Pitch of this note. */
    private final Pitch pitch;

    /** When this note is articulated. */
    private final Count start;

    /** How long this note is sustained for. */
    private final Count length;

    /**
     * A basic Note constructor utilizing the
     * default Technique and Articulation.
     * @param pitch The Pitch of this Note.
     * @param start The start time of this Note.
     * @param length The length of this Note.
     */
    public Note(Pitch pitch, Count start, Count length) {
        this.pitch          = pitch;
        this.start         = start;
        this.length         = length;
        this.articulation   = Articulation.DEFAULT;
        this.technique      = Technique.DEFAULT;
    }

    /**
     * A more complete constructor which allows for
     * initialization of Articulation Techniques.
     * @param pitch The Pitch of this Note.
     * @param start The start time of this Note.
     * @param length The length of this Note.
     * @param articulation The Articulation of this Note.
     * @param technique The Technique of this Note.
     */
    public Note(Pitch pitch, Count start, Count length, Articulation articulation, Technique technique) {
        this.pitch          = pitch;
        this.start          = start;
        this.length         = length;
        this.articulation   = articulation;
        this.technique      = technique;
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
        return start;
    }

    /**
     * Gets the end time of this Note.
     * @return The end time of this Note.
     */
    public Count getEnd() {
        return start.plus(length);
    }

    /**
     * Gets the length of this Note.
     * @return The length of this Note.
     */
    public Count getLength() {
        return length;
    }

    /**
     * Returns a nicely-formatted String of this Note.
     * @return A String representing this Note.
     */
    @Override
    public String toString() {
        return "(" + pitch.toString() + " " + length.toString() + ")";
    }
}
