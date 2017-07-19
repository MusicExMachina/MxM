package form;

import events.Note;
import events.TempoChange;
import events.TimeSigChange;
import sound.Chord;
import time.Count;
import time.Frame;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by celenp on 7/10/2017.
 */
public class LeadSheet implements IPassage {

    private TreeMap<Count, Sound> allNotes;
    private TreeMap<Count, Frame> melodyNotes;
    private TreeMap<Count, Chord> changesNotes;

    @Override
    public IPassage getExcerpt(Count start, Count end) {
        return null;
    }

    @Override
    public Count getStart() {
        return null;
    }

    @Override
    public Count getEnd() {
        return null;
    }

    @Override
    public Count getDuration() {
        return null;
    }

    @Override
    public Iterator<Note> soundItr() {
        return null;
    }

    @Override
    public Iterator<TimeSigChange> timeSigChangeItr() {
        return null;
    }

    @Override
    public Iterator<TempoChange> tempoChangeItr() {
        return null;
    }
}
