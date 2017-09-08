package form.events;

import org.jetbrains.annotations.NotNull;
import form.timelines.Part;

public interface IPartEvent extends IMusicEvent {
    public @NotNull
    Part getPart();
}
