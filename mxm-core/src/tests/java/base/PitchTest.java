package base;

import musicTheory.Interval;
import sound.Pitch;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PitchTest {

    @Test
    public void equivalenceTests() {
        Pitch pitch1a = Pitch.getInstance(60);
        Pitch pitch1b = Pitch.getInstance(48).transpose(Interval.getInstance(12));
        Pitch pitch1c = Pitch.getInstance(58).transpose(Interval.getInstance(2));

        Pitch pitch2a = Pitch.getInstance(0);
        Pitch pitch2b = Pitch.getInstance(9).transpose(Interval.getInstance(-9));
        Pitch pitch2c = Pitch.getInstance(7).transpose(Interval.getInstance(-7));

        assertTrue(pitch1a.compareTo(pitch1b) == 0 && pitch1a.compareTo(pitch1c) == 0);
        assertTrue(pitch2a.compareTo(pitch2b) == 0 && pitch2a.compareTo(pitch2c) == 0);
    }

    @Test
    public void extremesTest(){
        Pitch lowPitch = Pitch.getInstance(0);
        Pitch highPitch = Pitch.getInstance(120);
    }
}