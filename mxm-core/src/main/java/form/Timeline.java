package form;

import events.IMusicEvent;
import time.ITime;

import java.util.Iterator;
import java.util.TreeMap;

public interface Timeline <TimeType extends ITime, MusicEventType extends IMusicEvent<TimeType>> extends Iterable<IFrame<TimeType>> {

    public void add(MusicEventType event);

    public IFrame<TimeType> getFrameAt(ITime time);

    public ITime getStart();
    public ITime getEnd();
    public ITime getDuration();

    @Override
    public Iterator<IFrame<TimeType>> iterator();
}

class SerialTimeline <TimeType extends ITime, MusicEventType extends IMusicEvent<TimeType>> implements Timeline<TimeType,MusicEventType> {

    /** The frames of */
    private final TreeMap<TimeType, MonoFrame<TimeType,MusicEventType>> frames;

    public SerialTimeline() {
        this.frames = new TreeMap<>();
    }

    @Override
    public void add(IMusicEvent event) {

    }

    public MusicEventType getEventAt(ITime time) {
        return null;
    }

    @Override
    public IFrame<TimeType> getFrameAt(ITime time) {
        return null;
    }



    /*
    {

        if(frames.containsKey(time)) {
            return frames.get(time);
        }
        else {
            IFrame<MusicEventType> IFrameAtTime = new IFrame<MusicEventType>(time);
            frames.put(time, IFrameAtTime);
        }
    }
    */

    @Override
    public ITime getStart() {
        return null;
    }
    @Override
    public ITime getEnd() {
        return null;
    }
    @Override
    public ITime getDuration() {
        return getEnd().minus(getStart());
    }
    @Override
    public Iterator<IFrame<TimeType>> iterator() {
        return null;
    }
}

class ParallelTimeline <TimeType extends ITime, MusicEventType extends IMusicEvent<TimeType>> implements Timeline<TimeType,MusicEventType> {

    /** The frames of */
    private final TreeMap<TimeType, PolyFrame<TimeType,MusicEventType>> frames;

    public ParallelTimeline() {
        this.frames = new TreeMap<>();
    }


    public void add(MusicEventType event) {

    }

    @Override
    public IFrame<TimeType> getFrameAt(ITime time) {
        return null;
    }


    public Iterator<MusicEventType> getEventsAt(ITime time) {
        return null;
    }
    @Override
    public ITime getStart() {
        return null;
    }
    @Override
    public ITime getEnd() {
        return null;
    }
    @Override
    public ITime getDuration() {
        return getEnd().minus(getStart());
    }
    @Override
    public Iterator<IFrame<TimeType>> iterator() {
        return null;
    }
}
