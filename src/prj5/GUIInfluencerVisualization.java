package prj5;

import cs2.*;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI for visualizing influencer engagement metrics. Uses CS2GraphWindowLib
 * (Window, Button, Shape, TextShape, etc.). Supports time period, formula, and
 * sort selection with persistent state.
 */
public class GUIInfluencerVisualization
{

    private Window window;
    private InfluencerList originalList;
    private List<Influencer> displayedInfluencers = new ArrayList<>();

    // Persistent state
    private String timePeriod = "First Quarter"; // "January", "February",
                                                 // "March", "First Quarter"
    private String formula = "Traditional";       // "Traditional", "Reach"
    private String sort = "Channel Name";        // "Channel Name", "Engagement
                                                 // Rate"

    // Formatting
    private static final DecimalFormat DF = new DecimalFormat("#.#");
    private static final Color BAR_COLOR = new Color(70, 130, 180); // Steel
                                                                    // blue

    // Buttons
    private Button janBtn, febBtn, marBtn, q1Btn;
    private Button tradBtn, reachBtn;
    private Button nameBtn, rateBtn;
    private Button quitBtn;

    // Constants
    private static final int BAR_WIDTH = 60;
    private static final int BAR_GAP = 60;
    private static final int LABEL_OFFSET = 25;
    private static final double DISPLAY_FACTOR = 1.5;

    private static final int JANUARY = 0;
    private static final int FEBUARY = 1;
    private static final int MARCH = 2;

    /**
     * Constructs the GUI window and initializes components.
     *
     * @param list
     *            The influencer list to visualize.
     */
    public GUIInfluencerVisualization(InfluencerList list)
    {
        this.originalList = list;
        window = new Window("Influencer Data Visualizer");
        window
            .setSize((int)(500 * DISPLAY_FACTOR), (int)(500 * DISPLAY_FACTOR));

        setupButtons();
        updateDisplay();
    }


    private void setupButtons()
    {
        // Time Period
        janBtn = new Button("January");
        febBtn = new Button("February");
        marBtn = new Button("March");

        q1Btn = new Button("First Quarter");
        window.addButton(janBtn, WindowSide.SOUTH);
        window.addButton(febBtn, WindowSide.SOUTH);
        window.addButton(marBtn, WindowSide.SOUTH);
        window.addButton(q1Btn, WindowSide.SOUTH);

        janBtn.onClick(this, "clickAccept");
        febBtn.onClick(this, "clickAccept");
        marBtn.onClick(this, "clickAccept");
        q1Btn.onClick(this, "clickAccept");

        // Formula
        tradBtn = new Button("Traditional");
        reachBtn = new Button("Reach");

        window.addButton(tradBtn, WindowSide.WEST);
        window.addButton(reachBtn, WindowSide.WEST);

        tradBtn.onClick(this, "clickAccept");
        reachBtn.onClick(this, "clickAccept");

        // Sort
        nameBtn = new Button("Sort by Name");
        rateBtn = new Button("Sort by Rate");

        window.addButton(nameBtn, WindowSide.NORTH);
        window.addButton(rateBtn, WindowSide.NORTH);

        nameBtn.onClick(this, "clickAccept");
        rateBtn.onClick(this, "clickAccept");
        // Quit
        quitBtn = new Button("Quit");

        window.addButton(quitBtn, WindowSide.NORTH);
        quitBtn.onClick(this, "clickAccept");
    }


    public void clickAccept(Button button)
    {
        if (button == janBtn)
            timePeriod = "January";
        else if (button == febBtn)
            timePeriod = "February";
        else if (button == marBtn)
            timePeriod = "March";
        else if (button == q1Btn)
            timePeriod = "First Quarter";
        else if (button == tradBtn)
            formula = "Traditional";
        else if (button == reachBtn)
            formula = "Reach";
        else if (button == nameBtn)
            sort = "Channel Name";
        else if (button == rateBtn)
            sort = "Engagement Rate";
        else if (button == quitBtn)
        {
            endVisualization();
            return;
        }

        updateDisplay();
    }


