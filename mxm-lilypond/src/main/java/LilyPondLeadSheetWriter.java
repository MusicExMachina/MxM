/**
 * Created by celenp on 5/15/2017.
 */
public class LilyPondLeadSheetWriter {

    public static void main(String args[]) {
        writeBookEntry();
    }

    public static void writeBookEntry() {
        String songName = "Song";
        String songStyle = "Default";
        String composerName = "MxM";
        String tableOfContentsName = songName + "/" + composerName;

        // The bookpart block establishes this as a chapter-of-sorts in an overall book of pieces. Note that all the
        // Lilypond styling here is based on OpenBook, which may be found at https://github.com/veltzer/openbook
        System.out.println("\\bookpart {");
        System.out.println("\t\\tocItem \\markup \"" + tableOfContentsName + "\"");
        System.out.println("\t\\markup {");
        System.out.println("\t\t\\column {");
        System.out.println("\t\t\t\\override #'(baseline-skip . 3.5)");
        System.out.println("\t\t\t\\column {");
        System.out.println("\t\t\t\t\\huge \\larger \\bold");
        System.out.println("\t\t\t\t\\fill-line { \\larger \"Song Name\" }");
        System.out.println("\t\t\t\t\\fill-line { \"Music by " + composerName + "\" }");
        System.out.println("\t\t\t\t\\fill-line { \"" + songStyle + "\" }");
        System.out.println("\t\t\t}");
        System.out.println("\t\t}");
        System.out.println("\t}");
        System.out.println("\t\\noPageBreak");
        writeScore();
    }

    public static void writeScore() {
        System.out.println("\t\\score {");
        System.out.println("\t\t<<\t");
        System.out.println("\t\t\\new ChordNames = \"Chords\"");
        System.out.println("\t\t\\with {\t\\remove \"Bar_engraver\" }");
        System.out.println("\t\t\\chordmode {");
        System.out.println("\t\t\t\\startChords");
        System.out.println("\t\t\t\\startSong");
        printChords();
        System.out.println("\t\t\t\\endSong");
        System.out.println("\t\t\t\\endChords");
        System.out.println("\t\t}");
    }

    public static void printChords() {
        System.out.println("\t\t\t% This is where a pickup measure goes: \"\\partial\"");
        System.out.println("\t\t\t\\mySegno");
        System.out.println("\t\t\t\\startPart");
        System.out.println("\t\t\t\\repeat volta 2 {");
        System.out.println("\t\t\t\t% These are the chords to the A section, and every line ends with \"\\myEndLine\"");
        System.out.println("\t\t\t\tf:m7 | c:m11 | \\myCoda b:7.3-.5-.9-.11-.13- \\mark \\markup {D.S. al Coda} | \\myEndLine");
        System.out.println("\t\t\t}");
        System.out.println("\t\t\t\\endPart");
        System.out.println("\t\t\t\\startPart");
        System.out.println("\t\t\t\\repeat volta 2 {");
        System.out.println("\t\t\t\t% These are the chords to the B section, and every line ends with \"\\myEndLine\"");
        System.out.println("\t\t\t}");
        System.out.println("\t\t\t\\endPart");
    }
}