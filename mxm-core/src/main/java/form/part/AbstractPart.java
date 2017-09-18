package form.part;

import form.ITimeline;
import form.score.AbstractScore;
import form.score.IScore;
import properties.note.Instrument;
import properties.sound.Chord;
import properties.sound.ISound;
import properties.sound.Noise;
import properties.sound.Pitch;
import properties.time.ITime;
import properties.time.Tempo;
import properties.time.TimeSig;
import org.jetbrains.annotations.NotNull;
import events.sound.Note;
import events.time.TempoChange;
import events.time.TimeSigChange;

import java.util.Arrays;
import java.util.Collection;

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
    private final AbstractScore score;
    /** The instrument playing this part */
    private final Instrument instrument;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    protected AbstractPart(@NotNull AbstractScore score, @NotNull Instrument instrument) {
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
