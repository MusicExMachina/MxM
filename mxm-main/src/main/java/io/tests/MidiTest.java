package io.tests;

import io.MidiTools;
import org.junit.Test;

import javax.sound.midi.Sequence;

/**
 *
 */
public class MidiTest {

    /**
     * Tests that you can run
     */
    @Test
    public void basicTest() {
        try {
            //MidiTools.printSequencerInfo();
            //Sequence sequence = MidiTools.download("http://www.midiworld.com/download/4522");
            //Sequence sequence = MidiTools.download("http://www.mfiles.co.uk/downloads/edvard-grieg-peer-gynt1-morning-mood.mid");
            //Sequence sequence = MidiTools.download("http://www.classicalmidi.co.uk/music2/Pergynt4.mid");
            //Sequence sequence = MidiTools.load("C:/users/celenp/desktop/test.mid");
            Sequence sequence = MidiTools.load("schubertImpromptu.mid");
            MidiTools.parse(sequence);
            MidiTools.play(sequence);
            Thread.sleep(100000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests that you can run
     */
    @Test
    public void playTest() {
        System.out.print("A");
        try {
            System.out.print("B");
            //Sequence sequence1 = MidiTools.download("http://www.midiworld.com/midis/other/n1/EspanjaPrelude.mid");
            Sequence sequence2 = MidiTools.download("http://www.midiworld.com/download/4573");
            System.out.print("C");
            //MidiTools.play(sequence1);
            MidiTools.play(sequence2);
            System.out.print("D");
            Thread.sleep(100000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
