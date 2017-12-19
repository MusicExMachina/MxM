package form;

import time.Time;
import util.ITimeline;
import time.Tempo;
import time.TimeSig;
import events.TempoChange;
import events.TimeSigChange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <p> <b>Interface Overview:</b>
 * A passage is any segment of music that can stand "on its own," meaning that it contains timing information like
 * tempi and time signatures, in addition to some (or no) notes. There are two predominant passage types:
 * {@link IScore} and {@link IPart}.</p>
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
    default @NotNull Sonority getSonorityAt(@NotNull Time time) {
        HashSet<Pitch> pitches = new HashSet<>();
        for(Note note : getNotesAt(time)) {
            ISound sound = note.getSound();
            if(sound instanceof Pitch) {
                pitches.add((Pitch)sound);
            }
        }
        return Sonority.of(pitches);
    }
    default @NotNull Harmony getHarmonyAt(@NotNull Time time) {
        HashSet<PitchClass> pitchClasses = new HashSet<>();
        for(Note note : getNotesAt(time)) {
            ISound sound = note.getSound();
            if(sound instanceof Pitch) {
                pitchClasses.add(((Pitch)sound).getPitchClass());
            }
        }
        return Harmony.of(pitchClasses);
    }
    default @NotNull Timbre getTimbreAt(@NotNull Time time) {
        HashSet<Noise> noises = new HashSet<>();
        for(Note note : getNotesAt(time)) {
            ISound sound = note.getSound();
            if(sound instanceof Noise) {
                noises.add((Noise)sound);
            }
        }
        return Timbre.of(noises);
    }
    */
    // Getters for events during a specific time

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
    default @NotNull Collection<Note> getNotesAt(@NotNull Time time) {
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
