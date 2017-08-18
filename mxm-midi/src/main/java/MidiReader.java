import events.sounding.Note;
import sound.*;
import sound.Instrument;
import time.Count;
import time.Tempo;
import time.TimeSig;
import form.Part;
import io.Reader;

import javax.sound.midi.*;
import java.util.*;

/**
 * MidiReader is a class which does exactly what you'd expect.
 * events.sounding.Note that each MidiReader parses exactly *one* midi Sequence.
 * This means that the MidiTools class instantiates one for every
 * single file to be read. This class could potentially be
 * absorbed into MidiTools, but is separated for the code cleanness.
 */
public class MidiReader implements Reader<TraditionalScore> {

    /* A few of the most useful MidiTools messages */
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

    /* Input and output */
    private Sequence sequence;
    private TraditionalScore passage;

    /* Time signature change events in various time formats */
    private TreeMap<Long, TimeSig> timeSigsLong;
    private TreeMap<Float, TimeSig> timeSigsFloat;
    private TreeMap<Count, TimeSig> timeSigsCount;

    /* Tempo change events in various time formats */
    private TreeMap<Long,Integer> tempiLong;
    private TreeMap<Float,Tempo> tempiFloat;
    private TreeMap<Count, Tempo> tempiCount;

    /* Measure markers in longs */
    private TreeMap<Long,Float> timePoints;

    /* Note on events in various time formats */
    private HashMap<Track,TreeMap<Long,TreeSet<Pitch>>> noteOnsLong;
    private HashMap<Track,TreeMap<Float,TreeSet<Pitch>>> noteOnsFloat;
    private HashMap<Track,TreeMap<Count,TreeSet<Pitch>>> noteOnsCount;

    /* Note off events in various time formats */
    private HashMap<Track,TreeMap<Pitch,TreeSet<Long>>> noteOffsLong;
    private HashMap<Track,TreeMap<Pitch,TreeSet<Float>>> noteOffsFloat;
    private HashMap<Track,TreeMap<Pitch,TreeSet<Count>>> noteOffsCount;

    /* Instrument change events in various time formats */
    private HashMap<Track,TreeMap<Long, Instrument>> instChangeLong;
    private HashMap<Track,TreeMap<Float, Instrument>> instChangeFloat;
    private HashMap<Track,TreeMap<Count, Instrument>> instChangeCount;

    /**
     * The main method of MidiReader, which is the entire essence of this class. In fact, this class could be summed up
     * in a single method, but it is simply too cumbersome to do so.
     * @param sequence The midi Sequence to parse.
     * @return The form.ScoreEvent representing this Sequence.
     */
    public TraditionalScore read(Sequence sequence) {

        this.sequence = sequence;
        passage = new TraditionalScore();

        System.out.println("MIDI PARSER:\tParsing midi events...");
        parseAll();
        System.out.println("MIDI PARSER:\tFinished parsing midi events");

        System.out.println("MIDI PARSER:\tInterpolating midi events...");
        interpolateAll();
        System.out.println("MIDI PARSER:\tFinished interpolating midi events");

        System.out.println("MIDI PARSER:\tConverting to counts...");
        convertToCounts();
        System.out.println("MIDI PARSER:\tFinished converting to counts");

        System.out.println("MIDI PARSER:\tMaking parts...");
        makeParts();
        System.out.println("MIDI PARSER:\tFinished making parts");

        return passage;
    }

