package form;

import base.time.Time;
import form.events.IMusicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public interface IParallelTimeline <MusicEventType extends IMusicEvent> extends ITimeline <MusicEventType>,
                                                                                Iterable<IFrame<MusicEventType>> {
    @NotNull IFrame<MusicEventType> getFirstFrame();
    @NotNull IFrame<MusicEventType> getLastFrame();
    @NotNull IFrame<MusicEventType> getFrameAt(@NotNull Time time);
    @NotNull IFrame<MusicEventType> getFrameBefore(@NotNull Time time);
    @NotNull IFrame<MusicEventType> getFrameAfter(@NotNull Time time);
    @NotNull Iterator<IFrame<MusicEventType>> iterator();
}
