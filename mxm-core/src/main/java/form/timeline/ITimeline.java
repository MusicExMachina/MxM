package form.timeline;

import form.ITimed;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import properties.time.ITime;
import properties.time.Time;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Stream;

/**
 * We don't want classes outside this package instantiating timeline, but we want
 * a common public interface. Thus, the classes are private, but their corresponding
 * interfaces are public
 */
public interface ITimeline <TimedType extends ITimed> extends Iterable<TimedType> {
    default @NotNull ITime getStart() {
        if(getFirst() != null) return getFirst().getTime();
        return Time.ZERO;
    }
    default @NotNull ITime getEnd() {
        if(getLast() != null) return getLast().getTime();
        return Time.ZERO;
    }
    default @NotNull ITime getLength() {
        return getEnd().minus(getStart());
    }

    @Nullable TimedType getFirst();
    @Nullable TimedType getLast();
    @Nullable TimedType getAt(@NotNull ITime time);
    @Nullable TimedType getBefore(@NotNull ITime time);
    @Nullable TimedType getAfter(@NotNull ITime time);

    @NotNull Stream<TimedType> stream();
    @NotNull Stream<TimedType> parallelStream();
    @NotNull Iterator<TimedType> iterator();
    @NotNull Spliterator<TimedType> spliterator();
}
