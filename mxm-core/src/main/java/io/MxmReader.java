package io;

import form.Passage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;

/**
 * Created by celenp on 3/27/2017.
 */
public class MxmReader implements Reader<Passage> {
    public Passage read(String filename) {
        try {
            FileReader reader = new FileReader(filename);

            //reader.

            //for(String line : reader.)

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Passage> read(Collection<String> filenames) {
        return null;
    }
}
