package form;

import com.sun.istack.internal.NotNull;
import events.*;
import sound.ISound;
import sound.Instrument;
import time.*;

import java.util.*;

public class Score implements IPassage {

    private String title;

    // Timing information
    SerialTimeline<Measure, TimeSigChange> timeSigChanges;
    SerialTimeline<Count, TempoChange> tempoChanges;
    ParallelTimeline<Count, Note<? extends ISound>> allNotes;

    // Parts and sections
    TreeMap<Voice,Part> parts;
    TreeMap<Instrument,Set<Voice>> sections;


    public Score() {
        parts = new TreeMap<>();
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


    // ADD METHODS
    public void add(@NotNull Tempo tempo, @NotNull Count count) {
        //
        /*
        TempoChange tempoChange = new TempoChange(tempo,);
        tempoChanges.add()
        */
    }
    public void add(TimeSig timeSig, Measure measure) {

    }





    @Override
    public ITime getStart() {
        return allNotes.getStart();
    }
    @Override
    public ITime getEnd() {
        return null;
    }
    @Override
    public ITime getDuration() {
        return null;
    }



    @Override
    public Tempo getTempoAt(ITime time) {
        return tempoChanges.getEventAt(time).getTempo();
    }
    @Override
    public TimeSig getTimeSigAt(ITime time) {
        return timeSigChanges.getEventAt(time).getTimeSig();
    }
    @Override
    public Iterator<Note<? extends ISound>> getNotesAt(ITime time) {
        return allNotes.getEventsAt(time);
    }



    @Override
    public IPassage getExcerpt(Count start) {
        return null;
    }
    @Override
    public IPassage getExcerpt(Count start, Count end) {
        return null;
    }
}
