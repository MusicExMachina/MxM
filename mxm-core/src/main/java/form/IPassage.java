package form;

import com.sun.istack.internal.NotNull;
import events.Chord;
import events.Note;
import events.properties.Tempo;
import events.properties.TimeSig;
import sound.Sonority;
import time.Time;

import java.util.Iterator;

/**
 * Passage is an interface representing any collection of notes that may be iterated over, regardless of who plays them.
 * For instance, both a TraditionalScore and a Part are implementations of Passages- even though they represent different subsets
 * of a whole piece.
 */
public interface IPassage extends Iterable<Frame> {
    public @NotNull Time getStart();
    public @NotNull Time getEnd();
    public @NotNull Time getDuration();

    public @NotNull Tempo getTempoAt(Time time);
    public @NotNull TimeSig getTimeSigAt(Time time);
    public @NotNull Iterator<Note> noteItrAt(Time time);
    public @NotNull Iterator<Chord> chordItrAt(Time time);

    public @NotNull Sonority getSonorityAt(Time time);
    public @NotNull Sonority getHarmonyAt(Time time);

    public @NotNull IPassage getExcerpt(Time start);
    public @NotNull IPassage getExcerpt(Time start, Time end);
}
