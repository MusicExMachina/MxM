package io;

import form.Passage;
import form.Score;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Reader is a public interface for any class that takes a file as input and outputs a passage. This means that there
 * may be MIDI readers, MxM readers, LilyPond readers, MusicXML readers, ABC readers, and so on. Note that the different
 * types of passages will have different readers- a RealBookReader, a ScoreReader, a LineReader, etc.
 */
public interface Reader<T extends Passage> {
    static Score read(String filename) {
        return null;
    }
    static Collection<Score> read(Collection<String> filenames) {
        ArrayList<Score> passages = new ArrayList<>();
        for(String filename : filenames) {
            passages.add(read(filename));
        }
        return passages;
    }
}
