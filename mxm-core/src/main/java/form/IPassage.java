package form;

import events.Note;
import events.TempoChange;
import events.TimeSigChange;
import time.Count;

import java.util.Iterator;

/**
 * Passage is an interface representing any collection of notes that may be iterated over, regardless of who plays them.
 * For instance, both a Score and a Part are implementations of Passages- even though they represent different subsets
 * of a whole piece.
 */
public interface IPassage {
    public static int PICKUP_MEASURE_NUM = 0;
    public static int START_MEASURE_NUM = 1;

    public IPassage getExcerpt(Count start, Count end);

    public Count getStart();
    public Count getEnd();
    public Count getDuration();

    public Iterator<Note> soundItr();
    public Iterator<TimeSigChange> timeSigChangeItr();
    public Iterator<TempoChange> tempoChangeItr();
}
