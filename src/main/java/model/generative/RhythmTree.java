package model.generative;

import model.structural.Count;
import model.structural.Frame;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

/**
 * A conceptualization of rhythm as gradual, equal
 * subdivisions of some concrete amount of time.
 * RhythmTrees operate like many other data structures
 * save that their only "real" storage is in their
 * leaf nodes. This
 */
public class RhythmTree /* implements Serializable, Cloneable, Iterable<Frame>, Collection<Frame>, NavigableSet<Frame>, Set<Frame>, SortedSet<Frame> */ {

    private RhythmNode root;
    List<List<RhythmNode>> layers;

    public static final int DRAW_RAD = 20;

    /**
     * The RhythmTree default constructor
     */
    public RhythmTree() {
        root = new RhythmNode();
    }

    /**
     * The RhythmTree copy constructor
     *
     * @param other the RhythmTree to copy
     */
    public RhythmTree(RhythmTree other) {

    }

    /**
     * Quick constructor to make rhythm tree (mainly for testing)
     *
     * @param subDiv list of subdivisions in Breadth First order
     */
    public RhythmTree(int[] subDiv) throws IllegalArgumentException {
        Queue<RhythmNode> constructQueue = new ArrayDeque<>();

        root = new RhythmNode();
        root.children = new ArrayList<>();
        constructQueue.add(root);

        RhythmNode currentNode;

        for (int aSubDiv : subDiv) {
            if (constructQueue.size() != 0) {
                currentNode = constructQueue.poll();
            } else {
                throw new IllegalArgumentException("List cannot be converted to a rhythm tree, not enough divisions");
            }
            if (aSubDiv != 1) {
                for (int j = 0; j < aSubDiv; j++) {
                    RhythmNode child = new RhythmNode();
                    child.children = new ArrayList<>();
                    child.parent = currentNode;
                    currentNode.children.add(child);
                    constructQueue.add(child);
                }
            }
        }

        if (constructQueue.size() != 0) {
            throw new IllegalArgumentException("List cannot be converted to a rhythm tree, leftover divisions");
        }

    }

    /*   HERE BE OVERRIDE METHODS    */
    /* ABANDON ALL HOPE YE WHO ENTER */











//@Override
//public Iterator<Frame> iterator() {
            /*
        Iterator<Frame> it = new Iterator<Frame>() {
            private Node node = root;

            @Override
            public boolean hasNext() {
                Node nextNode           = null;
                int parentsChildNumber  = -1;
                nextNode= node.parent;
                for(int i = 0; i < nextNode.children.size(); i++) {
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

    /**
     * Paint the rhythm tree
     *
     * @param g Graphics2D object for painting
     * @param x top left corner or RhythmTree xcoord
     * @param y top left corner or RhythmTree ycoord
     */
    public void paint(Graphics2D g, int x, int y) {
        root.paint(g, x, y);

    }
}