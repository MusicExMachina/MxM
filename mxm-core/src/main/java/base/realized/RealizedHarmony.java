package base.realized;

import base.relative.ChordClass;
import base.sounds.Chord;
import base.relative.PitchClass;

import java.util.Set;

public class RealizedHarmony extends Chord implements IRealized {
    public RealizedHarmony(PitchClass root, ChordClass chordClass) {
        super(root, chordClass);
    }
}
