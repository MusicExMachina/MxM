package base;

import base.Count;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class CountTest {

    @Test
    public void zeroTests() {
        assertTrue(Count.ZERO.getMeasure() == 0);
        assertTrue(Count.ZERO.getNumerator() == 0);
        assertTrue(Count.ZERO.getDenominator() == 1);
        assertTrue(new Count(0).compareTo(Count.ZERO) == 0);
        assertTrue(new Count(0,1).compareTo(Count.ZERO) == 0);
        assertTrue(new Count(0,0,1).compareTo(Count.ZERO) == 0);
    }

    @Test
    public void equivalenceTests() {
        Count count1a = new Count(0);
        Count count1b = new Count(0,1);
        Count count1c = new Count(0,0,1);

        Count count2a = new Count(37);
        Count count2b = new Count(37,1);
        Count count2c = new Count(37,0,1);

        Count count3a = new Count(7,6);
        Count count3b = new Count(1,1,6);

        assertTrue(count1a.compareTo(count1b) == 0 && count1a.compareTo(count1c) == 0);
        assertTrue(count2a.compareTo(count2b) == 0 && count2a.compareTo(count2c) == 0);
        assertTrue(count3a.compareTo(count3b) == 0);
    }

    @Test
    public void addition(){
        Count count1a = new Count(1,4);
        Count count1b = new Count(1);
        Count count1c = new Count(5,4);
        Count count1d = new Count(1,1,4);
        Count sum1 = count1a.plus(count1b);

        assertTrue(sum1.compareTo(count1c) == 0);
        assertTrue(sum1.compareTo(count1d) == 0);

        Count count2a = new Count(19,5);
        Count count2b = new Count(1,5);
        Count count2c = new Count(4);
        Count count2d = new Count(20,5);
        Count sum2 = count2a.plus(count2b);

        assertTrue(sum2.compareTo(count2c) == 0);
        assertTrue(sum2.compareTo(count2d) == 0);
    }

    @Test
    public void subtraction(){
        Count count1a = new Count(1,4);
        Count count1b = new Count(1);
        Count count1c = new Count(-3,4);
        Count diff1 = count1a.minus(count1b);

        assertTrue(diff1.compareTo(count1c) == 0);

        Count count2a = new Count(19,5);
        Count count2b = new Count(1,5);
        Count count2c = new Count(4);
        Count count2d = new Count(18,5);
        Count diff2 = count2a.minus(count2b);

        assertTrue(diff2.compareTo(count2c) == 0);

        assertTrue(count1a.minus(count1a) == Count.ZERO);
        assertTrue(Count.ZERO.minus(Count.ZERO) == Count.ZERO);
    }
}