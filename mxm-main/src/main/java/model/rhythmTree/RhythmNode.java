package model.rhythmTree;

import model.basic.Count;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.basic.CountClass;
import model.form.Note;

/**
 * RhythmNodes are the very active, very exposed, mutable building block of RhythmTrees. One cannot
 * directly create anything but a root node, and then create more by subdividing existing ones.
 */
public class RhythmNode {

    /** A static int for drawing purposes. */
    private static final int DRAW_RAD = 20;

    /** The depth of this Node in the tree. */
    private int depth;

    /** The RhythmTree of this Node. */
    private RhythmTree tree;

    /** The parent of this Node. */
    private RhythmNode parent;

    /** All of the children of this Node. */
    private List<RhythmNode> children;

    /** The timing of this RhythmNode */
    private CountClass timing;

    /** The duration of this RhythmNode */
    private CountClass duration;

    /** The Note (if this is a leaf RhythmNode) */
    private Note note;

    /**
     * The RhythmNode constructor, used by the subdivide function in an almost-factory method.
     * @param parent The parent of this RhythmNode.
     * @param timing The timing of this RhythmNode.
     * @param duration The duration of this RhythmNode.
     */
    public RhythmNode(RhythmTree tree, RhythmNode parent, CountClass timing, CountClass duration) {
        if(parent != null)
            this.depth = parent.getDepth()+1;
        this.parent     = parent;
        this.tree       = tree;
        this.children   = new ArrayList<>();
        this.timing     = timing;
        this.duration   = duration;
    }

    /**
     * Returns the RhythmTree of this RhythmNode.
     * @return The RhythmTree of this RhythmNode.
     */
    public RhythmTree getTree() {
        return tree;
    }

    /**
     * Returns the parent of this RhythmNode.
     * @return The parent of this RhythmNode.
     */
    public RhythmNode getParent() {
        return parent;
    }

    /**
     * Gets the duration of this node in CountClasses.
     * @return The duration of this node in CountClasses.
     */
    public CountClass getDuration() {
        return duration;
    }

    /**
     * Gets the duration of this node in CountClasses.
     * @return The duration of this node in CountClasses.
     */
    public CountClass getTiming() {
        return timing;
    }
    /**
     * Returns all the children of this RhythmNode.
     * @return An edit-safe List of child nodes.
     */
    public List<RhythmNode> getChildren() {
        return new ArrayList<>(children);
    }

    /**
     * Returns the subdivision number of this RhythmNode.
     * @return The number of children of this RhythmNode.
     */
    public int getSubdivisions() {
        return children.size();
    }

    /**
     * Returns the depth of this RhythmNode in the tree.
     * @return The depth of this RhythmNode in the tree.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Returns the Note of this RhythmNode.
     * @return The Note of this RhythmNode.
     */
    public Note getNote() {
        return note;
    }

    /**
     * "Colors" a RhythmNode with a specific Note.
     * @param note The Note to color this RhythmNode with.
     */
    public void color(Note note) {
        note = note;
    }

    /**
     * Subdivides this RhythmNode a given number of times.
     * @param times The number of times to divide this RhythmNode.
     * @return A list of the children of this Node.
     */
    public List<RhythmNode> subdivide(int times) {

        // Ensure we're not trying something stupid
        if(times > 1) {
            if(children.size()==0) {
                // Add "times" many children
                CountClass newDuration = this.duration.dividedBy(times);
                for (int i = 0; i < times; i++) {
                    CountClass newTiming = this.timing.plus(newDuration.times(i));
                    children.add(new RhythmNode(tree,this,newTiming,newDuration));
                }
            }
            else throw new Error("This RhythmNode is already subdivided!");
        }
        else if(times < 0)
            throw new Error("Trying to subdivide this RhythmNode" + times + " times!");

        return children;
    }

    /**
     * Recursively creates a List of all RhythmNodes in this RhythmTree.
     * @return A List of all RhythmNodes in this RhythmTree.
     */
    public List<RhythmNode> toList() {
        LinkedList<RhythmNode> toReturn = new LinkedList<>();
        toReturn.add(this);
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
        if(children.size() == 0) {
            if(note == null) {
                return "o";
            }
            else {
                return note.getPitch().toString();
            }
        }
        String toReturn = "(";
        for(RhythmNode node : children) {
            toReturn += node.toString();
        }
        toReturn += ")";
        return toReturn;
    }

    /**
     * Paint the node, and its children w/ links
     * @param g Graphics object to paint with
     * @param x Top left corner of node x coord
     * @param y Top left corner of node y coord
     */
    public void paint(Graphics2D g, int x, int y) {
        if(children.size() == 0){
            g.drawOval(x, y, DRAW_RAD * 2, DRAW_RAD * 2);
        }else{
            g.fillOval(x, y, DRAW_RAD * 2, DRAW_RAD * 2);
        }
        for (int i = 0; i < children.size(); i++) {
            int childX = x + DRAW_RAD * 6;
            int childY = y + DRAW_RAD * 3 * i; //gap of 1 rad
            g.drawLine(x + DRAW_RAD * 2, y + DRAW_RAD, childX, childY + DRAW_RAD); // y for center
            //g.drawString("" + children.get(i).getChildren().size(), childX + DRAW_RAD / 2, childY + DRAW_RAD);
            if(children.get(i).getChildren().size() == 0) {
                g.drawString("Duration: " + children.get(i).getDuration(), childX + DRAW_RAD*3, childY + DRAW_RAD);
                g.drawString("Timing: " + children.get(i).getTiming(), childX + DRAW_RAD*3, childY + DRAW_RAD + 15);
            }
            children.get(i).paint(g, childX, childY);
        }
    }
}