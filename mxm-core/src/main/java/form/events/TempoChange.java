package form.events;

import base.time.Time;
import form.timelines.AbstractScore;
import org.jetbrains.annotations.NotNull;
import base.time.Tempo;


public class TempoChange extends AbstractInstantEvent implements IScoreEvent {
    // TEMPO CHANGE PROPERTIES
    private final AbstractScore score;
    private final Tempo tempo;

    // CONSTRUCTORS
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
