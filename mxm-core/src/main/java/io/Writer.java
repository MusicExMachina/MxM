package io;

import base.*;
import form.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;


public class Writer
{
    public static void write(Passage passage, String filename) {
        try {

            PrintWriter writer = new PrintWriter("output.mxm", "UTF-8");

            writer.println("> Passage");
            writer.println("  - Title");
            writer.println("      PASSAGE TITLE");
            writer.println("  - Composer");
            writer.println("      COMPOSER NAME");
            writer.println("  - Period");
            writer.println("      PERIOD");

            writer.println("  > Time Signatures");
            Iterator<Integer> timeSigItr = passage.timeSignatureIterator();
            while (timeSigItr.hasNext()) {
                Count count = new Count(timeSigItr.next());
                TimeSignature timeSig   = passage.getTimeSignatureAt(count);
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
                writer.println("        " + part.getInstrument().toString() + "");
                writer.println("      > Notes");

                for(Note note : part) {
                    String startNumStr      = padRight(String.valueOf(note.getStart().getNumerator()),5);
                    String startDenomStr    = padRight(String.valueOf(note.getStart().getDenominator()),5);
                    String endNumStr        = padRight(String.valueOf(note.getEnd().getNumerator()),5);
                    String endDenomStr      = padRight(String.valueOf(note.getEnd().getDenominator()),5);

                    String startStr = startNumStr + "/ " + startDenomStr;
                    String endStr = endNumStr + "/ " + endDenomStr;
                    String pitchStr = padRight(String.valueOf(note.getPitch().getValue()),5);

                    writer.println("          " + startStr + "    " + endStr + "    " +  pitchStr);
                }
            }
            writer.close();
        } catch (IOException e) {
            // do something
        }
    }
    private static String padRight(String string, int size) {
        String toReturn = string;
        while(toReturn.length() < size) {
            toReturn += " ";
        }
        return toReturn;
    }
    private static String padLeft(String string, int size) {
        String toReturn = string;
        while(toReturn.length() < size) {
            toReturn = " " + toReturn;
        }
        return toReturn;
    }
}
