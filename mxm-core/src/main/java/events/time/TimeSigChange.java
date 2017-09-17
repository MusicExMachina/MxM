package events.time;

import events.AbstractInstantEvent;
import events.IScoreEvent;
import properties.time.IMeasure;
import form.AbstractScore;
import org.jetbrains.annotations.NotNull;
import properties.time.TimeSig;

/**
 * Created by celenp on 6/11/2017.
 */
public class TimeSigChange extends AbstractInstantEvent implements IScoreEvent {
    private final AbstractScore score;
    private final TimeSig timeSig;

    public TimeSigChange(@NotNull AbstractScore score, @NotNull IMeasure timing, @NotNull TimeSig timeSig) {
        super(timing);
        this.score = score;
        this.timeSig = timeSig;
    }

    public TimeSig getTimeSig() {
        return timeSig;
    }

    @Override
    public AbstractScore getScore() {
        return score;
    }
}
