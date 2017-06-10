package io;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Writer is a public interface for any class that takes a passage as input and outputs a file. This means that there
 * may be MIDI writers, MxM writers, LilyPond writers, MusicXML writers, ABC writers, and so on.
 */
public abstract class Writer<T> {

    /**
     *
     * @param type
     * @param filename
     */
    public abstract void write(T type, String filename);

    /**
     * Writes a number of classes to a
     * @param types
     * @param filename
     */
    public void write(Collection<T> types, String filename) {
        ArrayList<T> toReturn = new ArrayList<>();
        int index = 0;
        for(T type : types) {
            write(type,filename + "_" + index++);
        }
    }

}
