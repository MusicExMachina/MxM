package io;

import model.basic.Pitch;
import model.form.Line;
import model.form.Note;
import model.rhythmTree.RhythmNode;
import model.rhythmTree.RhythmTree;
import model.form.Passage;
import model.basic.TimeSignature;
import model.trainable.Instrument;

import javax.sound.midi.*;
import java.util.*;

/**
 * MidiParser is a class which does exactly what you'd expect.
 * Note that each MidiParser parses exactly *one* midi Sequence.
 * This means that the MidiTools class instantiates one for every
 * single file to be read. This class could potentially be
 * absorbed into MidiTools, but is separated for the code cleanness.
 */
class MidiParser {

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

    // Input & output
    private Sequence sequence;
    private Passage passage;

    // Master Track Events
    private TreeMap<Long,TimeSignature> timeSignatures;
    private TreeMap<Long,Integer> pulsesPerQuarter;
    private TreeMap<Long,Float> timePoints;

    // Track Events
    private HashMap<Track,TreeMap<Pitch,TreeSet<Long>>> noteOns;
    private HashMap<Track,TreeMap<Pitch,TreeSet<Long>>> noteOffs;
    private HashMap<Track,TreeMap<Long,Instrument>> instSwitches;

    // Converted Events to Float format
    private HashMap<Track,TreeMap<Float,MidiFrame>> midiFrames;
    private HashMap<Track,TreeMap<Float,Instrument>> instSwitchFloats;

    // Proto-lines
    private List<MidiLine> protoLines;

    // The RhythmTrees for each Line
    private HashMap<Line,TreeMap<Integer,RhythmTree>> rhythmTrees;

    /**
     * The main method of MidiParser, which is the entire
     * essence of this class. In fact, this class could be
     * summed up in a single method, but it is simply too
     * cumbersome to do so.
     * @param sequence The midi Sequence to parse.
     * @return The Passage representing this Sequence.
     */
    public Passage run(Sequence sequence) {

        this.sequence = sequence;
        passage = new Passage();

        noteOns = new HashMap<>();
        noteOffs = new HashMap<>();
        instSwitches = new HashMap<>();

        timeSignatures = new TreeMap<>();
        pulsesPerQuarter = new TreeMap<>();
        timePoints = new TreeMap<>();

        midiFrames = new HashMap<>();
        instSwitchFloats = new HashMap<>();

        System.out.println("MIDI PARSER:\tParsing MidiEvents...");
        parseAll();
        System.out.println("MIDI PARSER:\t...Finished parsing MidiEvents.");

        System.out.println("MIDI PARSER:\tInterpreting MidiEvents...");
        interpretAll();
        System.out.println("MIDI PARSER:\t...Finished interpreting MidiEvents.");

        splitIntoProtoLines();

        System.out.println("MIDI PARSER:\tConverting to Counts...");
        convertToCounts();
        System.out.println("MIDI PARSER:\t...Finished converting to Counts.");

        //System.out.println(passage);
        for(Line line : passage) {
            System.out.println(line.getRhythm());
        }
        return passage;
    }

