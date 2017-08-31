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

    private static final int MAX_WIDTH = 64;

    ////////////////////////////////////////////////////////////////

    public static void log(@NotNull String string, int level) {
        System.out.println(fill("|",level) + " "
                            + padRight(string,MAX_WIDTH-2-(level*2))
                            + " " + fill("|",level));
    }

    public static void log(@NotNull Collection objects, int level) {
        String line = "";
        for(Object object : objects) {
            String string = object.toString();
            if(line.length() + string.length()+1 > (MAX_WIDTH - level*2 - 2)) {
                log(line, level);
                line = "";
            }
            line += string + " ";
        }
        log(line, level);
    }

    public static void logInitialization(@NotNull String title, @NotNull Collection objects, @NotNull long nsElapsed) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        logHeader(title);
        log("Initializing the following: ",1);
        log(objects,1);
        log("Inititalization complete",1);
        log("Time elapsed: " + formatter.format(nsElapsed) + " ns",1);
        logBarEnds();
        logEmpty();
    }

    ////////////////////////////////////////////////////////////////

    private static void logHeader(@NotNull String headerTitle) {
        logBarEnds();
        log(headerTitle, 1);
        logBarEnds();
    }
    private static void logBarEnds() {
        System.out.println("+" + fill("-",MAX_WIDTH-2)+ "+");
    }

    private static void logBar() {
        System.out.println(fill("-",MAX_WIDTH));
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