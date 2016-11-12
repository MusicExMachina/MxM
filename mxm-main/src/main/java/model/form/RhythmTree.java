package model.form;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * A conceptualization of rhythm as gradual, equal subdivisions of some concrete amount of time.
 * RhythmTrees operate like many other data structures save that their only "real" storage is in
 * their leaf nodes.
 */
public class RhythmTree {

    /** How deep a rhythm can go, Node-wise */
    public static int MAX_DEPTH = 8;

    /** Saves the root RhythmNode **/
    private RhythmNode root;

    /** The RhythmTree default constructor */
    public RhythmTree() {
        root = new RhythmNode();
    }

    /**
     * Quick constructor to make rhythm tree (mainly for testing)
     * @param subDiv list of subdivisions in Breadth First order
     */
    public RhythmTree(int[] subDiv) throws IllegalArgumentException{
        Queue<RhythmNode> constructQueue = new ArrayDeque<RhythmNode>();

        root = new RhythmNode();
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

    }

    /**
     * Returns a nicely-formatted string
     * representing this RhythmTree.
     * @return A String of this RhythmTree.
     */
    public String toString() {
        return root.toString();
    }

    /**
     * The RhythmTree copy constructor
     * @param other the RhythmTree to copy
     */
    public RhythmTree(RhythmTree other) {

    }

    /**
     * Getter for the root Node which fills the whole measure.
     * @return
     */
    public RhythmNode getRoot() {
        return root;
    }

    /**
     * Creates list from RhythmTree based on number of subdivisions
     * @return List of subdivisions breadth first order
     */
    public ArrayList<Integer> toList() {
        ArrayList<Integer> list = new ArrayList<>();
        PriorityQueue<RhythmNode> q = new PriorityQueue<>();
        q.add(root);
        RhythmNode curr = root;
        while(!q.isEmpty()){
            list.add(root.getChildren().size());
            q.addAll(root.getChildren());
        }
        return list;
    }


    /**
     * Paint the rhythm tree
     * @param g Graphics2D object for painting
     * @param x top left corner or RhythmTree xcoord
     * @param y top left corner or RhythmTree ycoord
     */
    public void paint(Graphics2D g, int x, int y) {
        getRoot().paint(g, x, y);
    }
}












//@Override
//public Iterator<Frame> iterator() {
            /*
        Iterator<Frame> it = new Iterator<Frame>() {
            private Node node = root;

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
        */
//   return null;
//}
