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

        Influencer removed = testList.remove(2);
        assertEquals(unique, removed);
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

        Influencer removed = testList.replace(2, uniqueUnused);
        assertEquals(unique, removed);
        assertEquals(uniqueUnused, testList.getEntry(2));
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

        assertEquals(unique, testList.getEntry(2));
        assertEquals(test, testList.getEntry(1));

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

}
