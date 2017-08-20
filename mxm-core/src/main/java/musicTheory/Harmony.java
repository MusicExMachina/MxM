package musicTheory;

import sound.ISound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Harmony implements ISound, Iterable<PitchClass> {

    private ArrayList<PitchClass> factors;
    private HarmonyClass harmonyClass;

    public Harmony(Set<PitchClass> pitchClasses){
        this.factors = pitchClasses;
    }

    public Harmony(PitchClass root, HarmonyClass harmonyClass) {
        /* For every interval in the HarmonyClass (which holds all intervals above
            the root, add a chord factor that is also that high above the root */
        for(IntervalClass intervalClass : harmonyClass) {
            factors.add(root.transpose(intervalClass));
        }
        this.harmonyClass = harmonyClass;
    }

    @Override
    public Iterator<PitchClass> iterator() {
        return null;
    }

    /**
     * Created by celenp on 7/13/2017.
     */
    public static class Lyric implements ISound {
    }
}
