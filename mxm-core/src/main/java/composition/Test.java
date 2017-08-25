package composition;

import base.relative.ChordClass;
import base.sounds.Chord;
import base.sounds.Pitch;
import base.time.Time;
import passage.Line;

import static base.relative.ChordClass.*;
import static base.relative.PitchClass.*;

public class Test {
    public static void main(String[] args) {
        LeadSheet leadSheet = new LeadSheet("My lead sheet");
        Line<Pitch> tune = leadSheet.getTune();
        Line<Chord> changes = leadSheet.getChanges();

        tune.add(Pitch.get(C_NATURAL,4),        Time.get(1,4))
                .add(Pitch.get(D_NATURAL,4),    Time.get(1,4))
                .add(Pitch.get(E_NATURAL,4),    Time.get(1,4))
                .add(Pitch.get(F_NATURAL,4),    Time.get(1,4))
                .add(Pitch.get(G_NATURAL,4),    Time.get(1,4))
                .add(Pitch.get(A_NATURAL,4),    Time.get(1,4))
                .add(Pitch.get(B_NATURAL,4),    Time.get(1,4))
                .add(Pitch.get(C_NATURAL,5),    Time.get(1,4));


        changes.add(Chord.get(C_NATURAL, MAJOR),                Time.get(1,2))
                .add(Chord.get(D_NATURAL, MINOR),               Time.get(1,2))
                .add(Chord.get(G_NATURAL, MAJOR),               Time.get(1,2))
                .add(Chord.get(C_NATURAL, DOMINANT_SEVENTH),    Time.get(1,2))
                .add(Chord.get(C_NATURAL, MAJOR),               Time.get(1));
    }
}
