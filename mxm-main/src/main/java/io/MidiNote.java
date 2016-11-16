package io;

import model.basic.Pitch;
import model.trainable.Instrument;

import java.util.Comparator;

/**
 * Created by celenp on 11/15/2016.
 */
public class MidiNote implements Comparator<MidiNote>, Comparable<MidiNote>{

    private Instrument instrument;
    private Pitch pitch;
    private float start;
    private float end;

    public MidiNote(Instrument instrument, Pitch pitch, float start, float end) {
        this.instrument = instrument;
        this.pitch = pitch;
        this.start = start;
        this.end = end;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public float getStart() {
        return start;
    }

    public float getEnd() {
        return end;
    }

    @Override
    public int compareTo(MidiNote o) {
        return pitch.compareTo(o.pitch);
    }

    @Override
    public int compare(MidiNote o1, MidiNote o2) {
            return pitch.compareTo(o2.pitch);
    }

    public Instrument getInstrument() {
        return instrument;
    }
/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MidiNote midiNote = (MidiNote) o;

        if (Float.compare(midiNote.start, start) != 0) return false;
        if (Float.compare(midiNote.end, end) != 0) return false;
        return pitch != null ? pitch.equals(midiNote.pitch) : midiNote.pitch == null;

    }

    @Override
    public int hashCode() {
        int result = pitch != null ? pitch.hashCode() : 0;
        result = 31 * result + (start != +0.0f ? Float.floatToIntBits(start) : 0);
        result = 31 * result + (end != +0.0f ? Float.floatToIntBits(end) : 0);
        return result;
    }
    */
}
