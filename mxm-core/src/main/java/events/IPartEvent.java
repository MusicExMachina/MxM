package events;

import com.sun.istack.internal.NotNull;
import passage.Part;

public interface IPartEvent extends IMusicEvent {
    public @NotNull Part getPart();
}
