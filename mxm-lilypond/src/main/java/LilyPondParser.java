import sound.Pitch;
import musicTheory.PitchClass;
import sound.Chord;
import musicTheory.ChordClass;

/**
 * Created by celenp on 5/16/2017.
 */
public class LilyPondParser {

    private Chord parseChord(String chordString) {
        String[] tokens = chordString.split(":");
        if (tokens.length != 2) {
            throw new Error("LILY POND PARSER:\t ChordNote string \"" + chordString + "\" improperly formatted.");
        }
        PitchClass pitch        = parsePitchClass(tokens[0]);
        ChordClass chordClass   = parseChordClass(tokens[1]);

        return new Chord(pitch,chordClass);
    }

    private Pitch parsePitch(String pitchString) {
        return null; // Pitch.getInstance(0, parsePitchClass(""));
    }

    private PitchClass parsePitchClass(String pitchClassString) {
        return null;
    }

    private ChordClass parseChordClass(String chordClassString) {
        switch (chordClassString) {
            case "":
            case "5":
                return ChordClass.MAJOR;
            case "m":
                return ChordClass.MINOR;
            case "aug":
                return ChordClass.AUGMENTED;
            case "dim":
                return ChordClass.DIMINISHED;
            case "7":
                return ChordClass.DOMINANT_SEVENTH;
            case "maj7":
            case "maj":
                return ChordClass.MAJOR_SEVENTH;
            case "m7":
                return ChordClass.MINOR_SEVENTH;
            case "dim7":
                return ChordClass.DIMINISHED_SEVENTH;
            case "aug7":
                return ChordClass.AUGMENTED_SEVENTH;
            case "m7.5-":
                return ChordClass.HALF_DIMINISHED_SEVENTH;
            case "m7+":
                return ChordClass.MINOR_MAJOR_SEVENTH;
            case "6":
                return ChordClass.MAJOR_SIXTH;
            case "m6":
                return ChordClass.MINOR_SIXTH;
            case "9":
                return ChordClass.DOMINANT_NINTH;
            case "maj9":
                return ChordClass.MAJOR_NINTH;
            case "m9":
                return ChordClass.MINOR_NINTH;
            case "11":
                return ChordClass.DOMINANT_ELEVENTH;
            case "maj11":
                return ChordClass.MAJOR_ELEVENTH;
            case "m11":
                return ChordClass.MINOR_ELEVENTH;
            case "13":
            case "13.11":
                return ChordClass.DOMINANT_THIRTEENTH;
            case "maj13.11":
                return ChordClass.MAJOR_THIRTEENTH;
            case "m13.11":
                return ChordClass.MINOR_THIRTEENTH;
            case "sus2":
                return ChordClass.SUSPENDED_SECOND;
            case "sus4":
                return ChordClass.SUSPENDED_FOURTH;
            case "1.5":
            case "1.8":
                return ChordClass.POWER_CHORD;
            default:
                String[] tokens = chordClassString.split(".");
                if (tokens.length != 2) {
                    throw new Error("LILY POND PARSER:\t ChordNote class string \"" + chordClassString + "\" improperly formatted.");
                }
        }
        return null;
    }

}