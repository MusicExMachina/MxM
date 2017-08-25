package passage;

import base.sounds.*;
import events.Note;
import base.time.Tempo;
import base.time.TimeSig;
import base.time.Time;
import events.TempoChange;
import events.TimeSigChange;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Line is an interface representing any collection of notes that may be iterated over, regardless of who plays them.
 * For instance, both a TraditionalScore and a Part are implementations of Passages- even though they represent different subsets
 * of a whole piece.
 */
public interface IPassage {
    // Iterators over specific event types
    @NotNull Iterator<TimeSigChange> timeSigChangeItr();     // All time signature changes
    @NotNull Iterator<TempoChange> tempoChangeItr();         // All tempo changes

    // Getters for iterators over events during a specific time
    @NotNull Iterator<Note> noteItrAt(Time time);                    // All notes
    @NotNull Iterator<Note<Pitch>> pitchedNoteItrAt(Time time);      // All pitched notes
    @NotNull Iterator<Note<Noise>> unpitchedNoteItrAt(Time time);    // All unpitched notes
    @NotNull Iterator<Note<Chord>> chordNoteItrAt(Time time);        // All chord notes

    // Getters for events during a specific time
    @NotNull Tempo getTempoAt(Time time);            // Tempo at a time
    @NotNull TimeSig getTimeSigAt(Time time);        // Time Signature at a time

    /*
    @NotNull Harmony getHarmonyAt(Time time);        // Harmony at a time
    @NotNull Sonority getSonorityAt(Time time);      // Sonority at a time
    @NotNull Timbre getTimbreAt(Time time);          // Timbre at a time
    */
}
