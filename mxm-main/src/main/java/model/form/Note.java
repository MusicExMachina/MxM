package model.form;

import model.basic.Articulation;
import model.basic.Technique;
import model.pitch.Pitch;
import model.time.Count;

/**
 * The Note class represents (in essence) a Pitch
 * with a given length. Articulation and Technique
 * are also applied, but mostly just for future expansion.
 */
public class Note {

    /** The quality of this note's attack. */
    private final Articulation articulation;

    /** The Technique of this note's production. */
    private final Technique technique;

    /** The Pitch of this note. */
    private final Pitch pitch;

    /** How long this note is sustained for. */
    private final Count length;

    /**
     * A basic Note constructor utilizing the
     * default Technique and Articulation.
     * @param pitch The Pitch of this Note.
     * @param length The length of this Note.
     */
    public Note(Pitch pitch, Count length) {
        this.pitch          = pitch;
        this.length         = length;
        this.articulation   = Articulation.DEFAULT;
        this.technique      = Technique.DEFAULT;
    }

    /**
     * A more complete constructor which allows for
     * initialization of Articulation Techniques.
     * @param pitch The Pitch of this Note.
     * @param length The length of this Note.
     * @param articulation The Articulation of this Note.
     * @param technique The Technique of this Note.
     */
    public Note(Pitch pitch, Count length, Articulation articulation, Technique technique) {
        this.pitch          = pitch;
        this.length         = length;
        this.articulation   = articulation;
        this.technique      = technique;
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
