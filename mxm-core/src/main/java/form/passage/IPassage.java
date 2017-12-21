package form.passage;

import form.time.Time;
import util.timeline.ITimeline;
import form.attributes.Tempo;
import form.attributes.TimeSig;
import form.events.TempoChange;
import form.events.TimeSigChange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IPassage {
    // Getters for iterators over form.events during a specific form.time

    /*
    default @NotNull Sonority getSonorityAt(@NotNull Time form.time) {
        HashSet<Pitch> pitches = new HashSet<>();
        for(Sound properties : getNotesAt(form.time)) {
            ISound sound = properties.getSound();
            if(sound instanceof Pitch) {
                pitches.add((Pitch)sound);
            }
        }
        return Sonority.of(pitches);
    }
    default @NotNull Harmony getHarmonyAt(@NotNull Time form.time) {
        HashSet<PitchClass> pitchClasses = new HashSet<>();
        for(Sound properties : getNotesAt(form.time)) {
            ISound sound = properties.getSound();
            if(sound instanceof Pitch) {
                pitchClasses.add(((Pitch)sound).getPitchClass());
            }
        }
        return Harmony.of(pitchClasses);
    }
    default @NotNull Timbre getTimbreAt(@NotNull Time form.time) {
        HashSet<Noise> noises = new HashSet<>();
        for(Sound properties : getNotesAt(form.time)) {
            ISound sound = properties.getSound();
            if(sound instanceof Noise) {
                noises.add((Noise)sound);
            }
        }
        return Timbre.of(noises);
    }
    */
    // Getters for form.events during a specific form.time

    default @Nullable TimeSig getTimeSigAt(@NotNull Time time) {
        TimeSigChange timeSigChange = getTimeSigChanges().getBefore(time);
        if(timeSigChange != null) {
            return timeSigChange.getTimeSig();
        }
        return null;
    }
    default @Nullable Tempo getTempoAt(@NotNull Time time) {
        TempoChange tempoChange = getTempoChanges().getBefore(time);
        if (tempoChange != null) {
            return tempoChange.getTempo();
        }
        return null;
    }
    /*
    default @NotNull Collection<Sound> getNotesAt(@NotNull Time form.time) {
        Frame<Sound> frame = getNotes().getBefore(form.time);
        if(frame != null) {
            return getNotes().getAt(form.time).ongoingEvents();
        }
        // Return a trivial collection
        return new ArrayList<>();
    }

    @NotNull ITimeline<Frame<Sound>> getNotes();
    */
    @NotNull ITimeline<TimeSigChange> getTimeSigChanges();
    @NotNull ITimeline<TempoChange> getTempoChanges();
}
