package io;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.util.Interval;
import javafx.util.Pair;
import model.basic.*;
import model.structure.Passage;
import model.trainable.*;
import model.trainable.Instrument;
import org.jfree.data.time.TimeSeries;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * Created by celenp on 10/18/2016.
 */
public class MidiParser {

    /* A few of the most useful Midi messages */
    private static final int SEQUENCE_NUMBER    = 0x00;
    private static final int TEXT_EVENT         = 0x01;
    private static final int COPYRIGHT_NOTICE   = 0x02;
    private static final int SEQUENCE_NAME      = 0x03;
    private static final int INSTRUMENT_NAME    = 0x04;
    private static final int LYRIC_TEXT         = 0x05;
    private static final int MARKER_TEXT        = 0x06;
    private static final int CUE_POINT          = 0x07;
    private static final int MIDI_CHNL_PREFIX   = 0x20;
    private static final int END_OF_TRACK       = 0x2F;
    private static final int TEMPO_SETTING      = 0x51;
    private static final int SMPTE_OFFSET       = 0x54;
    private static final int TIME_SIGNATURE     = 0x58;
    private static final int KEY_SIGNATURE      = 0x59;
    private static final int SEQUENCER_SPECIFIC = 0x7F;

    // Stage 1
    HashSet<Track> tracks;
    TreeMap<Long,HashSet<MidiEvent>> events;

    // Stage 2
    HashMap<Track,TreeMap<Long,HashSet<Pitch>>> noteOns;
    HashMap<Track,TreeMap<Long,HashSet<Pitch>>> noteOffs;
    TreeMap<Long,TimeSignature> timeSignatures;
    TreeMap<Long,Integer> ppqns;

    // Stage 3
    ArrayList<Long> measures;

    public MidiParser() {
        tracks          = new HashSet<Track>();
        events          = new TreeMap<Long,HashSet<MidiEvent>>();
        noteOns         = new HashMap<Track,TreeMap<Long,HashSet<Pitch>>>();
        noteOffs        = new HashMap<Track,TreeMap<Long,HashSet<Pitch>>>();
        timeSignatures  = new TreeMap<Long,TimeSignature>();
        ppqns           = new TreeMap<Long,Integer>();
    }

    Passage parse(Sequence sequence) {
        System.out.println("MIDI:\tReading in Midi sequence...");
        readAll(sequence);
        System.out.println("MIDI:\t...Finished reading in Midi sequence.");

        System.out.println("MIDI:\tParsing MidiEvents...");
        System.out.println("MIDI:\t...Finished parsing MidiEvents.");

        /*
        float divisionType = sequence.getDivisionType();
        float framesPerSecond = sequence.getResolution() * (1000000 / ppqns.get(new Long(0)));
        if(divisionType == Sequence.SMPTE_24) {
            framesPerSecond = 24;
        }
        else if(divisionType == Sequence.SMPTE_25) {
            framesPerSecond = 25;
        }
        else if(divisionType == Sequence.SMPTE_30) {
            framesPerSecond = 30;
        }
        float ticksPerSecond = sequence.getResolution() * framesPerSecond;
        double tickSize = 1.0 / ticksPerSecond;
        System.out.println(framesPerSecond);
        float lastSecond = 0.0f;
        */
        Long lastTick = null;
        for(Long curTick : ppqns.keySet()) {
            if(lastTick != null) {
                //float secondsPerQuarter = ppqns.get(tick) / 1000000f;
                //System.out.println("Seconds per quarter: " + secondsPerQuarter);
                //System.out.println("Ticks since last: " + (curTick - prevTick));
                TimeSignature timeSignature = timeSignatures.floorEntry(curTick).getValue();
                Integer ppqn = sequence.getResolution(); //ppqns.floorEntry(curTick).getValue();

                Long ticksPerMeasure = new Long(ppqn * 4 * timeSignature.getNumerator() / timeSignature.getDenominator());

                //System.out.println((curTick - lastTick) + "    " + ticksPerMeasure);
                System.out.println((float)(curTick - lastTick)/ ticksPerMeasure + " measures of " + timeSignature.toString() + " at " + 60000000 / ppqns.get(curTick) + " bpm");
            }
            lastTick = curTick;
        }
        /*
        for(Track track : noteOns.keySet()) {
            for(Long tick : noteOns.get(track).keySet()) {
                String allPitches = "";
                for(Pitch pitch : noteOns.get(track).get(tick)) {
                    allPitches += " " + pitch.toString();
                }
                System.out.println(track.toString() + "  " + tick.toString() + "  {" + allPitches + " }");
            }
        }
        */
        return new Passage();
    }

    // The Instrument that did a certain action
    void readAll(Sequence sequence) {

        /*
        for(Patch patch : sequence.getPatchList()) {
            System.out.println(patch.toString());
        }
        */

        // For every Midi track
        for (Track track : sequence.getTracks()) {
            // Add the track to "tracks"
            tracks.add(track);

            // For every Midi event
            for (int i = 0; i < track.size(); i++) {
                // Some information about this Midi event
                MidiEvent event     = track.get(i);
                MidiMessage message = event.getMessage();
                Long tick           = event.getTick();

                // Add the MidiEvent to "events"
                if(!events.containsKey(tick)) {
                    events.put(tick,new HashSet<MidiEvent>());
                }
                events.get(tick).add(event);

                if (message instanceof ShortMessage) {
                    // Cast it to a Midi short message and parse
                    parseShortMessage(track, event,(ShortMessage)message,tick);
                }
                else if (message instanceof MetaMessage) {
                    // Cast it to a Midi short message and parse
                    parseMetaMessage(track, event,(MetaMessage)message,tick);
                }
                else if (message instanceof SysexMessage) {
                    // Cast it to a Midi short message and parse
                    parseSysexMessage(track, event,(SysexMessage)message,tick);
                }
                else {
                    System.out.println("MIDI:\tUnrecognized type of MIDI message!");
                }
            }
        }
    }

