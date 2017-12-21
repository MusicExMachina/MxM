package form;

import form.events.IEvent;
import org.jetbrains.annotations.NotNull;
import time.Time;
import util.Frame;

/**
 * <p> <b>Interface Overview:</b>
 * The {@link ITimed} interface represents any object which may be said to exist "in time" with some sort of associated
 * {@link Time}. All {@link IEvent}s are {@link ITimed}, as are {@link Frame}</p>
 *
 * @author Patrick Celentano
 */
public interface ITimed {
    /**
     * Gets the time at which this event occurs
     * @return the time at which this event occurs
     */
    @NotNull Time getTime();
}
