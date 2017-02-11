package io;

import basic.Count;
import basic.Tempo;
import form.Note;
import form.Part;
import form.Passage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class Writer
{
    public static void write(Passage passage) {
        try {

            PrintWriter writer = new PrintWriter("output.mxm", "UTF-8");

            writer.println("> Passage\n");
            writer.println("  - Title");
            writer.println("      Sonata in C");
            writer.println("");
            writer.println("  - Composer");
            writer.println("      Wolfgang Amadeus Mozart");
            writer.println("");
            writer.println("  - Period");
            writer.println("      Classical");
            writer.println("");

            writer.println("  > Time Signatures");
            Iterator<Integer> timeSigItr = passage.timeSignatureIterator();
            while (timeSigItr.hasNext()) {
                Count count = new Count(timeSigItr.next());
                Tempo tempo = passage.getTempoAt(count);
                String measureString = StringUtils.rightPad(String.valueOf(tempo.getBPM()),4);
                String numString = StringUtils.rightPad(String.valueOf(count.getNumerator()),4);
                String denomString = StringUtils.rightPad(String.valueOf(count.getDenominator()),4);
                writer.println(measureString + "      " + numString + "  /  " + denomString);
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
                writer.println("    - Part\n");
                writer.println("      - Instrument");
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
