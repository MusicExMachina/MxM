package model.generative;

import learning.RhythmNetwork;
import model.structural.Count;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by celenp on 9/18/2016.
 */
public class RhythmNode {

    /**  */
    Count timing;
    //Count length;
    RhythmNode parent;
    List<RhythmNode> children;

    /**
     * A basic constructor for a RhythmNode.
     */
    public RhythmNode () {
        parent = null;
        children = null;
    }

    /**
     * Returns the parent of this RhythmNode.
     * @return The parent of this RhythmNode.
     */
    public RhythmNode getParent() {
        return parent;
    }

    /**
     * Returns all the children of this RhythmNode.
     * @return An edit-safe List of child nodes.
     */
    public List<RhythmNode> getChildren() {
        return new ArrayList<>(children);
    }

    /**
     * Subdivides this RhythmNode a given number of times.
     * @param times The number of times to divide this RhythmNode.
     * @return A list of the children of this Node.
     */
    public List<RhythmNode> subdivide(int times) {

        // Ensure we're not trying something stupid
        if(times >= 0) {
            if(children != null) {
                // Create the children ArrayList
                children = new ArrayList<RhythmNode>();

                // Add "times" many children
                for (int i = 0; i < times; i++) {
                    children.add(new RhythmNode());
                    children.get(i).parent = this;
                }
            }
            else throw new Error("This RhythmNode is already subdivided!");
        }
        else throw new Error("Trying to subdivide this RhythmNode" + times + " times!");

        // Return the children
        return children;
    }

    /**
     * Paint the node, and its children w/ links
     *
     * @param g Graphics object to paint with
     * @param x Top left corner of node x coord
     * @param y Top left corner of node y coord
     */
    public void paint(Graphics2D g, int x, int y) {
        g.drawOval(x, y, DRAW_RAD * 2, DRAW_RAD * 2);
        for (int i = 0; i < children.size(); i++) {
            int childX = x + DRAW_RAD * 6;
            int childY = y + DRAW_RAD * 3 * i; //gap of 1 rad
            g.drawLine(x + DRAW_RAD * 2, y + DRAW_RAD, childX, childY + DRAW_RAD); // y for center
            children.get(i).paint(g, childX, childY);
        }
    }
}
