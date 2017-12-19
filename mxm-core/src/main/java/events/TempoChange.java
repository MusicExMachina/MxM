package events;

import time.Time;
import form.AbstractScore;
import org.jetbrains.annotations.NotNull;
import time.Tempo;


public class TempoChange extends AbstractInstantEvent implements IScoreEvent {

    private final AbstractScore score;
    private final Tempo tempo;

    public TempoChange(@NotNull AbstractScore score, @NotNull Time timing, @NotNull Tempo tempo) {
        super(timing);
        this.score = score;
        this.tempo = tempo;
    }

    // GETTERS
    public @NotNull Tempo getTempo() {
        return tempo;
    }
    @Override
    public AbstractScore getScore() { return score; }
}
