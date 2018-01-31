package form.time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {
    @Test
    void creationTest() {
        // Factory method with Measure argument
        assertEquals(Time.of(Measure.ONE), Time.of(Beat.ONE, Measure.PICKUP));
        assertEquals(Time.of(Measure.ONE), Time.of(Beat.ZERO, Measure.ONE));
        assertEquals(Time.of(Measure.PICKUP), Time.of(Beat.ZERO, Measure.PICKUP));

        // Factory method with Beat and Measure arguments
        assertEquals(Time.of(Beat.ONE, Measure.ONE), Time.of(Measure.of(2)));
        assertEquals(Time.of(Beat.ZERO, Measure.ONE), Time.of(Measure.of(1)));
        assertEquals(Time.of(Beat.ZERO, Measure.PICKUP), Time.of(Measure.of(0)));
    }
}