package passage;
import base.sounds.*;
import events.*;
import base.time.Tempo;
import base.time.TimeSig;
import base.time.Time;

import java.util.Iterator;

public class Part extends Timeline<IPartEvent> implements IPassage {
    private final Score score;

    Part(Score score) {
        super(frames);
        this.score = score;
    }

    @Override
    public Iterator<IMusicEvent> eventItr() { return null; }
    @Override
    public Iterator<Note> noteItr() { return null; }
    @Override
    public Iterator<Note<Pitch>> pitchedNoteItr() { return null; }
    @Override
    public Iterator<Note<Noise>> unpitchedNoteItr() { return null; }
    @Override
    public Iterator<Note<Chord>> chordNoteItr() { return null; }
    @Override
    public Iterator<TimeSigChange> timeSigChangeItr() { return score.timeSigChangeItr(); }
    @Override
    public Iterator<TempoChange> tempoChangeItr() { return score.tempoChangeItr(); }


    @Override
    public Iterator<IMusicEvent> eventItrAt(Time time) { return null; }
    @Override
    public Iterator<Note> noteItrAt(Time time) { return null; }
    @Override
    public Iterator<Note<Pitch>> pitchedNoteItrAt(Time time) { return null; }
    @Override
    public Iterator<Note<Noise>> unpitchedNoteItrAt(Time time) { return null; }
    @Override
    public Iterator<Note<Chord>> chordNoteItrAt(Time time) { return null; }
    @Override
    public Harmony getHarmonyAt(Time time) { return null; }
    @Override
    public Sonority getSonorityAt(Time time) { return null; }
    @Override
    public Timbre getTimbreAt(Time time) { return null; }
    @Override
    public Tempo getTempoAt(Time time) { return score.getTempoAt(time); }
    @Override
    public TimeSig getTimeSigAt(Time time) { return score.getTimeSigAt(time); }
}



/*

    private final Instrument instrument;
    private final Timeline<IPartEvent> partEvents;

    Part(@NotNull Score score, @NotNull Instrument instrument) {
        this.score = score;
        this.instrument = instrument;
        this.partEvents = new Timeline<>();
    }


    public void add(@NotNull Note playableEvent) {
        Frame frame = partEvents.getFrameAtOrAdd(playableEvent.getStart());
        frame.addStart(playableEvent);
    }

    @Override
    public final @NotNull Time getStart() {
        return partEvents.getStart();
    }
    @Override
    public final @NotNull Time getEnd() {
        return partEvents.getEnd();
    }
    @Override
    public final @NotNull Time getDuration() {
        return partEvents.getDuration();
    }

    @Override
    public final @NotNull Tempo getTempoAt(@NotNull Time time) {
        return score.getTempoAt(time);
    }
    @Override
    public final @NotNull TimeSig getTimeSigAt(@NotNull Time time) {
        return score.getTimeSigAt(time);
    }

    @Override
    public @NotNull IPassage getExcerpt(@NotNull Time start) {
        return null;
    }
    @Override
    public @NotNull IPassage getExcerpt(@NotNull Time start, @NotNull Time end) {
        return null;
    }

    @Override
    public Iterator<Frame> iterator() {
        return masterT
    }
 */