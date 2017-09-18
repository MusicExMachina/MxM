import properties.sound.Chord;
import properties.sound.Pitch;
import properties.time.Tempo;
import properties.time.Time;
import form.score.LeadSheet;
import form.part.Line;

import java.util.logging.Level;
import java.util.logging.Logger;

import static properties.sound.ChordClass.*;
import static properties.sound.PitchClass.*;

public class Test {
    private static final Logger LOGGER = Logger.getLogger( Test.class.getSimpleName() );

    static {
        LOGGER.setLevel(Level.ALL);
    }

    public static void main(String[] args) {
        LOGGER.setLevel(Level.ALL);
        // ========================================================================================================= //
        System.out.println("Creating lead sheet");
        LeadSheet leadSheet = new LeadSheet("My lead sheet");
        Line<Pitch> tune = leadSheet.getTune();
        Line<Chord> changes = leadSheet.getChanges();
        // ========================================================================================================= //
        leadSheet.add(Tempo.get(120),Time.get(0));
        leadSheet.add(Tempo.get(100),Time.get(10));
        leadSheet.add(Tempo.get(120),Time.get(32));
        // ========================================================================================================= //
        System.out.println("Creating tune");
        tune.add(Pitch.get(C_NATURAL,4),        Time.get(1,4))
                .add(Pitch.get(D_NATURAL,4),    Time.get(1,4))
                .add(Pitch.get(E_NATURAL,4),    Time.get(1,4))
                .add(Pitch.get(F_NATURAL,4),    Time.get(1,4))
                .add(Pitch.get(G_NATURAL,4),    Time.get(1,4))
                .add(Pitch.get(A_NATURAL,4),    Time.get(1,4))
                .add(Pitch.get(B_NATURAL,4),    Time.get(1,4))
                .add(Pitch.get(C_NATURAL,5),    Time.get(1,4));
        // ========================================================================================================= //
        System.out.println("Creating changes");
        changes.add(Chord.get(C_NATURAL,MAJOR),         Time.get(1,2))
                .add(Chord.get(D_NATURAL,MINOR),        Time.get(1,2))
                .add(Chord.get(G_NATURAL,MAJOR),        Time.get(1,2))
                .add(Chord.get(C_NATURAL,DOM_SEVENTH),  Time.get(1,2))
                .add(Chord.get(C_NATURAL,MAJOR),        Time.get(1));
        // ========================================================================================================= //
        leadSheet.getTimeSigChanges().forEach(tsc -> LOGGER.log(Level.FINE,tsc.getTimeSig().toString()));
        leadSheet.getTempoChanges().forEach(tc -> LOGGER.log(Level.FINE,tc.getTempo().toString()));
        // ========================================================================================================= //
        tune.getNotes().forEach(note -> LOGGER.log(Level.FINE,note.getSound().toString()));
        changes.getNotes().forEach(note -> LOGGER.log(Level.INFO,note.getSound().toString()));
        // ========================================================================================================= //
    }
}
