package form.events;

import org.jetbrains.annotations.NotNull;
import form.timelines.AbstractPart;

public interface IPartEvent extends IMusicEvent {
    public @NotNull
    AbstractPart getPart();
}
