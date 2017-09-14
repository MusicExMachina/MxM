package form;

import base.harmony.Chord;
import base.sound.Noise;
import base.sound.Pitch;
import base.time.ITime;
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
 * For instance, both a TraditionalScore and a AbstractPart are implementations of Passages- even though they represent different subsets
 * of a whole piece.
 */
public interface IPassage {

    // Getters for iterators over form.events during a specific time
    @NotNull Iterator<Note> noteItrAt(ITime time);                    // All notes
    @NotNull Iterator<Note<Pitch>> pitchedNoteItrAt(ITime time);      // All pitched notes
    @NotNull Iterator<Note<Noise>> unpitchedNoteItrAt(ITime time);    // All unpitched notes
    @NotNull Iterator<Note<Chord>> chordNoteItrAt(ITime time);        // All chord notes

    // Getters for form.events during a specific time
    @NotNull Tempo getTempoAt(ITime time);            // Tempo at a time
    @NotNull TimeSig getTimeSigAt(ITime time);        // Time Signature at a time

    // Iterators over specific event types
    @NotNull ISerialTimeline<TimeSigChange> getTimeSigChanges();      // All time signature changes
    @NotNull ISerialTimeline<TempoChange> getTempoChanges();          // All tempo changes

    /*
    @NotNull Harmony getHarmonyAt(Time time);        // Harmony at a time
    @NotNull Sonority getSonorityAt(Time time);      // Sonority at a time
    @NotNull Timbre getTimbreAt(Time time);          // Timbre at a time
    */
}
