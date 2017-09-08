import io.MxmLog;
import base.harmony.Chord;
import base.pitch.Pitch;
import base.time.Tempo;
import base.time.Time;
import form.scoreTypes.LeadSheet;
import form.events.Note;
import form.events.TempoChange;
import form.events.TimeSigChange;
import form.timelines.Line;

import static base.harmony.ChordClass.*;
import static base.pitch.PitchClass.*;

public class Test {

    public static void main(String[] args) {
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
        for(TimeSigChange timeSigChange : leadSheet.getTimeSigChanges()) {
            MxmLog.log(timeSigChange.getTimeSig().toString(), 1);
        }
        for(TempoChange tempoChange : leadSheet.getTempoChanges()) {
            MxmLog.log(tempoChange.getTempo().toString(),1);
        }
        // ========================================================================================================= //
        for(Note<Pitch> note : tune) {
            MxmLog.log(note.getSound().toString(),0);
        }
        for(Note<Chord> note : changes) {
            MxmLog.log(note.getSound().toString(),0);
        }
        // ========================================================================================================= //
    }
}
