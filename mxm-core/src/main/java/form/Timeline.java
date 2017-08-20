package form;

import com.sun.istack.internal.NotNull;
import events.IMusicEvent;
import time.Time;

import java.util.*;

// mutable
class Timeline <MusicEventType extends IMusicEvent> implements Iterable<Frame> {

    private Time start;
    private Time end;
    private Time duration;

    private final TreeMap<Time,Frame> frames;
    private final List<Timeline> children;

    Timeline() {
        this.frames = new TreeMap<>();
        this.children = new ArrayList<>();
    }

    Timeline(@NotNull Timeline master) {
        this.frames = new TreeMap<>();
        this.children = new ArrayList<>();
        master.attach(this);
    }

    private void put(@NotNull Frame frame) {
        frames.put(frame.getTiming(), frame);
    }
    private void attach(@NotNull Timeline timeline) {
        children.add(timeline);
    }

    public @NotNull Frame getFrameAtOrAdd(@NotNull Time time) {
        if(frames.containsKey(time)) {
            return frames.get(time);
        }
        else {
            Frame frame = new Frame(time);
            frames.put(time,frame);
            for(Timeline timeline : children) {
                timeline.put(frame);
            }
            return frame;
        }
    }

    public @NotNull Time getStart() { return start; }
    public @NotNull Time getEnd() { return end; }
    public @NotNull Time getDuration() { return duration; }

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
