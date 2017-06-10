package base.sound;

/**
 * UnpitchedSounds are those Sounds which DO NOT have a definite Pitch.
 */
public class UnpitchedSound extends Sound {

    /**
     * Constructor for an UnpitchedSound
     * @param technique The Technique used to generate this PitchedSound
     * @param dynamic The Dynamic level at which this PitchedSound is played
     */
    protected UnpitchedSound(Technique technique, Dynamic dynamic) {
        super(technique, dynamic);
    }
}
