package form;

import events.*;
import sound.Instrument;
import time.*;

import java.util.*;

public class Score implements IPassage {

    private String title;

    Timeline<Count> allEvents;

    // Timing information
    Timeline<Measure> timeSignChanges;
    Timeline<Count> tempoChanges;
    // stylistic information

    Timeline<Count> allNotes;

    TreeMap<Voice,Part> parts;
    TreeMap<Instrument,Set<Voice>> sections;


    public Score() {
        parts = new TreeMap<>();
    }

    public Score(Score other) {
        // We must copy everything that is not immutable and doesn't rely on knowing its passage. This includes, for
        // instance,

    }

    public Score excerpt() {

    }

    public Iterator<Voice> allVoices() {
        return parts.keySet().iterator();
    }

    public Iterator<Voice> allParts() {
        return parts.keySet().iterator();
    }





    public Part getPart(Voice voice) {
        return parts.get(voice);
    }

    public List<Part> getParts(Collection<Voice> voices) {
        List<Part> toReturn = new ArrayList<>();
        for(Voice voice : voices) {
            toReturn.add(parts.get(voice));
        }
        return toReturn;
    }

    public List<Part> getParts(Instrument section) {
        List<Part> toReturn = new ArrayList<>();
        for(Voice voice : sections.get(section)) {
            toReturn.add(parts.get(voice));
        }
        return toReturn;
    }

    public List<Instrument> getSections() {
        List<Instrument> toReturn = new ArrayList<>();
        for(Instrument section : sections.keySet()) {
            toReturn.add(section);
        }
        return toReturn;
    }




    @Override
    public Count getStart() {
        return all;
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
        return tempoChanges.getFrameAt(time).iterator().next().getTempo();
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
    public Iterator<Note> noteItr() {
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

    @Override
    public Iterator<Frame<Count>> iterator() {
        return allEvents.iterator();
    }
}
