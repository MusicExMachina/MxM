package passage;

import com.sun.istack.internal.NotNull;
import events.IMusicEvent;
import base.time.Time;
import events.SpanningEvent;

import java.util.*;

// mutable
class Timeline <MusicEventType extends IMusicEvent> implements Iterable<Frame> {

    private final Timeline parent;
    private final TreeMap<Time,Frame> frames;

    Timeline() {
        this.parent = null;
        this.frames = new TreeMap<>();
    }

    Timeline(@NotNull Timeline parent) {
        this.parent = parent;
        this.frames = new TreeMap<>();
    }



    private Music add() {

    }









    private void put(@NotNull Frame frame) {
        frames.put(frame.getTime(),frame);
        for(Timeline timeline : children) {
            timeline.put(frame);
        }
    }

    private @NotNull Frame getFrameAtOrAdd(@NotNull Time time) {
        if(frames.containsKey(time)) {
            return frames.get(time);
        }
        else {
            Frame frame = new Frame(time);
            put(frame);
            return frame;
        }
    }


    // PUBLIC GETTERS
    public @NotNull Frame getFrameAt(Time time) {
        return frames.get(time);
    }
    public @NotNull Frame getFrameBefore(Time time) {
        return frames.floorEntry(time).getValue();
    }
    public @NotNull Frame getFrameAfter(Time time) {
        return frames.ceilingEntry(time).getValue();
    }
    public @NotNull Frame getFirstFrame() { return frames.firstEntry().getValue(); }
    public @NotNull Frame getLastFrame() { return frames.lastEntry().getValue(); }

    @Override
    public Iterator<Frame> iterator() {
        Collection constValues = java.util.Collections.unmodifiableCollection(frames.values());
        return constValues.iterator();
    }
}