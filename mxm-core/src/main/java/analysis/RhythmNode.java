package analysis;

import base.time.Count;
import java.util.ArrayList;
import java.util.List;

/**
 * RhythmNodes are the very active, very exposed, mutable building block of RhythmTrees. One cannot
 * directly create anything but a root node, and then create more by subdividing existing ones.
 */
public class RhythmNode {

    /** A static int for drawing purposes. */
    private static final int DRAW_RAD = 20;

    /** The depth of this Node in the tree. */
    private int depth;

    /** The analysis.RhythmTree of this Node. */
    private RhythmTree tree;

    /** The parent of this Node. */
    private RhythmNode parent;

    /** All of the children of this Node. */
    private List<RhythmNode> children;

    /** The timing of this analysis.RhythmNode */
    private Count timing;

    /** The duration of this analysis.RhythmNode */
    private Count duration;

    /**
     * The analysis.RhythmNode constructor, used by the subdivide function in an almost-factory method.
     * @param parent The parent of this analysis.RhythmNode.
     * @param timing The timing of this analysis.RhythmNode.
     * @param duration The duration of this analysis.RhythmNode.
     */
    public RhythmNode(RhythmTree tree, RhythmNode parent, Count timing, Count duration) {
        if(parent != null)
            this.depth = parent.getDepth()+1;
        this.parent     = parent;
        this.tree       = tree;
        this.children   = new ArrayList<>();
        this.timing     = timing;
        this.duration   = duration;
    }

    /**
     * Returns the analysis.RhythmTree of this analysis.RhythmNode.
     * @return The analysis.RhythmTree of this analysis.RhythmNode.
     */
    public RhythmTree getTree() {
        return tree;
    }

    /**
     * Returns the parent of this analysis.RhythmNode.
     * @return The parent of this analysis.RhythmNode.
     */
    public RhythmNode getParent() {
        return parent;
    }

    /**
     * Gets the duration of this node in Counts.
     * @return The duration of this node in Counts.
     */
    public Count getDuration() {
        return duration;
    }

    /**
     * Gets the duration of this node in Counts.
     * @return The duration of this node in Counts.
     */
    public Count getTiming() {
        return timing;
    }
    /**
     * Returns all the children of this analysis.RhythmNode.
     * @return An edit-safe List of child nodes.
     */
    public List<RhythmNode> getChildren() {
        return new ArrayList<>(children);
    }

    /**
     * Returns the subdivision number of this analysis.RhythmNode.
     * @return The number of children of this analysis.RhythmNode.
     */
    public int getValue() {
        return children.size();
    }

    /**
     * Returns the depth of this analysis.RhythmNode in the tree.
     * @return The depth of this analysis.RhythmNode in the tree.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Subdivides this analysis.RhythmNode a given number of times.
     * @param times The number of times to divide this analysis.RhythmNode.
     * @return A list of the children of this Node.
     */
    public List<RhythmNode> subdivide(int times) {

        // Ensure we're not trying something stupid
        if(times > 1) {
            if(children.size()==0) {
                // Add "times" many children
                Count newDuration = this.duration.dividedBy(times);
                for (int i = 0; i < times; i++) {
                    Count newTiming = this.timing.plus(newDuration.times(i));
                    children.add(new RhythmNode(tree,this,newTiming,newDuration));
                }
            }
            else throw new Error("This analysis.RhythmNode is already subdivided!");
        }
        else if(times == 1) {
            //tree.addFrame(timing);
        }
        else if(times < 0)
            throw new Error("Trying to subdivide this analysis.RhythmNode" + times + " times!");

        return children;
    }

    /**
     * Converts this analysis.RhythmNode to a List of Integer subdivisions
     * @return A List of Integer subdivisions.
     */
    public ArrayList<Integer> toList() {
        ArrayList<Integer> toReturn = new ArrayList<>();
        toReturn.add(getValue());
        for(RhythmNode child : children)
        {
            toReturn.addAll(child.toList());
        }
        return toReturn;
    }

    /**
     * Returns a nicely-formatted string
     * representing this rhythmTree.
     * @return A String of this rhythmTree.
     */
    public String toString() {
        String toReturn = "(";
        for(RhythmNode node : children) {
            toReturn += node.toString();
        }
        if(children.size() == 0) {
            String thisNode = "[ ";
            /*
            for(Note note : frame) {
                thisNode += note.getPitch().toString() + " ";
            }
            */
            return thisNode + "]";
        }
        toReturn += ")";
        return toReturn;
    }
}