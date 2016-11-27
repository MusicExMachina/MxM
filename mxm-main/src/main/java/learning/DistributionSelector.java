package learning;

import learning.noutputs.Distribution;

import java.util.ArrayList;

/**
 * Created by jpatsenker on 11/27/16.
 */
public abstract class DistributionSelector<T> {

    protected ArrayList<Distribution<T>> distributions;

    public DistributionSelector() {
        this.distributions = new ArrayList<>();
    }

    protected void addDistribution(Distribution<T> dist){
        distributions.add(dist);
    }

    public abstract T selectFromDistribution(Distribution<T> dist);

}
