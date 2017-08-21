package io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * MxmScoreWriter allows Passages to be written into .mxm files, an internal format
 * which stores all the information of a passage in a human-readable text
 * file, formatted such that it can be read again by the MxmScoreReader class.
 */
public class MxmScoreWriter extends Writer<TraditionalScore> {

    /**
     * Write takes a ScoreEvent and writes it to a file of the given name.
     * @param passage The ScoreEvent to write to a .mxm file
     * @param filename The name of the file (before .mxm is added)
     */
    @Override
    public void write(TraditionalScore passage, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename + ".mxm", "UTF-8");

            writer.println("> ScoreEvent");
            writer.println("  - Title");
            // writer.println("      " + passage.getName());
            writer.println("  - ScoreBuilder");
            writer.println("      " + passage.getComposer());
            writer.println("");

            /*
            // Write all of the base.time signature information
            writer.println("  > ITime Signatures");
            Iterator<TimeSignEvent> timeSigItr = passage.timeSignChangeIterator();
            while (timeSigItr.hasNext()) {
                Count count             = new Count(timeSigItr.next());
                TimeSig timeSig   = passage.getTimeSignatureAt(count);
                String measureString    = padRight(String.valueOf(count.getMeasure()),4);
                String numString        = String.valueOf(timeSig.getNumerator());
                String denomString      = String.valueOf(timeSig.getDenominator());
                writer.println("      " + measureString + "            " + numString + " / " + denomString);
            }
            writer.println("");

            writer.println("  > Tempi");
            Iterator<Count> tempoChangeItr = passage.tempoChangeIterator();
            while (tempoChangeItr.hasNext()) {
                Count count = tempoChangeItr.next();
                Tempo tempo = passage.getTempoAt(count);
                String numString    = padRight(String.valueOf(count.getNumerator()),5);
                String denomString  = padRight(String.valueOf(count.getDenominator()),5);
                String tempoString  = padRight(String.valueOf(tempo.getBPM()),5);
                writer.println("      " + numString + "/ " + denomString + "    " + tempoString);
            }
            writer.println("");

            writer.println("  > Parts");
            for(Part part : passage) {
                writer.println("    - Part");
                writer.println("      - Instrument");
                writer.println("          " + part.getInstrument().toString() + "");
                writer.println("      > Notes");

                int lastMeasure = 0;
                for(Note note : part) {

                    int measure = note.getStart().getMeasure();

                    if(measure != lastMeasure) {
                        writer.println("          m. " + padRight(String.valueOf(measure),3) + "------------------------------");
                        lastMeasure = measure;
                    }

                    String startNumStr      = padLeft(String.valueOf(note.getStart().getNumerator() % note.getStart().getDenominator()),4);
                    String startDenomStr    = padRight(String.valueOf(note.getStart().getDenominator()),5);
                    String endNumStr        = padLeft(String.valueOf(note.getEnd().getNumerator() % note.getEnd().getDenominator()),4);
                    String endDenomStr      = padRight(String.valueOf(note.getEnd().getDenominator()),5);

                    String startStr = startNumStr + " / " + startDenomStr;
                    String endStr = endNumStr + " / " + endDenomStr;
                    String pitchStr = String.valueOf(note.getPitch().getValue());

                    writer.println("          " + startStr + "    " + endStr + "    " +  pitchStr);
                }
            }
            */
            writer.close();
        } catch (IOException e) {
            // do something
        }
    }

    @Override
    public void write(Collection<TraditionalScore> types, String filename) {

    }

    /**
     * Takes string and pads it with spaces to ensure it is at least a
     * given length, with the text occupying the left portion of the
     * returned string.
     * @param string The String to pad
     * @param size The number of characters total
     * @return The resultant String
     */
    private static String padRight(String string, int size) {
        String toReturn = string;
        while(toReturn.length() < size) {
            toReturn += " ";
        }
        return toReturn;
    }

    /**
     * Takes string and pads it with spaces to ensure it is at least a
     * given length, with the text occupying the right portion of the
     * returned string.
     * @param string The String to pad
     * @param size The number of characters total
     * @return The resultant String
     */
    private static String padLeft(String string, int size) {
        String toReturn = string;
        while(toReturn.length() < size) {
            toReturn = " " + toReturn;
        }
        return toReturn;
    }

}
