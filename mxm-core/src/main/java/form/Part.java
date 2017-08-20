package form;

import com.sun.istack.internal.NotNull;
import events.IPartEvent;
import events.PlayableEvent;
import events.properties.Instrument;
import events.properties.Tempo;
import events.properties.TimeSig;
import time.Time;

public class Part <PlayableType extends PlayableEvent> implements IPassage {

    private final Score score;
    private final Instrument instrument;
    private final Timeline<IPartEvent> partEvents;

    Part(@NotNull Score score, @NotNull Instrument instrument) {
        this.score = score;
        this.instrument = instrument;
        this.partEvents = new Timeline<>();
    }

    @Override
    public final @NotNull Time getStart() {
        return partEvents.getStart();
    }
    @Override
    public final @NotNull Time getEnd() {
        return partEvents.getEnd();
    }
    @Override
    public final @NotNull Time getDuration() {
        return partEvents.getDuration();
    }

    @Override
    public final @NotNull Tempo getTempoAt(@NotNull Time time) {
        return score.getTempoAt(time);
    }
    @Override
    public final @NotNull TimeSig getTimeSigAt(@NotNull Time time) {
        return score.getTimeSigAt(time);
    }

    @Override
    public @NotNull IPassage getExcerpt(@NotNull Time start) {
        return null;
    }
    @Override
    public @NotNull IPassage getExcerpt(@NotNull Time start, @NotNull Time end) {
        return null;
    }
}