package passage;

import com.sun.istack.internal.NotNull;
import events.*;
import events.properties.Tempo;
import events.properties.TimeSig;
import composition.LineBuilder;
import base.Sonority;
import base.time.*;

import java.util.*;

public class Score extends Timeline<IScoreEvent> implements IPassage {

    private String title;

    // Timing information
    Timeline<IScoreEvent> scoreEvents;
    Timeline<TimeSigChange> timeSigChanges;
    Timeline<TempoChange> tempoChanges;

    // Other passage.events
    Timeline<Note> allNotes;

    public Score(String title) {
        this.title = title;
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
