package form.score;

import events.time.TempoChange;
import events.time.TimeSigChange;
import form.AbstractPassage;
import form.ITimeline;
import form.exceptions.TimelineOverlapError;
import form.part.IPart;
import form.timeline.SerialTimeline;
import org.jetbrains.annotations.NotNull;
import properties.time.Tempo;
import properties.time.TimeSig;
import properties.time.*;

import java.util.*;

/**
 * We store the public interfaces of such
 */
public abstract class AbstractScore extends AbstractPassage implements IScore {

    private String title;
    private Set<IPart> parts;

    // Timing Information
    private SerialTimeline<TimeSigChange> timeSigChanges;
    private SerialTimeline<TempoChange> tempoChanges;

    protected AbstractScore(String title) {
        this.title = title;
        this.parts = new HashSet<>();
        this.timeSigChanges = new SerialTimeline<>();
        this.tempoChanges = new SerialTimeline<>();
    }

    // ADDERS
    public @NotNull AbstractScore add(IPart part) {
        parts.add(part);
        return this;
    }
    public @NotNull AbstractScore add(@NotNull TimeSig timeSig, @NotNull IMeasure time) {
        timeSigChanges.add(new TimeSigChange(this, time, timeSig));
        return this;
    }
    public @NotNull AbstractScore add(@NotNull Tempo tempo, @NotNull ITime time) {
        tempoChanges.add(new TempoChange(this, time, tempo));
        return this;
    }

    @Override
    public final @NotNull ITimeline<TimeSigChange> getTimeSigChanges() { return timeSigChanges; }
    @Override
    public final @NotNull ITimeline<TempoChange> getTempoChanges() { return tempoChanges; }
}