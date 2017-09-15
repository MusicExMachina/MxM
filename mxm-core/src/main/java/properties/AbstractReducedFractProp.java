package properties;

public abstract class AbstractReducedFractProp extends AbstractFractionProp {

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    protected AbstractReducedFractProp(int num, int den) {
        super(num,den);
        reduce();
    }

    protected void reduce() {
        // Euclid's gcd algorithm, everyone's favorite
        int a = numerator, b = denominator;
        if (a == 0) { denominator /= b; return; }
        if (b == 0) { numerator /= a; return; }
        while (a != b) {
            if (a > b) { a -= b; } else { b -= a; }
        }
        numerator /= a;
        denominator /= a;
    }

}
