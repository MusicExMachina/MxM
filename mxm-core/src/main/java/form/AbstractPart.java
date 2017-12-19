package form;

import note.Instrument;
import sound.Chord;
import sound.ISound;
import sound.Noise;
import sound.Pitch;
import org.jetbrains.annotations.NotNull;

/**
 * ASDF
 *
 * @param <SoundType> The type of {@link ISound} that this part can play, whether that be {@link Pitch}, {@link Chord},
 *                   or {@link Noise}. One may not, for instance, add a {@link Pitch} to a {@link Chord}.
 */
public abstract class AbstractPart <SoundType extends ISound> implements IPart {

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** The score to which this part belongs */
    private final IScore score;
    /** The instrument playing this part */
    private final Instrument instrument;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    protected AbstractPart(@NotNull IScore score, @NotNull Instrument instrument) {
        this.score = score;
        this.instrument = instrument;
    }

    public final @NotNull IScore getScore() {
        return score;
    }
    public final @NotNull Instrument getInstrument() {
        return instrument;
    }
}
