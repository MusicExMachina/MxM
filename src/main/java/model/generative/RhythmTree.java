package model.generative;

import model.Count;

import java.io.Serializable;
import java.util.*;

/**
 * A conceptualization of rhythm as gradual, equal
 * subdivisions of some concrete amount of time.
 * RhythmTrees operate like many other data structures
 * save that their only "real" storage is in their
 * leaf nodes. This
 */

public class RhythmTree implements Serializable, Cloneable, Iterable<MusicEvent>, Collection<MusicEvent>, NavigableSet<MusicEvent>, Set<MusicEvent>, SortedSet<MusicEvent> {

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
    public Iterator<MusicEvent> iterator() {
        Iterator<MusicEvent> it = new Iterator<MusicEvent>() {
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
    }

    @Override
    public Note lower(Note note) {
        return null;
    }

    @Override
    public Note floor(Note note) {
        return null;
    }

    @Override
    public Note ceiling(Note note) {
        return null;
    }

    @Override
    public Note higher(Note note) {
        return null;
    }

    @Override
    public Note pollFirst() {
        return null;
    }

    @Override
    public Note pollLast() {
        return null;
    }

    @Override
    public NavigableSet<Note> descendingSet() {
        return null;
    }

    @Override
    public Iterator<Note> descendingIterator() {
        return null;
    }

    @Override
    public NavigableSet<Note> subSet(Note fromElement, boolean fromInclusive, Note toElement, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableSet<Note> headSet(Note toElement, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableSet<Note> tailSet(Note fromElement, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super Note> comparator() {
        return null;
    }

    @Override
    public SortedSet<Note> subSet(Note fromElement, Note toElement) {
        return null;
    }

    @Override
    public SortedSet<Note> headSet(Note toElement) {
        return null;
    }

    @Override
    public SortedSet<Note> tailSet(Note fromElement) {
        return null;
    }

    @Override
    public Note first() {
        return null;
    }

    @Override
    public Note last() {
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
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Note note) {
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
    public boolean addAll(Collection<? extends Note> c) {
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
}
