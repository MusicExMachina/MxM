package form;

import events.TempoChange;
import events.TimeSigChange;
import util.ITimeline;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import note.Instrument;
import sound.ISound;
import time.Time;
import time.Tempo;
import time.TimeSig;

/**
 * <p> <b>Interface Overview:</b>
 * Parts are passages to be played by a single player- be that a pianist, a horn player, a percussionist, or vocalist.
 * What is important about a part is that the instrument never switches, and that the score to which it is attached
 * remains its "master track" for all timing information, such as time signature or tempo changes.</p>
 *
 * <p> <b>Design Details:</b>
 * This interface stands as an <b>unmodifiable but mutable</b> outer interface for a series of classes which are both
 * modifiable and mutable. This allows for MxM to control access to such classes, which are often delicate in nature
 * and should not be modified by outside parties.</p>
 *
 * @param <SoundType> The type of {@link ISound} that this part can play, whether that be pitches, chords, noises, etc.
 *
 * @author Patrick Celentano
 */
public interface IPart <SoundType extends ISound> extends IPassage {
    /**
     * A getter to the score which this part is a part of
     * @return the score that this part belongs to
     */
    @NotNull IScore getScore();
    /**
     * A getter for the instrument which is playing this part
     * @return the instrument which is playing this part
     */
    @NotNull Instrument getInstrument();
    /**
     * Gets the time signature at a given time in this part, which is always dictated by the score
     * @param time the time at which to sample the part
     * @return the time signature at this point in the part
     */
    @Override
    default @Nullable TimeSig getTimeSigAt(@NotNull Time time) { return getScore().getTimeSigAt(time); }
    /**
     * Gets the tempo at a given time in this part, which is always dictated by the score
     * @param time the time at which to sample the part
     * @return the tempo at this point in the part
     */
    @Override
    default @Nullable Tempo getTempoAt(@NotNull Time time) { return getScore().getTempoAt(time); }
    /**
     * Gets an unmodifiable timeline of all time signature changes in this part, dictated by the score
     * @return an unmodifiable timeline of all time signature changes in this part
     */
    @Override
    default @NotNull ITimeline<TimeSigChange> getTimeSigChanges() { return getScore().getTimeSigChanges(); }
    /**
     * Gets an unmodifiable timeline of all tempo changes in this part, dictated by the score
     * @return an unmodifiable timeline of all tempo changes in this part
     */
    @Override
    default @NotNull ITimeline<TempoChange> getTempoChanges() { return getScore().getTempoChanges(); }
}
