package properties;

import org.jetbrains.annotations.NotNull;

/**
 *
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
