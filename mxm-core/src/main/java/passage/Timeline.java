package passage;

import com.sun.istack.internal.NotNull;
import events.IMusicEvent;
import base.time.Time;

import java.util.*;

// mutable
abstract class Timeline <MusicEventType extends IMusicEvent> implements Iterable<Frame> {

    private final TreeMap<Time,Frame> frames;

    private final Timeline parent;
    private final List<Timeline> children;

    Timeline() {
        this.frames = new TreeMap<>();
        this.parent = null;
        this.children = new ArrayList<>();
    }

    Timeline(@NotNull Timeline parent) {
        this.frames = new TreeMap<>();
        this.parent = parent;
        this.children = new ArrayList<>();
        parent.attach(this);
    }

    private void put(@NotNull Frame frame) {
        frames.put(frame.getTime(),frame);
        for(Timeline timeline : children) {
            timeline.put(frame);
        }
    }
    private void attach(@NotNull Timeline timeline) {
        children.add(timeline);
    }

    @NotNull Frame getFrameAtOrAdd(@NotNull Time time) {
        if(frames.containsKey(time)) {
            return frames.get(time);
        }
        else {
            Frame frame = new Frame(time);
            put(frame);
            return frame;
        }
    }

    public @NotNull Time getStart() { return frames.firstKey(); }
    public @NotNull Time getEnd() { return frames.lastKey(); }
    public @NotNull Time getDuration() { return getStart().minus(getEnd()); }

    public @NotNull Frame getFrameBefore(Time time) {
        return frames.floorEntry(time).getValue();
    }
    public @NotNull Frame getFrameAfter(Time time) {
        return frames.ceilingEntry(time).getValue();
    }

    @Override
    public Iterator<Frame> iterator() {
        Collection constValues = java.util.Collections.unmodifiableCollection(frames.values());
        return constValues.iterator();
    }
}
