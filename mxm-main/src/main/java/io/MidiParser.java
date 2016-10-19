package io;

import it.unimi.dsi.fastutil.Hash;
import javafx.util.Pair;
import model.basic.*;
import model.structure.Passage;
import model.trainable.*;
import model.trainable.Instrument;
import org.jfree.data.time.TimeSeries;

import javax.sound.midi.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * Created by celenp on 10/18/2016.
 */
public class MidiParser {

    /* A few of the most useful Midi messages */
    private static final int TEMPO_MSG       = 0x51;
    private static final int TIME_SIGN_MSG   = 0x58;
    private static final int TEXT_MSG        = 0x01;


    // Stage 1
    HashSet<Track> tracks;
    TreeMap<Long,HashSet<MidiEvent>> events;

    // Stage 2
    HashMap<Track,TreeMap<Long,HashSet<Pitch>>> noteOns;
    HashMap<Track,TreeMap<Long,HashSet<Pitch>>> noteOffs;
    TreeMap<Long,TimeSignature> timeSignatures;
    TreeMap<Long,Tempo> tempi;

    public MidiParser() {
        tracks          = new HashSet<Track>();
        events          = new TreeMap<Long,HashSet<MidiEvent>>();
        noteOns         = new HashMap<Track,TreeMap<Long,HashSet<Pitch>>>();
        noteOffs        = new HashMap<Track,TreeMap<Long,HashSet<Pitch>>>();
        timeSignatures  = new TreeMap<Long,TimeSignature>();
        tempi           = new TreeMap<Long,Tempo>();
    }


    Passage parse(Sequence sequence) {
        System.out.println("MIDI:\tReading in Midi sequence...");
        readAll(sequence);
        System.out.println("MIDI:\t...Finished reading in Midi sequence.");

        System.out.println("MIDI:\tParsing MidiEvents...");
        System.out.println("MIDI:\t...Finished parsing MidiEvents.");


        for(Long tick : timeSignatures.keySet()) {
            System.out.println(tick.toString() + "    " + timeSignatures.get(tick).toString());
        }
        for(Long tick : tempi.keySet()) {
            System.out.println(tick.toString() + "    " + tempi.get(tick).toString());
        }
        for(Track track : noteOns.keySet()) {
            for(Long tick : noteOns.get(track).keySet()) {
                String allPitches = "";
                for(Pitch pitch : noteOns.get(track).get(tick)) {
                    allPitches += " " + pitch.toString();
                }
                System.out.println(track.toString() + "  " + tick.toString() + "  { " + allPitches + " }");
            }
        }
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
            case 0x20:
                System.out.println("MIDI:\tMidi Channel prefix:" + message.getData().toString());
                break;
            case TEMPO_MSG:
                parseTempoMessage(track,event,message,tick);
                break;
            case TIME_SIGN_MSG:
                parseTimeSignatureMessage(track,event,message,tick);
                break;
            case TEXT_MSG:
                parseTextMessage(track,event,message,tick);
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
        int tempoValue = (data[0] & 0xff) << 16 |
                (data[1] & 0xff) << 8 |
                (data[2] & 0xff);
        int bpm = 60000000 / tempoValue;
        Tempo tempo = new Tempo(bpm);
        tempi.put(tick,tempo);
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
