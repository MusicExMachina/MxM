package base.analysis;

import base.sounds.Chord;
import base.relative.KeyClass;
import base.PitchClass;

import java.util.Set;

/**
 * Created by celenp on 5/13/2017.
 */
public class Key extends Chord {
    PitchClass tonic;
    KeyClass keyClass;

    public Key(Set<PitchClass> pitchClasses) {
        super(null,null);
    }
}
