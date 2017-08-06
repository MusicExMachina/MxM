package form;

import com.sun.istack.internal.NotNull;
import events.MusicEvent;
import events.Note;
import events.ParallelTimeline;
import events.TempoChange;
import events.TimeSigChange;
import time.*;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Parts represent passages played by a single performer or a number of performers in the same section. To put it
 * another way, Parts are identical to "parts" in a musical context- there can be a first trumpet part, cello part,
 * drum set part, so on and so forth. As a type of IPassage, they can be iterated over for their constituent events.
 */
public class Part implements IPassage {
    private final Score score;
    private final Voice voice;
    private final ParallelTimeline<Note> allNotes;

    public Part(@NotNull Score score,@NotNull Voice voice) {
        this.score = score;
        this.voice = voice;
        this.allNotes = new ParallelTimeline<>();
    }

    @Override
    public Count getStart() {
        return allNotes.getStart();
    }
    @Override
    public Count getEnd() {
        return allNotes.getEnd();
    }
    @Override
    public Count getDuration() {
        return allNotes.getDuration();
    }
    @Override

    public Tempo getTempoAt(Count time) {
        return score.getTempoAt(time);
    }
    @Override
    public TimeSig getTimeSigAt(Count time) {
        return score.getTimeSigAt(time);
    }
    @Override
    public TreeSet<Note> getNotesAt(Count time) {
        return null;
    }

    @Override
    public IPassage getExcerpt(Count start) {
        return null;
    }
    @Override
    public IPassage getExcerpt(Count start, Count end) {
        return null;
    }

    @Override
    public Iterator<TempoChange> tempoChangeItr() {
        return score.tempoChangeItr();
    }
    @Override
    public Iterator<TimeSigChange> timeSigChangeItr() {
        return score.timeSigChangeItr();
    }
}