package io;

import basic.Count;
import basic.Pitch;
import form.Note;
import form.Part;
import form.Passage;

import java.io.IOException;
import java.io.PrintWriter;

public class Writer
{
    public static void write(Passage passage) {
        try {

            PrintWriter writer = new PrintWriter("output.mxm", "UTF-8");

            for(Part part : passage) {
                for(Note note : part) {
                    Count start = note.getStart();
                    Count end = note.getEnd();
                    Pitch pitch = note.getPitch();
                    writer.println("\t\t" + start + " | " + end + " | " +  pitch);
                }
            }

            writer.println("The first line");
            writer.println("The second line");
            writer.close();
        } catch (IOException e) {
            // do something
        }
    }
}
