package model.basic;

import model.trainable.Instrument;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by celenp on 11/14/2016.
 */
public class Sonority {
    TreeMap<Pitch,HashMap<Instrument,Integer>> pitches;
}
