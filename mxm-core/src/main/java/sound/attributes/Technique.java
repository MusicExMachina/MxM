package sound.attributes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Technique {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static Technique DEFAULT;
    public static Technique PIZZICATO;
    public static Technique STOP_MUTE;
    public static Technique HARMON_MUTE;

    public static Technique HIT;
    public static Technique ROLL;

    private static final HashMap<String,Technique> ALL = new HashMap<>();

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static Technique of(@NotNull String name) {
        name = name.toLowerCase();
        if (ALL.containsKey(name)) {
            return ALL.get(name);
        }
        Technique newTechnique = new Technique(name);
        ALL.put(name, newTechnique);
        return newTechnique;
    }

    public static @NotNull Collection<Technique> all() {
        return Collections.unmodifiableCollection(ALL.values());
    }

    public static @NotNull Technique random() {
        return of(new ArrayList<>(ALL.keySet()).get(ThreadLocalRandom.current().nextInt(ALL.size())));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final String name;

    private Technique(@NotNull String name) {
        this.name = name;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}
