package form.passage;

import org.jetbrains.annotations.NotNull;

import sound.attributes.Instrument;
import sound.pitch.Pitch;

/**
 * <p> <b>Class overview:</b>
 * Chorales are a common classical music genre with four vocal parts: soprano, alto, tenor, and bass, each of which is
 * a proper line- one properties after the other, with no polyphony inside a single part.</p>
 *
 * <p> <b>Design Details:</b>
 * Subclasses of {@link Score} are intended to ease users in their composition by restricting the number and
 * type of parts.</p>
 *
 * @author Patrick Celentano
 */
public final class Chorale extends Score {

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** The soprano part */
    private final Line<Pitch> soprano;
    /** The alto part */
    private final Line<Pitch> alto;
    /** The tenor part */
    private final Line<Pitch> tenor;
    /** The bass part */
    private final Line<Pitch> bass;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * The chorale constructor, taking a title.
     * @param title the title of this chorale
     */
    public Chorale(@NotNull String title) {
        super(title);
        // Create four parts
        this.soprano = new Line<>(this,Instrument.DEFAULT);
        this.alto = new Line<>(this,Instrument.DEFAULT);
        this.tenor = new Line<>(this,Instrument.DEFAULT);
        this.bass = new Line<>(this,Instrument.DEFAULT);
        // Add them to our AbstractPassage
        this.add(soprano);
        this.add(alto);
        this.add(tenor);
        this.add(bass);
    }
    /**
     * Getter for the soprano part of this chorale.
     * @return the soprano part of this chorale
     */
    public final @NotNull Line<Pitch> getSoprano() {
        return soprano;
    }
    /**
     * Getter for the alto part of this chorale.
     * @return the alto part of this chorale
     */
    public final @NotNull Line<Pitch> getAlto() {
        return alto;
    }
    /**
     * Getter for the tenor part of this chorale.
     * @return the tenor part of this chorale
     */
    public final @NotNull Line<Pitch> getTenor() {
        return tenor;
    }
    /**
     * Getter for the bass part of this chorale.
     * @return the bass part of this chorale
     */
    public final @NotNull Line<Pitch> getBass() {
        return bass;
    }
}
