package model.generative;

import model.structural.Count;
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
        return new ArrayList<RhythmNode>(children);
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
}
