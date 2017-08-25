package composition;

import base.eventProps.Instrument;
import base.sounds.Pitch;
import org.jetbrains.annotations.NotNull;
import passage.Line;
import passage.Score;

public final class Chorale extends Score {
    private final Line<Pitch> soprano;
    private final Line<Pitch> alto;
    private final Line<Pitch> tenor;
    private final Line<Pitch> bass;

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

    public final @NotNull Line<Pitch> getSoprano() { return soprano; }
    public final @NotNull Line<Pitch> getAlto() { return alto; }
    public final @NotNull Line<Pitch> getTenor() { return tenor; }
    public final @NotNull Line<Pitch> getBass() { return bass; }
}
