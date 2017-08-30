package io;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public abstract class MxmLog {
    private static int MAX_WIDTH = 64;

    public static void log(@NotNull String string, int level) {
        System.out.println(fill("|",level) + " "
                + padRight(string,MAX_WIDTH-2-(level*2))
                + " " + fill("|",level));
    }
    public static void log(@NotNull Collection<String> strings, int level) {
        String line = "";
        for(String string : strings) {
            if(line.length() + string.length()+1 > (MAX_WIDTH - level*2 - 2)) {
                log(line, level);
                line = "";
            }
            line += string + " ";
        }
        log(line, level);
    }
    public static void logHeader(@NotNull String headerTitle) {
        logBar();
        log(headerTitle, 1);
        logBar();
    }
    public static void logFooter() {
        logBar();
    }
    private static void logBar() {
        System.out.println(fill("-",MAX_WIDTH));
    }
    private static @NotNull String fill(@NotNull String string, int size) {
        return String.join("", Collections.nCopies(size, string));
    }
    private static @NotNull String padRight(@NotNull String string, int size) {
        return String.format("%1$-" + size + "s", string);
    }
    private static @NotNull String padLeft(@NotNull String string, int size) {
        return String.format("%1$" + size + "s", string);
    }
}