package form.passage;

import form.events.TempoChange;
import form.events.TimeSigChange;
import time.Time;
import util.timeline.ITimeline;
import util.timeline.SerialTimeline;
import org.jetbrains.annotations.NotNull;
import time.Measure;
import time.Tempo;
import time.TimeSig;

import java.util.*;

public abstract class Score implements IPassage {

    private String title;
    private Set<Part> parts;
    private SerialTimeline<TimeSigChange> timeSigChanges;
    private SerialTimeline<TempoChange> tempoChanges;

    protected Score(@NotNull String title) {
        this.title = title;
        this.parts = new HashSet<>();
        this.timeSigChanges = new SerialTimeline<>();
        this.tempoChanges = new SerialTimeline<>();
    }

    public final @NotNull Collection<Part> getParts() {
        return Collections.unmodifiableSet(parts);
    }

    protected @NotNull Score add(Part part) {
        parts.add(part);
        return this;
    }
    public @NotNull Score add(@NotNull TimeSig timeSig, @NotNull Measure time) {
        timeSigChanges.add(new TimeSigChange(this, time, timeSig));
        return this;
    }
    public @NotNull Score add(@NotNull Tempo tempo, @NotNull Time time) {
        tempoChanges.add(new TempoChange(this, time, tempo));
        return this;
    }

    public final @NotNull String getTitle() {
        return title;
    }
    public final @NotNull ITimeline<TimeSigChange> getTimeSigChanges() { return timeSigChanges; }
    public final @NotNull ITimeline<TempoChange> getTempoChanges() { return tempoChanges; }
}