package base.relative;

import base.analysis.Interval;

import java.util.Iterator;
import java.util.TreeSet;

// Vertical relationships... a bunch of intervals above a root
public class SonorityClass implements Iterable<Interval> {
    private TreeSet<Interval> factors;

    @Override
    public Iterator<Interval> iterator() {
        return factors.iterator();
    }
}
