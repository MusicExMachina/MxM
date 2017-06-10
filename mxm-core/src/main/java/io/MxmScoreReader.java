package io;

import form.Score;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by celenp on 3/27/2017.
 */
public class MxmScoreReader extends Reader<Score> {
    public Score read(String filename) {
        try {
            FileReader reader = new FileReader(filename);

            //reader.

            //for(String line : reader.)

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
