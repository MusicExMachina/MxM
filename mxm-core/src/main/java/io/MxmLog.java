package io;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;

/**
 * This is a class for special logging functionality, essentially just to make logs prettier and more standard.
 */
public abstract class MxmLog {
    ////////////////////////////////////////////////////////////////

    private static final int MAX_TOTAL_WIDTH = 128;
    private static final int MAX_CONTENT_WIDTH = MAX_TOTAL_WIDTH - 16;

    public enum LogLevel { LOW, MEDIUM, HIGH }
    public static final LogLevel LOG_LEVEL = LogLevel.LOW;

    ////////////////////////////////////////////////////////////////

    public static void log(@NotNull String string, int level) {
        System.out.println(fill("|",level) + " "
                            + padRight(string, MAX_TOTAL_WIDTH -2-(level*2))
                            + " " + fill("|",level));
    }

    public static void log(@NotNull Collection objects, int level) {
        String line = "";
        for(Object object : objects) {
            String string = object.toString();
            if(line.length() + string.length()+1 > (MAX_CONTENT_WIDTH - level*2 - 2)) {
                log(line, level);
                line = "";
            }
            line += string + " ";
        }
        log(line, level);
    }

    public static void logStaticInit(@NotNull String title, @NotNull Collection objects, @NotNull long nsElapsed) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        if(LOG_LEVEL == LogLevel.HIGH) {
            logHeader(title);
            log("Initializing the following: ", 1);
            log(objects, 1);
            log("Inititalization complete", 1);
            log("Time elapsed: " + formatter.format(nsElapsed) + " ns", 1);
            logBarWithEnds();
            logEmpty();
        }
        else if(LOG_LEVEL == LogLevel.MEDIUM) {
            logHeader(title);
            log("Time elapsed: " + formatter.format(nsElapsed) + " ns", 1);
            logBarWithEnds();
            logEmpty();
        }
        else if(LOG_LEVEL == LogLevel.LOW) {
            logBarWithEnds();
            log(title + " initialized. Time elapsed: " + formatter.format(nsElapsed) + " ns.", 1);
            logBarWithEnds();
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