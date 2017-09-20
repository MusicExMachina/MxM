package form.score;

import form.ITitled;
import form.IPassage;
import form.part.IPart;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * <p> <b>Interface Overview:</b>
 * Parts are passages to be played by a single player- be that a pianist, a horn player, a percussionist, or vocalist.
 * What is important about a part is that the instrument never switches, and that the score to which it is attached
 * remains its "master track" for all timing information, such as time signature or tempo changes.</p>
 *
 * <p> <b>Design Details:</b>
 * This interface stands as an <b>unmodifiable but mutable</b> outer interface for a series of classes which are both
 * modifiable and mutable. This allows for MxM to control access to such classes, which are often delicate in nature
 * and should not be modified by outside parties.</p>
 *
 * @author Patrick Celentano
 */
public interface IScore extends IPassage, ITitled {
    /**
     * Getter for an unmodifiable collection of all parts in this score
     * @return an unmodifiable collection of all parts in this score
     */
    @NotNull Collection<IPart> getParts();
}
