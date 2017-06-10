package base.sound;

/**
 * Sound represents any "type" of Sound that can be produced. Note that Pitches are "types" of Sound that have definite
 * ordering and relationships (called Intervals). Other sounds, however- such as a bass drum hit- may have no easily-
 * definable relationship to other sounds produced on the same instrument (such as a drum roll).
 *
 * Note that the Sound class often wraps around the MIDI pitch class, given that different drum sounds, for example,
 * are assigned to different pitches on a MIDI keyboard. This class, however, prevents a learning algorithm from
 * identifying characteristics such as "an octave" in such examples as no such relations exist.
 *
 * TODO: Semi-pitched instruments, such as certain types of drums, may require a special Sound subclass like "SemiPitch"
 */
public abstract class Sound {

    /** The technique required to make this Sound. */
    private Technique technique;

    /** A measure of this Sound's loudness. */
    private Dynamic dynamic;

    /**
     * Constructor for a Sound, utilized by subclasses.
     * @param technique The Technique used to generate this PitchedSound
     * @param dynamic The Dynamic level at which this PitchedSound is played
     */
    Sound(Technique technique, Dynamic dynamic) {
        this.technique = technique;
        this.dynamic = dynamic;
    }

    public Dynamic getDynamic() {
        return dynamic;
    }

    public Technique getTechnique() {
        return technique;
    }
}
