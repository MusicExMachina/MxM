package learning.noutputs;

import model.basic.Pitch;

import java.util.HashMap;


public class PitchDistribution {


    HashMap<Pitch, Double> pmf;

    /**
     * Creates blank pitch distribution with uniform probability for every pitch
     */
    public PitchDistribution(){
        pmf = new HashMap<>();
        for(int i = Pitch.MIN_PITCH; i<Pitch.MAX_PITCH; i++) {
            pmf.put(new Pitch(i), 1d);
        }
    }


    /**
     * Update probability for specific pitch
     * @param p pitch to be updated
     * @param prob probability to be inserted
     */
    public void setProbabilityForPitch(Pitch p, Double prob){
        if (prob < 0 || prob>1){
            throw new IllegalArgumentException("Illegal Probability for Pitch " + p + ": " + prob);
        }

        pmf.put(p, prob);
    }

    /**
     * Retrieve probability from distribution
     * @param p pitch to retrieve probability for
     * @return
     */
    public double getProbabilityForPitch(Pitch p){
        return pmf.get(p);
    }





}
