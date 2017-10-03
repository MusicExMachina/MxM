package form;

import org.jetbrains.annotations.NotNull;
import properties.time.ITime;

/**
 * <p> <b>Interface Overview:</b>
 * The {@link ITimed} interface represents any object which may be said to exist "in time" with some sort of associated
 * {@link ITime}. All {@link events.IMusicEvent}s are {@link ITimed}, as are {@link form.timeline.IFrame}</p>
 *
 * @author Patrick Celentano
 */
public interface ITimed {
    /**
     * Gets the time at which this event occurs
     * @return the time at which this event occurs
     */
    @NotNull ITime getTime();
}
