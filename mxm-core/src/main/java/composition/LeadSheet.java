package composition;

import base.eventProps.Instrument;
import base.sounds.*;
import org.jetbrains.annotations.NotNull;
import passage.Line;
import passage.Score;

public final class LeadSheet extends Score {
    private final Line<Pitch> tune;
    private final Line<Chord> changes;

    public LeadSheet(@NotNull String title) {
        super(title);
        this.tune = new Line<>(this,Instrument.DEFAULT);
        this.changes = new Line<>(this,Instrument.DEFAULT);
        this.add(tune);
        this.add(changes);
    }

    public final @NotNull Line<Pitch> getTune() { return tune; }
    public final @NotNull Line<Chord> getChanges() { return changes; }
}
