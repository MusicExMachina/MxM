package model.form;

import model.structure.Ensemble;
import model.structure.Instrument;
import model.time.Count;

import java.util.*;

/**
 * After we're done creating a rhythmTree, we put all of its frames into a Passage. Passages are essentially
 * great big Frame holders, and provide users with the ability to look up what's happening at any given Count.
 */
public class Passage{

    /** The Ensemble of all Instruments which are involved in this Passage. */
    private Ensemble ensemble;

    /** */
    private HashMap<Instrument,List<Part>> sections;

    /** */
    private List<Part> parts;

}