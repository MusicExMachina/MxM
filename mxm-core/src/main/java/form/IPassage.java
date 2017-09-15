package form;

import properties.sound.Chord;
import properties.sound.Noise;
import properties.sound.Pitch;
import properties.time.ITime;
import events.sound.Note;
import properties.time.Tempo;
import properties.time.TimeSig;
import events.time.TempoChange;
import events.time.TimeSigChange;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Line is an interface representing any collection of notes that may be iterated over, regardless of who plays them.
 * For instance, both a TraditionalScore and a AbstractPart are implementations of Passages- even though they represent different subsets
 * of a whole piece.
 */
public interface IPassage {

    // Getters for iterators over events during a specific time
    @NotNull Iterator<Note> noteItrAt(ITime time);                    // All notes
    @NotNull Iterator<Note<Pitch>> pitchedNoteItrAt(ITime time);      // All pitched notes
    @NotNull Iterator<Note<Noise>> unpitchedNoteItrAt(ITime time);    // All unpitched notes
    @NotNull Iterator<Note<Chord>> chordNoteItrAt(ITime time);        // All chord notes

    // Getters for events during a specific time
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
