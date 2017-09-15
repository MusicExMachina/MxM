package form.timelines;

import properties.sound.ISound;
import properties.note.Instrument;
import properties.time.ITime;
import properties.time.Time;
import org.jetbrains.annotations.NotNull;
import events.sound.Note;

import java.util.Iterator;

/**
 * The
 * @param <SoundType> s
 */
public class Line<SoundType extends ISound> extends AbstractPart<SoundType> implements Iterable<Note<SoundType>> {

    /** All of the notes in this line, which may not overlap */
    private final SerialTimeline<Note<SoundType>> notes;
    /** We must save the last-written-to time in this Line */
    private ITime writeHead;

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
    public @NotNull Line<SoundType> add(@NotNull SoundType sound, @NotNull ITime length) {
        notes.addEvent(new Note<>(this, sound, writeHead, writeHead.plus(length)));
        writeHead = writeHead.plus(length);
        return this;
    }
    // Adds a rest
    public @NotNull Line<SoundType> add(@NotNull ITime length) {
        writeHead = writeHead.plus(length);
        return this;
    }

    //////////////
    // Iterator //
    //////////////

    @Override
    public Iterator<Note<SoundType>> iterator() { return notes.iterator(); }
}
