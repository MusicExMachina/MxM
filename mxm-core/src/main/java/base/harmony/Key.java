package base.harmony;

import base.harmony.Harmony;
import base.pitch.PitchClass;
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

    /** The tonic of this key, generally considered its most important pitch class */
    private PitchClass tonic;
    /** The type of key this is, i.e. major or minor */
    /** All keys suggest an underlying harmony of constituent pitch classes */
    private Harmony harmony;

    /**
     * A getter for the tonic (i.e. fundamental pitch class) of this key
     * @return the tonic of this key
     */
    public @NotNull PitchClass getTonic() {
        return tonic;
    }
    /**
     * Gets the implicit harmony (set of pitch classes) in this key
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
