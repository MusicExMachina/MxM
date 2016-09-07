package model.time_insensitive;

/**
 * MusicEvents are indivisible actions which
 * take some amount of time and can be performed
 * on an instrument. These include Notes and
 * Rests, though things like Glissandos might be
 * included eventually.
 */
public abstract class MusicEvent {
    /** The length of this event in Counts  */
    Count length;

    /* Getters for relative rhythmic information (not timesteps) */
    public Count getLength()    {  return length;  }
}
