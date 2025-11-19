package prj5;

import student.TestCase;

public class InfluencerListTest
    extends TestCase
{
    // ~ Fields ................................................................
    private InfluencerList testList;

    // ~Public Methods ........................................................

    /**
     * sets up variables for testing
     */
    public void setUp()
    {
        testList = new InfluencerList();

    }


    public void testAdd()
    {
        String test = "test";
        Influencer testInfluencer = new Influencer(test, test, test, test);
        testList.add(testInfluencer);
        assertEquals(1, testList.getSize());
    }


    public void testAddWithIndex()
    {
        String test = "test";
        Influencer testInfluencer = new Influencer(test, test, test, test);
        testList.add(testInfluencer);
        testList.add(testInfluencer);
        Influencer unique =
            new Influencer("unique", "unique", "unique", "unique");
        testList.add(1, unique);
        assertEquals(unique, testList.getEntry(1));
        testList.add(0, testInfluencer);
        assertEquals(4, testList.getSize());
        assertEquals(testInfluencer, testList.getEntry(0));
        
        testList.add(2, testInfluencer);
        assertEquals(5, testList.getSize());
        assertEquals(testInfluencer, testList.getEntry(2));
        

        Exception e = null;

        try
        {
            testList.add(-1, unique);

        }
        catch (IndexOutOfBoundsException exception)
        {
            e = exception;
        }
        assertNotNull(e);

        e = null;
        try
        {
            testList.add(50, unique);

        }
        catch (IndexOutOfBoundsException exception)
        {
            e = exception;
        }
        assertNotNull(e);
    }


    public void testRemove()
    {
        String test = "test";
        Influencer testInfluencer = new Influencer(test, test, test, test);
        testList.add(testInfluencer);
        testList.add(testInfluencer);
        Influencer unique =
            new Influencer("unique", "unique", "unique", "unique");
        testList.add(unique);
        Exception e = null;
        try
        {
            testList.remove(-1);

        }
        catch (IndexOutOfBoundsException exception)
        {
            e = exception;
        }
        assertNotNull(e);

        e = null;
        try
        {
            testList.remove(50);

        }
        catch (IndexOutOfBoundsException exception)
        {
            e = exception;
        }
        assertNotNull(e);

        Influencer removed = testList.remove(0);
        assertEquals(unique, removed);
        testList.add(unique);
        assertEquals(testInfluencer, testList.remove(2));
        assertEquals(testInfluencer, testList.remove(1));
    }


    public void testClear()
    {
        String test = "test";
        Influencer testInfluencer = new Influencer(test, test, test, test);
        testList.add(testInfluencer);
        testList.add(testInfluencer);
        assertEquals(2, testList.getSize());
        testList.clear();
        assertEquals(0, testList.getSize());
    }


    public void testReplace()
    {

        String test = "test";
        Influencer testInfluencer = new Influencer(test, test, test, test);
        testList.add(testInfluencer);
        testList.add(testInfluencer);
        Influencer unique =
            new Influencer("unique", "unique", "unique", "unique");
        testList.add(unique);
        Influencer uniqueUnused =
            new Influencer("unused", "unused", "unused", "unused");
        Exception e = null;
        try
        {
            testList.replace(-1, uniqueUnused);

        }
        catch (IndexOutOfBoundsException exception)
        {
            e = exception;
        }
        assertNotNull(e);

        e = null;
        try
        {
            testList.replace(50, uniqueUnused);

        }
        catch (IndexOutOfBoundsException exception)
        {
            e = exception;
        }
        assertNotNull(e);

        Influencer removed = testList.replace(0, uniqueUnused);
        assertEquals(unique, removed);
        assertEquals(uniqueUnused, testList.getEntry(0));
        testList.add(removed);
        removed = testList.replace(1, uniqueUnused);
        assertEquals(uniqueUnused, removed);
    }


    public void testGetEntry()
    {
        String test = "test";
        Influencer testInfluencer = new Influencer(test, test, test, test);
        testList.add(testInfluencer);
        testList.add(testInfluencer);
        Influencer unique =
            new Influencer("unique", "unique", "unique", "unique");
        testList.add(unique);

        assertEquals(unique, testList.getEntry(0));
        assertEquals(testInfluencer, testList.getEntry(1));

        Exception e = null;
        try
        {
            testList.getEntry(-1);

        }
        catch (IndexOutOfBoundsException exception)
        {
            e = exception;
        }
        assertNotNull(e);

        e = null;
        try
        {
            testList.getEntry(50);

        }
        catch (IndexOutOfBoundsException exception)
        {
            e = exception;
        }
        assertNotNull(e);
    }


    public void testToArray()
    {
        String test = "test";
        Influencer testInfluencer = new Influencer(test, test, test, test);
        testList.add(testInfluencer);
        testList.add(testInfluencer);
        Influencer unique =
            new Influencer("unique", "unique", "unique", "unique");
        testList.add(unique);

        Object[] arr = testList.toArray();

        assertEquals(3, arr.length);

        assertEquals(unique, arr[0]);
        assertEquals(testInfluencer, arr[1]);
        assertEquals(testInfluencer, arr[2]);
        testList.clear();
        Object[] empty = testList.toArray();
        assertEquals(0, empty.length);
    }


    public void testGetSize()
    {
        String test = "test";
        Influencer testInfluencer = new Influencer(test, test, test, test);
        testList.add(testInfluencer);
        testList.add(testInfluencer);
        assertEquals(2, testList.getSize());
        testList.clear();
        assertEquals(0, testList.getSize());
    }


    public void testSortByChannelName()
    {
        InfluencerList list = new InfluencerList();

        Influencer a = new Influencer("u1", "zChannel", "USA", "art");
        Influencer b = new Influencer("u2", "MIdChannel", "USA", "tech");
        Influencer c = new Influencer("u3", "alpha", "USA", "music");

        list.add(a);
        list.add(b);
        list.add(c);

        list.sortByChannelName();

        assertEquals("alpha", list.getEntry(0).getChannelName());
        assertEquals("MIdChannel", list.getEntry(1).getChannelName());
        assertEquals("zChannel", list.getEntry(2).getChannelName());
    }


    public void testSortByReachRate()
    {
        InfluencerList list = new InfluencerList();

        Influencer high = new Influencer("u1", "High", "USA", "art");
        Influencer mid = new Influencer("u2", "Mid", "USA", "tech");
        Influencer nan = new Influencer("u3", "NoViews", "USA", "travel");
        high.addDataForMonth(0, 100, 1, 1000, 50, 1000);
        high.addDataForMonth(1, 120, 1, 1000, 60, 1000);
        high.addDataForMonth(2, 140, 1, 1000, 80, 1000);
        mid.addDataForMonth(0, 10, 1, 1000, 5, 1000);
        mid.addDataForMonth(1, 20, 1, 1000, 10, 1000);
        mid.addDataForMonth(2, 30, 1, 1000, 15, 1000);
        nan.addDataForMonth(0, 10, 1, 1000, 5, 0);
        nan.addDataForMonth(1, 10, 1, 1000, 5, 0);
        nan.addDataForMonth(2, 10, 1, 1000, 5, 0);

        list.add(mid);
        list.add(nan);
        list.add(high);

        list.sortByReachRate();

        assertEquals("High", list.getEntry(0).getChannelName());
        assertEquals("Mid", list.getEntry(1).getChannelName());
        assertEquals("NoViews", list.getEntry(2).getChannelName());

    }


    public void testContains()
    {
        String test = "test";
        Influencer testInfluencer = new Influencer(test, test, test, test);
        testList.add(testInfluencer);
        testList.add(testInfluencer);
        Influencer unique =
            new Influencer("unique", "unique", "unique", "unique");
        testList.add(unique);
        Influencer no = new Influencer("no", "no", "no", "no");

        assertTrue(testList.contains(unique));
        assertTrue(testList.contains(testInfluencer));
        assertFalse(testList.contains(no));
    }


    public void testGetLength()
    {
        String test = "test";
        Influencer testInfluencer = new Influencer(test, test, test, test);
        testList.add(testInfluencer);
        testList.add(testInfluencer);
        Influencer unique =
            new Influencer("unique", "unique", "unique", "unique");
        testList.add(unique);

        assertEquals(3, testList.getLength());
        testList.clear();
        assertEquals(0, testList.getLength());
    }


    public void testIsEmpty()
    {
        String test = "test";
        Influencer testInfluencer = new Influencer(test, test, test, test);
        testList.add(testInfluencer);
        testList.add(testInfluencer);
        Influencer unique =
            new Influencer("unique", "unique", "unique", "unique");
        testList.add(unique);

        assertFalse(testList.isEmpty());
        testList.clear();
        assertTrue(testList.isEmpty());
    }


    public void testGetFront()
    {
        String test = "test";
        Influencer testInfluencer = new Influencer(test, test, test, test);
        testList.add(testInfluencer);
        testList.add(testInfluencer);
        Influencer unique =
            new Influencer("unique", "unique", "unique", "unique");
        testList.add(unique);

        assertEquals(unique, testList.getFront());
        testList.remove(0);
        assertEquals(testInfluencer, testList.getFront());
    }

}
