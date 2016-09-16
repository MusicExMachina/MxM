package model.time_free;

/**
 * Pitch is a glorified byte wrapper,
 * which allows for a little more dress
 * and prevents problems down the line
 * with arguments to constructors.
 */
public class Pitch {

    /* Useful variable bounds */
    public static int MIN_PITCH = 0;
    public static int MAX_PITCH = 120;
    public static int MIN_OCTAVE = -1;
    public static int MAX_OCTAVE = 9;

    private final int value;

    /**
     * Constructor taking in a MIDI value
     * @param value The pitch's MIDI value
     */
    public Pitch(int value) {
        if(value >= MIN_PITCH && value <= MAX_PITCH) {
            this.value = value;
        }
        else {
            throw new Error("Invalid pitch range!");
        }
    }

    /**
     * Copy constructor for Pitch
     * @param other The other Pitch
     */
    public Pitch(Pitch other) {
        this.value = other.value;
    }

    /**
     * Gets the pitch class of this pitch
     * @return
     */
    public int getPitchClass() {
        return value % 12;
    }

    /**
     * Gets the octave of this pitch
     * @return
     */
    public int getOctave() {
        return value/12 + MIN_OCTAVE;
    }

}
