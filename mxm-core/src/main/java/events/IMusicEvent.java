package events;

import form.ITimed;
import org.jetbrains.annotations.NotNull;

// An event can be in multiple frames, but only one score
public interface IMusicEvent extends ITimed {
    /**
     * Getter for a simple string representing this music event
     * @return a string representing this music event
     */
    @Override
    @NotNull String toString();
}

