package passage;

import com.sun.istack.internal.NotNull;
import events.Note;
import base.eventProps.Instrument;
import base.time.Tempo;
import base.time.TimeSig;
import composition.LineBuilder;
import base.time.*;
import events.timing.TempoChange;
import events.timing.TimeSigChange;

import java.util.*;

public class Score extends Timeline<IScoreEvent> implements IPassage {

    private String title;

    // Timing information
    private Timeline<TimeSigChange> timeSigChanges;
    private Timeline<TempoChange> tempoChanges;

    // Other passage.events
    private Timeline<Note> allNotes;

    public Score(String title) {
        this.title = title;
        this.timeSigChanges = new Timeline<>();
        this.tempoChanges = new Timeline<>();
    }

    //////////////////
    // Score Adders //
    //////////////////

    // Adds a part
    public Part add(Instrument instrument) {
        return new Part(this,instrument);
    }
    // Adds a time signature change
    public TimeSigChange add(TimeSig timeSig, Time time) {
        Frame frame = timeSigChanges.getFrameAtOrAdd(time);
        return new TimeSigChange(frame,timeSig);
    }
    // Adds a tempo change
    public TempoChange add(Tempo tempo, Time time) {
        Frame frame = tempoChanges.getFrameAtOrAdd(time);
        return new TempoChange(frame,tempo);
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
        return iterator();
    }
}
