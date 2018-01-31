package form.time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeatTest {
    @Test
    void creationTest() {
        assertEquals(Beat.ZERO, Beat.of(0,1));
        assertEquals(Beat.ONE, Beat.of(1,1));
    }
    @Test
    void comparisonTest() {
        assertTrue(Beat.ONE.compareTo(Beat.ZERO) > 0);
        assertTrue(Beat.ONE.compareTo(Beat.of(1,1)) == 0);
        assertTrue(Beat.ZERO.compareTo(Beat.of(0,1)) == 0);
        assertTrue(Beat.ZERO.compareTo(Beat.ONE) < 0);
    }
}