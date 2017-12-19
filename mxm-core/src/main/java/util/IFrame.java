package util;

import form.ITimed;
import events.IMusicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * <p> <b>Interface Overview:</b>
 * This is the public interface for a frame- a vertical slice of music events which occur simultaneously. Note that
 * users may not add directly to a frame, but rather, access such functionality through timeline (which themselves
 * are accessed through passages). This may seem like a lot of indirection, but it is crucially useful in preventing
 * mishaps by limiting access.</p>
 *
 * @param <MusicEventType> the type of music event that may be found in this frame
 */
public interface IFrame <MusicEventType extends IMusicEvent> extends ITimed {
    /**
     * Gets a collection of all events which start exactly on this frame
     * @return a collection of all events which start exactly on this frame
     */
    @NotNull Collection<MusicEventType> startedEvents();
    /**
     * Gets a collection of all events which either start on or continue through this frame, but do not end on it
     * @return a collection of all events which either start on or continue through this frame, but do not end on it
     */
    @NotNull Collection<MusicEventType> ongoingEvents();
    /**
     * Gets a collection of all events which neither start nor end on this frame, but rather pass through it
     * @return a collection of all events which neither start nor end on this frame, but rather pass through it
     */
    @NotNull Collection<MusicEventType> continuedEvents();
    /**
     * Gets a collection of all events which end exactly on this frame
     * @return a collection of the events which end exactly on this frame
     */
    @NotNull Collection<MusicEventType> endedEvents();
}
