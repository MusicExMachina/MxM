package passage;

import base.sounds.*;
import com.sun.istack.internal.NotNull;
import events.*;
import base.eventProps.Instrument;
import base.time.Tempo;
import base.time.TimeSig;
import composition.LineBuilder;
import base.time.*;

import java.util.*;

public class Score extends Timeline<IMusicEvent> implements IPassage {

    private String title;

    // Note information
    private Timeline<TimeSigChange> timeSigChanges;
    private Timeline<TempoChange> tempoChanges;

    // Timing information
    private Timeline<TimeSigChange> timeSigChanges;
    private Timeline<TempoChange> tempoChanges;

    // Other passage.events
    private Timeline<Note> allNotes;

    public Score(String title) {
        super(frames);
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
    public Iterator<IMusicEvent> eventItr() { return null; }
    @Override
    public Iterator<Note> noteItr() { return null; }
    @Override
    public Iterator<Note<Pitch>> pitchedNoteItr() { return null; }
    @Override
    public Iterator<Note<Noise>> unpitchedNoteItr() { return null; }
    @Override
    public Iterator<Note<Chord>> chordNoteItr() { return null; }
    @Override
    public Iterator<TimeSigChange> timeSigChangeItr() { return null; }
    @Override
    public Iterator<TempoChange> tempoChangeItr() { return null; }
    @Override
    public Iterator<IMusicEvent> eventItrAt(Time time) { return null; }
    @Override
    public Iterator<Note> noteItrAt(Time time) { return null; }
    @Override
    public Iterator<Note<Pitch>> pitchedNoteItrAt(Time time) { return null; }
    @Override
    public Iterator<Note<Noise>> unpitchedNoteItrAt(Time time) { return null; }
    @Override
    public Iterator<Note<Chord>> chordNoteItrAt(Time time) { return null; }
    @Override
    public Harmony getHarmonyAt(Time time) { return null; }
    @Override
    public Sonority getSonorityAt(Time time) { return null; }
    @Override
    public Timbre getTimbreAt(Time time) { return null; }
    @Override
    public Tempo getTempoAt(Time time) { return null; }
    @Override
    public TimeSig getTimeSigAt(Time time) { return timeSigChanges.getFrameBefore(time); }
}
