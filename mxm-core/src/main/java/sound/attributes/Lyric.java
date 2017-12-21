package sound.attributes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Lyric {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private static final HashMap<String,Lyric> ALL = new HashMap<>();

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static Lyric of(@NotNull String name) {
        name = name.toLowerCase();
        if (ALL.containsKey(name)) {
            return ALL.get(name);
        }
        Lyric newLyric = new Lyric(name);
        ALL.put(name, newLyric);
        return newLyric;
    }

    public static @NotNull Collection<Lyric> all() {
        return Collections.unmodifiableCollection(ALL.values());
    }

    public static @NotNull Lyric random() {
        return of(new ArrayList<>(ALL.keySet()).get(ThreadLocalRandom.current().nextInt(ALL.size())));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final String name;

    private Lyric(@NotNull String name) {
        this.name = name;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}