    /**
     * Reads in and parses noteQualities midi information, such
     * as all the events in a given midi Sequence. This
     * information is stored, and then later interpreted.
     */
    private void parseAll() {

        // Create all of the long (tick)-based timelines
        timeSigsLong   = new TreeMap<>();
        tempiLong      = new TreeMap<>();
        noteOnsLong    = new HashMap<>();
        noteOffsLong   = new HashMap<>();
        instChangeLong = new HashMap<>();

        // For every MidiTools track
        for (Track track : sequence.getTracks()) {
            // Create note ons and note offs for each track
            noteOnsLong.put(track,new TreeMap<Long, TreeSet<Pitch>>());
            noteOffsLong.put(track,new TreeMap<Pitch, TreeSet<Long>>());
            instChangeLong.put(track,new TreeMap<Long, Instrument>());

            // Set the initial instrument of this track (it may change)
            // Note that "-1" here just means anything else will override it
            //TreeMap<Long,sound.Instrument> init = new TreeMap<>();
            //init.put(-1L,sound.Instrument.DEFAULT);
            //instChangeLong.put(track,init);
            // TODO: Instrument change stuff

            // For every MidiTools event
            for (int i = 0; i < track.size(); i++) {
                // Some information about this MidiTools event
                MidiEvent event     = track.get(i);
                MidiMessage message = event.getMessage();
                Long tick           = event.getTick();

                // Parse the message based on what type of message it is
                if (message instanceof ShortMessage)
                    parseShortMessage(track, event,(ShortMessage)message,tick);
                else if (message instanceof MetaMessage)
                    parseMetaMessage(track, event,(MetaMessage)message,tick);
                else if (message instanceof SysexMessage)
                    parseSysexMessage(track, event,(SysexMessage)message,tick);
                else
                    System.out.println("MIDI:\tUnrecognized type of MIDI message!");
            }
        }
    }

