import networks.SimpleTextLSTM;
import networks.VisualizedTextLSTM;
import org.junit.Test;

/**
 * Created by Patrick on 10/1/2016.
 */
public class TextLSTMTest {
    @Test
    public void basicTest() {
        SimpleTextLSTM network = new SimpleTextLSTM("School ain't cool.",50,2);
        network.run();
    }
    @Test
    public void visualTest() {
        VisualizedTextLSTM network = new VisualizedTextLSTM("School ain't cool.",50,2);
        network.run();
    }
}
