package base;

import base.relative.PitchClass;
import sound.ISound;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

// IMMUTABLE;
public class Sonority implements ISound {
    private TreeSet<Pitch> pitches;

    public Sonority(Set<Pitch> pitches) {
        this.pitches = new TreeSet<>(pitches);
    }


    public Sonority subSonority(Pitch from, Pitch to) {
        return new Sonority(pitches.subSet(from,to));
    }

    public Harmony getHarmony() {
        Set<PitchClass> pitchClasses = new HashSet<>();
        for(Pitch pitch : pitches) {
            pitchClasses.add(pitch.getPitchClass());
        }
        return new Harmony(pitchClasses);
    }
}
