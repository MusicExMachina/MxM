package util.timeline;

import form.events.InstantEvent;
import form.events.SpanningEvent;
import form.events.IEvent;
import form.ITimed;
import org.jetbrains.annotations.NotNull;
import form.time.Time;

import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

/**
 * <p> <b>Interface Overview:</b>
 * This is the public interface for a frame- a vertical slice of music form.events which occur simultaneously. Sound that
 * users may not add directly to a frame, but rather, access such functionality through timeline (which themselves
 * are accessed through passages). This may seem like a lot of indirection, but it is crucially useful in preventing
 * mishaps by limiting access.</p>
 *
 * @param <MusicEventType> the type of music event that may be found in this frame
 */
public class Frame<MusicEventType extends IEvent> implements ITimed {

        //////////////////////////////
        // Member variables         //
        //////////////////////////////

        /** The form.time at which this frame occurs */
        private final Time time;
        /** All form.events which start exactly on this frame */
        private final TreeSet<MusicEventType> startedEvents;
        /** All form.events which start or continue through this frame */
        private final TreeSet<MusicEventType> ongoingEvents;
        /** All form.events which continue through this frame */
        private final TreeSet<MusicEventType> continuedEvents;
        /** All form.events which end exactly on this frame */
        private final TreeSet<MusicEventType> endedEvents;

        //////////////////////////////
        // Member methods           //
        //////////////////////////////

        /**
         * A limited-access constructor which takes in a form.time, and initializes the various event-holding collections
         * @param time the form.time at which this event occurs
         */
        Frame(@NotNull Time time) {
            this.time = time;
            this.startedEvents = new TreeSet<>();
            this.ongoingEvents = new TreeSet<>();
            this.continuedEvents = new TreeSet<>();
            this.endedEvents = new TreeSet<>();
        }

        // Package private on purpose- we don't want users adding form.events, only score.
        void add(@NotNull MusicEventType event) {
            if(event instanceof InstantEvent) {
                startedEvents.add(event);
                ongoingEvents.add(event);
            }
            else throw new Error("Frame: Cannot add an event of type " + event.getClass());
        }
        // Package private on purpose- we don't want users adding form.events, only score.
        void addStart(@NotNull MusicEventType event) {
            if(event instanceof SpanningEvent) {
                startedEvents.add(event);
                ongoingEvents.add(event);
            }
            else throw new Error("Frame: Cannot add an event of type " + event.getClass());
        }
        // Package private on purpose- we don't want users adding form.events, only score.
        void addContinue(@NotNull MusicEventType event) {
            if(event instanceof SpanningEvent) {
                continuedEvents.add(event);
                ongoingEvents.add(event);
            }
            else throw new Error("Frame: Cannot add an event of type " + event.getClass());
        }
        // Package private on purpose- we don't want users adding form.events, only score.
        void addEnd(@NotNull MusicEventType event) {
            if(event instanceof SpanningEvent) {
                endedEvents.add((MusicEventType)event);
            }
            else throw new Error("Frame: Cannot add an event of type " + event.getClass());
        }

        public final @NotNull Time getTime() {
            return time;
        }

        public final @NotNull Collection<MusicEventType> startedEvents() {
            return Collections.unmodifiableCollection(startedEvents);
        }
        public final @NotNull Collection<MusicEventType> ongoingEvents() {
            return Collections.unmodifiableCollection(ongoingEvents);
        }
        public final @NotNull Collection<MusicEventType> continuedEvents() {
            return Collections.unmodifiableCollection(continuedEvents);
        }
        public final @NotNull Collection<MusicEventType> endedEvents() {
            return Collections.unmodifiableCollection(endedEvents);
        }
    }
