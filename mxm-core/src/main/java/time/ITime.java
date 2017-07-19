package time;

import java.util.Comparator;

/**
 * The time interface represents musical time in a totally abstract sense. This exists- in essence- only to allow for
 * the coexistence of measures and counts- that is, timing can either be in full measures, or counts, which are like a
 * fractional variety of measures. This may seem unnecessary, but it allows for events like time signature changes to
 * take place ONLY on barlines.
 */
public interface ITime extends Comparable<ITime>, Comparator<ITime> {
    // No methods other than those inherited from Comparable and Comparator
}
