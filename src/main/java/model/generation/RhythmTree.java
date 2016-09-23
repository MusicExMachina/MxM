package model.generation;

import java.util.*;

/**
 * A conceptualization of rhythm as gradual, equal
 * subdivisions of some concrete amount of time.
 * RhythmTrees operate like many other data structures
 * save that their only "real" storage is in their
 * leaf nodes.
 */
public class RhythmTree {

    /** How deep a rhythm can go, Node-wise */
    public static int MAX_DEPTH = 8;

    /** Saves each layer individually **/
    List<List<RhythmNode>> layers = new ArrayList<List<RhythmNode>>(MAX_DEPTH);

    /**
     * The RhythmTree default constructor
     */
    public RhythmTree() {
        //layers.get(0) = new RhythmNode(null,);
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
        return layers.get(0).get(0);
    }
}












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
