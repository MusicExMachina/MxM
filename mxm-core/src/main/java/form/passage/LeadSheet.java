package form.passage;

import sound.Chord;
import sound.pitch.Pitch;
import org.jetbrains.annotations.NotNull;

import sound.attributes.Instrument;

public final class LeadSheet extends Score {

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    private final Line<Pitch> tune;
    private final Line<Chord> changes;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    public LeadSheet(@NotNull String title) {
        super(title);
        // Create the tune and changes
        this.tune = new Line<>(this,Instrument.DEFAULT);
        this.changes = new Line<>(this,Instrument.DEFAULT);
        // Add them to our AbstractPassage
        this.add(tune);
        this.add(changes);
    }


    public final @NotNull Line<Pitch> getTune() {
        return tune;
    }
    public final @NotNull Line<Chord> getChanges() {
        return changes;
    }
}
