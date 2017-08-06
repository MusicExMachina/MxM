package form;

import events.Frame;
import events.Note;
import time.*;

import java.util.TreeSet;

/**
 * Passage is an interface representing any collection of notes that may be iterated over, regardless of who plays them.
 * For instance, both a TraditionalScore and a Part are implementations of Passages- even though they represent different subsets
 * of a whole piece.
 */
public interface IPassage extends Iterable<Frame> {
    public Count getStart();
    public Count getEnd();
    public Count getDuration();

    public Tempo getTempoAt(Count time);
    public TimeSig getTimeSigAt(Count time);
    public TreeSet<Note> getNotesAt(Count time);

    public IPassage getExcerpt(Count start);
    public IPassage getExcerpt(Count start, Count end);
}
