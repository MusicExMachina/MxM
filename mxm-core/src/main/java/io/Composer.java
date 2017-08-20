package io;

import com.sun.istack.internal.NotNull;
import events.TempoChange;
import events.TimeSigChange;
import form.Score;
import time.Count;
import events.properties.Tempo;
import events.properties.TimeSig;

public class Composer {

    private Score score;

    private TempoChange lastTempoChange;
    private TimeSigChange lastTimeSigChange;

    private Count currentCount;

    private void advance(Count amountToAdvanceBy) {
        currentCount = currentCount.plus(amountToAdvanceBy);
    }





    
    private void writeTempoChange(@NotNull Tempo tempo) {
        score.add(tempo, currentCount);
    }
    private void writeTimeSig(@NotNull TimeSig timeSig) {
        score.add(timeSig, currentCount.getMeasure());
    }
}
