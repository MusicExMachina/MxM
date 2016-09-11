package model.time_insensitive;

/**
 * A Count is the fundamental measurement of
 * musical time. Note that Counts are essentially
 * just fractions. They're not really that special.
 */
public class Count {

    private static int PRIME_PRECISION = 16;
    private static int[] PRIMES = new int[]{
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
            59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113,
            127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181,
            191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251
    };

    int measure;
    byte[] numerators    = new byte[PRIME_PRECISION];
    byte[] denominators  = new byte[PRIME_PRECISION];

    /**
     *
     * @param numerator
     * @param denominator
     */
    public Count(int numerator, int denominator) {
        // First, glean the measure number
        measure = numerator/denominator;
        // Second, get the sub-measure fraction
        numerator = numerator%denominator;

        // Iterates over all the possible primes
        for(int i = PRIME_PRECISION-1; i >= 0; i++) {
            // While it's divisible by this prime
            while(numerator % PRIMES[i] == 0) {
                // Reduce by this prime
                numerator = numerator / PRIMES[i];
                // denominator = denominator / PRIMES[i];
                numerators[i]++;
            }
        }
    }

    float getValue() {
        float total = measure;
        // Add up all the constituent fractions
        for(int i = 0; i < PRIME_PRECISION;  i++) {
            total += numerators[i] / (PRIMES[i] * denominators[i]);
        }
        return total;
    }


}
