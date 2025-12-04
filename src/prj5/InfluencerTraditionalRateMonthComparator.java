package prj5;

import java.util.Comparator;

// -------------------------------------------------------------------------
/**
 * New comparator by traditional rate that compares a month of interest instead
 * of just q1
 * 
 * @author ryanjeronimus
 * @version Dec 3, 2025
 */
public class InfluencerTraditionalRateMonthComparator
    implements Comparator<Influencer>
{
    private int month;

    // ----------------------------------------------------------
    /**
     * Create a new InfluencerTraditionalRateMonthComparator object.
     * 
     * @param month
     *            - month of interest
     */
    public InfluencerTraditionalRateMonthComparator(int month)
    {
        this.month = month;
    }


    @Override
    public int compare(Influencer a, Influencer b)
    {
        double aRate = calculate(a);
        double bRate = calculate(b);
        return Double.compare(bRate, aRate);
    }


    private double calculate(Influencer inf)
    {
        double likes = inf.getLikes(month);
        double comments = inf.getComments(month);
        double followers = inf.getFollowers(month);

        if (followers == 0)
        {
            return Double.NaN;
        }

        return (likes + comments) / followers * 100;
    }
}
