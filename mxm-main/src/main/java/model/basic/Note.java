package model.basic;

/**
 * Created by celenp on 10/15/2016.
 */
public class Note {

    private final Articulation articulation;
    private final Technique technique;
    private final Pitch pitch;
    private final Count length;

    public Note(Pitch pitch, Count length) {
        this.pitch          = pitch;
        this.length         = length;
        this.articulation   = Articulation.DEFAULT;
        this.technique      = Technique.DEFAULT;
    }

    public Note(Pitch pitch, Count length, Articulation articulation, Technique technique) {
        this.pitch          = pitch;
        this.length         = length;
        this.articulation   = articulation;
        this.technique      = technique;
    }

    @Override
    public String toString() {
        return "[ Note | pitch: " + pitch.toString() + ", length: " + length.toString() + " ]";
    }
}
