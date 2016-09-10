package model.generative;

import model.time_insensitive.Count;
import model.time_insensitive.Note;

import java.io.Serializable;
import java.util.*;

/**
 * A conceptualization of rhythm as gradual, equal
 * subdivisions of some concrete amount of time.
 * RhythmTrees operate like many other data structures
 * save that their only "real" storage is in their
 * leaf nodes. This
 */
public class RhythmTree implements Serializable, Cloneable, Iterable<Note>, Collection<Note>, NavigableSet<Note>, Set<Note>, SortedSet<Note> {

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

    @Override
    public Iterator<Note> iterator() {
        return null;
    }
}
