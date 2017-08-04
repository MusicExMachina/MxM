package time;

import events.MusicEvent;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by celenp on 7/15/2017.
 *
 *
 * Note that copy constructors and
 */
public class Timeline <MusicEventType extends MusicEvent> implements Iterable<Frame> {

    /** The frames of */
    private NavigableMap<MusicEventType,Frame<MusicEventType>> frames;

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
    private Timeline(Timeline<MusicEventType> other) {
        this.frames = other.frames;
    }


    /**
     * A form of copy constructor, which 
     * @param other
     */
    private Timeline(Timeline<MusicEventType> other, ITime startTime) {
        this.frames = other.frames.tailMap(startTime,true);
    }

    /**
     * A form of copy constructor, which
     * @param other
     */
    private Timeline(Timeline<MusicEventType> other, Count startTime, Count endTime) {

    }

    /**
     * Adds a frame at a given time, or returns the existing frame at that time. Note that this process is how
     * @param time
     * @return
     */
    public Frame<MusicEventType> addOrGetAt(Count time) {

        if(frames.containsKey(time)) {
            return frames.get(time);
        }
        else {
            Frame<MusicEventType> frameAtTime = new Frame<TimeType>(time);
            frames.put(time, frameAtTime);
        }
    }

    public Frame<TimeType> getFrameAt(ITime time) {
        return frames.floorEntry(time).getValue();
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<Frame<TimeType>> iterator() {
        return frames.values().iterator();
    }
}
