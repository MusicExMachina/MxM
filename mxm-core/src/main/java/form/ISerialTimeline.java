package form;

import base.time.ITime;
import base.time.Time;
import form.events.IMusicEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

/**
 * <p> <b>Interface Overview:</b>
 * The {@link ISerialTimeline} interface represents a timeline in which all musical events are ordered serially- one
 * after the other, that is. This contrasts starkly with a {@link IParallelTimeline} in which many events may happen
 * simultaneously, and are stored in a {@link IFrame}.</p>
 *
 * @param <MusicEventType> The subclass of IMusicEvent which this timeline may hold. It's worth noting that this might
 *                        be IMusicEvent itself- in which case, all music events may be stored within this timeline.
 *
 * @author Patrick Celentano
 */
public interface ISerialTimeline <MusicEventType extends IMusicEvent> extends ITimeline <MusicEventType>,
                                                                                Iterable<MusicEventType> {
    /**
     * A getter for the first event that occurs in this timeline
     * @return the first event in this timeline, or null if there are no events
     */
    @Nullable MusicEventType getFirstEvent();
    /**
     * A getter for the last event that occurs in this timeline
     * @return the last event in this timeline, or null if there are no events
     */
    @Nullable MusicEventType getLastEvent();
    /**
     * A getter for an event which occurs at a given time
     * @param time the time to check
     * @return the event that occurs <i>exactly</i> at a specified time
     */
    @Nullable MusicEventType getEventAt(@NotNull ITime time);
    /**
     *
     * @param time
     * @return
     */
    @Nullable MusicEventType getEventBefore(@NotNull ITime time);
    /**
     *
     * @param time
     * @return
     */
    @Nullable MusicEventType getEventAfter(@NotNull ITime time);
    /**
     *
     * @return
     */
    @NotNull Iterator<MusicEventType> iterator();

}
