package theory.composite;

import org.jetbrains.annotations.NotNull;
import properties.sound.ISound;
import properties.sound.Noise;

import java.util.Arrays;
import java.util.Collection;

public class Timbre implements ISound {

    public static @NotNull Timbre get(@NotNull Noise... noises) {
        return new Timbre();
    }
    public static @NotNull Timbre get(@NotNull Collection<Noise> noises) {
        return new Timbre();
    }
}
