package form.events;

import form.passage.Score;
import time.Measure;
import org.jetbrains.annotations.NotNull;
import time.Time;
import time.TimeSig;

public class TimeSigChange extends InstantEvent {

    private final Score score;
    private final TimeSig timeSig;

    public TimeSigChange(@NotNull Score score, @NotNull Measure measure, @NotNull TimeSig timeSig) {
        super(Time.of(measure));
        this.score = score;
        this.timeSig = timeSig;
    }

    public TimeSig getTimeSig() {
        return timeSig;
    }
    public Score getScore() {
        return score;
    }
}
