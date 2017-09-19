package form;

import org.jetbrains.annotations.NotNull;

/**
 * <p> <b>Interface Overview:</b>
 * The {@link ITitled} interface represents any object which is named, such as a score. Pretty simple stuff. </p>
 *
 * @author Patrick Celentano
 */
public interface ITitled {
    /**
     * Gets the title of this work
     * @return the title of this work
     */
    @NotNull String getTitle();
}
