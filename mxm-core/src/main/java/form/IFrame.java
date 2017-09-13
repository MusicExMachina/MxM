package form;

import base.time.Time;
import form.events.IMusicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 *
 * @param <MusicEventType>
 */
public interface IFrame <MusicEventType extends IMusicEvent> {
    /**
     * Getter for the time at which this frame occurs
     * @return the time at which this frame occurs
     */
    @NotNull Time getTime();
    /**
     *
     * @return
     */
    @NotNull Iterator<MusicEventType> eventsStartedItr();
    /**
     *
     * @return
     */
    @NotNull Iterator<MusicEventType> eventsContinuedItr();
    /**
     *
     * @return
     */
    @NotNull Iterator<MusicEventType> eventsEndedItr();
    /**
     *
     * @return
     */
    @NotNull Iterator<MusicEventType> eventsNotStartedItr();
    /**
     *
     * @return
     */
    @NotNull Iterator<MusicEventType> eventsNotEndedItr();
}
