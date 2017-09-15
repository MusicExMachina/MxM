package events;

import org.jetbrains.annotations.NotNull;
import properties.time.ITime;

// An event can be in multiple frames, but only one score
public interface IMusicEvent {

    /**
     * Gets the time at which this event occurs
     * @return the time at which this event occurs
     */
    @NotNull ITime getTiming();

    /**
     * Getter for a simple string representing this music event
     * @return a string representing this music event
     */
    @Override
    @NotNull String toString();
}

