package learning.tests;

import learning.TextLSTM;
import org.junit.Test;

/**
 * Created by Patrick on 10/1/2016.
 */
public class TextLSMTest {
    @Test
    public void basicTest() {
        TextLSTM network = new TextLSTM("I am flacid on Tuesdays when the sun rises. ",50,2);
        network.run();
    }
}
