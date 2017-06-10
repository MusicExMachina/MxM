package events;

/**
 * MeasureEvents are MusicEvents that can only take place on barlines. For instance, TimeSignChangeEvents, by their
 * nature, can only take place on barlines (that is, Count 0/0 of a measure).
 */
public class BarlineEvent {

    /**
     * PartEvents are not universal to a Score, but rather applied to only a single Part. For instance, a typical PartEvent
     * is a Note, which cannot be added directly to a Score, but rather to a single Part in a Score (what would a note
     * just written on a score, assigned to no performer, mean?)
     */
    public static interface PartEvent extends MusicEvent {

    }
}