    /**
     * Reads in and parses noteQualities midi information, such
     * as all the events in a given midi Sequence. This
     * information is stored, and then later interpreted.
     */
    private void parseAll() {

        // For every MidiTools track
        for (Track track : sequence.getTracks()) {
            // Add the track to "tracks"

            TreeMap<Long,Instrument> init = new TreeMap<Long, Instrument>();
            init.put(0L,Instrument.DEFAULT);
            instSwitches.put(track,init);

            // For every MidiTools event
            for (int i = 0; i < track.size(); i++) {
                // Some information about this MidiTools event
                MidiEvent event     = track.get(i);
                MidiMessage message = event.getMessage();
                Long tick           = event.getTick();

                if (message instanceof ShortMessage) {
                    // Cast it to a MidiTools short message and parse
                    parseShortMessage(track, event,(ShortMessage)message,tick);
                }
                else if (message instanceof MetaMessage) {
                    // Cast it to a MidiTools short message and parse
                    parseMetaMessage(track, event,(MetaMessage)message,tick);
                }
                else if (message instanceof SysexMessage) {
                    // Cast it to a MidiTools short message and parse
                    parseSysexMessage(track, event,(SysexMessage)message,tick);
                }
                else {
                    System.out.println("MIDI:\tUnrecognized type of MIDI message!");
                }
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
                System.out.println("MIDI:\tUnrecognized MidiTools ShortMessage " + message.getCommand() + " on channel " + message.getChannel());
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
                //System.out.println("MIDI:\tSequence number");
                break;
            case TEXT_EVENT:
                parseTextMessage(track,event,message,tick);
                break;
            case COPYRIGHT_NOTICE:
                //System.out.println("MIDI:\tCopyright notice");
                break;
            case SEQUENCE_NAME:
                //System.out.println("MIDI:\tSequence name");
                break;
            case INSTRUMENT_NAME:
                //System.out.println("MIDI:\tInstrument name");
                break;
            case LYRIC_TEXT:
                //System.out.println("MIDI:\tLyric text");
                break;
            case MARKER_TEXT:
                //System.out.println("MIDI:\tMarker text");
                break;
            case CUE_POINT:
                //System.out.println("MIDI:\tCue point");
                break;
            case MIDI_CHNL_PREFIX:
                //System.out.println("MIDI:\tMidi channel control");
                break;
            case END_OF_TRACK:
                //System.out.println("MIDI:\tEnd of track");
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
                //System.out.println("MIDI:\tSequencer-specific");
                break;
            default:
                System.out.println("MIDI:\tUnrecognized MidiTools MetaMessage " + message.getData());
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
        System.out.println("MIDI:\tUnrecognized MidiTools SysexMessage " + message.getData());
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

        // If this was a note-off message in disguise
        if(velocityValue == 0) {
            parseNoteOffMessage(track,event,message,tick);
        }
        else {
            // If this track hasn't had a note-on yet
            if (!noteOns.containsKey(track)) {
                noteOns.put(track, new TreeMap<Pitch, TreeSet<Long>>());
            }

            // If this track hasn't had a note on at this tick
            if (!noteOns.get(track).containsKey(pitch)) {
                noteOns.get(track).put(pitch, new TreeSet<Long>());
            }

            // Add this basic to note-ons
            noteOns.get(track).get(pitch).add(tick);
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

        // If this track hasn't had a note-on yet
        if(!noteOffs.containsKey(track)){
            noteOffs.put(track, new TreeMap<Pitch, TreeSet<Long>>());
        }

        // If this track hasn't had a note on at this tick
        if(!noteOffs.get(track).containsKey(pitch)){
            noteOffs.get(track).put(pitch,new TreeSet<Long>());
        }

        // Add this basic to note-ons
        noteOffs.get(track).get(pitch).add(tick);
    }

    /**
     * Parses a MIDI control change (short) message.
     * @param track The track this message is on.
     * @param event The event of this message.
     * @param message The message itself.
     * @param tick The tick of this event's timing.
     */
    private void parseControlChangeMessage(Track track, MidiEvent event, ShortMessage message, Long tick) {
        /*
        switch (message.getCommand()) {
            case BANK_SELECT:
                if(!instSwitches.containsKey(track)) {
                    instSwitches.put(track,new TreeMap<Long, Instrument>());
                }
                instSwitches.get(track).put(tick,Instrument.getGeneralMIDIInstrument(message.getMessage()[3]));
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
        instSwitches.get(track).put(tick,Instrument.getGeneralMIDIInstrument(message.getData1()));
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
        pulsesPerQuarter.put(tick,ppqn); // Pulses Per Quarter Note
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
        TimeSignature timeSignature;
        if(numerator == 0 || denominator == 0) {
            System.out.println("MIDI:\tImproper time signature message, reverting to 4/4");
            timeSignature = new TimeSignature(4,4);
        }
        else {
            timeSignature = new TimeSignature(numerator, denominator);
        }
        timeSignatures.put(tick,timeSignature);
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
        System.out.print("MIDI:\tReading text: " + string);
    }

    /**
     * "Interprets" stored midi data, by extracting useful
     * features, and saving time points which which we will
     * later convert all events to counts.
     */
    private void interpretAll() {

        Integer resolution  = sequence.getResolution();
        long tick           = 0;
        long prevTick       = 0;
        long finalTick      = sequence.getTickLength();
        float time          = 0.0f;

        while(tick <= finalTick) {
            // Information
            TimeSignature timeSignature = timeSignatures.floorEntry(tick).getValue();
            long ticksPerMeasure        = resolution * 4 * timeSignature.getNumerator() / timeSignature.getDenominator();
            long ticksElapsed           = tick - prevTick;
            float timeElapsed           = (float)ticksElapsed/ticksPerMeasure;

            // Add to the time
            time += timeElapsed;

            // Add this timePoint, if it's not a duplicate
            if(!timePoints.containsKey(tick)) {
                timePoints.put(tick, time);
            }

            // Set "prevTick"
            prevTick = tick;
            // Ensure we don't select the same tick again
            tick++;

            // Check for the next changes in key signature or pulses per quarter note.
            // If there are upcoming changes, figure out when they are, and if not, say
            // that they're infinitely far away.
            Map.Entry<Long,TimeSignature> nextTimeSigChange     = timeSignatures.ceilingEntry(tick);
            Map.Entry<Long,Integer> nextPulsesPerQuarterChange  = pulsesPerQuarter.ceilingEntry(tick);
            long nextTimeSigChangeTick                          = Long.MAX_VALUE;
            long nextPulsesPerQuarterChangeTick                 = Long.MAX_VALUE;

            if(nextTimeSigChange != null) {
                nextTimeSigChangeTick = nextTimeSigChange.getKey();
            }
            if(nextPulsesPerQuarterChange != null) {
                nextPulsesPerQuarterChangeTick = nextPulsesPerQuarterChange.getKey();
            }

            // If the next upcoming event is the end of the piece, mark it with a time point
            // and be done with it.
            if(tick <= finalTick &&
               finalTick <= nextTimeSigChangeTick &&
               finalTick <= nextPulsesPerQuarterChangeTick)
            {
                tick = finalTick;
            }
            // Else, if a time signature change is due sooner, move the tick to that
            else if (nextTimeSigChangeTick <= nextPulsesPerQuarterChangeTick) {
                tick = nextTimeSigChangeTick;
            }
            // Otherwise, the next ppqn change must be the closest relevant difference
            else {
                tick = nextPulsesPerQuarterChangeTick;
            }
        }

        for(Track track : instSwitches.keySet()) {
            instSwitchFloats.put(track,new TreeMap<Float,Instrument>());
            for(Long switchTick : instSwitches.get(track).keySet()) {
                instSwitchFloats.get(track).put(interpolate(switchTick),instSwitches.get(track).get(switchTick));
            }
        }


        // Convert all note-on messages to float format
        for(Track track : noteOns.keySet()) {
            midiFrames.put(track,new TreeMap<Float,MidiFrame>());
            for(Pitch pitch : noteOns.get(track).keySet()) {
                for(Long startTime : noteOns.get(track).get(pitch)) {
                    // Get the next note off after this note on
                    Long endTime = noteOffs.get(track).get(pitch).ceiling(startTime + 1);

                    Float startTimeFloat = interpolate(startTime);
                    Float endTimeFloat = interpolate(endTime);

                    MidiFrame frame = midiFrames.get(track).get(startTimeFloat);

                    if(frame == null) {
                        midiFrames.get(track).put(startTimeFloat,new MidiFrame(startTimeFloat));
                        frame = midiFrames.get(track).get(startTimeFloat);
                    }
                    frame.add(new MidiNote(instSwitchFloats.get(track).floorEntry(startTimeFloat).getValue(),
                                            pitch,startTimeFloat,endTimeFloat));
                }
            }
        }
    }

    /**
     * Splits all notes into proto-lines
     */
    private void splitIntoProtoLines() {
        protoLines = new ArrayList<>();
        for(Track track : midiFrames.keySet()) {
            for(MidiFrame frame : midiFrames.get(track).values()) {
                for (MidiNote note : frame) {
                    boolean addedNote = false;
                    for(MidiLine line : protoLines) {
                        if (line.canAdd(note)) {
                            line.add(note);
                            addedNote = true;
                            break;
                        }
                    }
                    if(!addedNote) {
                        MidiLine newLine = new MidiLine(note.getInstrument());
                        protoLines.add(newLine);
                        newLine.add(note);
                    }
                }
            }
        }
    }


    /**
     * Converts the all events into fractions-of-a-measure
     * format. Note that this is a touchy, time-consuming
     * process prone to minor errors, and thus, this function
     * is likely to require tweaking going forward.
     */
    private void convertToCounts() {

        // For every proto line
        for(MidiLine protoLine : protoLines) {

            // Create an array to hold all of the "open" lines in this Track
            Line line = new Line(protoLine.getInstrument());

            List<RhythmTree> rhythmTrees = new ArrayList<>();
            List<Note> unendedNotes = new ArrayList<>();

            int measure = (int)Math.floor(protoLine.getStart());
            NavigableMap<Float,MidiNote> notesInMeasure;

            do {
                notesInMeasure = protoLine.getMeasure(measure);

                if(notesInMeasure.size() > 0) {
                    // Create a rhythm tree for every measure
                    RhythmTree rhythmTree = new RhythmTree(measure);
                    // Given measure bounds, build a rhythm tree off of those notes
                    subdivideNode(rhythmTree.getRoot(), measure, measure + 1.0f, notesInMeasure, unendedNotes);
                    rhythmTrees.add(rhythmTree);

                    MidiMeasure midiMeasure = new MidiMeasure(measure,rhythmTree);

                    line.add(midiMeasure);
                }
                measure++;
            }
            while(measure < protoLine.getEnd());

            // Add the line to the passage
            passage.add(line);
        }
    }

    /**
     * A useful method that interpolates a tick between established
     * time points. Note that this is similar to the way that pixels
     * are interpolated in digital images.
     * @param tick The tick representing the time to be interpolated.
     * @return The float value of this tick as fractions of a measure.
     */
    private float interpolate(long tick) {
        // Get the timepoints before and after this tick
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
     * Subdivides a given RhythmNode, which involves calculating the
     * optimal number of equal subdivisions, given the note-ons that
     * occur during the time of a given node. This function is called
     * recursively until no note-ons have been accounted for.
     * @param node The RhythmNode that we're subdividing.
     * @param start The start time as fractions-of-a-measure.
     * @param end The end time as fractions-of-a-measure.
     */
    private void subdivideNode (RhythmNode node, float start, float end, NavigableMap<Float,MidiNote> notes, List<Note> unendedNotes) {

        // Figure out the subdivision which will bring about the lowest error.
        int subdivisions = 1;
        float lowestError = Float.MAX_VALUE;

        //System.out.println("start: " + start + " end: " + end + " num notes : " + notes.size());

        // For all possible subdivision amounts
        for(int trySubdiv = 1; trySubdiv < 55; trySubdiv++) {
            // If there's at least one note on during this node
            if(notes.keySet().size() > 0 &&
                    notes.keySet().size() <= trySubdiv ) {
                float totalError = 0.0f;
                // For every note on during this node
                for(Float time : notes.keySet()) {
                    float lowestDistance = Float.MAX_VALUE;
                    // Calculate the total "error" of each trySubdiv
                    for(int num = 0; num < trySubdiv; num++) {
                        float perfectDivision = start + ((end - start) / trySubdiv * (float) num);
                        float distance = Math.abs(perfectDivision - time);
                        if(distance < lowestDistance) {
                            lowestDistance = distance;
                        }
                    }
                    //System.out.println("ld " + lowestDistance);
                    float weightedError = trySubdiv * trySubdiv * lowestDistance;
                    totalError += weightedError;
                }
                //System.out.println(trySubdiv + " ) " + totalError);
                // If this subdivision number is better than all previous
                if(totalError < lowestError) {
                    subdivisions = trySubdiv;
                    lowestError = totalError;
                }
            }
        }
        // If this node is to be subdivided at all
        if(subdivisions > 1) {
            // Subdivide only by small, prime numbers
            for(int i = 2; i < 10; i++) {
                if(subdivisions % i == 0) {
                    subdivisions = i;
                    break;
                }
            }
            //System.out.println("> " + subdivisions);
            Collection<RhythmNode> children = node.subdivide(subdivisions);
            int childNumber = 0;
            for (RhythmNode child : children) {
                float beginTime = start + ((end - start) / (float) subdivisions * (float) childNumber);
                float endTime = start + ((end - start) / (float) subdivisions * (float) (childNumber + 1));
                subdivideNode(child, beginTime, endTime, notes.subMap(beginTime,true,endTime,false), unendedNotes);
                childNumber++;
            }
        }
        else if(subdivisions == 1 && notes.keySet().size() > 0) {
            int measure = (int)Math.floor(start);
            Note note = new Note(notes.floorEntry(end).getValue().getPitch());
            note.setStart(node);
            node.color(note);
        }
    }
}