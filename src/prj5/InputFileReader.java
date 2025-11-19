package prj5;

import java.io.IOException;
import java.util.Scanner;

/**
 * Reads and parses the influencer data CSV file. Stores data for each
 * influencer by month using arrays.
 */

public class InputFileReader
{

    private InfluencerList influencerList;

    private static final int JANUARY_INDEX = 0;
    private static final int FEBRUARY_INDEX = 1;
    private static final int MARCH_INDEX = 2;
    private static final int NUM_MONTHS = 12;

    public InputFileReader(String filename)
        throws IOException
    {
        influencerList = new InfluencerList();
        Scanner inStream = IOHelper.createScanner(filename);
        inStream.nextLine(); // Skip header line

        while (inStream.hasNextLine())
        {
            String line = inStream.nextLine().replaceAll(" ", "");
            String[] values = line.split(",", -1);

            if (values.length < 10)
            {
                continue;
            }

            String month = values[0].trim();
            String username = values[1].trim();
            String channel = values[2].trim();
            String country = values[3].trim();
            String mainTopic = values[4].trim();

            if (!isValidMonthForProcessing(month))
            {
                continue;
            }

            int likes = toInt(values[5].trim());
            int posts = toInt(values[6].trim());
            int followers = toInt(values[7].trim());
            int comments = toInt(values[8].trim());
            int views = toInt(values[9].trim());

            Influencer influencer = findInfluencerByChannel(channel);
            if (influencer == null)
            {
                influencer =
                    new Influencer(username, channel, country, mainTopic);
                influencerList.add(influencer);
            }

            int monthIndex = getMonthIndex(month);
            if (monthIndex != -1)
            {
                influencer.addDataForMonth(
                    monthIndex,
                    likes,
                    posts,
                    followers,
                    comments,
                    views);
            }
        }

        inStream.close();
    }


    private boolean isValidMonthForProcessing(String month)
    {
        return "January".equals(month) || "February".equals(month)
            || "March".equals(month) || "May".equals(month)
            || "June".equals(month) || "July".equals(month)
            || "August".equals(month) || "September".equals(month)
            || "October".equals(month) || "November".equals(month)
            || "December".equals(month) || "April".equals(month);
    }


    private int getMonthIndex(String month)
    {
        switch (month)
        {
            case "January":
                return 0;
            case "February":
                return 1;
            case "March":
                return 2;
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
                return -1;
        }
    }


    private int toInt(String str)
    {
        try
        {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }


    private Influencer findInfluencerByChannel(String channel)
    {
        for (int i = 0; i < influencerList.getSize(); i++)
        {
            Influencer current = influencerList.getEntry(i);
            if (current.getChannelName().equals(channel))
            {
                return current;
            }
        }
        return null;
    }


    public InfluencerList getInfluencerList()
    {
        return influencerList;
    }
}
