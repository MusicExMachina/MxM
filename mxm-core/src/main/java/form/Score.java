package form;

import base.Count;
import base.Instrument;
import base.TimeSign;
import events.eventTypes.TempoEvent;
import events.eventTypes.TimeSignEvent;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class Score {

    private String name;
    private String composer;

    protected Set<Part> parts;
    protected TreeMap<Instrument,List<Part>> partsBySection;

    public Iterator<Part> partIterator() { return null; }
    public Iterator<Instrument> sectionIterator() { return null; }

    public Iterator<TempoEvent> tempoChangeIterator() { return null; }
    public Iterator<TimeSignEvent> timeSignChangeIterator() { return null; }

    public String getName() {
        return name;
    }

    public String getComposer() {
        return composer;
    }

    public TimeSign getTimeSignatureAt(Count count) {
        return null;
    }
}