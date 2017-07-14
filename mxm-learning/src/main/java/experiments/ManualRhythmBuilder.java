package experiments;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;

/**
 * Created by jpatsenker on 12/2/16.
 */
public class ManualRhythmBuilder {
//    public static void main(String[] args) throws InvalidMidiDataException, MidiUnavailableException {
//        int[][] grieg = new int[][]{new int[]{2,2,2,2,2,2,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1},
//                                    new int[]{2,2,2,2,2,1,1,1,1,1,1},
//                                    new int[]{2,2,2,2,2,2,2,1,1,1,1,1,1,1,1},
//                                    new int[]{2,2,2,2,2,1,1,1,1,1,1},
//                                    new int[]{2,2,2,2,2,2,1,1,1,1,1,1,1},
//                                    new int[]{2,2,2,2,2,1,1,1,1,1,2,1,1},
//                                    new int[]{2,2,2,2,1,1,1,1,1},
//                                    new int[]{2,2,2,1,2,2,1,1,1,1,1},
//                                    new int[]{2,2,2,2,2,1,1,1,1,2,1,1,1},
//                                    new int[]{2,2,1,2,1,1,1}};
//
//        int[][] schubert = new int[][]{new int[]{2,2,2,1,1,2,1,1,1},
//                                        new int[]{2,2,2,1,1,2,1,1,2,1,1},
//                                        new int[]{2,2,2,1,1,2,1,1,1},
//                                        new int[]{2,2,2,2,1,1,1,1,1},
//                                        new int[]{2,2,2,3,1,1,1,1,1,1},
//                                        new int[]{2,2,2,2,1,1,1,1,1},
//                                        new int[]{2,2,2,2,2,1,1,1,1,1,1},
//                                        new int[]{2,2,2,2,1,1,2,1,1,2,1,1,2,2,1,1,2,1,1}};
//
//        form.Part l = new form.Part(sound.Instrument.ACOUSTIC_GRAND_PIANO);
//        for (int i = 0; i < schubert.length; i++) {
//            int[] aGrieg = schubert[i];
//            events.sounding.Note[] cs = new events.sounding.Note[schubert[i].length];
//            for (int j = 0; j<schubert[i].length; j++){
//                cs[j] = new events.sounding.Note(sound.Pitch.getInstance(60));
//            }
//            analysis.RhythmTree r = new analysis.RhythmTree(aGrieg, cs);
//            //l.add(new MidiMeasure(i, r));
//        }
//        System.out.println(l.getRhythm());
//
//        form.ScoreEvent p = new form.ScoreEvent();
//
//        p.add(l);
//        p.addTimeSignature(new time.TimeSign(4, 4), 0);
//        p.addTempoChange(new time.Tempo(120), base.Count.ZERO);
//
//        Sequence seq = MidiTools.write(p);
//
//        System.out.println(p);
//
//        MidiTools.play(seq);
//    }
}
