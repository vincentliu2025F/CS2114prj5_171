
package prj5;

import java.util.Comparator;

public class InfluencerReachRateComparator
    implements Comparator<Influencer>
{

    private static final double NA_SORT_VALUE = Double.NEGATIVE_INFINITY;

    @Override
    public int compare(Influencer o1, Influencer o2)
    {
        double rate1 = o1.calculateReachRateQ1();
        double rate2 = o2.calculateReachRateQ1();

        if (Double.isNaN(rate1))
        {
            rate1 = NA_SORT_VALUE;
        }
        if (Double.isNaN(rate2))
        {
            rate2 = NA_SORT_VALUE;
        }
        return Double.compare(rate2, rate1);
    }
}
