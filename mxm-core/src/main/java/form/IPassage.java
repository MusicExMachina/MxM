package form;

import form.timeline.ISerialTimeline;
import properties.sound.*;
import properties.time.ITime;
import events.sound.Note;
import properties.time.Tempo;
import properties.time.TimeSig;
import events.time.TempoChange;
import events.time.TimeSigChange;
import org.jetbrains.annotations.NotNull;
import theory.composite.Sonority;
import theory.composite.Timbre;
import theory.harmony.Harmony;

import java.util.Collection;
import java.util.HashSet;

/**
 *
 */
public interface IPassage {
    // Getters for iterators over events during a specific time

    /**
     *
     * @param time
     * @return
     */
    @NotNull Collection<Note> getNotesAt(@NotNull ITime time);
    /**
     * Returns the sonority at this point in the passage
     * @param time the time at which to sample the passage
     * @return the sonority at this point in the passage
     */
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
    /**
     * Returns the harmony at this point in the passage
     * @param time the time at which to sample the passage
     * @return the harmony at this point in the passage
     */
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
    /**
     * Returns the timbre at this point in the passage
     * @param time the time at which to sample the passage
     * @return the timbre at this point in the passage
     */
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

    // Getters for events during a specific time

    /**
     *
     * @param time
     * @return
     */
    @NotNull Tempo getTempoAt(ITime time);
    /**
     *
     * @param time
     * @return
     */
    @NotNull TimeSig getTimeSigAt(ITime time);
    /**
     *
     * @return
     */
    @NotNull ISerialTimeline<TimeSigChange> timeSigChanges();
    /**
     *
     * @return
     */
    @NotNull ISerialTimeline<TempoChange> tempoChanges();
}
