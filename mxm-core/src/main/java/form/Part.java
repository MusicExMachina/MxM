package form;

import com.sun.istack.internal.NotNull;
import events.Note;
import sound.ISound;
import time.Count;
import time.ITime;
import time.Tempo;
import time.TimeSig;

import java.util.Iterator;

/**
 * Parts represent passages played by a single performer or a number of performers in the same section. To put it
 * another way, Parts are identical to "parts" in a musical context- there can be a first trumpet part, cello part,
 * drum set part, so on and so forth. As a type of IPassage, they can be iterated over for their constituent events.
 */
public class Part implements IPassage {
    private final Score score;
    private final Voice voice;
    private final ParallelTimeline<Count, Note<? extends ISound>> notes;

    public Part(@NotNull Score score, @NotNull Voice voice) {
        this.score = score;
        this.voice = voice;
        this.notes = new ParallelTimeline<>();
    }

    @Override
    public ITime getStart() {
        return notes.getStart();
    }
    @Override
    public ITime getEnd() {
        return notes.getEnd();
    }
    @Override
    public ITime getDuration() {
        return notes.getDuration();
    }

    @Override
    public Tempo getTempoAt(ITime time) {
        return score.getTempoAt(time);
    }
    @Override
    public TimeSig getTimeSigAt(ITime time) {
        return score.getTimeSigAt(time);
    }
    @Override
    public Iterator<Note<? extends ISound>> getNotesAt(ITime time) {
        return notes.getFrameAt(time).iterator();
    }

    @Override
    public IPassage getExcerpt(Count start) {
        return null;
    }
    @Override
    public IPassage getExcerpt(Count start, Count end) {
        return null;
    }
}