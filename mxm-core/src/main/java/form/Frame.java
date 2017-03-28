package form;

import base.Count;
import base.Pitch;

import java.util.TreeMap;

/**
 * Created by celenp on 3/22/2017.
 */
public class Frame {
    Count time;
    TreeMap<Pitch,Note> notes;

    /**
     * A noteQualities form.Note constructor utilizing the
     * default Technique and Articulation.
     * @param pitch The base.Pitch of this form.Note.
     */
    public Frame(Count time) {
        this.time = time;
        this.notes = new TreeMap<>();
    }

    public void add(Note note) {
        notes.put(note.getPitch(),note);
    }
}
