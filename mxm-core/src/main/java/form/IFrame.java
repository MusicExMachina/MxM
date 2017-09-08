package form;

import base.time.Time;
import form.events.IMusicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public interface IFrame <MusicEventType extends IMusicEvent> {
    @NotNull Time getTime();
    @NotNull Iterator<MusicEventType> eventsStartedItr();
    @NotNull Iterator<MusicEventType> eventsContinuedItr();
    @NotNull Iterator<MusicEventType> eventsEndedItr();
    @NotNull Iterator<MusicEventType> eventsNotStartedItr();
    @NotNull Iterator<MusicEventType> eventsNotEndedItr();
}
