package form;

import form.timeline.IFrame;
import org.jetbrains.annotations.Nullable;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 *
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

    default @Nullable Tempo getTempoAt(@NotNull ITime time) {
        TempoChange tempoChange = getTempoChanges().getBefore(time);
        if (tempoChange != null) {
            return tempoChange.getTempo();
        }
        return null;
    }
    default @Nullable TimeSig getTimeSigAt(@NotNull ITime time) {
        TimeSigChange timeSigChange = getTimeSigChanges().getBefore(time);
        if(timeSigChange != null) {
            return timeSigChange.getTimeSig();
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
