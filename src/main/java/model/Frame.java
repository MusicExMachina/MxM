package model;

/**
 * A class for storing information about single events
 * in a Passage.
 */
public class Frame {

    /** The start of this Frame in Counts   */
    Count start;
    /** The length of this event in Counts  */
    Count length;
    /** The Pitch of this Frame             */
    Pitch pitch;
    /** The Dynamic of this Frame           */
    Dynamic dynamic;
    /** The Tempo of this Frame             */
    Tempo tempo;

    /**
     * A getter for this Frame's Pitch.
     * @return The Pitch of this Frame (null if a rest).
     */
    public Pitch getPitch() {
        return pitch;
    }

    /**
     * A setter for this Frame's Pitch.
     * @param pitch The Pitch of this Frame (null if a rest).
     */
    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    /**
     * A getter for this Frame's Dynamic level.
     * @return The Dynamic of this Frame.
     */
    public Dynamic getDynamic() {
        return dynamic;
    }

    /**
     * A setter for this Frame's Dynamic level.
     * @param dynamic The Dynamic of this Frame.
     */
    public void setDynamic(Dynamic dynamic) {
        this.dynamic = dynamic;
    }

    /**
     * A getter for this Frame's Tempo.
     * @return The Tempo of this Frame.
     */
    public Tempo getTempo() {
        return tempo;
    }

    /**
     * A setter for this Frame's Tempo.
     * @param tempo The Tempo of this Frame.
     */
    public void setTempo(Tempo tempo) {
        this.tempo = tempo;
    }
}
