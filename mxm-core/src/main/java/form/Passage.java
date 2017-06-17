package form;

import events.MusicEvent;
import events.Note;
import events.TempoChange;
import events.TimeSigChange;
import sound.Instrument;
import time.Count;

import java.util.*;

/**
 * Passages are a section of organized music over a set amount of time, best
 * Passages can be cut exerpted to form other, smaller Passages. For instance, out of a large Passage, like the Adagio
 * movement of Dvorak's Ninth Symphony, one might
 */
public class Passage implements Iterable<Part> {

    private static int PICKUP_MEASURE_NUM = 0;
    private static int START_MEASURE_NUM = 1;

    private Score excerptOf;
    private TreeMap<Count,MusicEvent> events;
    private TreeMap<Count,TempoChange> tempoChanges;
    private TreeMap<Count, TimeSigChange> timeSigChanges;

    private HashSet<Part> allParts;
    private TreeMap<Instrument,ArrayList<Part>> partsBySection;
    private HashMap<Part,TreeMap<Count, Note>> notesByPart;

    public Passage(Score score) {
        this.excerptOf          = score;
        this.events             = new TreeMap<>();
        this.tempoChanges       = new TreeMap<>();
        this.timeSigChanges     = new TreeMap<>();
        this.allParts           = new HashSet<>();
        this.partsBySection     = new TreeMap<>();
        this.notesByPart        = new HashMap<>();
    }

    public Iterator<Part> partItr() { return allParts.iterator(); }

    public Iterator<Instrument> sectionItr() { return partsBySection.keySet().iterator(); }

    public Iterator<TempoChange> tempoChangeIterator() { return tempoChanges.values().iterator(); }

    public Iterator<TimeSigChange> timeSigChangeItr() { return timeSigChanges.values().iterator(); }

    @Override
    public Iterator<Part> iterator() {
        return null;
    }







/*
    public Passage excerpt(int startMeasure, int endMeasure) {

    }

    public Passage excerpt(Instrument instrument) {

    }

    public Passage excerpt(Instrument instrument) {

    }







    public String toString() {
        return title + " by " + composer + " full score";
    }

    @Override
    public Iterator<MusicEvent> iterator() {
        return null;
    }
    */
}
