package prj5;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * tests the TraditonalRate comparator object.
 * 
 * @author ryanjeronimus
 * @version Dec 5, 2025
 */
public class InfluencerTraditionalRateComparatorTest
    extends TestCase
{
    // ~ Fields ................................................................
    private InfluencerTraditionalRateComparator comparator;

    /**
     * sets up member variables for testing
     */
    public void setUp()
    {
        comparator = new InfluencerTraditionalRateComparator();

    }


    // ----------------------------------------------------------
    /**
     * Tests the comparator compare method thoroughly
     */
    // ~Public Methods ........................................................
    public void testCompare()
    {

        Influencer high = new Influencer("u1", "High", "USA", "art");
        Influencer nan = new Influencer("u3", "NoViews", "USA", "travel");
        high.addDataForMonth(0, 100, 1, 1000, 50, 1000);
        high.addDataForMonth(1, 120, 1, 1000, 60, 1000);
        high.addDataForMonth(2, 140, 1, 1000, 80, 1000);
        nan.addDataForMonth(0, 10, 1, 1000, 5, 0);
        nan.addDataForMonth(1, 10, 1, 1000, 5, 0);
        nan.addDataForMonth(2, 10, 1, 1000, 5, 0);

        nan.setFollowers(0, 0);
        nan.setFollowers(0, 1);
        nan.setFollowers(0, 2);

        assertEquals(1, comparator.compare(nan, high));
        assertEquals(-1, comparator.compare(high, nan));
        assertEquals(0, comparator.compare(nan, nan));
        nan.setFollowers(1, 1);
        assertEquals(1, comparator.compare(high, nan));

    }

}
