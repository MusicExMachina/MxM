package form;

import base.Chord;
import base.Noise;
import base.Pitch;
import org.jetbrains.annotations.NotNull;
import form.events.*;
import base.Tempo;
import base.TimeSig;
import base.time.*;

import java.util.*;

public abstract class Score implements IPassage {

    private String title;
    private Set<Part> parts;

    // Note information
    private SerialTimeline<TimeSigChange> timeSigChanges;
    private SerialTimeline<TempoChange> tempoChanges;

    // Other form.form.events
    private ParallelTimeline<Note> allNotes;
    private ParallelTimeline<Note<Pitch>> allPitchedNotes;
    private ParallelTimeline<Note<Noise>> allUnpitchedNotes;
    private ParallelTimeline<Note<Chord>> allChordNotes;

    protected Score(String title) {
        this.title = title;
        this.parts = new HashSet<>();

        this.timeSigChanges = new SerialTimeline<>();
        this.tempoChanges = new SerialTimeline<>();

        this.allNotes = new ParallelTimeline<>();
        this.allPitchedNotes = new ParallelTimeline<>();
        this.allUnpitchedNotes = new ParallelTimeline<>();
        this.allChordNotes = new ParallelTimeline<>();
    }

    //////////////////
    // Score Adders //
    //////////////////

    // Adds a part
    public @NotNull Score add(Part part) {
        parts.add(part);
        return this;
    }
    // Adds a time signature change
    public @NotNull Score add(@NotNull TimeSig timeSig, @NotNull Measure time) {
        timeSigChanges.addEvent(new TimeSigChange(this, time, timeSig));
        return this;
    }
    // Adds a tempo change
    public @NotNull Score add(@NotNull Tempo tempo, @NotNull Time time) {
        tempoChanges.addEvent(new TempoChange(this, time, tempo));
        return this;
    }

    @Override
    public final @NotNull SerialTimeline<TimeSigChange> getTimeSigChanges() { return timeSigChanges; }
    @Override
    public final @NotNull SerialTimeline<TempoChange> getTempoChanges() { return tempoChanges; }

    public Iterator<Note> noteItrAt(Time time) { return allNotes.getFrameBefore(time).eventsNotEndedItr(); }
    @Override
    public Iterator<Note<Pitch>> pitchedNoteItrAt(Time time) { return allPitchedNotes.getFrameBefore(time).eventsNotEndedItr(); }
    @Override
    public Iterator<Note<Noise>> unpitchedNoteItrAt(Time time) { return allUnpitchedNotes.getFrameBefore(time).eventsNotEndedItr(); }
    @Override
    public Iterator<Note<Chord>> chordNoteItrAt(Time time) { return allChordNotes.getFrameBefore(time).eventsNotEndedItr(); }

    @Override
    public @NotNull Tempo getTempoAt(Time time) { return tempoChanges.getEventBefore(time).getTempo(); }
    @Override
    public @NotNull TimeSig getTimeSigAt(Time time) { return timeSigChanges.getEventBefore(time).getTimeSig(); }
}
