import base.Count;
import org.junit.Test;

public class DynamicTest {


    @Test
    public void countCreation(){
        Count ZERO;
        Count FULL_MEASURE;
        Count HALF_MEASURE;
        Count THIRD_MEASURE;
        Count QUARTER_MEASURE;
        Count EIGHTH_MEASURE;

        Count StartOfMeasure = new Count(4); //Measure 4, base.Count 1
        Count MiddleOfMeasure = new Count(2, 1); //Measure 2, base.Count 1
        Count TwoMeasures = new Count(1, 4, 3); //Starting at measure 1, a measure tied across the bar to the first in a triplet
        Count OddDivision = new Count(1, 9); //Odd division
        Count ManualDivision = new Count(0, 1);
        Count Reducible = new Count(3,9); //Should result in a triplet
    }
/*
    @Test
    public void countAction() {
        getMeasure;
        getBeat;
        getSubdivision;
    }

    @Test
    public void countMath() {
        plus;
        minus;
        times;
        reduce;
        compare;
        compareTo;
        equals;
    }

    @Test
    public void countConversion() {
        toFloat;
        toDouble;
        toString();
    }
*/
    @Test
    public void illegalCount() {
        //base.Count IllegalDivision = new base.Count(1, 0); //Can't divide by 0
    }


}