package form.scoreTypes;

import base.sound.Chord;
import base.sound.Pitch;
import form.timelines.AbstractScore;
import org.jetbrains.annotations.NotNull;

import base.properties.Instrument;

import form.timelines.Line;

/**
 * Lead sheets are a common jazz notation in which there is a "tune" (or melody) and "changes" (or a chord progression).
 */
public final class LeadSheet extends AbstractScore {
    /** The tune, i.e. the melody */
    private final Line<Pitch> tune;
    /** The changes, i.e. the chord progression */
    private final Line<Chord> changes;
    /**
     * The lead sheet constructor, taking a title.
     * @param title the title of this lead sheet
     */
    public LeadSheet(@NotNull String title) {
        super(title);
        this.tune = new Line<>(this,Instrument.DEFAULT);
        this.changes = new Line<>(this,Instrument.DEFAULT);
        this.add(tune);
        this.add(changes);
    }
    /**
     * Getter for the tune of this lead sheet.
     * @return the tune of this lead sheet
     */
    public final @NotNull Line<Pitch> getTune() {
        return tune;
    }
    /**
     * Getter for the changes of this lead sheet.
     * @return the changes of this lead sheet
     */
    public final @NotNull Line<Chord> getChanges() {
        return changes;
    }
}
