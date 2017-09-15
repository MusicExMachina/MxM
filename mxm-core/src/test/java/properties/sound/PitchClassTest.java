package properties.sound;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static properties.sound.PitchClass.*;
import static org.junit.jupiter.api.Assertions.*;

class PitchClassTest {
    @Test
    void allValueTest() {
        assertTrue(C_FLAT.getValue() == 11);
        assertTrue(C_NATURAL.getValue() == 0);
        assertTrue(C_SHARP.getValue() == 1);
        assertTrue(D_FLAT.getValue() == 1);
        assertTrue(D_NATURAL.getValue() == 2);
        assertTrue(D_SHARP.getValue() == 3);
        assertTrue(E_FLAT.getValue() == 3);
        assertTrue(E_NATURAL.getValue() == 4);
        assertTrue(E_SHARP.getValue() == 5);
        assertTrue(F_FLAT.getValue() == 4);
        assertTrue(F_NATURAL.getValue() == 5);
        assertTrue(F_SHARP.getValue() == 6);
        assertTrue(G_FLAT.getValue() == 6);
        assertTrue(G_NATURAL.getValue() == 7);
        assertTrue(G_SHARP.getValue() == 8);
        assertTrue(A_FLAT.getValue() == 8);
        assertTrue(A_NATURAL.getValue() == 9);
        assertTrue(A_SHARP.getValue() == 10);
        assertTrue(B_FLAT.getValue() == 10);
        assertTrue(B_NATURAL.getValue() == 11);
        assertTrue(B_SHARP.getValue() == 0);
    }
    @Test
    void allEquivalenceTest() {
        // ==
        assertTrue(C_FLAT == get(11));
        assertTrue(C_NATURAL == get(0));
        assertTrue(C_SHARP == get(1));
        assertTrue(D_FLAT == get(1));
        assertTrue(D_NATURAL == get(2));
        assertTrue(D_SHARP == get(3));
        assertTrue(E_FLAT == get(3));
        assertTrue(E_NATURAL == get(4));
        assertTrue(E_SHARP == get(5));
        assertTrue(F_FLAT == get(4));
        assertTrue(F_NATURAL == get(5));
        assertTrue(F_SHARP == get(6));
        assertTrue(G_FLAT == get(6));
        assertTrue(G_NATURAL == get(7));
        assertTrue(G_SHARP == get(8));
        assertTrue(A_FLAT == get(8));
        assertTrue(A_NATURAL == get(9));
        assertTrue(A_SHARP == get(10));
        assertTrue(B_FLAT == get(10));
        assertTrue(B_NATURAL == get(11));
        assertTrue(B_SHARP == get(0));
        // .equals()
        assertTrue(C_FLAT.equals(get(11)));
        assertTrue(C_NATURAL.equals(get(0)));
        assertTrue(C_SHARP.equals(get(1)));
        assertTrue(D_FLAT.equals(get(1)));
        assertTrue(D_NATURAL.equals(get(2)));
        assertTrue(D_SHARP.equals(get(3)));
        assertTrue(E_FLAT.equals(get(3)));
        assertTrue(E_NATURAL.equals(get(4)));
        assertTrue(E_SHARP.equals(get(5)));
        assertTrue(F_FLAT.equals(get(4)));
        assertTrue(F_NATURAL.equals(get(5)));
        assertTrue(F_SHARP.equals(get(6)));
        assertTrue(G_FLAT.equals(get(6)));
        assertTrue(G_NATURAL.equals(get(7)));
        assertTrue(G_SHARP.equals(get(8)));
        assertTrue(A_FLAT.equals(get(8)));
        assertTrue(A_NATURAL.equals(get(9)));
        assertTrue(A_SHARP.equals(get(10)));
        assertTrue(B_FLAT.equals(get(10)));
        assertTrue(B_NATURAL.equals(get(11)));
        assertTrue(B_SHARP.equals(get(0)));
    }
    @Test
    void allItrTest() {
        Iterator<PitchClass> pitchClassItr = PitchClass.all().iterator();
        for(int value = 0; value < 12; value++) {
            assertTrue(pitchClassItr.next() == get(value));
        }
    }

    @Test
    void transposeTest() {
        for(int pcVal = 0; pcVal < 12; pcVal++) {
            PitchClass pitchClass = get(pcVal);
            for(int icVal = 0; icVal < 12; icVal++) {
                IntervalClass intervalClass = IntervalClass.get(icVal);
                PitchClass result = pitchClass.transpose(intervalClass);
                assertTrue(result.getValue() == (pcVal+icVal)%12);
            }
        }
    }

    @Test
    void equals() {
    }
}