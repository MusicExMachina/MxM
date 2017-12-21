package form.passage;

import form.events.Note;
import sound.ISound;
import time.Duration;
import time.Measure;
import util.timeline.ITimeline;
import util.timeline.SerialTimeline;
import sound.attributes.Instrument;
import time.Time;
import org.jetbrains.annotations.NotNull;

public class Line<SoundType extends ISound> extends Part<SoundType> {

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    private final SerialTimeline<Note<SoundType>> notes;
    private Time writeHead;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    public Line(@NotNull Score score, @NotNull Instrument instrument) {
        super(score,instrument);
        this.notes = new SerialTimeline<>();
        this.writeHead = Time.of(Measure.ONE);
    }

    //////////////////////////////
    // Adder methods            //
    //////////////////////////////

    public @NotNull Line<SoundType> add(@NotNull SoundType sound, @NotNull Duration length) {
        notes.add(new Note<>(this, sound, writeHead, writeHead.plus(length)));
        writeHead = writeHead.plus(length);
        return this;
    }

    public @NotNull Line<SoundType> add(@NotNull Duration length) {
        writeHead = writeHead.plus(length);
        return this;
    }

    public @NotNull ITimeline<Note<SoundType>> getNotes() {
        return notes;
    }
}
