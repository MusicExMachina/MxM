package learning.noutputs;

import model.structure.Pitch;

import java.util.HashMap;


public class PitchDistribution {


    HashMap<Pitch, Double> pmf;


    public PitchDistribution(){
        pmf = new HashMap<>();
        for(int i = Pitch.MIN_PITCH; i<Pitch.MAX_PITCH; i++) {
            pmf.put(new Pitch(i), 0d);
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
