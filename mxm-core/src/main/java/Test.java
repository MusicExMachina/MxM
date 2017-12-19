import sound.Chord;
import sound.Pitch;
import time.Duration;
import time.Tempo;
import time.Time;
import form.LeadSheet;
import form.Line;

import java.util.logging.Level;
import java.util.logging.Logger;

import static sound.ChordClass.*;
import static sound.PitchClass.*;

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
        leadSheet.add(Tempo.of(120), Time.of(0));
        leadSheet.add(Tempo.of(100), Time.of(10));
        leadSheet.add(Tempo.of(120), Time.of(32));
        // ========================================================================================================= //
        System.out.println("Creating tune");
        tune.add(Pitch.get(C_NATURAL,4),        Duration.of(1,4))
                .add(Pitch.get(D_NATURAL,4),    Duration.of(1,4))
                .add(Pitch.get(E_NATURAL,4),    Duration.of(1,4))
                .add(Pitch.get(F_NATURAL,4),    Duration.of(1,4))
                .add(Pitch.get(G_NATURAL,4),    Duration.of(1,4))
                .add(Pitch.get(A_NATURAL,4),    Duration.of(1,4))
                .add(Pitch.get(B_NATURAL,4),    Duration.of(1,4))
                .add(Pitch.get(C_NATURAL,5),    Duration.of(1,4));
        // ========================================================================================================= //
        System.out.println("Creating changes");
        changes.add(Chord.get(C_NATURAL,MAJOR),         Duration.of(1,2))
                .add(Chord.get(D_NATURAL,MINOR),        Duration.of(1,2))
                .add(Chord.get(G_NATURAL,MAJOR),        Duration.of(1,2))
                .add(Chord.get(C_NATURAL,DOM_SEVENTH),  Duration.of(1,2))
                .add(Chord.get(C_NATURAL,MAJOR),        Duration.of(1));
        // ========================================================================================================= //
        leadSheet.getTimeSigChanges().forEach(tsc -> LOGGER.log(Level.FINE,tsc.getTimeSig().toString()));
        leadSheet.getTempoChanges().forEach(tc -> LOGGER.log(Level.FINE,tc.getTempo().toString()));
        // ========================================================================================================= //
        tune.getNotes().forEach(note -> LOGGER.log(Level.FINE,note.getSound().toString()));
        changes.getNotes().forEach(note -> LOGGER.log(Level.INFO,note.getSound().toString()));
        // ========================================================================================================= //
    }
}
