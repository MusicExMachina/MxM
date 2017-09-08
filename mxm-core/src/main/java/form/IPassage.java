package form;

import base.harmony.Chord;
import base.properties.Noise;
import base.pitch.Pitch;
import form.events.Note;
import base.time.Tempo;
import base.time.TimeSig;
import base.time.Time;
import form.events.TempoChange;
import form.events.TimeSigChange;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Line is an interface representing any collection of notes that may be iterated over, regardless of who plays them.
 * For instance, both a TraditionalScore and a Part are implementations of Passages- even though they represent different subsets
 * of a whole piece.
 */
public interface IPassage {

    // Getters for iterators over form.events during a specific time
    @NotNull Iterator<Note> noteItrAt(Time time);                    // All notes
    @NotNull Iterator<Note<Pitch>> pitchedNoteItrAt(Time time);      // All pitched notes
    @NotNull Iterator<Note<Noise>> unpitchedNoteItrAt(Time time);    // All unpitched notes
    @NotNull Iterator<Note<Chord>> chordNoteItrAt(Time time);        // All chord notes

    // Getters for form.events during a specific time
    @NotNull Tempo getTempoAt(Time time);            // Tempo at a time
    @NotNull TimeSig getTimeSigAt(Time time);        // Time Signature at a time

    // Iterators over specific event types
    @NotNull ISerialTimeline<TimeSigChange> getTimeSigChanges();      // All time signature changes
    @NotNull ISerialTimeline<TempoChange> getTempoChanges();          // All tempo changes

    /*
    @NotNull Harmony getHarmonyAt(Time time);        // Harmony at a time
    @NotNull Sonority getSonorityAt(Time time);      // Sonority at a time
    @NotNull Timbre getTimbreAt(Time time);          // Timbre at a time
    */
}
