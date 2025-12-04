package prj5;

import cs2.*;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI for visualizing influencer engagement metrics.
 * Uses CS2GraphWindowLib (Window, Button, Shape, TextShape, etc.).
 * Supports time period, formula, and sort selection with persistent state.
 */
public class GUIInfluencerVisualization extends Window implements ClickAction {
    private InfluencerList originalList;
    private List<Influencer> displayedInfluencers = new ArrayList<>();

    // Persistent state
    private String timePeriod = "First Quarter"; // "January", "February", "March", "First Quarter"
    private String formula = "Traditional";       // "Traditional", "Reach"
    private String sort = "Channel Name";        // "Channel Name", "Engagement Rate"

    // Formatting
    private static final DecimalFormat DF = new DecimalFormat("#.#");
    private static final Color BAR_COLOR = new Color(70, 130, 180); // Steel blue

    // Buttons
    private Button janBtn, febBtn, marBtn, q1Btn;
    private Button tradBtn, reachBtn;
    private Button nameBtn, rateBtn;
    private Button quitBtn;

    // Constants
    private static final int BAR_WIDTH = 60;
    private static final int BAR_GAP = 30;
    private static final int LABEL_OFFSET = 25;

    /**
     * Constructs the GUI window and initializes components.
     *
     * @param list The influencer list to visualize.
     */
    public GUIInfluencerVisualization(InfluencerList list) {
        super(850, 600, "Social Media Influencer Analytics — Group: vliu1234 abc1234 xyz5678");
        this.originalList = list;

        setupButtons();
        updateDisplay();
        setVisible(true);
    }

    private void setupButtons() {
        // Time Period
        janBtn = new Button("January", this);
        febBtn = new Button("February", this);
        marBtn = new Button("March", this);
        q1Btn = new Button("First Quarter", this);

        // Formula
        tradBtn = new Button("Traditional", this);
        reachBtn = new Button("Reach", this);

        // Sort
        nameBtn = new Button("Sort by Name", this);
        rateBtn = new Button("Sort by Rate", this);

        // Quit
        quitBtn = new Button("Quit", this);

        // Add buttons to window (left side by default)
        addButton(janBtn, WindowSide.TOP);
        addButton(febBtn, WindowSide.TOP);
        addButton(marBtn, WindowSide.TOP);
        addButton(q1Btn, WindowSide.TOP);

        addButton(tradBtn, WindowSide.TOP);
        addButton(reachBtn, WindowSide.TOP);

        addButton(nameBtn, WindowSide.TOP);
        addButton(rateBtn, WindowSide.TOP);

        addButton(quitBtn, WindowSide.TOP);
    }

    @Override
    public void clickAccepted(Button button) {
        if (button == janBtn) timePeriod = "January";
        else if (button == febBtn) timePeriod = "February";
        else if (button == marBtn) timePeriod = "March";
        else if (button == q1Btn) timePeriod = "First Quarter";
        else if (button == tradBtn) formula = "Traditional";
        else if (button == reachBtn) formula = "Reach";
        else if (button == nameBtn) sort = "Channel Name";
        else if (button == rateBtn) sort = "Engagement Rate";
        else if (button == quitBtn) {
            endVisualization();
            return;
        }

        updateDisplay();
    }

    @Override
    public void clickRejected(Button button) {
        // Ignore
    }

    private void updateDisplay() {
        // Clone and sort
        InfluencerList working = new InfluencerList();
        for (int i = 0; i < originalList.getLength(); i++) {
            working.add(originalList.getEntry(i));
        }

        if ("Channel Name".equals(sort)) {
            working.sortByChannelName();
        } else if ("Engagement Rate".equals(sort)) {
            working.sortByReachRate(); // Per spec: only Reach Rate used for rate sorting in GUI
        }

        displayedInfluencers.clear();
        for (int i = 0; i < working.getLength(); i++) {
            displayedInfluencers.add(working.getEntry(i));
        }

        redraw();
    }

    private void redraw() {
        clearShapes();

        int n = displayedInfluencers.size();
        if (n == 0) return;

        // Chart area
        int chartTop = 130;
        int chartHeight = getHeight() - chartTop - 40;
        int startX = 50;

        // Find max rate for scaling
        double maxRate = 1.0;
        for (Influencer inf : displayedInfluencers) {
            double r = getRate(inf);
            if (!Double.isNaN(r) && r > maxRate) maxRate = r;
        }

        // Draw bars and labels
        for (int i = 0; i < n; i++) {
            Influencer inf = displayedInfluencers.get(i);
            double rate = getRate(inf);

            int x = startX + i * (BAR_WIDTH + BAR_GAP);
            int barBottom = getHeight() - 30;
            int barHeight = Double.isNaN(rate) ? 0 : (int) ((rate / maxRate) * (chartHeight - 20));
            int barTop = barBottom - barHeight;

            // Bar
            Shape bar = new Shape(x, barTop, BAR_WIDTH, barHeight);
            bar.setFilled(true);
            bar.setFillColor(BAR_COLOR);
            addShape(bar);

            // Channel name (X-axis label)
            TextShape nameLabel = new TextShape(x + BAR_WIDTH / 2, barBottom + 5, inf.getChannelName());
            nameLabel.setFontSize(10);
            addShape(nameLabel);

            // Rate value (above bar)
            String rateStr = Double.isNaN(rate) ? "N/A" : DF.format(rate);
            TextShape rateLabel = new TextShape(x + BAR_WIDTH / 2, barTop - LABEL_OFFSET, rateStr);
            rateLabel.setFontSize(11);
            addShape(rateLabel);
        }
    }

    private double getRate(Influencer inf) {
        switch (timePeriod) {
            case "January": return calculateMonthlyRate(inf, 0);
            case "February": return calculateMonthlyRate(inf, 1);
            case "March": return calculateMonthlyRate(inf, 2);
            default: // First Quarter
                if ("Traditional".equals(formula)) {
                    return inf.calculateTraditionalRateQ1();
                } else {
                    return inf.calculateReachRateQ1();
                }
        }
    }

    private double calculateMonthlyRate(Influencer inf, int month) {
        int likes = inf.getLikes(month);
        int comments = inf.getComments(month);

        if ("Traditional".equals(formula)) {
            int followers = inf.getFollowers(month);
            return followers == 0 ? Double.NaN : (likes + comments) * 100.0 / followers;
        } else { // Reach
            int views = inf.getViews(month);
            return views == 0 ? Double.NaN : (likes + comments) * 100.0 / views;
        }
    }

    private void endVisualization() {
        System.exit(0);
    }
}