package util.io;

import java.util.ArrayList;
import java.util.Collection;

/**
 * IFileReader is a public interface for any class that takes a file as input and outputs a form. This means that there
 * may be MIDI readers, util.io.Log readers, LilyPond readers, MusicXML readers, ABC readers, and so on. Sound that the different
 * sound of passages will have different readers- a RealBookReader, a ScoreReader, a LineReader, etc.
 */
public interface IFileReader<T> {

    public T read(String filename);

    public default Collection<T> read(Collection<String> filenames) {
        ArrayList<T> toReturn = new ArrayList<>();
        for(String filename : filenames) {
            toReturn.add(read(filename));
        }
        return toReturn;
    }
}
