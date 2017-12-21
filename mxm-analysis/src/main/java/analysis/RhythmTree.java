package analysis;

import form.time.Beat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
public class RhythmTree {

    private RhythmNode root;

    public RhythmTree() {
        root = new RhythmNode(this);
    }

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

    public String toString() {
        return root.toString();
    }

    public RhythmNode getRoot() {
        return root;
    }

    public ArrayList<Integer> toList() {
        return root.toList();
    }
}

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

    private RhythmNode(RhythmTree tree, RhythmNode parent, Beat timing, Beat duration) {
        if(parent != null)
            this.depth = parent.getDepth()+1;
        this.parent     = parent;
        this.tree       = tree;
        this.children   = new ArrayList<>();
        this.timing     = timing;
        this.duration   = duration;
    }

    public RhythmTree getTree() {
        return tree;
    }

    public RhythmNode getParent() {
        return parent;
    }

    public Beat getDuration() {
        return duration;
    }

    public Beat getTiming() {
        return timing;
    }

    public List<RhythmNode> getChildren() {
        return new ArrayList<>(children);
    }

    public int getValue() {
        return children.size();
    }

    public int getDepth() {
        return depth;
    }

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

    public ArrayList<Integer> toList() {
        ArrayList<Integer> toReturn = new ArrayList<>();
        toReturn.add(getValue());
        for(RhythmNode child : children)
        {
            toReturn.addAll(child.toList());
        }
        return toReturn;
    }

    public String toString() {
        String toReturn = "(";
        for(RhythmNode node : children) {
            toReturn += node.toString();
        }
        if(children.size() == 0) {
            String thisNode = "[ ";
            //for(Sound properties : frame) {
            //    thisNode += properties.getPitch().toString() + " ";
            //}
            return thisNode + "]";
        }
        toReturn += ")";
        return toReturn;
    }
}
*/