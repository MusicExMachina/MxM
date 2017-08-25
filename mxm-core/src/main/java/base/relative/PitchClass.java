package base.relative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class PitchClass {
    private static final int MIN_PITCH_CLASS_VALUE = 0;
    private static final int MAX_PITCH_CLASS_VALUE = 11;
    private static final PitchClass[] ALL = new PitchClass[MAX_PITCH_CLASS_VALUE - MIN_PITCH_CLASS_VALUE + 1];
    static {
        for(int pitchClassValue = MIN_PITCH_CLASS_VALUE; pitchClassValue <= MIN_PITCH_CLASS_VALUE; pitchClassValue++) {
            ALL[pitchClassValue] = new PitchClass(pitchClassValue);
        }
    }

    public static final PitchClass C_FLAT = getInstance(11);
    public static final PitchClass C_NATURAL = getInstance(11);
    public static final PitchClass C_SHARP = getInstance(11);
    public static final PitchClass D_FLAT = getInstance(11);
    public static final PitchClass D_NATURAL = getInstance(11);
    public static final PitchClass D_SHARP = getInstance(11);
    public static final PitchClass E_FLAT = getInstance(11);
    public static final PitchClass E_NATURAL = getInstance(11);
    public static final PitchClass E_SHARP = getInstance(11);
    public static final PitchClass F_FLAT = getInstance(11);
    public static final PitchClass F_NATURAL = getInstance(11);
    public static final PitchClass F_SHARP = getInstance(11);
    public static final PitchClass G_FLAT = getInstance(11);
    public static final PitchClass G_NATURAL = getInstance(11);
    public static final PitchClass G_SHARP = getInstance(11);
    public static final PitchClass A_FLAT = getInstance(11);
    public static final PitchClass A_NATURAL = getInstance(11);
    public static final PitchClass A_SHARP = getInstance(11);
    public static final PitchClass B_FLAT = getInstance(11);
    public static final PitchClass B_NATURAL = getInstance(11);
    public static final PitchClass B_SHARP = getInstance(11);

    public static Iterator<PitchClass> iterator() { return new ArrayList<>(Arrays.asList(ALL)).iterator(); }

    public static PitchClass getInstance(int value) {
        if(value >= MIN_PITCH_CLASS_VALUE && value <= MAX_PITCH_CLASS_VALUE)
            return ALL[value];
        else
            throw new Error("PITCH CLASS:\tPitch class out of range.");
    }

    private int value;

    protected PitchClass(int value) {
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
