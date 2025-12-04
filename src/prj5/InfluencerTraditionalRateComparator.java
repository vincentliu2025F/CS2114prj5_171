package prj5;

import java.util.Comparator;

// -------------------------------------------------------------------------
/**
 * Comparator for sorting by traditional engagement rates.
 * 
 * @author ryanjeronimus
 * @version Dec 3, 2025
 */
public class InfluencerTraditionalRateComparator
    implements Comparator<Influencer>
{

    @Override
    public int compare(Influencer a, Influencer b)
    {
        double rateA = a.calculateTraditionalRateQ1();
        double rateB = b.calculateTraditionalRateQ1();

        if (Double.isNaN(rateA) && Double.isNaN(rateB))
        {
            return 0;
        }
        if (Double.isNaN(rateA))
        {
            return 1;
        }
        if (Double.isNaN(rateB))
        {

            return -1;
        }
        return Double.compare(rateB, rateA);
    }
}
