package form;

import org.jetbrains.annotations.NotNull;
import properties.time.ITime;

public interface ITimed {
    /**
     * Gets the time at which this event occurs
     * @return the time at which this event occurs
     */
    @NotNull ITime getTime();
}
