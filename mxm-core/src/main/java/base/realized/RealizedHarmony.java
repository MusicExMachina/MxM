package base.realized;

import base.relative.ChordClass;
import base.sounds.Chord;
import base.relative.PitchClass;

public class RealizedHarmony extends Chord implements IRealized {
    public RealizedHarmony(PitchClass root, ChordClass chordClass) {
        super(root, chordClass);
    }
}
