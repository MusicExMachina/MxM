package form;

import base.Count;
import base.Tempo;
import base.TimeSignature;

import java.util.Iterator;

/**
 * Created by celenp on 5/13/2017.
 */
public class Passage implements Iterable<Note> {


    public TimeSignature getTimeSignature(int measure) {
        return null;
    }

    public TimeSignature getTimeSignature(Count time) {
        return getTimeSignature(time.getMeasure());
    }

    public Tempo getTempo(Count time) {
        return null;
    }

    public Count getLength() {
        return null;
    }









    @Override
    public Iterator<Note> iterator() {
        return null;
    }
}
