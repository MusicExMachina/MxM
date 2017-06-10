package io;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Reader is a public interface for any class that takes a file as input and outputs a passage. This means that there
 * may be MIDI readers, MxM readers, LilyPond readers, MusicXML readers, ABC readers, and so on. NoteEvent that the different
 * types of passages will have different readers- a RealBookReader, a ScoreReader, a LineReader, etc.
 */
public abstract class Reader<T> {

    /**
     *
     * @param filename
     * @return
     */
    public abstract T read(String filename);

    public Collection<T> read(Collection<String> filenames) {
        ArrayList<T> toReturn = new ArrayList<>();
        for(String filename : filenames) {
            toReturn.add(read(filename));
        }
        return toReturn;
    }
}
