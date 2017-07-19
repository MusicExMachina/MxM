package time;

import events.IMusicEvent;

import java.util.Iterator;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by celenp on 7/15/2017.
 *
 *
 * Note that copy constructors and
 */
public class Timeline <TimeType extends ITime> implements Iterable<Frame<TimeType>> {

    /** The frames of */
    private NavigableMap<TimeType,Frame<TimeType>> frames;

    /**
     *
     */
    public Timeline() {
        this.frames = new TreeMap<>();
    }

    /**
     * The basic copy constructor
     * @param other
     */
    private Timeline(Timeline other) {
        this.frames = other.frames;
    }


    /**
     * A form of copy constructor, which 
     * @param other
     */
    private Timeline(Timeline other, TimeType startTime) {
        this.frames = other.frames.tailMap(startTime,true);
    }

    /**
     * A form of copy constructor, which
     * @param other
     */
    private Timeline(Timeline other, TimeType startTime, TimeType endTime) {

    }

    /**
     *
     * @param startTime
     * @return
     */
    public Timeline<TimeType,EventType> subTimeline(ITime startTime) {

    }

    /**
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public Timeline<TimeType,EventType> subTimeline(ITime startTime, ITime endTime) {

    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<Frame<TimeType, EventType>> iterator() {
        return frames.values().iterator();
    }
}
