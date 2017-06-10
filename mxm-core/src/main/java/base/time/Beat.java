package base.time;

/**
 * Created by celenp on 2/11/2017.
 */
public class Beat implements Comparable<Beat>{
    private int numerator;
    private int denominator;

    public Beat(int numerator, int denominator) {

        if(denominator > 0) {
            this.numerator = numerator;
            this.denominator = denominator;
            reduce();
            //this.countClass  = new Beat(numerator,denominator);
        }
        else {
            throw new Error("Cannot create a base.time.Count with a denominator less than or equal to zero.");
        }
    }

    /**
     * A getter for this base.time.Count's numerator.
     * @return This base.time.Count's numerator.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * A getter for this base.time.Count's denominator.
     * @return This base.time.Count's denominator.
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Converts this base.time.Count to a float.
     * @return This base.time.Count's float value.
     */
    public float toFloat() {
        return (float)numerator/denominator;
    }

    /**
     * Converts this base.time.Count to a double.
     * @return This base.time.Count's double value.
     */
    public double toDouble() {
        return (double)numerator/denominator;
    }

    /**
     * Reduces the base.time.Count's internal fraction, a vital
     * method which is run exactly once- every time a
     * new base.time.Count is created. This could be lumped in
     * the constructor, but why do that?
     */
    private void reduce() {
        // Euclid's gcd algorithm, everyone's favorite
        int a = numerator;
        int b = denominator;

        if(a == 0 && b == 0) {
            throw new IllegalArgumentException("Undefined Count: (0,0)");
        }
        else if(a == 0){
            denominator /= b;
            return;
        }
        else if(b == 0){
            numerator /= a;
            return;
        }

        while (a != b) {
            if (a > b) {
                a -= b;
            }
            else {
                b -= a;
            }
        }

        // Reduce both numerator and denominator
        // by "a," the greatest common factor.
        numerator   /= a;
        denominator /= a;
    }

    @Override
    public int compareTo(Beat o) {
        return Double.compare(this.toDouble(),other.toDouble());
    }

}
