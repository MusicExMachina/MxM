package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by celenp on 9/4/2016.
 */
public class Frame implements Comparable<Frame> {

    TreeSet<Note> notes;
    long time;

    /*
    */

    public long getTime() {
        return time;
    }
    /*
    public List<Note> getNotes() {
        //return new ArrayList<Note>(notes.toArray());
    }
    */
    @Override
    public int compareTo(Frame o) {
        //return getTime() > o.getTime();
    }
}
