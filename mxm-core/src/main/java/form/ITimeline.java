package form;

import events.IMusicEvent;

/**
 * We don't want classes outside this package instantiating timeline, but we want
 * a common public interface. Thus, the classes are private, but their corresponding
 * interfaces are public
 */
public interface ITimeline <MusicEventType extends IMusicEvent> {

}
