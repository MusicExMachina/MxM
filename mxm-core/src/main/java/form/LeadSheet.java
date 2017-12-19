package form;

import sound.Chord;
import sound.Pitch;
import org.jetbrains.annotations.NotNull;

import note.Instrument;

/**
 * <p> <b>Class overview:</b>
 * Lead sheets are a common jazz notation in which there is a "tune" (melody) and "changes"- (sound progression) each
 * of which is a proper line- one note after the other, with no polyphony inside a single part.</p>
 *
 * <p> <b>Design Details:</b>
 * Subclasses of {@link AbstractScore} are intended to ease users in their composition by restricting the number and
 * type of parts.</p>
 *
 * @author Patrick Celentano
 */
public final class LeadSheet extends AbstractScore {

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** The tune, i.e. the melody */
    private final Line<Pitch> tune;
    /** The changes, i.e. the sound progression */
    private final Line<Chord> changes;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * The lead sheet constructor, taking a title.
     * @param title the title of this lead sheet
     */
    public LeadSheet(@NotNull String title) {
        super(title);
        // Create the tune and changes
        this.tune = new Line<>(this,Instrument.DEFAULT);
        this.changes = new Line<>(this,Instrument.DEFAULT);
        // Add them to our AbstractPassage
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
