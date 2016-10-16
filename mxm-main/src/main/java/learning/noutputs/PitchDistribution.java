package learning.noutputs;

import model.basic.Pitch;

import java.util.HashMap;


public class PitchDistribution {


    HashMap<Pitch, Double> pmf;


    public PitchDistribution(){
        pmf = new HashMap<>();
        for(Pitch pitch : Pitch.ALL_PITCHES) {
            pmf.put(pitch, 0d);
        }
    }

    public void setProbabilityForPitch(Pitch p, Double prob){
        if (prob < 0 || prob>1){
            throw new IllegalArgumentException("Illegal Probability for Pitch " + p + ": " + prob);
        }

        pmf.put(p, prob);
    }

    public double getProbabilityForPitch(Pitch p){
        return pmf.get(p);
    }



}
