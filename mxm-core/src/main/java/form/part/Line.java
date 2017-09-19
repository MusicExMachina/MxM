package form.part;

import form.score.IScore;
import form.timeline.ITimeline;
import form.timeline.SerialTimeline;
import properties.sound.ISound;
import properties.note.Instrument;
import properties.time.ITime;
import properties.time.Time;
import org.jetbrains.annotations.NotNull;
import events.sound.Note;

/**
 * The
 * @param <SoundType> s
 */
public class Line<SoundType extends ISound> extends AbstractPart<SoundType> {

    //////////////////////////////
    // Member variables         //
    //////////////////////////////

    /** All of the notes in this line, which may not overlap */
    private final SerialTimeline<Note<SoundType>> notes;
    /** We must save the last-written-to time in this Line */
    private ITime writeHead;

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /***
     *
     * @param score
     * @param instrument
     */
    public Line(@NotNull IScore score, @NotNull Instrument instrument) {
        super(score,instrument);
        this.notes = new SerialTimeline<>();
        this.writeHead = Time.MEASURE_ONE;
    }

    //////////////////////////////
    // Adder methods            //
    //////////////////////////////

    /**
     * Adds a new note to this line of a given sound and length
     * @param sound the sound of this note
     * @param length the length of this note
     * @return this line, for use in chaining such commands
     */
    public @NotNull Line<SoundType> add(@NotNull SoundType sound, @NotNull ITime length) {
        notes.add(new Note<>(this, sound, writeHead, writeHead.plus(length)));
        writeHead = writeHead.plus(length);
        return this;
    }
    /**
     * Adds a new rest to this line of a given sound and length
     * @param length the length of this rest
     * @return this line, for use in chaining such commands
     */
    public @NotNull Line<SoundType> add(@NotNull ITime length) {
        writeHead = writeHead.plus(length);
        return this;
    }
    /**
     * Returns a timeline of all the notes in this line
     * @return a timeline of all the notes in this line
     */
    public @NotNull ITimeline<Note<SoundType>> getNotes() {
        return notes;
    }
}
