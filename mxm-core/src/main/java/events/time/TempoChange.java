package events.time;

import events.AbstractInstantEvent;
import events.IScoreEvent;
import properties.time.ITime;
import form.score.AbstractScore;
import org.jetbrains.annotations.NotNull;
import properties.time.Tempo;


public class TempoChange extends AbstractInstantEvent implements IScoreEvent {
    // TEMPO CHANGE PROPERTIES
    private final AbstractScore score;
    private final Tempo tempo;

    // CONSTRUCTORS
    public TempoChange(@NotNull AbstractScore score, @NotNull ITime timing, @NotNull Tempo tempo) {
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
