package base.relative;

import java.util.ArrayList;
import java.util.Iterator;

public class PitchClass {

    private static final int MIN_PITCH_CLASS_VALUE = 0;
    private static final int MAX_PITCH_CLASS_VALUE = 11;
    private static final ArrayList<PitchClass> ALL = new ArrayList<>();
    static {
        for(int pitchClassValue = MIN_PITCH_CLASS_VALUE; pitchClassValue <= MIN_PITCH_CLASS_VALUE; pitchClassValue++)
            ALL.add(new PitchClass(pitchClassValue));
    }

    public static PitchClass C_FLAT     = getInstance(11);
    public static PitchClass C_NATURAL  = getInstance(0);
    public static PitchClass C_SHARP    = getInstance(1);
    public static PitchClass D_FLAT     = getInstance(1);
    public static PitchClass D_NATURAL  = getInstance(2);
    public static PitchClass D_SHARP    = getInstance(3);
    public static PitchClass E_FLAT     = getInstance(3);
    public static PitchClass E_NATURAL  = getInstance(4);
    public static PitchClass E_SHARP    = getInstance(5);
    public static PitchClass F_FLAT     = getInstance(4);
    public static PitchClass F_NATURAL  = getInstance(5);
    public static PitchClass F_SHARP    = getInstance(6);
    public static PitchClass G_FLAT     = getInstance(6);
    public static PitchClass G_NATURAL  = getInstance(7);
    public static PitchClass G_SHARP    = getInstance(8);
    public static PitchClass A_FLAT     = getInstance(8);
    public static PitchClass A_NATURAL  = getInstance(9);
    public static PitchClass A_SHARP    = getInstance(10);
    public static PitchClass B_FLAT     = getInstance(10);
    public static PitchClass B_NATURAL  = getInstance(11);
    public static PitchClass B_SHARP    = getInstance(0);

    public static Iterator<PitchClass> iterator() { return ALL.iterator(); }

    public static PitchClass getInstance(int value) {
        if(value >= MIN_PITCH_CLASS_VALUE && value <= MAX_PITCH_CLASS_VALUE)
            return ALL.get(value);
        else
            throw new Error("PITCH CLASS:\tPitch class out of range.");
    }

    private int value;

    public PitchClass(int value) {
        this.value = (byte)value;
    }

    public int getValue() {
        return value;
    }
    public PitchClass transpose(IntervalClass intervalClass) {
        return PitchClass.getInstance((value + intervalClass.getSize() % 12));
    }
    public IntervalClass minus(PitchClass other) {
        return IntervalClass.getInstance(other.value - this.value);
    }

    public float normalized() {
        return (float)(value - MIN_PITCH_CLASS_VALUE)/(float)(MAX_PITCH_CLASS_VALUE - MIN_PITCH_CLASS_VALUE);
    }
    public String toString() {
        switch (value) {
            case 0:     return "C";     case 1:     return "Db";
            case 2:     return "D";     case 3:     return "Eb";
            case 4:     return "E";     case 5:     return "F";
            case 6:     return "Gb";    case 7:     return "G";
            case 8:     return "Ab";    case 9:     return "A";
            case 10:    return "Bb";    case 11:    return "B";
            default:    return "ERROR";
        }
    }
    @Override
    public boolean equals(Object o) {
        return this == o;
    }
    @Override
    public int hashCode() {
        return value;
    }
}
