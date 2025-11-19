package prj5;

import java.io.IOException;

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

        boolean showConsole = true;
        boolean showGUI = false;

        if (showConsole)
        {
            // TODO
        }

        if (showGUI)
        {
            // not needed
        }
    }

}
