package form;

import events.Note;
import sound.ISound;
import time.Count;
import time.ITime;
import time.Tempo;
import time.TimeSig;

import java.util.Iterator;

/**
 * Passage is an interface representing any collection of notes that may be iterated over, regardless of who plays them.
 * For instance, both a Score and a Part are implementations of Passages- even though they represent different subsets
 * of a whole piece.
 */
public interface IPassage {
    public ITime getStart();
    public ITime getEnd();
    public ITime getDuration();

    public Tempo getTempoAt(ITime time);
    public TimeSig getTimeSigAt(ITime time);
    public Iterator<Note<? extends ISound>> getNotesAt(ITime time);

    public IPassage getExcerpt(Count start);
    public IPassage getExcerpt(Count start, Count end);
}