    void parseShortMessage(Track track, MidiEvent event, ShortMessage message, Long tick) {

        // Figure out the message's command
        switch (message.getCommand()) {
            case ShortMessage.NOTE_ON:
                parseNoteOnMessage(track, event, message, tick);
                break;
            case ShortMessage.NOTE_OFF:
                parseNoteOffMessage(track, event, message, tick);
                break;
            case ShortMessage.CONTROL_CHANGE:
                parseControlChangeMessage(track, event, message, tick);
                break;
            case ShortMessage.PROGRAM_CHANGE:
                parseProgramChangeMessage(track, event, message, tick);
                break;
            default:
                System.out.println("MIDI:\tUnrecognized Midi ShortMessage " + message.getCommand() + " on channel " + message.getChannel());
                break;
        }
    }

    void parseMetaMessage(Track track, MidiEvent event, MetaMessage message, Long tick) {

        // Figure out the message's type
        switch(message.getType()) {

            case SEQUENCE_NUMBER:
                System.out.println("MIDI:\tSequence number");
                break;
            case TEXT_EVENT:
                parseTextMessage(track,event,message,tick);
                break;
            case COPYRIGHT_NOTICE:
                System.out.println("MIDI:\tCopyright notice");
                break;
            case SEQUENCE_NAME:
                System.out.println("MIDI:\tSequence name");
                break;
            case INSTRUMENT_NAME:
                System.out.println("MIDI:\tInstrument name");
                break;
            case LYRIC_TEXT:
                System.out.println("MIDI:\tLyric text");
                break;
            case MARKER_TEXT:
                System.out.println("MIDI:\tMarker text");
                break;
            case CUE_POINT:
                System.out.println("MIDI:\tCue point");
                break;
            case MIDI_CHNL_PREFIX:
                System.out.println("MIDI:\tMidi channel control");
                break;
            case END_OF_TRACK:
                System.out.println("MIDI:\tEnd of track");
                break;
            case TEMPO_SETTING:
                parseTempoMessage(track,event,message,tick);
                break;
            case SMPTE_OFFSET:
                System.out.println("MIDI:\tMidi channel control");
            case TIME_SIGNATURE:
                parseTimeSignatureMessage(track,event,message,tick);
                break;
            case KEY_SIGNATURE:
                System.out.println("MIDI:\tKey signature");
                break;
            case SEQUENCER_SPECIFIC:
                System.out.println("MIDI:\tSequencer-specific");
                break;
            default:
                System.out.println("MIDI:\tUnrecognized Midi MetaMessage " + message.getData());
                break;
        }
    }

    void parseSysexMessage(Track track, MidiEvent event, SysexMessage message, Long tick) {
        System.out.println("MIDI:\tUnrecognized Midi SysexMessage " + message.getData());
    }

    // MIDI SHORT MESSAGES

    void parseNoteOnMessage(Track track, MidiEvent event, ShortMessage message, Long tick) {

        // Data pulled off of the MidiEvent
        int pitchValue      = message.getData1();
        int velocityValue   = message.getData2();
        Pitch pitch         = new Pitch(pitchValue);

        // If this was a note-off message in disguise
        if(velocityValue == 0) {
            parseNoteOffMessage(track,event,message,tick);
        }
        else {
            // If this track hasn't had a note-on yet
            if (!noteOns.containsKey(track)) {
                noteOns.put(track, new TreeMap<Long, HashSet<Pitch>>());
            }

            // If this track hasn't had a note on at this tick
            if (!noteOns.get(track).containsKey(tick)) {
                noteOns.get(track).put(tick, new HashSet<Pitch>());
            }

            // Add this pitch to note-ons
            noteOns.get(track).get(tick).add(pitch);
        }
    }

    void parseNoteOffMessage(Track track, MidiEvent event, ShortMessage message, Long tick) {

        // Data pulled off of the MidiEvent
        int pitchValue      = message.getData1();
        Pitch pitch         = new Pitch(pitchValue);

        // If this track hasn't had a note-on yet
        if(!noteOffs.containsKey(track)){
            noteOffs.put(track, new TreeMap<Long, HashSet<Pitch>>());
        }

        // If this track hasn't had a note on at this tick
        if(!noteOffs.get(track).containsKey(tick)){
            noteOffs.get(track).put(tick,new HashSet<Pitch>());
        }

        // Add this pitch to note-ons
        noteOffs.get(track).get(tick).add(pitch);
    }

    void parseControlChangeMessage(Track track, MidiEvent event, ShortMessage message, Long tick) {

    }

    void parseProgramChangeMessage(Track track, MidiEvent event, ShortMessage message, Long tick) {

    }

    // MIDI META MESSAGES

    void parseTempoMessage(Track track, MidiEvent event, MetaMessage message, Long tick) {
        byte[] data = message.getData();
        Integer ppqn = new Integer((data[0] & 0xff) << 16 | (data[1] & 0xff) << 8 | (data[2] & 0xff));
        ppqns.put(tick,ppqn); // Pulses Per Quarter Note
    }

    void parseTimeSignatureMessage(Track track, MidiEvent event, MetaMessage message, Long tick) {
        byte[] data = message.getData();
        int numerator   = data[0];
        int denominator = 2 << (data[1] - 1);
        TimeSignature timeSignature = new TimeSignature(numerator, denominator);
        timeSignatures.put(tick,timeSignature);
    }

    void parseTextMessage(Track track, MidiEvent event, MetaMessage message, Long tick) {
        byte[] data = message.getData();
        String string = new String(data);
        System.out.println("MIDI:\tReading text: " + string);
    }
}
