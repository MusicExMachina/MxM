package learning.tests;

import learning.TextLSTM;
import org.junit.Test;

/**
 * Created by Patrick on 10/1/2016.
 */
public class TextLSMTest {
    @Test
    public void basicTest() {
        TextLSTM network = new TextLSTM("this is an input string",20,4);
        network.run();
    }
}
