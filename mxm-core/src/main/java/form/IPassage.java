package form;

import form.timeline.ITimeline;
import properties.time.ITime;
import properties.time.Tempo;
import properties.time.TimeSig;
import events.time.TempoChange;
import events.time.TimeSigChange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <p> <b>Interface Overview:</b>
 * A passage is any segment of music that can stand "on its own," meaning that it contains timing information like
 * tempi and time signatures, in addition to some (or no) notes. There are two predominant passage types:
 * {@link form.score.IScore} and {@link form.part.IPart}.</p>
 *
 * <p> <b>Design Details:</b>
 * This interface stands as an <b>unmodifiable but mutable</b> outer interface for a series of classes which are both
 * modifiable and mutable. This allows for MxM to control access to such classes, which are often delicate in nature
 * and should not be modified by outside parties.</p>
 *
 * @author Patrick Celentano
 */
public interface IPassage {
    // Getters for iterators over events during a specific time

    /*
    default @NotNull Sonority getSonorityAt(@NotNull ITime time) {
        HashSet<Pitch> pitches = new HashSet<>();
        for(Note note : getNotesAt(time)) {
            ISound sound = note.getSound();
            if(sound instanceof Pitch) {
                pitches.add((Pitch)sound);
            }
        }
        return Sonority.get(pitches);
    }
    default @NotNull Harmony getHarmonyAt(@NotNull ITime time) {
        HashSet<PitchClass> pitchClasses = new HashSet<>();
        for(Note note : getNotesAt(time)) {
            ISound sound = note.getSound();
            if(sound instanceof Pitch) {
                pitchClasses.add(((Pitch)sound).getPitchClass());
            }
        }
        return Harmony.get(pitchClasses);
    }
    default @NotNull Timbre getTimbreAt(@NotNull ITime time) {
        HashSet<Noise> noises = new HashSet<>();
        for(Note note : getNotesAt(time)) {
            ISound sound = note.getSound();
            if(sound instanceof Noise) {
                noises.add((Noise)sound);
            }
        }
        return Timbre.get(noises);
    }
    */
    // Getters for events during a specific time

    default @Nullable TimeSig getTimeSigAt(@NotNull ITime time) {
        TimeSigChange timeSigChange = getTimeSigChanges().getBefore(time);
        if(timeSigChange != null) {
            return timeSigChange.getTimeSig();
        }
        return null;
    }
    default @Nullable Tempo getTempoAt(@NotNull ITime time) {
        TempoChange tempoChange = getTempoChanges().getBefore(time);
        if (tempoChange != null) {
            return tempoChange.getTempo();
        }
        return null;
    }
    /*
    default @NotNull Collection<Note> getNotesAt(@NotNull ITime time) {
        IFrame<Note> frame = getNotes().getBefore(time);
        if(frame != null) {
            return getNotes().getAt(time).ongoingEvents();
        }
        // Return a trivial collection
        return new ArrayList<>();
    }

    @NotNull ITimeline<IFrame<Note>> getNotes();
    */
    @NotNull ITimeline<TimeSigChange> getTimeSigChanges();
    @NotNull ITimeline<TempoChange> getTempoChanges();
}
