package model.form;

import model.rhythmTree.RhythmTree;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by celenp on 11/14/2016.
 */
public class Rhythm implements Iterable<RhythmTree> {

    private TreeMap<Integer,RhythmTree> trees;

    public Rhythm() {
        trees = new TreeMap<>();
    }

    public void add(int measure, RhythmTree tree) {
        if(!trees.containsKey(measure)) {
            trees.put(measure,tree);
        }
        else {
            throw new Error("RHYTHM:\tTrying to add a RhythmTree on top of another at measure " + measure + "!");
        }
    }

    @Override
    public Iterator<RhythmTree> iterator() {
        return trees.values().iterator();
    }
}
