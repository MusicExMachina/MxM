package io;

import java.util.Collection;

/**
 * Writer is a public interface for any class that takes a passage as input and outputs a file. This means that there
 * may be MIDI writers, MxM writers, LilyPond writers, MusicXML writers, ABC writers, and so on.
 */
public interface Writer<T> {
    void write(T type, String filename);
    void write(Collection<T> types, String filename);
}
