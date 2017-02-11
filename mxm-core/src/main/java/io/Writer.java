package io;

import base.*;
import form.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

public class Writer
{
    public static void write(Passage passage, String filename) {
        try {

            PrintWriter writer = new PrintWriter(filename+".mxm", "UTF-8");

            writer.println("> form.Passage\n");
            writer.println("  - Title");
            writer.println("      PASSAGE TITLE");
            writer.println("");
            writer.println("  - Composer");
            writer.println("      COMPOSER NAME");
            writer.println("");
            writer.println("  - Period");
            writer.println("      PERIOD");
            writer.println("");

            writer.println("  > Time Signatures");
            Iterator<Integer> timeSigItr = passage.timeSignatureIterator();
            while (timeSigItr.hasNext()) {
                Count count = new Count(timeSigItr.next());
                TimeSignature timeSig = passage.getTimeSignatureAt(count);
                String measureString = StringUtils.rightPad(String.valueOf(count.getMeasure()),4);
                String numString = StringUtils.rightPad(String.valueOf(timeSig.getNumerator()),4);
                String denomString = StringUtils.rightPad(String.valueOf(timeSig.getDenominator()),4);
                writer.println("      " + measureString + "    " + numString + "  /  " + denomString);
            }
            writer.println("");

            writer.println("  > Tempi");
            Iterator<Count> tempoChangeItr = passage.tempoChangeIterator();
            while (tempoChangeItr.hasNext()) {
                Count count = tempoChangeItr.next();
                Tempo tempo = passage.getTempoAt(count);
                String numString = StringUtils.rightPad(String.valueOf(count.getNumerator()),5);
                String denomString = StringUtils.rightPad(String.valueOf(count.getDenominator()),5);
                String tempoString = StringUtils.rightPad(String.valueOf(tempo.getBPM()),5);
                writer.println("      " + numString + " /  " + denomString + " " + tempoString);
            }
            writer.println("");


            writer.println("  > Parts\n");
            for(Part part : passage) {
                writer.println("    - form.Part\n");
                writer.println("      - base.Instrument");
                writer.println("        " + part.getInstrument().toString() + "\n");
                writer.println("      > Notes\n");

                for(Note note : part) {
                    String startNumStr = StringUtils.rightPad(String.valueOf(note.getStart().getNumerator()),5);
                    String startDenomStr = StringUtils.leftPad(String.valueOf(note.getStart().getDenominator()),5);
                    String endNumStr = StringUtils.rightPad(String.valueOf(note.getEnd().getNumerator()),5);
                    String endDenomStr = StringUtils.leftPad(String.valueOf(note.getEnd().getDenominator()),5);

                    String startStr = startNumStr + " /  " + startDenomStr;
                    String endStr = endNumStr + " /  " + endDenomStr;
                    String pitchStr = StringUtils.leftPad(String.valueOf(note.getEnd().getDenominator()),5);

                    writer.println("          " + startStr + "        " + endStr + "        " +  pitchStr);
                }
                writer.println("");
            }
            writer.close();
        } catch (IOException e) {
            // do something
        }
    }
}
