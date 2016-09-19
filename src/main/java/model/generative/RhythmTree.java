package model.generative;

import model.structural.Frame;
import java.util.*;

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

    /**
     * The RhythmTree default constructor
     */
    public RhythmTree() {
        root = new RhythmNode();
    }

    /**
     * The RhythmTree copy constructor
     * @param other the RhythmTree to copy
     */
    public RhythmTree(RhythmTree other) {

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
