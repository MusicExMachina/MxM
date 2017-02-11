package experiments;

import form.Part;
import basic.Count;
import basic.Pitch;
import basic.Tempo;
import basic.TimeSignature;
import form.Note;
import form.Passage;
import rhythmTree.RhythmTree;
import MidiTools;
import trainable.Instrument;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;

/**
 * Created by jpatsenker on 12/2/16.
 */
public class ManualRhythmBuilder {


    public static void main(String[] args) throws InvalidMidiDataException, MidiUnavailableException {
        int[][] grieg = new int[][]{new int[]{2,2,2,2,2,2,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1},
                                    new int[]{2,2,2,2,2,1,1,1,1,1,1},
                                    new int[]{2,2,2,2,2,2,2,1,1,1,1,1,1,1,1},
                                    new int[]{2,2,2,2,2,1,1,1,1,1,1},
                                    new int[]{2,2,2,2,2,2,1,1,1,1,1,1,1},
                                    new int[]{2,2,2,2,2,1,1,1,1,1,2,1,1},
                                    new int[]{2,2,2,2,1,1,1,1,1},
                                    new int[]{2,2,2,1,2,2,1,1,1,1,1},
                                    new int[]{2,2,2,2,2,1,1,1,1,2,1,1,1},
                                    new int[]{2,2,1,2,1,1,1}};

        int[][] schubert = new int[][]{new int[]{2,2,2,1,1,2,1,1,1},
                                        new int[]{2,2,2,1,1,2,1,1,2,1,1},
                                        new int[]{2,2,2,1,1,2,1,1,1},
                                        new int[]{2,2,2,2,1,1,1,1,1},
                                        new int[]{2,2,2,3,1,1,1,1,1,1},
                                        new int[]{2,2,2,2,1,1,1,1,1},
                                        new int[]{2,2,2,2,2,1,1,1,1,1,1},
                                        new int[]{2,2,2,2,1,1,2,1,1,2,1,1,2,2,1,1,2,1,1}};

        Part l = new Part(Instrument.ACOUSTIC_GRAND_PIANO);
        for (int i = 0; i < schubert.length; i++) {
            int[] aGrieg = schubert[i];
            Note[] cs = new Note[schubert[i].length];
            for (int j = 0; j<schubert[i].length; j++){
                cs[j] = new Note(Pitch.getInstance(60));
            }
            RhythmTree r = new RhythmTree(aGrieg, cs);
            //l.add(new MidiMeasure(i, r));
        }
        System.out.println(l.getRhythm());

        Passage p = new Passage();

        p.add(l);
        p.addTimeSignature(new TimeSignature(4, 4), 0);
        p.addTempoChange(new Tempo(120),Count.ZERO);

        Sequence seq = MidiTools.write(p);

        System.out.println(p);

        MidiTools.play(seq);
    }



}
