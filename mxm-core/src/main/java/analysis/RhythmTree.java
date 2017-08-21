package analysis;

import base.time.Beat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * A conceptualization of rhythm as gradual, equal subdivisions of some concrete amount of base.time.
 * RhythmTrees operate like many other data structures save that their only "real" storage is in
 * their leaf nodes.
 */
public class RhythmTree {

    /** Saves the root analysis.RhythmNode **/
    private RhythmNode root;

    /**
     * The rhythmTree default constructor
     */
    public RhythmTree() {
        root = new RhythmNode(this);
    }

    /**
     * Quick constructor to make rhythm tree (mainly for testing)
     * @param subDiv list of subdivisions in Breadth First order
     */
    public RhythmTree(int[] subDiv) throws IllegalArgumentException{
        Queue<RhythmNode> constructQueue = new ArrayDeque<RhythmNode>();

        root = new RhythmNode(this);
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
     * representing this rhythmTree.
     * @return A String of this rhythmTree.
     */
    public String toString() {
        return root.toString();
    }

    /**
     * Getter for the root analysis.RhythmNode which fills the whole measure.
     * @return Gets the root analysis.RhythmNode of this analysis.RhythmTree.
     */
    public RhythmNode getRoot() {
        return root;
    }

    /**
     * Converts this analysis.RhythmTree to a List of Integer subdivisions
     * @return A List of Integer subdivisions.
     */
    public ArrayList<Integer> toList() {
        return root.toList();
    }
}

/**
 * RhythmNodes are the very active, very exposed, mutable building block of RhythmTrees. One cannot
 * directly create anything but a root node, and then create more by subdividing existing ones.
 */
class RhythmNode {
    private int depth;
    private RhythmTree tree;
    private RhythmNode parent;
    private List<RhythmNode> children;
    private Beat timing;
    private Beat duration;


    RhythmNode(RhythmTree tree) {
        this.parent     = null;
        this.tree       = tree;
        this.children   = new ArrayList<>();
        this.timing     = Beat.ZERO;
        this.duration   = Beat.ONE;
    }

    /**
     * The analysis.RhythmNode constructor, used by the subdivide function in an almost-factory method.
     * @param parent The parent of this analysis.RhythmNode.
     * @param timing The timing of this analysis.RhythmNode.
     * @param duration The duration of this analysis.RhythmNode.
     */
    private RhythmNode(RhythmTree tree, RhythmNode parent, Beat timing, Beat duration) {
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
    public Beat getDuration() {
        return duration;
    }

    /**
     * Gets the duration of this node in Counts.
     * @return The duration of this node in Counts.
     */
    public Beat getTiming() {
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
                int newDenominator = this.duration.getDenominator() * times;
                int newNumerator = this.duration.getNumerator() * times;
                // Add "times" many children
                for (int i = 0; i < times; i++) {
                    Beat newTiming = Beat.get(newNumerator + i, newDenominator);
                    Beat newDuration = Beat.get(1, newDenominator);
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