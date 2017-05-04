package io;

import form.Passage;
import java.util.Collection;

/**
 * Writer is a public interface for any class that takes a passage as input and outputs a file. This means that there
 * may be MIDI writers, MxM writers, LilyPond writers, MusicXML writers, ABC writers, and so on.
 */
public interface Writer {
    static void write(Passage passage, String filename) {

    }

    static void write(Collection<Passage> passages, String filenamePrefix) {
        int counter = 0;
        for(Passage passage : passages) {
            write(passage,filenamePrefix + "_" + counter);
            counter++;
        }
    }
}
