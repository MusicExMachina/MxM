package form.events;

import org.jetbrains.annotations.NotNull;
import form.Part;

public interface IPartEvent extends IMusicEvent {
    public @NotNull
    Part getPart();
}
