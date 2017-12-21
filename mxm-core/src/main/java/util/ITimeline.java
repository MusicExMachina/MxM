package util;

import form.ITimed;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import time.Duration;
import time.Measure;
import time.Time;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Stream;

/**
 * We don't want classes outside this package instantiating timeline, but we want
 * a common public interface. Thus, the classes are private, but their corresponding
 * interfaces are public
 */
public interface ITimeline <TimedType extends ITimed> extends Iterable<TimedType> {
    default @NotNull Time getStart() {
        if(getFirst() != null) return getFirst().getTime();
        return Time.of(Measure.ONE);
    }
    default @NotNull Time getEnd() {
        if(getLast() != null) return getLast().getTime();
        return Time.of(Measure.ONE);
    }
    default @NotNull Duration getLength() {
        return getEnd().minus(getStart());
    }

    @Nullable TimedType getFirst();
    @Nullable TimedType getLast();
    @Nullable TimedType getAt(@NotNull Time time);
    @Nullable TimedType getBefore(@NotNull Time time);
    @Nullable TimedType getAfter(@NotNull Time time);

    @NotNull Stream<TimedType> stream();
    @NotNull Stream<TimedType> parallelStream();
    @NotNull Iterator<TimedType> iterator();
    @NotNull Spliterator<TimedType> spliterator();
}
