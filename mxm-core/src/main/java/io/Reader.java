package io;

import form.Passage;
import java.util.Collection;

/**
 * Reader is a public interface for any class that takes a file as input and outputs a passage. This means that there
 * may be MIDI readers, MxM readers, LilyPond readers, MusicXML readers, ABC readers, and so on.
 */
public interface Reader {
    Passage read(String filename);
    Collection<Passage> read(Collection<String> filenames);
}
