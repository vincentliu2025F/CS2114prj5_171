package prj5;


import java.io.IOException;
import java.util.Scanner;

/**
 * Reads and parses the influencer data CSV file.
 * Stores data for each influencer by month using arrays.
 */

public class InputFileReader {

    private LinkedListed<Influencer> influencerList;

    private static final int JANUARY_INDEX = 0;
    private static final int FEBRUARY_INDEX = 1;
    private static final int MARCH_INDEX = 2;
    private static final int NUM_MONTHS = 12;

    public InputFileReader(String filename) throws IOException {
        influencerList = new LinkedListed<>();
        Scanner inStream = IOHelper.createScanner(filename);
        inStream.nextLine(); // Skip header line

        while (inStream.hasNextLine()) {
            String line = inStream.nextLine();
            String[] values = line.split(",", -1);

            if (values.length < 10) {
                continue;
            }

            String month = values[0].trim();
            String username = values[1].trim();
            String channelName = values[2].trim();
            String country = values[3].trim();
            String mainTopic = values[4].trim();

            if (!isValidMonthForProcessing(month)) {
                continue;
            }

            int likes = toInt(values[5].trim());
            int posts = toInt(values[6].trim());
            int followers = toInt(values[7].trim());
            int comments = toInt(values[8].trim());
            int views = toInt(values[9].trim());

            Influencer influencer = findInfluencerByChannel(channelName);
            if (influencer == null) {
                influencer = new Influencer(username, channelName, country, mainTopic);
                influencerList.add(influencer);
            }

            int monthIndex = getMonthIndex(month);
            if (monthIndex != -1) {
                influencer.addDataForMonth(monthIndex, likes, posts, followers, comments, views);
            }
        }

        inStream.close();
    }

    private boolean isValidMonthForProcessing(String month) {
        return "January".equals(month) || "February".equals(month) || "March".equals(month);
    }

    private int getMonthIndex(String month) {
        switch (month) {
            case "January": return JANUARY_INDEX;
            case "February": return FEBRUARY_INDEX;
            case "March": return MARCH_INDEX;
            default: return -1;
        }
    }

    private int toInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private Influencer findInfluencerByChannel(String channelName) {
        for (int i = 0; i < influencerList.getSize(); i++) {
            Influencer current = influencerList.get(i);
            if (current.getChannelName().equals(channelName)) {
                return current;
            }
        }
        return null;
    }

    public LinkedListed<Influencer> getInfluencerList() {
        return influencerList;
    }
}