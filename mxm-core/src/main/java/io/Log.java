package io;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;

/**
 * This is a class for special logging functionality, essentially just to make logs prettier and more standard.
 */
public abstract class Log {
    ////////////////////////////////////////////////////////////////

    private static final int MAX_TOTAL_WIDTH = 128;
    private static final int MAX_CONTENT_WIDTH = MAX_TOTAL_WIDTH - 16;

    public enum LogLevel { LOW, MEDIUM, HIGH }
    public static final LogLevel LOG_LEVEL = LogLevel.LOW;

    ////////////////////////////////////////////////////////////////

    public static void debug(@NotNull String tag, @NotNull String message) {
        System.out.println(tag + ": " + message);
    }
    public static void debug(@NotNull String tag, @NotNull String message, @NotNull Collection objects) {
        StringBuilder line = new StringBuilder(tag + ": " + message + " ");
        for(Object object : objects) {
            String string = object.toString();
            if(line.length() + string.length()+1 > (MAX_CONTENT_WIDTH - 2)) {
                debug("", line.toString());
                line = new StringBuilder();
            }
            line.append(string).append(" ");
        }
        debug("", line.toString());
    }
    public static void warning(@NotNull String tag, @NotNull String message) {
        System.err.println(tag + ": " + message);
    }
    public static void warning(@NotNull String tag, @NotNull String message, @NotNull Collection objects) {
        StringBuilder line = new StringBuilder(tag + ": " + message + " ");
        for(Object object : objects) {
            String string = object.toString();
            if(line.length() + string.length()+1 > (MAX_CONTENT_WIDTH - 2)) {
                warning("", line.toString());
                line = new StringBuilder();
            }
            line.append(string).append(" ");
        }
        warning("", line.toString());
    }
    public static @NotNull Error error(@NotNull String tag, @NotNull String message) {
        return new Error(tag + ": " + message);
    }





    // Deprecated:

    public static void log(@NotNull String string, int level) {
        System.out.println(fill("|",level) + " "
                            + padRight(string, MAX_TOTAL_WIDTH -2-(level*2))
                            + " " + fill("|",level));
    }


    public static void logStaticInit(@NotNull String tag, @NotNull Collection objects, @NotNull long nsElapsed) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        if(LOG_LEVEL == LogLevel.HIGH) {
            debug(tag,"initialization",objects);
            debug(tag,"time elapsed: " + formatter.format(nsElapsed) + " ns");
        }
        else if(LOG_LEVEL == LogLevel.MEDIUM || LOG_LEVEL == LogLevel.LOW) {
            debug(tag,"initialization");
            debug(tag,"time elapsed: " + formatter.format(nsElapsed) + " ns");
        }
    }

    ////////////////////////////////////////////////////////////////

    private static void logHeader(@NotNull String headerTitle) {
        logBarWithEnds();
        log(headerTitle, 1);
        logBarWithEnds();
    }
    private static void logBarWithEnds() {
        System.out.println("+" + fill("-", MAX_TOTAL_WIDTH -2)+ "+");
    }
    private static void logBar() {
        System.out.println(fill("-", MAX_TOTAL_WIDTH));
    }
    private static void logEmpty() {
        System.out.println("");
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