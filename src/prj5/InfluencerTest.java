package prj5;

import student.TestCase;

/**
 * Tests the Influencer class.
 *
 * @author nabiil
 * @version 2025-11-19
 */
public class InfluencerTest
    extends TestCase
{

    private Influencer inf;

    /**
     * Set up before each test.
     */
    public void setUp()
    {
        inf = new Influencer("userA", "ChannelA", "USA", "Tech");

        // Add Q1 sample data
        inf.addDataForMonth(0, 10, 2, 100, 5, 200);   // Jan
        inf.addDataForMonth(1, 20, 3, 110, 10, 250);  // Feb
        inf.addDataForMonth(2, 30, 4, 120, 15, 300);  // Mar
    }


    // -------------------------------------------------------------
    /**
     * Tests getters.
     */
    public void testGetters()
    {
        assertEquals("userA", inf.getUsername());
        assertEquals("ChannelA", inf.getChannelName());
        assertEquals("USA", inf.getCountry());
        assertEquals("Tech", inf.getTopics());

        assertEquals(10, inf.getLikes(0));
        assertEquals(4, inf.getPosts(2));
        assertEquals(120, inf.getFollowers(2));
        assertEquals(15, inf.getComments(2));
        assertEquals(300, inf.getViews(2));
    }


    // -------------------------------------------------------------
    /**
     * Tests the setter methods.
     */
    public void testSetters()
    {
        inf.setUsername("newUser");
        inf.setChannelName("newChan");
        inf.setCountry("Canada");
        inf.setTopics("Music");

        inf.setLikes(99, 0);
        inf.setPosts(55, 1);
        inf.setFollowers(800, 2);
        inf.setComments(77, 2);
        inf.setViews(900, 2);

        assertEquals("newUser", inf.getUsername());
        assertEquals("newChan", inf.getChannelName());
        assertEquals("Canada", inf.getCountry());
        assertEquals("Music", inf.getTopics());

        assertEquals(99, inf.getLikes(0));
        assertEquals(55, inf.getPosts(1));
        assertEquals(800, inf.getFollowers(2));
        assertEquals(77, inf.getComments(2));
        assertEquals(900, inf.getViews(2));
    }


    // -------------------------------------------------------------
    /**
     * Tests Q1 reach rate calculation.
     */
    public void testCalculateReachRateQ1()
    {
        // totals: likes = 60, comments = 30, views = 750
        double expected = (60 + 30) / 750.0 * 100;
        assertEquals(expected, inf.calculateReachRateQ1(), 0.0001);
    }

    
    // -------------------------------------------------------------
    /**
     * Tests Q1 traditional rate calculation.
     */
    public void testCalculateTraditionalRateQ1()
    {
        // total likes = 60, comments = 30, followers in March = 330
        double expected = ((60 + 30) / 330.0) * 100;
        assertEquals(expected, inf.calculateTraditionalRateQ1(), 0.0001);
    }


    // -------------------------------------------------------------
    /**
     * Tests the equals() method
     */
    public void testEquals()
    {
        Influencer same = new Influencer("userA", "ChannelA", "USA", "Tech");
        same.addDataForMonth(0, 10, 2, 100, 5, 200);
        same.addDataForMonth(1, 20, 3, 110, 10, 250);
        same.addDataForMonth(2, 30, 4, 120, 15, 300);

        assertTrue(inf.equals(same));
        assertFalse(inf.equals(null));
        assertFalse(inf.equals("string"));

        Influencer diff = new Influencer("other", "ChannelA", "USA", "Tech");
        assertFalse(inf.equals(diff));

        Influencer diffStats =
            new Influencer("userA", "ChannelA", "USA", "Tech");
        diffStats.addDataForMonth(0, 999, 0, 0, 0, 0);
        assertFalse(inf.equals(diffStats));
    }


    /**
     * Tests the reach rate with 0 views (for code coverages)
     */
    public void testCalculateReachRateQ1ZeroViews()
    {
        Influencer zeroViews = new Influencer("u", "c", "x", "t");
        assertTrue(Double.isNaN(zeroViews.calculateReachRateQ1()));
    }


    /**
     * traditional rate calc with zero folowers (for code coverage
     */
    public void testCalculateTraditionalRateQ1ZeroFollowers()
    {
        Influencer zeroFollowers = new Influencer("u", "c", "x", "t");
        zeroFollowers.addDataForMonth(2, 5, 1, 0, 2, 10); // followers = 0
        assertTrue(Double.isNaN(zeroFollowers.calculateTraditionalRateQ1()));
    }


    /**
     * attempting full code coverage. Testing the Equals() method.
     */
    public void testEqualsDifferences()
    {
        Influencer base = new Influencer("u", "c", "x", "t");
        Influencer other = new Influencer("u", "c", "x", "t");

        assertTrue(base.equals(other));

        other.setLikes(99, 0);
        assertFalse(base.equals(other));
        other.setLikes(0, 0);

        other.setComments(77, 1);
        assertFalse(base.equals(other));
        other.setComments(0, 1);

        other.setViews(123, 2);
        assertFalse(base.equals(other));
        other.setViews(0, 2);

        other.setPosts(55, 3);
        assertFalse(base.equals(other));
        other.setPosts(0, 3);
        other.setFollowers(500, 4);
        assertFalse(base.equals(other));
        other.setFollowers(0, 4);

        assertTrue(base.equals(other));
    }


    /**
     * More testing of the equals method for code coverage purposes.
     */
    public void testEqualsMetadataDifferences()
    {
        Influencer a = new Influencer("userA", "ChannelA", "USA", "Tech");
        Influencer b = new Influencer("userA", "ChannelA", "USA", "Tech");
        for (int i = 0; i < 12; i++)
        {
            a.addDataForMonth(i, 1, 1, 1, 1, 1);
            b.addDataForMonth(i, 1, 1, 1, 1, 1);
        }
        b.setChannelName("DifferentChan");
        assertFalse(a.equals(b));
        b.setChannelName("ChannelA");
        b.setUsername("DifferentUser");
        assertFalse(a.equals(b));
        b.setUsername("userA");
        b.setCountry("Canada");
        assertFalse(a.equals(b));
        b.setCountry("USA");
        b.setTopics("Gaming");
        assertFalse(a.equals(b));
        b.setTopics("Tech");

        assertTrue(a.equals(b));

        a = new Influencer("userA", "ChannelA", "USA", "Tech");
        b = a;
        assertTrue(a.equals(b));
    }
}
