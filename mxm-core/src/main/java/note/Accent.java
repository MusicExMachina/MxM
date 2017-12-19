package note;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Accent {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private static final HashMap<String,Accent> ALL = new HashMap<>();

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static Accent of(@NotNull String name) {
        name = name.toLowerCase();
        if (ALL.containsKey(name)) {
            return ALL.get(name);
        }
        Accent newAccent = new Accent(name);
        ALL.put(name, newAccent);
        return newAccent;
    }

    public static @NotNull Collection<Accent> all() {
        return Collections.unmodifiableCollection(ALL.values());
    }

    public static @NotNull Accent random() {
        return of(new ArrayList<>(ALL.keySet()).get(ThreadLocalRandom.current().nextInt(ALL.size())));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final String name;

    private Accent(@NotNull String name) {
        this.name = name;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}
