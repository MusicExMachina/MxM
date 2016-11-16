package model.form;

import model.rhythmTree.RhythmTree;

import java.util.Iterator;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Rhythm is a class which allows easy access over a line's rhythm trees.
 * This also makes it a great deal more straightforward for the rhythmic
 * learning portion of this project- just take in rhythms and output more.
 */
public class Rhythm implements Iterable<RhythmTree> {

    /** The RhythmTrees which constitute this Rhythm. */
    private NavigableMap<Integer,RhythmTree> trees;

    /**
     * The rhythm constructor, which takes no input and
     * simply initializes the internal trees container.
     */
    public Rhythm() {
        trees = new TreeMap<>();
    }

    /**
     * Adds a new rhythm tree to this rhythm, or throws an error, if
     * there is already a rhythm tree of this measure in this rhythm.
     * @param measure The measure of this rhythm tree.
     * @param tree The rhythm tree itself.
     */
    public void add(int measure, RhythmTree tree) {
        if(!trees.containsKey(measure)) {
            trees.put(measure,tree);
        }
        else {
            throw new Error("RHYTHM:\tTrying to add a RhythmTree on top of another at measure " + measure + "!");
        }
    }

    /**
     * Returns a nicely-formatted string representing this rhythm.
     * @return A string representing this rhythm.
     */
    @Override
    public String toString() {
        String toReturn = "";
        for(Integer measure : trees.keySet()) {
            toReturn += "m " + measure + ". : " + trees.get(measure).toString() + "\n";
        }
        return toReturn;
    }

    /**
     * Returns an iterator over all the rhythm trees in this rhythm.
     * @return An iterator over all the rhythm trees in this rhythm.
     */
    @Override
    public Iterator<RhythmTree> iterator() {
        return trees.values().iterator();
    }
}
