package theory.harmony;

import properties.sound.PitchClass;
import org.jetbrains.annotations.NotNull;

/**
 * Created by celenp on 5/13/2017.
 *
 *
 * There is no inherent ordering to pitches in a key... that's called a scale.
 *
 * @author Patrick Celentano
 */
public class Key {

    /** The tonic of this key, generally considered its most important sound class */
    private PitchClass tonic;
    /** The type of key this is, i.e. major or minor */
    /** All keys suggest an underlying harmony of constituent sound classes */
    private Harmony harmony;

    /**
     * A getter for the tonic (i.e. fundamental sound class) of this key
     * @return the tonic of this key
     */
    public @NotNull PitchClass getTonic() {
        return tonic;
    }
    /**
     * Gets the implicit harmony (set of sound classes) in this key
     * @return the harmony implied by this key
     */
    public final @NotNull Harmony getHarmony() {
        return harmony;
    }

    /*
    public @NotNull Chord getFunction(@NotNull Function function) {

    }
    */
}
