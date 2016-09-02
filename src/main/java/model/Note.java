package model;
/**
 * A Note in MxM is very similar to a single note in the musical
 * sense, save that an MxM note may also be a glissando.
 */
public class Note implements Comparable<Note>
{
    // Note information
    int uid;                    /** A unique ID number for this note   */
    int pitch;                  /** The midi pitch of this note        */
    int velocity;               /** The starting dynamic of this note  */
    long attack;                /** The timestep when this note begins */
    long release;               /** The timestep when this note ends   */

    static int lastUID = 0;    /** Ensures no two notes will have the same UID */

    /**
     * A basic constructor that only assigns the UID
     */
    public Note() {
        uid = lastUID++;
    }
    public void setPitch(int pitch)         { this.pitch = pitch;       }
    public void setVelocity(int velocity)   { this.velocity = velocity; }
    public void setAttack(long attack)      { this.attack = attack;     }
    public void setRelease(long release)    { this.release = release;   }

    public int getUID()                    { return uid; }

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

    // Allows Notes to be sorted by their pitches (useful for quick analysis)
    @Override
    public int compareTo(Note n)
    {
        return Integer.compare(uid,n.uid);
    }
}
