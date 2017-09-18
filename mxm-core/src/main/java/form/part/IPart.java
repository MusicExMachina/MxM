package form.part;

import events.time.TempoChange;
import events.time.TimeSigChange;
import form.IPassage;
import form.ITimeline;
import form.score.IScore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import properties.note.Instrument;
import properties.sound.ISound;
import properties.time.ITime;
import properties.time.Tempo;
import properties.time.TimeSig;

public interface IPart <SoundType extends ISound> extends IPassage {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @NotNull IScore getScore();
    @NotNull Instrument getInstrument();

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    default @Nullable Tempo getTempoAt(ITime time) { return getScore().getTempoAt(time); }
    @Override
    default @Nullable TimeSig getTimeSigAt(ITime time) { return getScore().getTimeSigAt(time); }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    default @NotNull ITimeline<TimeSigChange> getTimeSigChanges() { return getScore().getTimeSigChanges(); }
    @Override
    default @NotNull ITimeline<TempoChange> getTempoChanges() { return getScore().getTempoChanges(); }
}
