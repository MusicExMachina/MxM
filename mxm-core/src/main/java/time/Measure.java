package time;

import java.util.Comparator;

/**
 * Created by celenp on 7/13/2017.
 */
public class Measure implements ITime, Comparable<Measure>, Comparator<Measure> {

    public static Measure PICKUP    = new Measure(0);
    public static Measure ONE       = new Measure(1);

    private int number;

    public Measure(int number) {
        this.number = number;
    }

    public int getMeasureNum() {
        return number;
    }


    public Measure plus(Measure other) {
        return new Measure(number + other.number);
    }

    public Measure minus(Measure other) {
        return new Measure(number - other.number);
    }

    public float toFloat() {
        return (float)number;
    }

    public double toDouble() {
        return (double)number;
    }


    public String toString() {
        return number == 0 ? "m. pickup" : "m." + number;
    }

    @Override
    public int compareTo(Measure other) {
        return Integer.compare(number,other.number);
    }

    @Override
    public int compare(Measure measure1, Measure measure2) {
        return Integer.compare(measure1.number,measure2.number);
    }

    @Override
    public ITime plus(ITime other) {
        return null;
    }

    @Override
    public ITime minus(ITime other) {
        return null;
    }

    @Override
    public ITime times(int amount) {
        return null;
    }

    @Override
    public ITime dividedBy(int amount) {
        return null;
    }
}
