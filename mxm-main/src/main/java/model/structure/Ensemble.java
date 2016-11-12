package model.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by celenp on 11/7/2016.
 */
public class Ensemble {

    public Ensemble PIANO_SOLO;
    public Ensemble FOUR_PART_CHOIR;
    public Ensemble STRING_QUARTET;
    public Ensemble WOODWIND_QUINTET;
    public Ensemble BRASS_QUINTET;




    private List<Instrument> instruments;
    public Ensemble() {
        instruments = new ArrayList<>();
    }

    public Iterator<Instrument> iterator() {
        return instruments.iterator();
    }
}
