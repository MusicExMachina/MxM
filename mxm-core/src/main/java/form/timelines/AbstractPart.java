package form.timelines;

import base.properties.Instrument;
import base.sound.Chord;
import base.sound.ISound;
import base.sound.Noise;
import base.sound.Pitch;
import base.time.ITime;
import base.time.Tempo;
import base.time.TimeSig;
import form.IPassage;
import form.ISerialTimeline;
import org.jetbrains.annotations.NotNull;
import form.events.Note;
import form.events.TempoChange;
import form.events.TimeSigChange;

import java.util.Iterator;

public abstract class AbstractPart<SoundType extends ISound> implements IPassage {
    private final AbstractScore score;
    private final Instrument instrument;

    AbstractPart(AbstractScore score, Instrument instrument) {
        this.score = score;
        this.instrument = instrument;
    }

    public final @NotNull AbstractScore getScore() { return score; }
    public final @NotNull Instrument getInstrument() { return instrument; }

    // Iterators over specific event types
    @Override
    public final @NotNull ISerialTimeline<TimeSigChange> getTimeSigChanges() { return getScore().getTimeSigChanges(); }
    @Override
    public final @NotNull ISerialTimeline<TempoChange> getTempoChanges() { return getScore().getTempoChanges(); }

    // Getters for form.events during a specific time
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
