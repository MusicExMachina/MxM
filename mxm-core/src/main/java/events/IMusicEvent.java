package events;

import time.Frame;
import time.ITime;

/**
 * A
 *
 * Note that music events cannot be compared, as how could one compare a note to a tempo change? Or two notes ocurring
 * at the same time? It becomes too hazy to define by a simple comparison operator.
 */
public interface IMusicEvent<TimeType extends ITime> {
    public Frame<TimeType> getFrame();
    public TimeType getTiming();
}
