package base.time;

/**
 * Created by celenp on 6/9/2017.
 */
public class Measure implements Time {

    public static Measure PICKUP = new Measure(0);
    public static Measure ONE = new Measure(1);

    private int measureNumber;

    public Measure() {
        this.measureNumber = 1;
    }

    public Measure(int measureNumber) {
        this.measureNumber = measureNumber;
    }

    @Override
    public int compareTo(Time o) {
        return 0;
    }

    @Override
    public int compare(Time o1, Time o2) {
        return 0;
    }
}
