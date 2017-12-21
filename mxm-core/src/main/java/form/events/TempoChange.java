package form.events;

import form.time.Time;
import form.passage.Score;
import org.jetbrains.annotations.NotNull;
import form.attributes.Tempo;


public class TempoChange extends InstantEvent {

    private final Score score;
    private final Tempo tempo;

    public TempoChange(@NotNull Score score, @NotNull Time timing, @NotNull Tempo tempo) {
        super(timing);
        this.score = score;
        this.tempo = tempo;
    }

    public @NotNull Tempo getTempo() {
        return tempo;
    }
    public Score getScore() { return score; }
}
