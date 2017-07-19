package time;

/**
 * Created by celenp on 7/13/2017.
 */
public class Measure implements ITime {

    public static Measure PICKUP    = new Measure(0);
    public static Measure ONE       = new Measure(1);

    private int number;

    public Measure(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    /**
     * Converts this count to a float.
     * @return This count's float value.
     */
    public float toFloat() {
        return (float)number;
    }

    /**
     * Converts this count to a double.
     * @return This count's double value.
     */
    public double toDouble() {
        return (double)number;
    }


    @Override
    public int compareTo(ITime o) {
        return 0;
    }

    @Override
    public int compare(ITime o1, ITime o2) {
        return 0;
    }

    public String toString() {
        if(number == 0)
            return "m. pickup";
        else
            return "m." + number;
    }

}