    /**
     * Parses a MIDI short message.
     * @param track The track this message is on.
     * @param event The event of this message.
     * @param message The message itself.
     * @param tick The tick of this event's timing.
     */
    private void parseShortMessage(Track track, MidiEvent event, ShortMessage message, Long tick) {

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
                System.out.println("MIDI PARSER:\tUnrecognized MidiTools ShortMessage " + message.getCommand() + " on channel " + message.getChannel());
                break;
        }
    }

    /**
     * Parses a MIDI meta message.
     * @param track The track this message is on.
     * @param event The event of this message.
     * @param message The message itself.
     * @param tick The tick of this event's timing.
     */
    private void parseMetaMessage(Track track, MidiEvent event, MetaMessage message, Long tick) {

        // Figure out the message's type
        switch(message.getType()) {

            case SEQUENCE_NUMBER:
                //System.out.println("MIDI PARSER:\tSequence number");
                break;
            case TEXT_EVENT:
                parseTextMessage(track,event,message,tick);
                break;
            case COPYRIGHT_NOTICE:
                System.out.println("MIDI PARSER:\tCopyright notice");
                break;
            case SEQUENCE_NAME:
                //System.out.println("MIDI PARSER:\tSequence name");
                break;
            case INSTRUMENT_NAME:
                //System.out.println("MIDI PARSER:\tInstrument name");
                break;
            case LYRIC_TEXT:
                //System.out.println("MIDI PARSER:\tLyric text");
                break;
            case MARKER_TEXT:
                //System.out.println("MIDI PARSER:\tMarker text");
                break;
            case CUE_POINT:
                //System.out.println("MIDI PARSER:\tCue point");
                break;
            case MIDI_CHNL_PREFIX:
                //System.out.println("MIDI PARSER:\tMidi channel control");
                break;
            case END_OF_TRACK:
                //System.out.println("MID PARSERI:\tEnd of track");
                break;
            case TEMPO_SETTING:
                parseTempoMessage(track,event,message,tick);
                break;
            case SMPTE_OFFSET:
                //System.out.println("MIDI:\tMidi channel control");
            case TIME_SIGNATURE:
                parseTimeSignatureMessage(track,event,message,tick);
                break;
            case KEY_SIGNATURE:
                //System.out.println("MIDI:\tKey signature");
                break;
            case SEQUENCER_SPECIFIC:
                System.out.println("MIDI PARSER:\tSequencer-specific");
                break;
            default:
                System.out.println("MIDI PARSER:\tUnrecognized MetaMessage " + Arrays.toString(message.getData()));
                break;
        }
    }

    /**
     * Parses a MIDI system-exclusive message.
     * @param track The track this message is on.
     * @param event The event of this message.
     * @param message The message itself.
     * @param tick The tick of this event's timing.
     */
    private void parseSysexMessage(Track track, MidiEvent event, SysexMessage message, Long tick) {
        System.out.println("MIDI PARSER:\tUnrecognized MidiTools SysexMessage " + message.getData());
    }

    /**
     * Parses a MIDI note-on (short) message.
     * @param track The track this message is on.
     * @param event The event of this message.
     * @param message The message itself.
     * @param tick The tick of this event's timing.
     */
    private void parseNoteOnMessage(Track track, MidiEvent event, ShortMessage message, Long tick) {

        // Data pulled off of the MidiEvent
        int pitchValue      = message.getData1();
        int velocityValue   = message.getData2();
        Pitch pitch         = Pitch.getInstance(pitchValue);

        //System.out.println(tick + "\t" + sound);

        // If this was a note-off message in disguise
        if(velocityValue == 0) {
            parseNoteOffMessage(track,event,message,tick);
        }
        else {
            // If this track hasn't had a note on at this tick
            if (!noteOnsLong.get(track).containsKey(tick)) {
                noteOnsLong.get(track).put(tick, new TreeSet<Pitch>());
            }

            // Add this basic to note-ons
            noteOnsLong.get(track).get(tick).add(pitch);
        }
    }

    /**
     * Parses a MIDI note-off (short) message.
     * @param track The track this message is on.
     * @param event The event of this message.
     * @param message The message itself.
     * @param tick The tick of this event's timing.
     */
    private void parseNoteOffMessage(Track track, MidiEvent event, ShortMessage message, Long tick) {

        // Data pulled off of the MidiEvent
        int pitchValue      = message.getData1();
        Pitch pitch         = Pitch.getInstance(pitchValue);

        // If this track hasn't had a note on at this tick
        if (!noteOffsLong.get(track).containsKey(pitch)) {
            noteOffsLong.get(track).put(pitch, new TreeSet<Long>());
        }

        // Add this basic to note-offs
        noteOffsLong.get(track).get(pitch).add(tick);
    }

    /**
     * Parses a MIDI control change (short) message.
     * @param track The track this message is on.
     * @param event The event of this message.
     * @param message The message itself.
     * @param tick The tick of this event's timing.
     */
    private void parseControlChangeMessage(Track track, MidiEvent event, ShortMessage message, Long tick) {
        // TODO: Write parseControlChangeMessage()
        /*
        switch (message.getCommand()) {
            case BANK_SELECT:
                if(!instChangeLong.containsKey(track)) {
                    instChangeLong.put(track,new TreeMap<Long, sound.Instrument>());
                }
                instChangeLong.get(track).put(tick,sound.Instrument.getGeneralMIDIInstrument(message.getMessage()[3]));
                break;
        }
        */
    }

    /**
     * Parses a MIDI program change (short) message.
     * @param track The track this message is on.
     * @param event The event of this message.
     * @param message The message itself.
     * @param tick The tick of this event's timing.
     */
    private void parseProgramChangeMessage(Track track, MidiEvent event, ShortMessage message, Long tick) {
        instChangeLong.get(track).put(tick, Instrument.getGeneralMIDIInstrument(message.getData1()));
    }

    /**
     * Parses a MIDI tempo change (meta) message.
     * @param track The track this message is on.
     * @param event The event of this message.
     * @param message The message itself.
     * @param tick The tick of this event's timing.
     */
    private void parseTempoMessage(Track track, MidiEvent event, MetaMessage message, Long tick) {
        byte[] data = message.getData();
        Integer ppqn = (data[0] & 0xff) << 16 | (data[1] & 0xff) << 8 | (data[2] & 0xff);
        tempiLong.put(tick,60000000/ppqn); // 60 000 000 / Pulses Per Quarter events.sounding.Note - I think this is right
    }

    /**
     * Parses a MIDI time signature (meta) message.
     * @param track The track this message is on.
     * @param event The event of this message.
     * @param message The message itself.
     * @param tick The tick of this event's timing.
     */
    private void parseTimeSignatureMessage(Track track, MidiEvent event, MetaMessage message, Long tick) {
        byte[] data = message.getData();
        int numerator   = data[0];
        int denominator = 2 << (data[1] - 1);
        TimeSig timeSignature  = TimeSig.getInstance(4,4);
        if(numerator == 0 || denominator == 0) {
            System.out.println("MIDI PARSER:\tImproper time signature message, reverting to 4/4");
        }
        else {
            timeSignature = TimeSig.getInstance(numerator, denominator);
        }
        timeSigsLong.put(tick,timeSignature);
    }

    /**
     * Parses a MIDI text (meta) message.
     * @param track The track this message is on.
     * @param event The event of this message.
     * @param message The message itself.
     * @param tick The tick of this event's timing.
     */
    private void parseTextMessage(Track track, MidiEvent event, MetaMessage message, Long tick) {
        byte[] data = message.getData();
        String string = new String(data);
        // Remove any awkward newlines from the text
        System.out.println("MIDI PARSER:\tText: \"" + string.replace("\n", "").replace("\r", "")+"\"");
    }

    /**
     * "Interprets" stored midi data, by extracting useful
     * features, and saving time points which which we will
     * later convert all events to counts.
     */
    private void interpolateAll() {

        Integer resolution = sequence.getResolution();
        long tick = 0;
        long prevTick = 0;
        long finalTick = sequence.getTickLength();
        float time = 0.0f;

        // Create the next stage's note-on and note-off collections
        timeSigsFloat = new TreeMap<>();
        tempiFloat = new TreeMap<>();
        timePoints = new TreeMap<>();
        timePoints.put(0L,0.0f);

        noteOnsFloat = new HashMap<>();
        noteOffsFloat = new HashMap<>();
        instChangeFloat = new HashMap<>();

        // Add the first time signature, if there is one
        if (!timeSigsLong.isEmpty()) {
            timeSigsFloat.put(0f, timeSigsLong.firstEntry().getValue());
        }
        // Else, make it 4/4, because why not?
        else {
            timeSigsFloat.put(0f, TimeSig.getInstance(4, 4));
            System.out.println("MIDI PARSER: No time signature... defaulting to 4/4");
        }

        // Add the first tempo, if there is one
        if (tempiLong.firstEntry() != null) {
            tempiFloat.put(0f, new Tempo(tempiLong.firstEntry().getValue()));
        }
        // Else, make it 120, because why not?
        else {
            tempiFloat.put(0f, new Tempo(120));
            System.out.println("MIDI PARSER: No tempo... defaulting to 120bpm");
        }

        // For every tick in this midi sequence
        // (Don't worry, we skip ahead efficiently)
        while (tick <= finalTick) {

            // Update the time signature if need be
            if(timeSigsLong.get(tick) != null) {
                timeSigsFloat.put(time, timeSigsLong.get(tick));
            }
            // Update the tempo if need be
            if(tempiLong.get(tick) != null) {
                tempiFloat.put(time, new Tempo(tempiLong.get(tick)));
            }


            // Information
            TimeSig timeSig = timeSigsLong.floorEntry(tick).getValue();
            long ticksPerMeasure = resolution * 4 * timeSig.getNumerator() / timeSig.getDenominator();
            long ticksElapsed = tick - prevTick;
            float timeElapsed = (float) ticksElapsed / ticksPerMeasure;

            // Add to the time and set "prevTick"
            time += timeElapsed;
            prevTick = tick;
            tick++;

            // Add this timePoint, if it's not a duplicate
            if (!timePoints.containsKey(tick)) {
                timePoints.put(tick, time);
            }

            // If there's a time signature change, add that in
            if (timeSigsLong.containsKey(tick)) {
                timeSigsFloat.put(time, timeSig);
            }

            // If there's a PPQN change, add that in
            if (tempiLong.containsKey(tick)) {
                // TODO: Is this right? I think it might not be...
                tempiFloat.put(time, new Tempo(tempiLong.get(tick)));
            }

            // Check for the next changes in key signature or pulses per quarter note.
            // If there are upcoming changes, figure out when they are, and if not, say
            // that they're infinitely far away.
            long nextTimeSigChangeTick = Long.MAX_VALUE;
            long nextPPQNChangeTick = Long.MAX_VALUE;

            if (timeSigsLong.ceilingEntry(tick) != null) {
                nextTimeSigChangeTick = timeSigsLong.ceilingEntry(tick).getKey();
            }
            if (tempiLong.ceilingEntry(tick) != null) {
                nextPPQNChangeTick = tempiLong.ceilingEntry(tick).getKey();
            }

            // If the next upcoming event is the end of the piece, mark it with a time point
            // and be done with it.
            if (tick <= finalTick && finalTick <= nextTimeSigChangeTick && finalTick <= nextPPQNChangeTick) {
                tick = finalTick;
            }
            // Else, if a time signature change is due sooner, move the tick to that
            else if (nextTimeSigChangeTick <= nextPPQNChangeTick) {
                tick = nextTimeSigChangeTick;
            }
            // Otherwise, the next ppqn change must be the closest relevant difference
            else {
                tick = nextPPQNChangeTick;
            }
        }

        for(Track track : instChangeLong.keySet()) {
            instChangeFloat.put(track,new TreeMap<Float, Instrument>());
            for(Long switchTick : instChangeLong.get(track).keySet()) {
                instChangeFloat.get(track).put(interpolate(switchTick), instChangeLong.get(track).get(switchTick));
            }
        }

        // TODO: Avoid all these ".gets" and optimize
        // Convert all note-on messages to float format
        for(Track track : noteOnsLong.keySet()) {
            noteOnsFloat.put(track, new TreeMap<Float, TreeSet<Pitch>>());

            // For each frame
            for (Long noteOnTick : noteOnsLong.get(track).keySet()) {
                noteOnsFloat.get(track).put(interpolate(noteOnTick), new TreeSet<Pitch>());

                // For each sound
                for (Pitch pitchStarted : noteOnsLong.get(track).get(noteOnTick)) {
                    noteOnsFloat.get(track).get(interpolate(noteOnTick)).add(pitchStarted);
                }
            }
        }

        // TODO: Avoid all these ".gets" and optimize
        // Convert all note-off messages to float format
        for(Track track : noteOffsLong.keySet()) {
            noteOffsFloat.put(track, new TreeMap<Pitch, TreeSet<Float>>());

            // For each frame
            for (Pitch pitch : noteOffsLong.get(track).keySet()) {
                noteOffsFloat.get(track).put(pitch, new TreeSet<Float>());
                // For each sound
                for (Long noteOffTick : noteOffsLong.get(track).get(pitch)) {
                    noteOffsFloat.get(track).get(pitch).add(interpolate(noteOffTick));
                }
            }
        }

        // Free up the previous stage's note-on and note-off collections
        timeSigsLong.clear();
        tempiLong.clear();
        noteOnsLong.clear();
        noteOffsLong.clear();
        instChangeLong.clear();
    }

    /**
     * Converts the all events into fractions-of-a-measure
     * format. events.sounding.Note that this is a touchy, time-consuming
     * process prone to minor errors, and thus, this function
     * is likely to require tweaking going forward.
     */
    private void convertToCounts() {

        timeSigsCount = new TreeMap<>();
        tempiCount = new TreeMap<>();

        noteOnsCount = new HashMap<>();
        noteOffsCount = new HashMap<>();
        instChangeCount = new HashMap<>();

        // For each time signature change
        for (Float timeSigChangeTime : timeSigsFloat.keySet()) {
            // The best-matching count to put this event on
            Count count = closestCount(timeSigChangeTime);
            timeSigsCount.put(count,timeSigsFloat.get(timeSigChangeTime));
        }

        // For each tempo change
        for (Float tempoChangeTime : tempiFloat.keySet()) {
            // The best-matching count to put this event on
            Count count = closestCount(tempoChangeTime);
            tempiCount.put(count,tempiFloat.get(tempoChangeTime));
        }

        // TODO: Make a "tracks" set?
        // For every track
        for(Track track : noteOnsFloat.keySet()) {

            // Add a space for the new collection
            noteOnsCount.put(track, new TreeMap<Count, TreeSet<Pitch>>());
            noteOffsCount.put(track, new TreeMap<Pitch, TreeSet<Count>>());
            instChangeCount.put(track, new TreeMap<Count, Instrument>());

            // For each note-on frame
            for (Float instChangeTime : instChangeFloat.get(track).keySet()) {
                // The best-matching count to put this event on
                Count count = closestCount(instChangeTime);
                instChangeCount.get(track).put(count,instChangeFloat.get(track).get(instChangeTime));
            }

            // For each note-on frame
            for (Float noteOnFloat : noteOnsFloat.get(track).keySet()) {

                // The best-matching count to put this event on
                Count count = closestCount(noteOnFloat);

                // Add a sound set at this time
                noteOnsCount.get(track).put(count, new TreeSet<Pitch>());

                // For each sound to add... add it
                for (Pitch pitchStarted : noteOnsFloat.get(track).get(noteOnFloat)) {
                    noteOnsCount.get(track).get(count).add(pitchStarted);
                }
            }

            for (Pitch pitch : noteOffsFloat.get(track).keySet()) {
                noteOffsCount.get(track).put(pitch, new TreeSet<Count>());
                for (Float noteOffFloat : noteOffsFloat.get(track).get(pitch)) {
                    Count count = closestCount(noteOffFloat);
                    noteOffsCount.get(track).get(pitch).add(count);
                }
            }
        }
        // Clear old collections
        timeSigsFloat.clear();
        tempiFloat.clear();
        noteOnsFloat.clear();
        noteOffsFloat.clear();
        instChangeFloat.clear();
    }

    /**
     *
     */
    private void makeParts() {

        for(Count timeSigChange : timeSigsCount.keySet()) {
            passage.addTimeSignature(timeSigsCount.get(timeSigChange),timeSigChange.getMeasure());
        }
        for(Count tempoChange : tempiCount.keySet()) {
            passage.addTempoChange(tempiCount.get(tempoChange),tempoChange);
        }

        // For every track
        for(Track track : noteOnsCount.keySet()) {
            if(!noteOnsCount.get(track).isEmpty() && !instChangeCount.get(track).isEmpty()) {
                Instrument instrument = instChangeCount.get(track).firstEntry().getValue();
                Part part = new Part(instrument);
                // TODO: Instrument changes

                for(Map.Entry<Count, TreeSet<Pitch>> pair : noteOnsCount.get(track).entrySet()) {
                    Count start = pair.getKey();
                    for(Pitch pitch : pair.getValue()) {
                        Count end = noteOffsCount.get(track).get(pitch).ceiling(start);
                        System.out.println(new Note(start,end,pitch));
                        part.add(new Note(start,end,pitch));
                    }
                }
                // Add the part to the passage
                passage.add(part);
            }
        }
    }

    /**
     * A useful method that interpolates a tick between established
     * time points. events.sounding.Note that this is similar to the way that pixels
     * are interpolated in digital images.
     * @param tick The tick representing the time to be interpolated.
     * @return The float value of this tick as fractions of a measure.
     */
    private float interpolate(long tick) {
        // Get the time points before and after this tick
        long earlierTick    = timePoints.floorKey(tick);
        long laterTick      = timePoints.ceilingKey(tick);

        // If we're right on the time point we want
        if(Long.compare(earlierTick,laterTick) == 0) {
            return timePoints.get(earlierTick);
        }
        // If we have to interpolate
        else {
            // Figure out what fractions-of-a-measure those time points
            // represent, and lerp between them to figure out where "tick" is
            float earlierTimePoint  = timePoints.get(earlierTick);
            float laterTimePoint    = timePoints.get(laterTick);
            float relativePosition  = (float)(tick - earlierTick) / (float)(laterTick - earlierTick);
            return (relativePosition * (laterTimePoint - earlierTimePoint )) + earlierTimePoint;
        }
    }

    /**
     *
     * @param time
     * @return
     */
    private Count closestCount(float time) {
        // Now we get to calculate where in the measure this lies
        int measure = (int)Math.floor(time);
        float remainder = time - measure;

        // We need a time.Count that's sufficiently close to the remainder
        int numerator = 1;
        int denominator = 1;

        // For all possible subdivision amounts
        for(int testDenominator = 1; testDenominator < 60; testDenominator++) {
            float increment = 1f/(float)testDenominator;
            float closestSnapPoint = increment*Math.round(remainder/increment);
            float distance = Math.abs(increment - closestSnapPoint);

            // If it's close enough
            if(distance < .001f) {
                denominator = testDenominator;
                numerator = Math.round(time*denominator);
                break;
            }
        }
        // The best-matching count to put this event on
        return new Count(numerator,denominator);
    }

}