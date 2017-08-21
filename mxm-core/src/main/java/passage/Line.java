package passage;

import com.sun.istack.internal.NotNull;
import events.Note;

import java.util.Iterator;

/**
 * A line is a special type of note-part in which there are never two notes played simultaneously. This allows a simple
 * iteration over notes, where each note's start is equal to or greater than the ending of the last note.
 */
public class Line extends Part<Note> {


    public @NotNull Iterator<Note> noteItr() {

    }
}
