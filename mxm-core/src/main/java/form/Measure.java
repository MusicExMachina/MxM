package form;

import events.MusicEvent;
import events.sounding.FiguredBassNote;
import time.Beat;
import time.Tempo;
import time.TimeSign;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by celenp on 6/10/2017.
 */
public class Measure implements Iterable<MusicEvent> {

    private static int PICKUP_MEASURE_NUM = 0;
    private static int START_MEASURE_NUM = 1;

    private int measureNumber;
    private TimeSign timeSign;
    private TreeMap<Beat, MusicEvent> allEvents;
    private TreeMap<Beat, NoteEvent> allNotes;
    private TreeMap<Beat, ChordEvent> allChords;
    private TreeMap<Beat, FiguredBassNote> allUnpitched;

    private TreeMap<Beat, MusicEvent> events;
    private TreeMap<Beat, Tempo> tempi;

    public Measure(Collection<MusicEvent> events) {
        for(MusicEvent event : events) {
            this.events.add(event.getBeat(),event);
        }
    }

    @Override
    public Iterator<MusicEvent> iterator() {
        return events.values().iterator();
    }
}
