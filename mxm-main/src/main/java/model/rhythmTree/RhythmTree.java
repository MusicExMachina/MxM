package model.rhythmTree;

import model.basic.Count;

import model.form.Frame;
import java.awt.*;
import java.util.*;

/**
 * A conceptualization of rhythm as gradual, equal subdivisions of some concrete amount of time.
 * RhythmTrees operate like many other data structures save that their only "real" storage is in
 * their leaf nodes.
 */
public class RhythmTree {

    /** Saves the root RhythmNode **/
    private RhythmNode root;

    /** A TreeMap of all the Frames in this RhythmTree*/
    private TreeMap<Count,Frame> frames;

    /**
     * The rhythmTree default constructor
     */
    public RhythmTree() {
        root = new RhythmNode(this,null,Count.ZERO,Count.FULL_MEASURE);
        frames = new TreeMap<>();
    }

    /**
     * Quick constructor to make rhythm tree (mainly for testing)
     * @param subDiv list of subdivisions in Breadth First order
     */
    public RhythmTree(int[] subDiv) throws IllegalArgumentException{
        Queue<RhythmNode> constructQueue = new ArrayDeque<RhythmNode>();

        root = new RhythmNode(this,null,Count.ZERO,Count.FULL_MEASURE);
        constructQueue.add(root);

        RhythmNode currentNode;

        for (int aSubDiv : subDiv) {
            if (constructQueue.size() != 0) {
                currentNode = constructQueue.poll();
            } else {
                throw new IllegalArgumentException("List cannot be converted to a rhythm tree, not enough divisions");
            }
            if(aSubDiv != 1) {
                currentNode.subdivide(aSubDiv);
                constructQueue.addAll(currentNode.getChildren());
            }
        }

        if(constructQueue.size() != 0){
            throw new IllegalArgumentException("List cannot be converted to a rhythm tree, leftover divisions");
        }
        frames = new TreeMap<>();
    }

    /**
     * Returns a nicely-formatted string
     * representing this rhythmTree.
     * @return A String of this rhythmTree.
     */
    public String toString() {
        return root.toString();
    }

    /**
     * Getter for the root RhythmNode which fills the whole measure.
     * @return Gets the root RhythmNode of this RhythmTree.
     */
    public RhythmNode getRoot() {
        return root;
    }

    /**
     * Converts this RhythmTree to a List of Integer subdivisions
     * @return A List of Integer subdivisions.
     */
    public ArrayList<Integer> toList() {
        return root.toList();
    }

    /**
     *
     * @return
     */
    /*
    @Override
    public Iterator<RhythmNode> iterator() {
        Iterator<RhythmNode> it = new Iterator<Frame>() {
            private RhythmNode node = root;

            @Override
            public boolean hasNext() {
                RhythmNode nextNode           = null;
                int parentsChildNumber  = -1;
                nextNode= node.getParent();
                for(int i = 0; i < nextNode.getChildren().size(); i++) {
                    if(nextNode.children.get(i).equals(node)) {
                        parentsChildNumber = i;
                    }
                }
                if(parentsChildNumber < nextNode.children.size()) {

                }
                return currentIndex < currentSize && arrayList[currentIndex] != null;
            }

            @Override
            public MusicEvent next() {
                return arrayList[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
        return null;
    }
*/

    /**
     * Paint the rhythm tree
     * @param g Graphics2D object for painting
     * @param x top left corner or rhythmTree xcoord
     * @param y top left corner or rhythmTree ycoord
     */
    public void paint(Graphics2D g, int x, int y) {
        getRoot().paint(g, x, y);
    }
}