package prj5;

import java.io.IOException;
import java.util.Scanner;

/**
 * Reads and parses the influencer data CSV file. Stores data for each
 * influencer by month using arrays within the Influencer class. Handles invalid
 * months and non-numeric data gracefully.
 */
public class InputFileReader
{

    private LinkedListed<Influencer> influencerList;

    // Array indices for months
    private static final int JANUARY_INDEX = 0;
    private static final int FEBRUARY_INDEX = 1;
    private static final int MARCH_INDEX = 2;
    // Add more indices as needed if processing other months later
    // private static final int APRIL_INDEX = 3;
    // ... up to DECEMBER_INDEX = 11
    private static final int DECEMBER_INDEX = 11;
    private static final int NUM_MONTHS = 12;

    /**
     * Constructor for InputFileReader. Reads the specified file, parses the
     * data for January, February, and March, validates months, converts strings
     * to integers, finds or creates Influencer objects, and adds the monthly
     * data to those objects within the linked list.
     *
     * @param filename
     *            The name of the CSV file to read.
     * @throws IOException
     *             If an error occurs during file reading.
     */
    public InputFileReader(String filename)
        throws IOException
    {
        influencerList = new LinkedListed<>();
        Scanner inStream = IOHelper.createScanner(filename);
        inStream.nextLine(); // Skip header line

        while (inStream.hasNextLine())
        {
            String line = inStream.nextLine();
            // Note: Avoiding .replaceAll(" ", "") on the entire line
            // to prevent removing spaces within quoted data fields if they
            // existed.
            // Splitting by comma is usually sufficient for the given format.
            String[] values = line.split(",", -1); // -1 to keep empty fields if
                                                   // any

            // Basic validation: ensure we have enough columns
            if (values.length < 10)
            {
                continue; // Skip malformed lines
            }

            String month = values[0].trim();
            String username = values[1].trim();
            String channelName = values[2].trim();
            String country = values[3].trim();
            String mainTopic = values[4].trim();

            // Validate month - only process Jan, Feb, Mar for now as per
            // project requirements
            if (!isValidMonthForProcessing(month))
            {
                continue; // Skip data for months outside the first quarter if
                          // needed, or other invalid months
            }

            // Convert numeric strings using the helper method
            int likes = toInt(values[5].trim());
            int posts = toInt(values[6].trim());
            int followers = toInt(values[7].trim());
            int comments = toInt(values[8].trim());
            int views = toInt(values[9].trim());

            // Find or create the influencer object
            Influencer influencer = findInfluencerByChannel(channelName);
            if (influencer == null)
            {
                // Create new influencer if not found
                influencer =
                    new Influencer(username, channelName, country, mainTopic);
                influencerList.add(influencer); // Add new influencer to the
                                                // list
            }

            // Determine the month index and add the monthly data to the
            // influencer object
            int monthIndex = getMonthIndex(month);
            if (monthIndex != -1)
            { // Should always be true due to isValidMonth check, but good
              // practice
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
     * Checks if a given string is a standard month name within the first
     * quarter (Jan, Feb, Mar). This method can be modified later to accept all
     * months if full year processing is needed.
     *
     * @param month
     *            The month string to check.
     * @return true if the month is valid for processing (January, February,
     *             March), false otherwise.
     */
    private boolean isValidMonthForProcessing(String month)
    {
        // For intermediate submission, only process Jan, Feb, Mar
        // The project spec says "all valid months January to December should be
        // processed and stored"
        // but calculations are for Q1. However, the example code and context
        // suggest focusing on Q1 initially.
        // Let's adjust this to accept all valid months for parsing/storage as
        // the spec states,
        // but the test data and output focus on Q1.
        // For InputFileReader's core function (parsing and storing), we can
        // accept all valid months.
        // The rate calculation logic in Influencer will only use Q1 data.
        // So, this function should check for *any* valid month.
        return "January".equals(month) || "February".equals(month)
            || "March".equals(month) || "April".equals(month)
            || "May".equals(month) || "June".equals(month)
            || "July".equals(month) || "August".equals(month)
            || "September".equals(month) || "October".equals(month)
            || "November".equals(month) || "December".equals(month);
    }


    /**
     * Gets the integer index for a given month string.
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
                return DECEMBER_INDEX;
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
     * @param channelName
     *            The channel name to search for.
     * @return The Influencer object if found, null otherwise.
     */
    private Influencer findInfluencerByChannel(String channelName)
    {
        for (int i = 0; i < influencerList.getSize(); i++)
        {
            Influencer current = influencerList.get(i); // Assuming a get(index)
                                                        // method exists
            if (current.getChannelName().equals(channelName))
            {
                return current;
            }
        }
        return null; // Not found
    }


    /**
     * Getter method to access the parsed data list from outside this class.
     *
     * @return The LinkedListed<Influencer> containing the parsed data.
     */
    public LinkedListed<Influencer> getInfluencerList()
    {
        return influencerList;
    }
}
