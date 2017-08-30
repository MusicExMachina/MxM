/*
public class LilyPondLeadSheetWriter {

    String songName = "Song";
    String songStyle = "Default";
    String composerName = "base.MxM";

    public static void main(String args[]) {
        LilyPondLeadSheetWriter writer = new LilyPondLeadSheetWriter();
        writer.writeBookEntry();
    }

    public void writeBookEntry() {

        // The bookpart block establishes this as a chapter-of-sorts in an overall book of pieces. Note that all the
        // Lilypond styling here is based on OpenBook, which may be found at https://github.com/veltzer/openbook
        System.out.println("\\bookpart {");
        writeHeader();
        writeScore();
        writeCopyright();
        System.out.println("}");
    }

    private void writeHeader() {
        String tableOfContentsName = songName + "/" + composerName;

        System.out.println("\t\\tocItem \\markup \"" + tableOfContentsName + "\"");
        System.out.println("\t\\markup {");
        System.out.println("\t\t\\column {");
        System.out.println("\t\t\t\\override #'(baseline-skip . 3.5)");
        System.out.println("\t\t\t\\column {");
        System.out.println("\t\t\t\t\\huge \\larger \\bold");
        System.out.println("\t\t\t\t\\fill-line { \\larger \"" + songName + "\" }");
        System.out.println("\t\t\t\t\\fill-line { \"Music by " + composerName + "\" }");
        System.out.println("\t\t\t\t\\fill-line { \"" + songStyle + "\" }");
        System.out.println("\t\t\t}");
        System.out.println("\t\t}");
        System.out.println("\t}");
        System.out.println("\t\\noPageBreak");
    }

    private void writeScore() {
        System.out.println("\t\\score {");
        System.out.println("\t\t<<\t");
        writeChords();
        writeMelody();
        writeLyrics();
        System.out.println("\t\t>>");
        System.out.println("\t\t\\layout { }");
        System.out.println("\t}");
    }

    private void writeChords() {
        System.out.println("\t\t\\new ChordNames = \"Chords\"");
        System.out.println("\t\t\\with {\t\\remove \"Bar_engraver\" }");
        System.out.println("\t\t\\chordmode {");
        System.out.println("\t\t\t\\startChords");
        System.out.println("\t\t\t\\startSong");
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
        System.out.println("\t\t\t\\endSong");
        System.out.println("\t\t\t\\endChords");
        System.out.println("\t\t}");
    }

    private void writeMelody() {
        System.out.println("\t\t\\new Staff=\"Melody\" {");
        System.out.println("\t\t\t\\new Voice=\"Voice\"");
        System.out.println("\t\t\t\\relative c' {");
        System.out.println("\t\t\t\t\\tempo \"Allegro\" 4 = 168");
        System.out.println("\t\t\t\t\\base.time 4/4");
        System.out.println("\t\t\t\t\\key c \\major");
        System.out.println("\t\t\t\t% This is where a pickup measure goes: \"\\partial\"");
        System.out.println("\t\t\t\t\\repeat volta 2 {");
        System.out.println("\t\t\t\t\t% These are the notes for the A section");
        System.out.println("\t\t\t\t}");
        System.out.println("\t\t\t\t\\repeat volta 2 {");
        System.out.println("\t\t\t\t\t% These are the notes for the B section");
        System.out.println("\t\t\t\t}");
        System.out.println("\t\t\t}");
        System.out.println("\t\t}");
    }

    private void writeLyrics() {
        System.out.println("\t\t\\new Lyrics=\"Lyrics\" \\lyricsto \"Voice\"");

        // Each lyricmoded block contains all sections of music in order. Seperate blocks represent lyrics for
        // each base.time through (if there's a repeat, we might see different lyrics the second base.time around).

        System.out.println("\t\t\\lyricmode {");
        System.out.println("\t\t\t% These are the lyrics to the song");
        System.out.println("\t\t}");
        System.out.println("\t\t\\new Lyrics=\"Lyrics\" \\lyricsto \"Voice\"");
        System.out.println("\t\t\\lyricmode {");
        System.out.println("\t\t\t% These are the lyrics the second base.time through");
        System.out.println("\t\t}");
        System.out.println("\t\t\\new Lyrics=\"Lyrics\" \\lyricsto \"Voice\"");
        System.out.println("\t\t\\lyricmode {");
        System.out.println("\t\t\t% And these are the lyrics the third base.time through");
        System.out.println("\t\t}");
    }

    private void writeCopyright() {
        String curYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        System.out.println("\t\\noPageBreak");
        System.out.println("\t\\markup \\column {");
        System.out.println("\t\t\\null");
        System.out.println("\t\t\\fill-line { \\smaller \\smaller { \"Copyright © " +  curYear + " by " + composerName + "\" } }");
        System.out.println("\t\t\\fill-line { \\smaller \\smaller { \"Typeset by base.MxM, derived from OpenBook\" } }");
        System.out.println("\t}");
    }
}
*/