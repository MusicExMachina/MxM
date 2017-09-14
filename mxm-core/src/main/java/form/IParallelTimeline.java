package form;

import base.time.ITime;
import base.time.Time;
import form.events.IMusicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * <p> <b>Interface Overview:</b>
 * The {@link IParallelTimeline} interface represents a timeline in which musical events may occur at any time, even
 * simultaneously. This contrasts starkly with a {@link ISerialTimeline} in which many events must happen one after the
 * other, with no overlap. In a {@link IParallelTimeline}, contemporaneous events are stored in a {@link IFrame}, which
 * is simply a vertical stack of events at a given time</p>
 *
 * @param <MusicEventType> The subclass of IMusicEvent which this timeline may hold. It's worth noting that this might
 *                        be IMusicEvent itself- in which case, all music events may be stored within this timeline.
 *
 * @author Patrick Celentano
 */
public interface IParallelTimeline <MusicEventType extends IMusicEvent> extends ITimeline <MusicEventType>,
                                                                                Iterable<IFrame<MusicEventType>> {
    @NotNull IFrame<MusicEventType> getFirstFrame();
    @NotNull IFrame<MusicEventType> getLastFrame();
    @NotNull IFrame<MusicEventType> getFrameAt(@NotNull ITime time);
    @NotNull IFrame<MusicEventType> getFrameBefore(@NotNull ITime time);
    @NotNull IFrame<MusicEventType> getFrameAfter(@NotNull ITime time);
    @NotNull Iterator<IFrame<MusicEventType>> iterator();
}
