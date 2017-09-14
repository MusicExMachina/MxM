import base.properties.Dynamic;
import base.sound.*;

import java.util.stream.IntStream;

public class OtherOtherTest {
    public static void main(String[] args) {
        IntStream.range(0,10).forEach(i -> {
            System.out.println(Pitch.random());
            System.out.println(PitchClass.random());
            System.out.println(Interval.random());
            System.out.println(IntervalClass.random());
            System.out.println(Dynamic.random());
        });
    }
}
