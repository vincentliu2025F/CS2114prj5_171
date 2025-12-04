package prj5;

import java.io.IOException;
import java.text.DecimalFormat;


public class ProjectRunner
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    // ----------------------------------------------------------
    /**
     * Main method of project
     * 
     * @param args
     *            - runtime arguments
     * @throws IOException
     *             - exception when parsing file
     */
    public static void main(String[] args)
        throws IOException
    {

        InputFileReader filer;
        if (args.length > 0)
        {
            filer = new InputFileReader(args[0]);
        }
        else
        {
            filer = new InputFileReader("SampleInput1_2023.csv");
        }

        boolean showConsole = false;
        boolean showGUI = true;

        if (showConsole)
        {
            InfluencerList list = filer.getInfluencerList();
            list.sortByChannelName();

            DecimalFormat formatter = new DecimalFormat("#.#");

            for (int i = 0; i < list.getSize(); i++)
            {
                Influencer inf = list.getEntry(i);
                double tradRate = inf.calculateTraditionalRateQ1();
                System.out.println(inf.getChannelName());
                if (Double.isNaN(tradRate))
                {
                    System.out.println("traditional: N/A");
                }
                else
                {
                    System.out
                        .println("traditional: " + formatter.format(tradRate));
                }
                System.out.println("==========");
            }
            System.out.println("**********");
            System.out.println("**********");

            list.sortByReachRate();

            for (int i = 0; i < list.getSize(); i++)
            {
                Influencer inf = list.getEntry(i);

                double reach = inf.calculateReachRateQ1();
                System.out.println(inf.getChannelName());
                if (Double.isNaN(reach))
                {
                    System.out.println("reach: N/A");
                }
                else
                {
                    System.out.println("reach: " + formatter.format(reach));
                }
                System.out.println("==========");
            }
        }

        if (showGUI)
        {
            new GUIInfluencerVisualization(filer.getInfluencerList());
        }
    }

}
