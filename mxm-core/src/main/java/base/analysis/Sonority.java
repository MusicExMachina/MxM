package base.analysis;

import base.sounds.Chord;
import base.sounds.ISound;
import base.sounds.Pitch;
import base.relative.PitchClass;

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

    public Chord getHarmony() {
        Set<PitchClass> pitchClasses = new HashSet<>();
        for(Pitch pitch : pitches) {
            pitchClasses.add(pitch.getPitchClass());
        }
        return null;// new Chord(pitchClasses);
    }
}
