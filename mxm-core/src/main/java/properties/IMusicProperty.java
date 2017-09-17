package properties;

import org.jetbrains.annotations.NotNull;

/**
 * <p> <b>Interface Overview:</b>
 * Music properties are self-contained, atomic musical descriptors which may be applied to musical events, timeline,
 * and so forth.</p>
 *
 * <p> <b>Design Details:</b>
 * All music properties must implement a static get() method instead of a constructor, and remain immutable. While this
 * cannot be enforced by inheritance, such a design makes logical sense for all music properties.</p>
 *
 * @author Patrick Celentano
 */
public interface IMusicProperty {
    /**
     * All music properties must override {@link Object}.toString().
     * @return a string representation of this music property
     */
    @Override
    @NotNull String toString();
    /**
     * All music properties must override {@link Object}.hashCode().
     * @return The hash code for this music property
     */
    @Override
    int hashCode();
}
