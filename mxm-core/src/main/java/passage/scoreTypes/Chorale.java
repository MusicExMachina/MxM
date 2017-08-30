package passage.scoreTypes;

import org.jetbrains.annotations.NotNull;

import base.properties.Instrument;
import base.sounds.Pitch;

import passage.Line;
import passage.Score;

/**
 * Chorales are a common classical music genre with four vocal parts: soprano, alto, tenor, and bass.
 */
public final class Chorale extends Score {
    /** The soprano part */
    private final Line<Pitch> soprano;
    /** The alto part */
    private final Line<Pitch> alto;
    /** The tenor part */
    private final Line<Pitch> tenor;
    /** The bass part */
    private final Line<Pitch> bass;
    /**
     * The chorale constructor, taking a title.
     * @param title the title of this chorale
     */
    public Chorale(@NotNull String title) {
        super(title);
        this.soprano = new Line<>(this,Instrument.DEFAULT);
        this.alto = new Line<>(this,Instrument.DEFAULT);
        this.tenor = new Line<>(this,Instrument.DEFAULT);
        this.bass = new Line<>(this,Instrument.DEFAULT);
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
