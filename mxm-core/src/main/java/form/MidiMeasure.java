package form;

import rhythmTree.RhythmTree;

/**
 * Created by celenp on 11/16/2016.
 */
public class MidiMeasure {
    RhythmTree rhythmTree;
    int measure;

    public MidiMeasure(int measure, RhythmTree rhythmTree) {
        this.measure = measure;
        this.rhythmTree = rhythmTree;
    }

    public RhythmTree getRhythmTree() {
        return rhythmTree;
    }
    public int getMeasure() {
        return measure;
    }
}
