package form.exceptions;

/**
 * <p> <b>Exception Overview:</b>
 * A special type of {@link Exception} that occurs when trying to add a </p>
 *
 * @author Patrick Celentano
 */
public class SerialTimelineOverlapException extends Exception {
    /**
     * A basic constructor, that uses {@link Exception}'s
     */
    public SerialTimelineOverlapException() {
        super("Cannot add one event on top of another in a Serial Timeline!\"");
    }
}
