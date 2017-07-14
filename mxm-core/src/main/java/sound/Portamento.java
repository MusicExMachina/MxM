package sound;

/**
 * PortamentoSounds involve a bend between two discrete Pitches.
 */
public class Portamento implements Sound {

    /** The starting Pitch of this Portamento */
    private Pitch startPitch;

    /** The ending Pitch of this Portamento */
    private Pitch endPitch;

    /**
     * Constructor for a Portamento.
     * @param startPitch The starting Pitch of this Portamento
     * @param endPitch The ending Pitch of this Portamento
     * @param technique The Technique used to generate this Note
     * @param dynamic The Dynamic level at which this Note is played
     */
    public Portamento(Pitch startPitch, Pitch endPitch, Technique technique, Dynamic dynamic) {
        super();
        this.startPitch = startPitch;
        this.endPitch = endPitch;
    }

    /**
     * A getter for the starting Pitch of this Portamento.
     * @return The starting Pitch of this Portamento
     */
    public Pitch getStartPitch() {
        return startPitch;
    }

    /**
     * A getter for the ending Pitch of this Portamento.
     * @return The ending Pitch of this Portamento
     */
    public Pitch getEndPitch() {
        return endPitch;
    }
}
