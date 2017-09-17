package form;

import form.timeline.ISerialTimeline;
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

import java.util.Iterator;

/**
 * ASDF
 *
 * @param <SoundType> The type of {@link ISound} that this part can play, whether that be {@link Pitch}, {@link Chord},
 *                   or {@link Noise}. One may not, for instance, add a {@link Pitch} to a {@link Chord}.
 */
public abstract class AbstractPart<SoundType extends ISound> implements IPassage {

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

    /**
     * The constructor for
     * @param score
     * @param instrument
     */
    protected AbstractPart(@NotNull AbstractScore score, @NotNull Instrument instrument) {
        this.score = score;
        this.instrument = instrument;
    }
    /**
     * A getter for the score to which this part belongs
     * @return the score to which this part belongs
     */
    public final @NotNull AbstractScore getScore() {
        return score;
    }
    /**
     * A getter for the instrument of this part
     * @return the instrument of this part
     */
    public final @NotNull Instrument getInstrument() {
        return instrument;
    }




    // Iterators over specific event types
    @Override
    public final @NotNull ISerialTimeline<TimeSigChange> timeSigChanges() { return getScore().timeSigChanges(); }
    @Override
    public final @NotNull ISerialTimeline<TempoChange> getTempoChanges() { return getScore().getTempoChanges(); }

    // Getters for events during a specific time
    @Override
    public final @NotNull Tempo getTempoAt(ITime time) { return getScore().getTempoAt(time); }
    @Override
    public final @NotNull TimeSig getTimeSigAt(ITime time) { return getScore().getTimeSigAt(time); }




    @NotNull
    @Override
    public Iterator<Note> noteItrAt(ITime time) { return null; }
    @NotNull
    @Override
    public Iterator<Note<Pitch>> pitchedNoteItrAt(ITime time) { return null; }
    @Override
    public Iterator<Note<Noise>> unpitchedNoteItrAt(ITime time) { return null; }
    @Override
    public Iterator<Note<Chord>> chordNoteItrAt(ITime time) { return null; }
}
