package form.timelines;

import base.ISound;
import base.properties.Instrument;
import base.time.Time;
import org.jetbrains.annotations.NotNull;
import form.events.Note;

import java.util.Iterator;

/**
 * The
 * @param <SoundType>
 */
public class Line<SoundType extends ISound> extends AbstractPart<SoundType> implements Iterable<Note<SoundType>> {

    /** All of the notes in this line, which may not overlap */
    private final SerialTimeline<Note<SoundType>> notes;
    /** We must save the last-written-to time in this Line */
    private Time writeHead;

    //////////////////
    // Constructors //
    //////////////////

    public Line(@NotNull AbstractScore score, @NotNull Instrument instrument) {
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
