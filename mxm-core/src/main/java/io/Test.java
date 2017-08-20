package io;

import events.properties.Instrument;
import form.Score;
import sound.Pitch;
import time.Time;

import static musicTheory.PitchClass.*;

public class Test {
    public static void main(String[] args) {
        Score score     = new Score("My score");

        Part soprano = score.add(Instrument.DEFAULT);
        LineBuilder alto       = new LineBuilder(Instrument.DEFAULT);
        LineBuilder tenor      = new LineBuilder(Instrument.DEFAULT);
        LineBuilder bass       = new LineBuilder(Instrument.DEFAULT);

        soprano.add(Pitch.get(C_NATURAL,    1), Time.get(1))
                .add(Pitch.get(D_NATURAL,   1), Time.get(1))
                .add(Pitch.get(E_NATURAL,   1), Time.get(1))
                .add(Pitch.get(F_NATURAL,   1), Time.get(1))
                .add(Pitch.get(G_NATURAL,   1), Time.get(1));

    }
}
