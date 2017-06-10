package base.sound;

/**
 * PitchedSounds are those Sounds which have a definite Pitch.
 */
public class PitchedSound extends Sound {

    /** The Pitch of this PitchedSound */
    private Pitch pitch;

    /**
     * Constructor for a PitchedSound.
     * @param pitch The Pitch of this PitchedSound
     * @param technique The Technique used to generate this PitchedSound
     * @param dynamic The Dynamic level at which this PitchedSound is played
     */
    public PitchedSound(Pitch pitch, Technique technique, Dynamic dynamic) {
        super(technique, dynamic);
        this.pitch = pitch;
    }

    /**
     * A getter for the Pitch of this PitchedSound.
     * @return The Pitch of this PitchedSound
     */
    public Pitch getPitch() {
        return pitch;
    }
}
