package io;

import com.sun.istack.internal.NotNull;
import events.Chord;
import events.Note;
import form.Part;
import form.Score;
import form.events.Note;
import musicTheory.Harmony;
import sound.Pitch;
import sound.Sonority;
import time.ITime;
import time.Time;

import java.util.Iterator;

public class LineBuilder extends Part<Note> {
    Time latestTime;

    private LineBuilder(Score score, Voice voice) {
        super(score, voice);
    }

    // Add a rest
    public LineBuilder add(Time length) {
        latestTime = latestTime.plus(length);
        return this;
    }
    public LineBuilder add(Pitch pitch, Time length) {
        // Add a note
        return add(length);
    }

    @Override
    public @NotNull Iterator<Note> noteItrAt(@NotNull Time time) {
        return null;
    }

    @Override
    public @NotNull Iterator<Chord> chordItrAt(@NotNull Time time) {
        return new Iterator<Chord>();
    }

    @Override
    public @NotNull Sonority getSonorityAt(@NotNull Time time) {

    }

    @Override
    public @NotNull Harmony getHarmonyAt(@NotNull Time time) {

    }
}
