package realized;

import musicTheory.Harmony;
import musicTheory.HarmonyClass;
import musicTheory.PitchClass;

import java.util.Set;

public class RealizedHarmony extends Harmony implements IRealized {
    public RealizedHarmony(Set<PitchClass> pitchClasses) {
        super(pitchClasses);
    }

    public RealizedHarmony(PitchClass root, HarmonyClass harmonyClass) {
        super(root, harmonyClass);
    }
}
