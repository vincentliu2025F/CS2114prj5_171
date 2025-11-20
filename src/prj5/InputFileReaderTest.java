package prj5;

import student.TestCase;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

// -------------------------------------------------------------------------
/**
 * Test class for the input file reader (JUNIT)
 * 
 * @author vincentliu
 * @version Nov 19, 2025
 */
public class InputFileReaderTest
    extends TestCase
{

    private InputFileReader reader;
    private InfluencerList influencerList;
    private static final String TEST_FILE_BASIC =
        "TestInputFileReader_Basic.csv";
    private static final String TEST_FILE_INVALID_MONTH =
        "TestInputFileReader_InvalidMonth.csv";
    private static final String TEST_FILE_NON_NUMERIC =
        "TestInputFileReader_NonNumeric.csv";

    /**
     * Sets up test fixtures before each test method.
     */
    public void setUp()
    {
        // Use a sample input file that you have in your project root
        // Make sure the file exists and matches the expected format
        try
        {
            reader = new InputFileReader("SampleInput1_2023.csv"); // Or another
                                                                   // known good
                                                                   // file
            influencerList = reader.getInfluencerList();
        }
        catch (IOException e)
        {
            System.out.println(
                "Error setting up InputFileReader in test: " + e.getMessage());
            // You might want the test to fail if setup fails
            fail(
                "Failed to initialize InputFileReader for testing: "
                    + e.getMessage());
        }
    }


    /**
     * Tears down test fixtures after each test method.
     */
    public void tearDown()
    {
        // Clean up test files if they were created
        java.io.File file1 = new java.io.File(TEST_FILE_BASIC);
        java.io.File file2 = new java.io.File(TEST_FILE_INVALID_MONTH);
        java.io.File file3 = new java.io.File(TEST_FILE_NON_NUMERIC);
        file1.delete();
        file2.delete();
        file3.delete();
    }


    /**
     * Tests the constructor and basic parsing. Checks if influencers are loaded
     * and stored correctly.
     */
    public void testConstructorAndParsing()
    {
        assertNotNull("InputFileReader should create a list", influencerList);
        assertFalse(
            "List should not be empty after reading a valid file",
            influencerList.isEmpty());

        // Example: Check if specific influencers from the sample file are
        // present
        // You need to know the expected channel names in your sample file.
        // This is a basic check, you might need more specific logic depending
        // on your sample data.
        // Assume SampleInput1_2023.csv has these channels after processing Jan,
        // Feb, Mar data.
        // Adjust based on your actual sample data.
        assertEquals(
            "Expected 4 influencers in SampleInput1_2023.csv",
            4,
            influencerList.getSize());

        // Find specific influencers by channel name (assuming helper method or
        // iterating)
        Influencer foundInfluencer = null;
        for (int i = 0; i < influencerList.getSize(); i++)
        {
            Influencer current = influencerList.getEntry(i); // Assuming
                                                             // getEntry exists
            if ("wizardHighSchool".equals(current.getChannelName()))
            {
                foundInfluencer = current;
                break;
            }
        }
        assertNotNull(
            "Should find influencer 'wizardHighSchool'",
            foundInfluencer);
        assertEquals(
            "Username for 'allaboutfootball' should be 'aafootball'",
            "actionDan",
            foundInfluencer.getUsername());

        // Find another influencer
        foundInfluencer = null;
        for (int i = 0; i < influencerList.getSize(); i++)
        {
            Influencer current = influencerList.getEntry(i);
            if ("ArtAllDay".equals(current.getChannelName()))
            {
                foundInfluencer = current;
                break;
            }
        }
        assertNotNull("Should find influencer 'ArtAllDay'", foundInfluencer);
        assertEquals(
            "Country for 'ArtAllDay' should be 'CA'",
            "CA",
            foundInfluencer.getCountry());
    }


    /**
     * Tests that invalid months are skipped during parsing. Creates a temporary
     * file with an invalid month entry.
     */
    public void testInvalidMonthSkipping()
    {
        // Create a temporary file with valid and invalid months
        try (
            BufferedWriter writer =
                new BufferedWriter(new FileWriter(TEST_FILE_INVALID_MONTH)))
        {
            writer.write(
                "month,username,channel name,country,main topic,likes,posts,followers,comments,views\n");
            writer.write(
                "January,valid_user,valid_channel,US,Test,100,5,1000,20,5000\n");
            writer.write(
                "Noctober,invalid_user,valid_channel,US,Test,999,99,9999,99,9999\n"); // Invalid
                                                                                      // month
            writer.write(
                "February,valid_user,valid_channel,US,Test,150,6,1050,25,5500\n");
            writer.write(
                "March,valid_user,valid_channel,US,Test,200,7,1100,30,6000\n");
            // Add another influencer with only valid months
            writer.write(
                "January,other_user,other_channel,CA,Other,50,3,800,10,4000\n");
            writer.write(
                "February,other_user,other_channel,CA,Other,75,4,850,15,4500\n");
            writer.write(
                "March,other_user,other_channel,CA,Other,100,5,900,20,5000\n");
        }
        catch (IOException e)
        {
            fail(
                "Failed to create temporary test file for invalid month test: "
                    + e.getMessage());
        }

        try
        {
            InputFileReader readerInvalid =
                new InputFileReader(TEST_FILE_INVALID_MONTH);
            InfluencerList listInvalid = readerInvalid.getInfluencerList();

            assertEquals(
                "List should contain 2 influencers after processing invalid month file",
                2,
                listInvalid.getSize());

            Influencer validChannelInfluencer = null;
            for (int i = 0; i < listInvalid.getSize(); i++)
            {
                Influencer current = listInvalid.getEntry(i);
                if ("valid_channel".equals(current.getChannelName()))
                {
                    validChannelInfluencer = current;
                    break;
                }
            }

            assertNotNull(
                "Should find influencer 'valid_channel'",
                validChannelInfluencer);
            // The influencer should have data for Jan, Feb, Mar (indices 0, 1,
            // 2) and N/A for others (0)
            // Check January data (index 0)
            assertEquals(
                "Likes for valid_channel in January should be 100",
                100,
                validChannelInfluencer.getLikes(0));
            assertEquals(
                "Followers for valid_channel in January should be 1000",
                1000,
                validChannelInfluencer.getFollowers(0));
            // Check February data (index 1)
            assertEquals(
                "Likes for valid_channel in February should be 150",
                150,
                validChannelInfluencer.getLikes(1));
            // Check March data (index 2)
            assertEquals(
                "Likes for valid_channel in March should be 200",
                200,
                validChannelInfluencer.getLikes(2));

            // The row with "Noctober" should not contribute data for the first
            // quarter.
            // Its data (999, 99, 9999, 99, 9999) should not appear in
            // Jan/Feb/Mar arrays.
            // The traditional rate calculation should only use Jan/Feb/Mar
            // data.
            // Traditional Rate = ((Jan Comments + Feb Comments + Mar Comments)
            // + (Jan Likes + Feb Likes + Mar Likes)) / Mar Followers * 100
            // Rate = ((20 + 25 + 30) + (100 + 150 + 200)) / 1100 * 100 = (75 +
            // 450) / 1100 * 100 = 525 / 1100 * 100 = 47.727...
            double expectedRate =
                (double)(20 + 25 + 30 + 100 + 150 + 200) / 1100 * 100;
            assertEquals(
                "Traditional rate for valid_channel should ignore Noctober data",
                expectedRate,
                validChannelInfluencer.calculateTraditionalRateQ1(),
                0.01); // Delta for floating point comparison

        }
        catch (IOException e)
        {
            System.out.println(
                "Error reading temporary test file for invalid month test: "
                    + e.getMessage());
            fail(
                "Failed to read temporary test file for invalid month test: "
                    + e.getMessage());
        }
    }


    /**
     * Tests the toInt helper method indirectly through parsing. Creates a
     * temporary file with non-numeric data in numeric fields.
     */
    public void testToIntHelper()
    {
        // Create a temporary file with non-numeric data in a numeric field
        // (e.g., likes)
        try (
            BufferedWriter writer =
                new BufferedWriter(new FileWriter(TEST_FILE_NON_NUMERIC)))
        {
            writer.write(
                "month,username,channel name,country,main topic,likes,posts,followers,comments,views\n");
            writer.write(
                "January,valid_user,valid_channel,US,Test,abc,5,1000,20,5000\n"); // Non-numeric
                                                                                  // likes
            writer.write(
                "February,valid_user,valid_channel,US,Test,150,6,1050,25,5500\n");
            writer.write(
                "March,valid_user,valid_channel,US,Test,200,7,1100,30,6000\n");
        }
        catch (IOException e)
        {
            fail(
                "Failed to create temporary test file for non-numeric test: "
                    + e.getMessage());
        }

        try
        {
            InputFileReader readerNonNumeric =
                new InputFileReader(TEST_FILE_NON_NUMERIC);
            InfluencerList listNonNumeric =
                readerNonNumeric.getInfluencerList();

            assertEquals(
                "List should contain 1 influencer after processing non-numeric file",
                1,
                listNonNumeric.getSize());

            Influencer validChannelInfluencer = listNonNumeric.getEntry(0); // Should
                                                                            // be
                                                                            // the
                                                                            // only
                                                                            // one

            assertNotNull(
                "Should find influencer 'valid_channel'",
                validChannelInfluencer);
            // The non-numeric value "abc" for likes in January should be
            // converted to 0
            assertEquals(
                "Likes for valid_channel in January should be 0 after toInt conversion",
                0,
                validChannelInfluencer.getLikes(0)); // January index is 0
            assertEquals(
                "Followers for valid_channel in January should be 1000",
                1000,
                validChannelInfluencer.getFollowers(0));
            // Check February data (index 1)
            assertEquals(
                "Likes for valid_channel in February should be 150",
                150,
                validChannelInfluencer.getLikes(1));
            // Check March data (index 2)
            assertEquals(
                "Likes for valid_channel in March should be 200",
                200,
                validChannelInfluencer.getLikes(2));

            // The traditional rate calculation should now use 0 for January
            // likes.
            // Traditional Rate = ((Jan Comments + Feb Comments + Mar Comments)
            // + (Jan Likes + Feb Likes + Mar Likes)) / Mar Followers * 100
            // Rate = ((20 + 25 + 30) + (0 + 150 + 200)) / 1100 * 100 = (75 +
            // 350) / 1100 * 100 = 425 / 1100 * 100 = 38.636...
            double expectedRate =
                (double)(20 + 25 + 30 + 0 + 150 + 200) / 1100 * 100;
            assertEquals(
                "Traditional rate for valid_channel should handle non-numeric January likes as 0",
                expectedRate,
                validChannelInfluencer.calculateTraditionalRateQ1(),
                0.01); // Delta for floating point comparison

        }
        catch (IOException e)
        {
            System.out.println(
                "Error reading temporary test file for non-numeric test: "
                    + e.getMessage());
            fail(
                "Failed to read temporary test file for non-numeric test: "
                    + e.getMessage());
        }
    }


    /**
     * Ensures that malformed rows (fewer than 10 fields) are skipped.
     */
    public void testMalformedLineSkipping()
    {
        String filename = "TestMalformed.csv";

        try (
            BufferedWriter writer =
                new BufferedWriter(new FileWriter(filename)))
        {
            writer.write(
                "month,username,channel,country,main,likes,posts,followers,comments,views\n");

            writer.write("February,userA,chanA,US,Test\n");

            writer.write("March,userA,chanA,US,Test,20,2,200,10,300\n");

        }
        catch (IOException e)
        {
            fail("Could not create test fil");
        }

        try
        {
            InputFileReader rdr = new InputFileReader(filename);
            InfluencerList list = rdr.getInfluencerList();
            assertEquals(1, list.getSize());
            Influencer inf = list.getEntry(0);
            assertEquals(10, inf.getLikes(0));
            assertEquals(20, inf.getLikes(2));

            assertEquals(0, inf.getLikes(1));

        }
        catch (IOException e)
        {
            fail("Failed reading malformed test file");
        }
    }


    /**
     * tests all valid months in isValidMonthForProcessing() for full coverage.
     * Makes new file for testing
     */
    public void testAllValidMonths()
    {
        String filename = "TestAllMonths.csv";

        try (
            BufferedWriter writer =
                new BufferedWriter(new FileWriter(filename)))
        {
            writer.write(
                "month,username,channel,country,main,likes,posts,followers,comments,views\n");

            String[] months = { "January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November",
                "December" };

            for (int i = 0; i < months.length; i++)
            {
                writer
                    .write(months[i] + ",u,chanM,US,T," + i + ",1,10,1,100\n");
            }

        }
        catch (IOException e)
        {
            fail("Cannot write all-months file");
        }

        try
        {
            InputFileReader rdr = new InputFileReader(filename);
            InfluencerList list = rdr.getInfluencerList();

            assertEquals(1, list.getSize());
            Influencer inf = list.getEntry(0);

            for (int i = 0; i < 12; i++)
            {
                assertEquals(i, inf.getLikes(i));
            }

        }
        catch (IOException e)
        {
            fail("Could not read all months test file");
        }
    }


    /**
     * testing all month index cases for full code coverage. Makes new file for
     * testing
     */
    public void testAllMonthIndexCases()
    {
        String filename = "TestMonthIndex.csv";

        try (
            BufferedWriter writer =
                new BufferedWriter(new FileWriter(filename)))
        {
            writer.write(
                "month,username,channel,country,main,likes,posts,followers,comments,views\n");
            writer.write("April,u,ch,US,T,1,1,10,1,10\n");
            writer.write("May,u,ch,US,T,2,1,10,1,10\n");
            writer.write("June,u,ch,US,T,3,1,10,1,10\n");
            writer.write("July,u,ch,US,T,4,1,10,1,10\n");
            writer.write("August,u,ch,US,T,5,1,10,1,10\n");
            writer.write("September,u,ch,US,T,6,1,10,1,10\n");
            writer.write("October,u,ch,US,T,7,1,10,1,10\n");
            writer.write("November,u,ch,US,T,8,1,10,1,10\n");
            writer.write("December,u,ch,US,T,9,1,10,1,10\n");

            writer.write("Blarch,u,ch,US,T,99,1,10,1,10\n");

        }
        catch (IOException e)
        {
            fail("Could not create month-index test file: " + e.getMessage());
        }

        try
        {
            InputFileReader rdr = new InputFileReader(filename);
            InfluencerList list = rdr.getInfluencerList();

            assertEquals(1, list.getSize());
            Influencer inf = list.getEntry(0);

            assertEquals(1, inf.getLikes(3));   // April
            assertEquals(2, inf.getLikes(4));   // May
            assertEquals(3, inf.getLikes(5));   // June
            assertEquals(4, inf.getLikes(6));   // July
            assertEquals(5, inf.getLikes(7));   // August
            assertEquals(6, inf.getLikes(8));   // September
            assertEquals(7, inf.getLikes(9));   // October
            assertEquals(8, inf.getLikes(10));  // November
            assertEquals(9, inf.getLikes(11));  // December
            for (int i = 0; i < 12; i++)
            {
                assertFalse(99 == inf.getLikes(i));
            }

        }
        catch (IOException e)
        {
            fail("Could not read month-index file");
        }
    }


    /**
     * Directly ensures that monthIndex == -1 branch is hit
     */
    public void testMonthIndexEqualsNegativeOne()
    {
        String filename = "TestMonthIndexNegative.csv";

        try (
            BufferedWriter writer =
                new BufferedWriter(new FileWriter(filename)))
        {
            writer.write(
                "month,username,channel,country,main,likes,posts,followers,comments,views\n");
            writer.write("January,u,ch,US,T,5,1,10,1,10\n");

            writer.write("Smarch,u,ch,US,T,999,1,10,1,10\n");

        }
        catch (IOException e)
        {
            fail("Could not create negative test");
        }

        try
        {
            InputFileReader rdr = new InputFileReader(filename);
            InfluencerList list = rdr.getInfluencerList();

            assertEquals(1, list.getSize());
            Influencer inf = list.getEntry(0);

            assertEquals(5, inf.getLikes(0));
            for (int i = 1; i < 12; i++)
            {
                assertEquals(0, inf.getLikes(i));
            }

        }
        catch (IOException e)
        {
            fail("Could not read negative-month");
        }
    }

}
