package passage.musicEvents;

import org.jetbrains.annotations.NotNull;
import passage.Part;

public interface IPartEvent extends IMusicEvent {
    public @NotNull
    Part getPart();
}
