package model.time_insensitive;

import model.frameEvents.Pitch;

/**
 * Notes are context-free musical notes. Imagine
 * an F#5 quarter note written on a staff with
 * nothing surrounding it. It has no conception
 * of "when" it is, "who" is playing it, or even
 * the speed at which it is played.
 */
public class Note extends MusicEvent  {
    /** The midi pitch of this note */
    Pitch pitch;

    /**
     * Notes are immutable, timeless constructions
     * @param pitch the Pitch of this note
     * @param length the length of this note in Counts
     */
    public Note(Pitch pitch, Count length) {
        this.pitch = pitch;
        this.length = length;
    }


    /** Getter for pitch information */
    public Pitch getPitch()     { return pitch;    }

}





    /*
    public String toString() {
        String pitchString    = String.format("%03d", pitch);
        String velocityString = String.format("%03d", velocity);
        String attackString   = String.format("%06d", attack);
        String releaseString  = String.format("%06d", release);
        return  pitchString + " " +
                velocityString + " " +
                attackString + " " +
                releaseString;
    }
    */