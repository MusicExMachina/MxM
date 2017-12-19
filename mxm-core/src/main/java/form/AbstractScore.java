package form;

import events.TempoChange;
import events.TimeSigChange;
import time.Time;
import util.ITimeline;
import util.SerialTimeline;
import org.jetbrains.annotations.NotNull;
import time.Measure;
import time.Tempo;
import time.TimeSig;

import java.util.*;

/**
 * We store the public interfaces of such
 */
public abstract class AbstractScore implements IScore {

    private String title;
    private Set<IPart> parts;

    // Timing Information
    private SerialTimeline<TimeSigChange> timeSigChanges;
    private SerialTimeline<TempoChange> tempoChanges;

    /**
     * Constructor for an AbstractScore, to be called by subclasses
     * @param title the tite of this passage
     */
    protected AbstractScore(@NotNull String title) {
        this.title = title;
        this.parts = new HashSet<>();
        this.timeSigChanges = new SerialTimeline<>();
        this.tempoChanges = new SerialTimeline<>();
    }
    /**
     * Getter for an unmodifiable collection of all parts in this score
     * @return an unmodifiable collection of all parts in this score
     */
    public final @NotNull Collection<IPart> getParts() {
        return Collections.unmodifiableSet(parts);
    }


    // ADDERS
    protected @NotNull AbstractScore add(IPart part) {
        parts.add(part);
        return this;
    }
    public @NotNull AbstractScore add(@NotNull TimeSig timeSig, @NotNull Measure time) {
        timeSigChanges.add(new TimeSigChange(this, time, timeSig));
        return this;
    }
    public @NotNull AbstractScore add(@NotNull Tempo tempo, @NotNull Time time) {
        tempoChanges.add(new TempoChange(this, time, tempo));
        return this;
    }

    /**
     * Getter for the title of this work
     * @return the title of this work
     */
    public final @NotNull String getTitle() {
        return title;
    }
    /**
     * Returns an unmodifiable timeline of all the time signature changes in this score
     * @return an unmodifiable timeline of all the time signature changes in this score
     */
    @Override
    public final @NotNull ITimeline<TimeSigChange> getTimeSigChanges() { return timeSigChanges; }
    /**
     * Returns an unmodifiable timeline of all the tempo changes in this score
     * @return an unmodifiable timeline of all the tempo changes in this score
     */
    @Override
    public final @NotNull ITimeline<TempoChange> getTempoChanges() { return tempoChanges; }
}