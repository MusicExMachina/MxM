package events;

import time.Count;

import java.util.Iterator;
import java.util.TreeMap;

public interface Timeline <MusicEventType extends IMusicEvent> extends Iterable<IFrame<MusicEventType>> {

    public void add(MusicEventType event);

    public IFrame<MusicEventType> getFrameAt(Count count);

    public Count getStart();
    public Count getEnd();
    public Count getDuration();

    @Override
    public Iterator<IFrame<MusicEventType>> iterator();
}

public class SerialTimeline <MusicEventType extends IMusicEvent> implements Timeline {

    /** The frames of */
    private final TreeMap<Count, MonoFrame<MusicEventType>> frames;

    public SerialTimeline() {
        this.frames = new TreeMap<Count, MonoFrame<MusicEventType>> frames;
    }

    @Override
    public void add(IMusicEvent event) {

    }

    @Override
    public IFrame getFrameAt(Count count) {
        return null;
    }

    @Override
    public Count getStart() {
        return null;
    }

    @Override
    public Count getEnd() {
        return null;
    }

    @Override
    public Count getDuration() {
        return null;
    }

    @Override
    public Iterator<IFrame> iterator() {
        return null;
    }
}

public class ParallelTimeline <MusicEventType extends IMusicEvent> implements Timeline {

    /** The frames of */
    private final TreeMap<Count, IFrame> frames;


    public void add(MusicEventType event) {

    }
    {

        if(frames.containsKey(time)) {
            return frames.get(time);
        }
        else {
            IFrame<MusicEventType> IFrameAtTime = new IFrame<MusicEventType>(time);
            frames.put(time, IFrameAtTime);
        }
    }
}