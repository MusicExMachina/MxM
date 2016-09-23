package io;

import com.sun.media.sound.StandardMidiFileReader;
import javax.sound.midi.*;
import javax.sound.midi.spi.MidiFileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
    new URL("http://www.midiworld.com/midis/other/n1/EspanjaPrelude.mid"));
    new URL("http://www.midiworld.com/download/4573"));
    new URL("http://www.midiworld.com/download/4522"));
    new URL("http://www.piano-midi.de/midis/debussy/deb_clai.mid"));
    new URL("http://www.8notes.com/school/midi/piano/debussy_clair.mid")
 */

/*
public class Converter
{
    static final int NOTE_ON    = 0x90;
    static final int NOTE_OFF   = 0x80;
    static final long MAX_TIME  = 1000;

    TreeMap<Integer, Note> notes;         */
/** A "dump" of all the notes in this piece by ID *//*

    TreeMap<Long, List<Note>> frames;     */
/** Allows for groupings by concurrency *//*

    TreeMap<String, List<Note>> parts;    */
/** Allows for groupings by instrumment *//*


    public static void main (String args[]) {
        Converter converter = new Converter();
        converter.run();
        converter.writeToFile("test");
    }

    */
/**
     * Reads a MIDI file from a given url string
     * @param str the url to read from
     * @return the MIDI Sequence read
     *//*

    public static Sequence readFromURL (String str) {
        MidiFileReader reader = new StandardMidiFileReader();
        try {
            return reader.getSequence(new URL(str));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    */
/**
     * Attempts to write the current information to a file
     *//*

    private void writeToFile (String fileName) {
        List<String> lines = new ArrayList<>();
        lines.add("notes {");
        for(Integer id : notes.keySet()) {
            String idStr = String.format("%03d", id);
            lines.add("\t" + idStr + "   "+ notes.get(id).toString());
        }
        lines.add("}");
        lines.add("frames {");
        for(Long time : frames.keySet()) {
            List<Note> notesInFrame = frames.get(time);
            String timeStr = String.format("%06d", time);
            String frameString = "\t" + timeStr + "  ";
            for(Note note : notesInFrame) {
                String idStr = String.format("%03d", note.getUID());
                frameString += " " + idStr;
            }
            lines.add(frameString);
        }
        lines.add("}");
        lines.add("parts {");
        lines.add("}");

        Arrays.asList("The first line", "The second line");

        try {
            Path file = Paths.get(fileName + ".score");
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Sequence sequence = readFromURL("http://www.midiworld.com/download/4522");
        notes = new TreeMap<>();
        frames = new TreeMap<>();
        parts = new TreeMap<>();

        for(Patch patch : sequence.getPatchList()) {
            System.out.println(patch.toString());
        }

        // For every MIDI track
        for (Track track : sequence.getTracks())
        {
            // For every MIDI event
            for (int i = 0; i < track.size(); i++)
            {
                // Some information about this MIDI event
                MidiEvent event     = track.get(i);
                MidiMessage message = event.getMessage();
                Long tick           = event.getTick();

                // If it's a MIDI short message
                if (message instanceof ShortMessage)
                {
                    // Cast it to a MIDI short message
                    ShortMessage sm = (ShortMessage)message;
                    int channel     = sm.getChannel();

                    // If it's a note on message
                    if (sm.getCommand() == NOTE_ON)
                    {
                        Note note       = new Note();
                        int pitch       = sm.getData1();
                        int velocity    = sm.getData2();

                        note.setPitch(pitch);
                        note.setVelocity(velocity);
                        note.setAttack(tick);

                        notes.put(note.getUID(),note);

                        if(frames.containsKey(tick))
                        {
                            frames.get(tick).add(note);
                        }
                        else
                        {
                            frames.put(tick,new ArrayList<Note>());
                            frames.get(tick).add(note);
                        }
                    }
                    // If it's a note off message
                    else if (sm.getCommand() == NOTE_OFF)
                    {
                        //Pitch pitch     = new Pitch(sm.getData1())
                    }
                    // If it's some other MIDI short message
                    else
                    {
                        // Not sure what to do here
                    }
                }
                // If it's a MIDI short message
                else if (message instanceof MetaMessage)
                {
                    // Cast it to a MIDI short message
                    MetaMessage mm = (MetaMessage)message;
                    int type = mm.getType();
                    if(type == 0x51) {
                        System.out.println("A:" + mm.getData().toString());
                    }
                    if(type == 0x20) {
                        System.out.println("MIDI Channel prefix:" + mm.getData().toString());
                    }
                }
                // If it's a SysEx Message
                else
                {

                }
            }
        }
        // Print everything out
        for(Long tick : frames.keySet())
        {
            // System.out.println(tick.toString() + " \t: " +frames.get(tick).toString());
        }
    }

}*/
