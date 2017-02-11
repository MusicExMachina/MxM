package noutputs;

import noutputs.Distribution;
import noutputs.DistributionSelector;

/**
 * Created by jpatsenker on 11/27/16.
 */
public class SimpleRandomSelector<T> extends DistributionSelector<T> {
    @Override
    public T selectFromDistribution(Distribution<T> dist) {
        this.addDistribution(dist);
        return dist.choose(1);
    }
}
