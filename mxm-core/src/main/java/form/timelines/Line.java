package form.timelines;

import base.ISound;
import base.properties.Instrument;
import base.time.Time;
import org.jetbrains.annotations.NotNull;
import form.events.Note;

import java.util.Iterator;

public class Line<SoundType extends ISound> extends Part<SoundType> implements Iterable<Note<SoundType>> {
    private final SerialTimeline<Note<SoundType>> notes;
    private Time writeHead;

    //////////////////
    // Constructors //
    //////////////////

    public Line(@NotNull Score score, @NotNull Instrument instrument) {
        super(score,instrument);
        this.notes = new SerialTimeline<>();
        this.writeHead = Time.MEASURE_ONE;
    }

    /////////////////
    // Line Adders //
    /////////////////

    // Adds a note
    public @NotNull Line<SoundType> add(@NotNull SoundType sound, @NotNull Time length) {
        notes.addEvent(new Note<>(this, sound, writeHead, writeHead.plus(length)));
        writeHead = writeHead.plus(length);
        return this;
    }
    // Adds a rest
    public @NotNull Line<SoundType> add(@NotNull Time length) {
        writeHead = writeHead.plus(length);
        return this;
    }

    //////////////
    // Iterator //
    //////////////

    @Override
    public Iterator<Note<SoundType>> iterator() { return notes.iterator(); }
}
