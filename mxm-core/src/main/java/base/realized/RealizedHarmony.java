package base.realized;

import base.Harmony;
import base.relative.HarmonyClass;
import base.relative.PitchClass;

import java.util.Set;

public class RealizedHarmony extends Harmony implements IRealized {
    public RealizedHarmony(Set<PitchClass> pitchClasses) {
        super(pitchClasses);
    }

    public RealizedHarmony(PitchClass root, HarmonyClass harmonyClass) {
        super(root, harmonyClass);
    }
}
