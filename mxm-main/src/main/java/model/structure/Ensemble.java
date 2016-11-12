package model.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by celenp on 11/7/2016.
 */
public class Ensemble {

    private List<Instrument> instruments;
    private HashMap<Instrument,List<Part>> parts;

    public Ensemble() {
        instruments = new ArrayList<>();
    }

    public Iterator<Instrument> iterator() {
        return instruments.iterator();
    }



}
