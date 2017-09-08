package form;

import base.time.Time;
import form.events.IMusicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public interface ISerialTimeline <MusicEventType extends IMusicEvent> extends ITimeline <MusicEventType>,
                                                                                Iterable<MusicEventType> {
    @NotNull MusicEventType getFirstEvent();
    @NotNull MusicEventType getLastEvent();
    @NotNull MusicEventType getEventAt(@NotNull Time time);
    @NotNull MusicEventType getEventBefore(@NotNull Time time);
    @NotNull MusicEventType getEventAfter(@NotNull Time time);
    @NotNull Iterator<MusicEventType> iterator();

}
