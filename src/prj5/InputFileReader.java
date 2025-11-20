package prj5;

import java.io.IOException;
import java.util.Scanner;
import student.IOHelper;

/**
 * Reads and parses the influencer data CSV file. Stores data for each
 * influencer by month using arrays within the Influencer class. Handles invalid
 * months and non-numeric data gracefully.
 */
public class InputFileReader
{

    private InfluencerList influencerList;

    // Array indices for monthswewewee
    private static final int JANUARY_INDEX = 0;
    private static final int FEBRUARY_INDEX = 1;
    private static final int MARCH_INDEX = 2;
    // ... other month indices ...
    private static final int NUM_MONTHS = 12;

    /**
     * Constructor for InputFileReader. Reads the specified file, parses the
     * data for each row, validates months, converts strings to integers using
     * the helper method, finds or creates Influencer objects, and adds the
     * monthly data to those objects within the InfluencerList.
     *
     * @param filename
     *            The name of the CSV file to read.
     * @throws IOException
     *             If an error occurs during file reading.
     */
    public InputFileReader(String filename)
        throws IOException
    {
        influencerList = new InfluencerList();
        Scanner inStream = IOHelper.createScanner(filename);
        inStream.nextLine(); // Skip header line

        while (inStream.hasNextLine())
        {
            String line = inStream.nextLine().replaceAll(" ", ""); // Remove
                                                                   // spaces (as
                                                                   // per spec
                                                                   // example)
            String[] values = line.split(",", -1); // Split, keeping empty
                                                   // fields

            // Basic validation: ensure we have enough columns
            if (values.length < 10)
            {
                continue; // Skip malformed lines
            }

            String month = values[0].trim();
            String username = values[1].trim();
            String channel = values[2].trim();
            String country = values[3].trim();
            String mainTopic = values[4].trim();

            // Validate month - only process valid months (Jan-Dec)
            if (!isValidMonthForProcessing(month))
            {
                continue; // Skip data for invalid months like "Noctober"
            }

            // Convert numeric strings using the helper method
            int likes = toInt(values[5].trim());
            int posts = toInt(values[6].trim());
            int followers = toInt(values[7].trim());
            int comments = toInt(values[8].trim());
            int views = toInt(values[9].trim());

            // Find or create the influencer object based on channel name
            Influencer influencer = findInfluencerByChannel(channel);
            if (influencer == null)
            {
                // Create new influencer if not found in the list
                influencer =
                    new Influencer(username, channel, country, mainTopic);
                influencerList.add(influencer); // Add new influencer to the
                                                // list
            }

            // Determine the month index and add the monthly data to the
            // influencer object
            int monthIndex = getMonthIndex(month);
            if (monthIndex != -1)
            { // Should always be true due to isValidMonth check
                influencer.addDataForMonth(
                    monthIndex,
                    likes,
                    posts,
                    followers,
                    comments,
                    views);
            }
        }

        inStream.close(); // Close the scanner after reading
    }


    /**
     * Checks if a given string is a standard month name (January to December).
     *
     * @param month
     *            The month string to check.
     * @return true if the month is valid (January-December), false otherwise.
     */
    private boolean isValidMonthForProcessing(String month)
    {
        return "January".equals(month) || "February".equals(month)
            || "March".equals(month) || "April".equals(month) // Corrected order
            || "May".equals(month) || "June".equals(month)
            || "July".equals(month) || "August".equals(month)
            || "September".equals(month) || "October".equals(month)
            || "November".equals(month) || "December".equals(month);
    }


    /**
     * Gets the integer index for a given month string (0 for January, 1 for
     * February, etc.).
     *
     * @param month
     *            The month string.
     * @return The index (0-11) or -1 if invalid.
     */
    private int getMonthIndex(String month)
    {
        switch (month)
        {
            case "January":
                return JANUARY_INDEX;
            case "February":
                return FEBRUARY_INDEX;
            case "March":
                return MARCH_INDEX;
            case "April":
                return 3;
            case "May":
                return 4;
            case "June":
                return 5;
            case "July":
                return 6;
            case "August":
                return 7;
            case "September":
                return 8;
            case "October":
                return 9;
            case "November":
                return 10;
            case "December":
                return 11;
            default:
                return -1; // Should not happen if isValidMonth is called first
        }
    }


    /**
     * Helper method to convert a string to an integer. Handles potential
     * NumberFormatException by returning 0.
     *
     * @param str
     *            The string to convert.
     * @return The integer value, or 0 if conversion fails.
     */
    private int toInt(String str)
    {
        try
        {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException e)
        {
            // According to spec, if conversion fails, treat as 0
            return 0;
        }
    }


    /**
     * Finds an influencer in the list by their channel name.
     *
     * @param channel
     *            The channel name to search for.
     * @return The Influencer object if found, null otherwise.
     */
    private Influencer findInfluencerByChannel(String channel)
    {
        for (int i = 0; i < influencerList.getSize(); i++)
        {
            Influencer current = influencerList.getEntry(i); // Assuming
                                                             // getEntry exists
            if (current.getChannelName().equals(channel))
            {
                return current;
            }
        }
        return null; // Not found
    }


    /**
     * Getter method to access the parsed data list from outside this class.
     *
     * @return The InfluencerList containing the parsed data.
     */
    public InfluencerList getInfluencerList()
    {
        return influencerList;
    }
}
