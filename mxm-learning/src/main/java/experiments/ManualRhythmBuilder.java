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
//        form.Part l = new form.Part(base.Instrument.ACOUSTIC_GRAND_PIANO);
//        for (int i = 0; i < schubert.length; i++) {
//            int[] aGrieg = schubert[i];
//            form.Note[] cs = new form.Note[schubert[i].length];
//            for (int j = 0; j<schubert[i].length; j++){
//                cs[j] = new form.Note(base.Pitch.getInstance(60));
//            }
//            RhythmTree r = new RhythmTree(aGrieg, cs);
//            //l.add(new MidiMeasure(i, r));
//        }
//        System.out.println(l.getRhythm());
//
//        form.Score p = new form.Score();
//
//        p.add(l);
//        p.addTimeSignature(new base.TimeSignature(4, 4), 0);
//        p.addTempoChange(new base.Tempo(120), base.Count.ZERO);
//
//        Sequence seq = MidiTools.write(p);
//
//        System.out.println(p);
//
//        MidiTools.play(seq);
//    }
}
