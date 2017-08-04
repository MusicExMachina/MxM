package form;

import events.MusicEvent;
import events.Note;
import events.TempoChange;
import events.TimeSigChange;
import time.*;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Parts represent passages played by a single performer or a number of performers in the same section. To put it
 * another way, Parts are identical to "parts" in a musical context- there can be a first trumpet part, cello part,
 * drum set part, so on and so forth. As a type of IPassage, they can be iterated over for their constituent events.
 */
public class Part implements IPassage {
    private Piece piece;

    /** The voice that plays this part */
    private Voice voice;



    private TreeMap<Beat, MusicEvent> allEvents;
    private TreeMap<Beat, Note> allNotes;







    public Part(Voice voice, IPassage passage) {

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
    public Tempo getTempoAt(ITime time) {
        return null;
    }

    @Override
    public TimeSig getTimeSigAt(ITime time) {
        return null;
    }

    @Override
    public TreeSet<Note> getNotesAt(ITime time) {
        return null;
    }

    @Override
    public IPassage getExcerpt(Count start) {
        return null;
    }

    @Override
    public IPassage getExcerpt(Count start, Count end) {
        return null;
    }

    @Override
    public Iterator<TempoChange> tempoChangeItr() {
        return piece.tempoChangeItr();
    }

    @Override
    public Iterator<TimeSigChange> timeSigChangeItr() {
        return piece.timeSigChangeItr();
    }
}