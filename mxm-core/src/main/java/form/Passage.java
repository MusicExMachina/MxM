package form;

import events.TempoEvent;
import sound.Instrument;

import java.util.*;

/**
 * Passages are a section of organized music over a set amount of time, best
 * Passages can be cut exerpted to form other, smaller Passages. For instance, out of a large Passage, like the Adagio
 * movement of Dvorak's Ninth Symphony, one might
 */
public class Passage implements Iterable<Measure> {

    ArrayList<Measure> measures;

    public Measure addMeasure() {
        Measure newMeasure = new Measure();

    }


    protected Set<Part> allParts;
    protected HashMap<Instrument,Integer> sectionSize;
    protected HashMap<Instrument,List<Part>> partsBySection;

    public Iterator<Part> partIterator() { return null; }
    public Iterator<Instrument> sectionIterator() { return null; }

    public Iterator<TempoEvent> tempoChangeIterator() { return null; }


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
    public Iterator<Measure> iterator() {
        return null;
    }
}
