package events;

import time.Measure;
import form.AbstractScore;
import org.jetbrains.annotations.NotNull;
import time.Time;
import time.TimeSig;

public class TimeSigChange extends AbstractInstantEvent implements IScoreEvent {

    private final AbstractScore score;
    private final TimeSig timeSig;

    public TimeSigChange(@NotNull AbstractScore score, @NotNull Measure measure, @NotNull TimeSig timeSig) {
        super(Time.of(measure.getNumber()));
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