    private void updateDisplay()
    {
        // Clone and sort
        InfluencerList working = new InfluencerList();
        for (int i = 0; i < originalList.getLength(); i++)
        {
            working.add(originalList.getEntry(i));
        }

        if ("Channel Name".equals(sort))
        {
            working.sortByChannelName();
        }
        else if ("Engagement Rate".equals(sort))
        {
            if ("Traditional".equals(formula))
            {
                switch (timePeriod)
                {
                    case "January":
                        working.sortByTraditionalRateForMonth(JANUARY);
                        break;
                    case "February":
                        working.sortByTraditionalRateForMonth(FEBUARY);
                        break;
                    case "March":
                        working.sortByTraditionalRateForMonth(MARCH);
                        break;
                    default: // First Quarter
                        working.sortByTraditionalRate();
                        break;
                }
            }
            else
            {
                switch (timePeriod)
                {
                    case "January":
                        working.sortByReachRateForMonth(JANUARY);
                        break;
                    case "February":
                        working.sortByReachRateForMonth(FEBUARY);
                        break;
                    case "March":
                        working.sortByReachRateForMonth(MARCH);
                        break;
                    default: // First Quarter
                        working.sortByReachRate();
                }

            }

        }

        displayedInfluencers.clear();
        for (int i = working.getLength() - 1; i >= 0; i--)
        {
            displayedInfluencers.add(working.getEntry(i));
        }

        redraw();
    }


    private void redraw()
    {
        window.removeAllShapes();

        int n = displayedInfluencers.size();
        if (n == 0)
            return;

        int panelHeight = window.getGraphPanelHeight();
        int panelWidth = window.getGraphPanelWidth();

        // Chart area
        int chartTop = 130;
        int chartHeight = panelHeight - chartTop - 40;
        int startX = 50;

        // Find max rate for scaling
        double maxRate = 1.0;
        for (Influencer inf : displayedInfluencers)
        {
            double r = getRate(inf);
            if (!Double.isNaN(r) && r > maxRate)
                maxRate = r;
        }

        // Draw bars and labels
        for (int i = 0; i < n; i++)
        {
            Influencer inf = displayedInfluencers.get(i);
            double rate = getRate(inf);

            int x = startX + i * (BAR_WIDTH + BAR_GAP);
            int barBottom = panelHeight - 70;
            int barHeight = Double.isNaN(rate)
                ? 0
                : (int)((rate / maxRate) * (chartHeight - 20));
            int barTop = barBottom - barHeight;

            // Bar
            Shape bar = new Shape(x, barTop, BAR_WIDTH, barHeight);
            bar.setForegroundColor(BAR_COLOR);
            window.addShape(bar);

            // Channel name (X-axis label)
            TextShape nameLabel = new TextShape(0, 0, inf.getChannelName());
            int textX = x + BAR_WIDTH / 2 - nameLabel.getWidth() / 2;
            int textY = barBottom + 5;

            nameLabel.setX(textX);
            nameLabel.setY(textY);
            window.addShape(nameLabel);

            // Rate value (above bar)
            String rateStr = Double.isNaN(rate) ? "N/A" : DF.format(rate);
            TextShape rateLabel = new TextShape(0, 0, rateStr);

            int rateX = x + BAR_WIDTH / 2 - rateLabel.getWidth() / 2;
            int rateY = textY + nameLabel.getHeight() + 3;

            rateLabel.setX(rateX);
            rateLabel.setY(rateY);

            window.addShape(rateLabel);
        }
    }


    private double getRate(Influencer inf)
    {
        switch (timePeriod)
        {
            case "January":
                return calculateMonthlyRate(inf, 0);
            case "February":
                return calculateMonthlyRate(inf, 1);
            case "March":
                return calculateMonthlyRate(inf, 2);
            default: // First Quarter
                if ("Traditional".equals(formula))
                {
                    return inf.calculateTraditionalRateQ1();
                }
                else
                {
                    return inf.calculateReachRateQ1();
                }
        }
    }


    private double calculateMonthlyRate(Influencer inf, int month)
    {
        int likes = inf.getLikes(month);
        int comments = inf.getComments(month);

        if ("Traditional".equals(formula))
        {
            int followers = inf.getFollowers(month);
            return followers == 0
                ? Double.NaN
                : (likes + comments) * 100.0 / followers;
        }
        else
        { // Reach
            int views = inf.getViews(month);
            return views == 0 ? Double.NaN : (likes + comments) * 100.0 / views;
        }
    }


    private void endVisualization()
    {
        System.exit(0);
    }
}
