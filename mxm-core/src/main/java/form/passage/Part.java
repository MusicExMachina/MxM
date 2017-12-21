package form.passage;

import form.events.TempoChange;
import form.events.TimeSigChange;
import sound.attributes.Instrument;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import time.Tempo;
import time.Time;
import time.TimeSig;
import util.timeline.ITimeline;

public abstract class Part<SoundType> implements IPassage {

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    private final Score score;
    private final Instrument instrument;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    protected Part(@NotNull Score score, @NotNull Instrument instrument) {
        this.score = score;
        this.instrument = instrument;
    }

    public final @NotNull Score getScore() {
        return score;
    }
    public final @NotNull Instrument getInstrument() {
        return instrument;
    }

    @Override
    public final @Nullable TimeSig getTimeSigAt(@NotNull Time time) { return score.getTimeSigAt(time); }
    @Override
    public final @Nullable Tempo getTempoAt(@NotNull Time time) { return score.getTempoAt(time); }
    @Override
    public final @NotNull ITimeline<TimeSigChange> getTimeSigChanges() { return score.getTimeSigChanges(); }
    @Override
    public final @NotNull ITimeline<TempoChange> getTempoChanges() { return score.getTempoChanges(); }
}
