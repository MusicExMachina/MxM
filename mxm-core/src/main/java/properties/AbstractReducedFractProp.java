package properties;

/**
 * <p> <b>Class overview:</b>
 * This class wraps a basic property: an integer for use in classes like {@link properties.time.Tempo} or even
 * {@link properties.sound.Pitch}.</p>
 *
 * <p> <b>Design Details:</b>
 * This abstract class represents an underlying property, and provides basic variables and methods for all derived
 * classes to utilize. Essentially, this just prevents unnecessary code duplication. </p>
 *
 * @author Patrick Celentano
 */
public abstract class AbstractReducedFractProp extends AbstractFractionProp {

    //////////////////////////////
    // Member methods           //
    //////////////////////////////

    /**
     * A constructor for a fraction-based property which is inherently reduced
     * @param num the numerator of this fraction
     * @param den the denominator of this fraction
     */
    protected AbstractReducedFractProp(int num, int den) {
        super(num,den);
        reduce();
    }
    /**
     * This method forces reduction of the underlying {@link AbstractFractionProp}, called immediately after it is
     * initialized. This ensures that {@link AbstractReducedFractProp}s are always reduced.
     */
    private void reduce() {
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
