package model.generative;

import model.Count;
import model.Frame;

import java.io.Serializable;
import java.util.*;

/**
 * A conceptualization of rhythm as gradual, equal
 * subdivisions of some concrete amount of time.
 * RhythmTrees operate like many other data structures
 * save that their only "real" storage is in their
 * leaf nodes. This
 */

public class RhythmTree implements Serializable, Cloneable, Iterable<Frame>, Collection<Frame>, NavigableSet<Frame>, Set<Frame>, SortedSet<Frame> {

    private class Node {
        Count timing;
        Count length;
        Node parent;
        List<Node> children;
    }

    private Node root = null;

    /**
     * The RhythmTree default constructor
     */
    public RhythmTree() {

    }

    /**
     * The RhythmTree copy constructor
     * @param other the RhythmTree to copy
     */
    public RhythmTree(RhythmTree other) {

    }

    /*   HERE BE OVERRIDE METHODS    */
    /* ABANDON ALL HOPE YE WHO ENTER */

    @Override
    public Frame lower(Frame frame) {
        return null;
    }

    @Override
    public Frame floor(Frame frame) {
        return null;
    }

    @Override
    public Frame ceiling(Frame frame) {
        return null;
    }

    @Override
    public Frame higher(Frame frame) {
        return null;
    }

    @Override
    public Frame pollFirst() {
        return null;
    }

    @Override
    public Frame pollLast() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Frame> iterator() {
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
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Frame frame) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Frame> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public NavigableSet<Frame> descendingSet() {
        return null;
    }

    @Override
    public Iterator<Frame> descendingIterator() {
        return null;
    }

    @Override
    public NavigableSet<Frame> subSet(Frame fromElement, boolean fromInclusive, Frame toElement, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableSet<Frame> headSet(Frame toElement, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableSet<Frame> tailSet(Frame fromElement, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super Frame> comparator() {
        return null;
    }

    @Override
    public SortedSet<Frame> subSet(Frame fromElement, Frame toElement) {
        return null;
    }

    @Override
    public SortedSet<Frame> headSet(Frame toElement) {
        return null;
    }

    @Override
    public SortedSet<Frame> tailSet(Frame fromElement) {
        return null;
    }

    @Override
    public Frame first() {
        return null;
    }

    @Override
    public Frame last() {
        return null;
    }
}
