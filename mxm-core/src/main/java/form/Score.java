package form;

import com.sun.istack.internal.NotNull;
import events.*;
import events.properties.Tempo;
import events.properties.TimeSig;
import io.LineBuilder;
import sound.Sonority;
import time.*;

import java.util.*;

public class Score implements IPassage {

    private String title;

    // Master timeline
    Timeline<IMusicEvent> masterTimeline;

    // Timing information
    Timeline<IScoreEvent> scoreEvents;
    Timeline<TimeSigChange> timeSigChanges;
    Timeline<TempoChange> tempoChanges;

    // Other form.events
    Timeline<Note> allNotes;

    public Score(String title) {
        this.title = title;
        this.masterTimeline = new Timeline<>();
    }

    public Score add(LineBuilder lineBuilder) {
        return this;
    }

    public void add(Tempo tempo, Time time) {
        Frame frame = masterTimeline.getFrameAtOrAdd(time);
        frame.add(new TempoChange(frame,tempo));
    }

    @Override
    public Time getStart() {
        return null;
    }

    @Override
    public Time getEnd() {
        return null;
    }

    @Override
    public Time getDuration() {
        return null;
    }

    @Override
    public Tempo getTempoAt(Time time) {
        return null;
    }

    @Override
    public TimeSig getTimeSigAt(Time time) {
        return null;
    }

    @Override
    public Iterator<Note> noteItrAt(Time time) {
        return null;
    }

    @Override
    public Iterator<Chord> chordItrAt(Time time) {
        return null;
    }

    @Override
    public Sonority getSonorityAt(Time time) {
        return null;
    }

    @Override
    public Sonority getHarmonyAt(Time time) {
        return null;
    }

    @Override
    public IPassage getExcerpt(Time start) {
        return null;
    }

    @Override
    public IPassage getExcerpt(Time start, Time end) {
        return null;
    }


    @Override
    public @NotNull Iterator<Frame> iterator() {
        return masterTimeline.iterator();
    }
}
