package time;

import java.util.Comparator;

/**
 * Created by celenp on 6/10/2017.
 */
public interface Time extends Comparator<Time>, Comparable<Time> {
    float toFloat();
    double toDouble();
    String toString();
}
