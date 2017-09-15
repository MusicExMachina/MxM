package form;

import properties.time.ITime;
import events.IMusicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 *
 * @param <MusicEventType> s
 */
public interface IFrame <MusicEventType extends IMusicEvent> {
    /**
     * Getter for the time at which this frame occurs
     * @return the time at which this frame occurs
     */
    @NotNull ITime getTime();
    /**
     *
     * @return a
     */
    @NotNull Iterator<MusicEventType> eventsStartedItr();
    /**
     *
     * @return a
     */
    @NotNull Iterator<MusicEventType> eventsContinuedItr();
    /**
     *
     * @return a
     */
    @NotNull Iterator<MusicEventType> eventsEndedItr();
    /**
     *
     * @return a
     */
    @NotNull Iterator<MusicEventType> eventsNotStartedItr();
    /**
     *
     * @return a
     */
    @NotNull Iterator<MusicEventType> eventsNotEndedItr();
}
