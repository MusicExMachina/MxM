package events;

import base.time.Time;
import com.sun.istack.internal.NotNull;
import events.InstantEvent;
import base.time.Tempo;
import passage.Score;


public class TempoChange extends InstantEvent implements IScoreEvent {
    // TEMPO CHANGE PROPERTIES
    private final Score score;
    private final Tempo tempo;

    // CONSTRUCTORS
    public TempoChange(@NotNull Score score, @NotNull Time timing, @NotNull Tempo tempo) {
        super(timing);
        this.score = score;
        this.tempo = tempo;
    }

    // GETTERS
    public @NotNull Tempo getTempo() {
        return tempo;
    }
    @Override
    public Score getScore() { return score; }
}
