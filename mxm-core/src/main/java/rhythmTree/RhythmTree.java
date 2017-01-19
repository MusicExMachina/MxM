package rhythmTree;

import basic.Count;
import form.Note;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * A conceptualization of rhythm as gradual, equal subdivisions of some concrete amount of time.
 * RhythmTrees operate like many other data structures save that their only "real" storage is in
 * their leaf nodes.
 */
public class RhythmTree implements Iterable<RhythmNode> {

    /** Saves the root RhythmNode. */
    private RhythmNode root;

    /**
     * The rhythmTree default constructor
     */
    public RhythmTree(Integer measure) {
        root = new RhythmNode(this, null, new Count(measure), new Count(measure + 1));
    }

    /**
     * Quick constructor to make rhythm tree (mainly for testing)
     * @param subDiv list of subdivisions in Breadth First order
     */
    public RhythmTree(int[] subDiv) throws IllegalArgumentException {
        Queue<RhythmNode> constructQueue = new ArrayDeque<RhythmNode>();

        root = new RhythmNode(this, null, Count.ZERO, Count.ONE);
        constructQueue.add(root);

        RhythmNode currentNode;

        for (int aSubDiv : subDiv) {
            if (constructQueue.size() != 0) {
                currentNode = constructQueue.poll();
            } else {
                for (int subdiv: subDiv){
                    System.out.print(subdiv);
                }
                System.out.println();
                throw new IllegalArgumentException("List cannot be converted to a rhythm tree, not enough divisions");
            }
            if (aSubDiv != 1) {
                currentNode.subdivide(aSubDiv);
                constructQueue.addAll(currentNode.getChildren());
            }
        }

        if (constructQueue.size() != 0) {
            throw new IllegalArgumentException("List cannot be converted to a rhythm tree, leftover divisions");
        }
    }

    /**
     * Quick constructor to make painted rhythm tree (mainly for testing)
     * @param subDiv list of subdivisions in Breadth First order
     */
    public RhythmTree(int[] subDiv, Note[] colors) throws IllegalArgumentException {
        Queue<RhythmNode> constructQueue = new ArrayDeque<RhythmNode>();

        root = new RhythmNode(this, null, Count.ZERO, Count.ONE);
        constructQueue.add(root);

        RhythmNode currentNode;

        for (int i = 0; i < subDiv.length; i++) {
            int aSubDiv = subDiv[i];
            if (constructQueue.size() != 0) {
                currentNode = constructQueue.poll();
            } else {
                throw new IllegalArgumentException("List cannot be converted to a rhythm tree, not enough divisions");
            }
            if (aSubDiv != 1) {
                currentNode.subdivide(aSubDiv);
                constructQueue.addAll(currentNode.getChildren());
            }
            if (aSubDiv==1){
                currentNode.color(colors[i]);
            }
        }

        if (constructQueue.size() != 0) {
            throw new IllegalArgumentException("List cannot be converted to a rhythm tree, leftover divisions");
        }
    }

    /**
     * Returns a nicely-formatted string
     * representing this rhythmTree.
     *
     * @return A String of this rhythmTree.
     */
    public String toString() {
        return root.toString();
    }

    /**
     * Getter for the root RhythmNode which fills the whole measure.
     *
     * @return Gets the root RhythmNode of this RhythmTree.
     */
    public RhythmNode getRoot() {
        return root;
    }

    /**
     * Recursively creates a List of all RhythmNodes in this RhythmTree.
     *
     * @return A List of all RhythmNodes in this RhythmTree.
     */
    public List<RhythmNode> toList() {
        return root.toList();
    }

    /**
     * Converts this RhythmTree to a List of Integer subdivisions
     *
     * @return A List of Integer subdivisions.
     */
    public List<Integer> toSubdivisionList() {
        List<RhythmNode> nodes = toList();
        List<Integer> toReturn = new ArrayList<>();
        for (RhythmNode node : nodes) {
            toReturn.add(node.getSubdivisions());
        }
        return toReturn;
    }

    /**
     * Paint the rhythm tree
     * @param g Graphics2D object for painting
     * @param x top left corner or rhythmTree xcoord
     * @param y top left corner or rhythmTree ycoord
     */
    public void paint(Graphics2D g, int x, int y) {
        getRoot().paint(g, x, y);
    }

    @Override
    public Iterator<RhythmNode> iterator() {
        List<RhythmNode> nodes = toList();
        return toList().iterator();
    }
}