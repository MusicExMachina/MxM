package sound.unpitched;

import org.jetbrains.annotations.NotNull;
import sound.ISound;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class Noise implements ISound {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static final Noise HIT = of("hit");
    public static final Noise CRASH = of("crash");

    private static final HashMap<String,Noise> ALL = new HashMap<>();

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static Noise of(@NotNull String name) {
        // Standardize
        name = name.toLowerCase();
        // If the noise already exists, return that one
        if (ALL.containsKey(name)) {
            return ALL.get(name);
        }
        // If the noise has not already been created, create it now
        Noise newNoise = new Noise(name);
        ALL.put(name,newNoise);
        return newNoise;
    }

    public static @NotNull Collection<Noise> all() {
        return Collections.unmodifiableCollection(ALL.values());
    }

    public static @NotNull Noise random() {
        return of(new ArrayList<>(ALL.keySet()).get(ThreadLocalRandom.current().nextInt(ALL.size())));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final String name;

    private Noise(String name) {
        this.name = name;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public final @NotNull String getName() {
        return name;
    }

    @Override
    public final boolean equals(Object object) {
        return this == object;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}