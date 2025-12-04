package prj5;

import java.util.Comparator;

// -------------------------------------------------------------------------
/**
 *  New comparator that can compare Reach Rate of influencers by month instead of just q1.
 * 
 *  @author ryanjeronimus
 *  @version Dec 3, 2025
 */
public class InfluencerReachRateMonthComparator
    implements Comparator<Influencer>
{
    private int month;

    // ----------------------------------------------------------
    /**
     * Create a new InfluencerReachRateMonthComparator object.
     * @param month - month of interest
     */
    public InfluencerReachRateMonthComparator(int month) {
        this.month = month;
    }

    @Override
    public int compare(Influencer a, Influencer b)
    {
        double aRate = calculate(a);
        double bRate = calculate(b);

        return Double.compare(aRate, bRate);
    }


    private double calculate(Influencer inf)
    {
        double likes = inf.getLikes(month);
        double comments = inf.getComments(month);
        double views = inf.getViews(month);
        if (views == 0)
        {
            return Double.NaN;
        }
        return (likes + comments) / views * 100;
    }
}
