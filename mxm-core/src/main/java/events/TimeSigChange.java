package events;

import base.time.Measure;
import org.jetbrains.annotations.NotNull;
import base.time.TimeSig;
import passage.Score;

/**
 * Created by celenp on 6/11/2017.
 */
public class TimeSigChange extends InstantEvent implements IScoreEvent {
    private final Score score;
    private final TimeSig timeSig;

    public TimeSigChange(@NotNull Score score, @NotNull Measure timing, @NotNull TimeSig timeSig) {
        super(timing);
        this.score = score;
        this.timeSig = timeSig;
    }

    public TimeSig getTimeSig() {
        return timeSig;
    }

    @Override
    public Score getScore() {
        return score;
    }
}
