package prj5;

// -------------------------------------------------------------------------
/**
 * Influencer class which contains variables and logic pertaining to the
 * influencers in this project
 * 
 * @author nabiilf
 * @version Nov 19, 2025
 */
public class Influencer
{

    // Fields
    private String username;
    private String channelName;
    private String country;
    private String topics;
// private int likes;
// private int posts;
// private int followers;
// private int comments;
// private int views;

    private Statistics[] monthlyStats;

    // ----------------------------------------------------------
    /**
     * Create a new Influencer object.
     * 
     * @param username
     *            - influencer username
     * @param channelName
     *            - channel name
     * @param country
     *            - influencer country
     * @param topic
     *            - influencer topic
     */
    public Influencer(
        String username,
        String channelName,
        String country,
        String topic)
    {
        this.username = username;
        this.channelName = channelName;
        this.country = country;
        this.topics = topic;

        monthlyStats = new Statistics[12];
        for (int i = 0; i < 12; i++)
        {
            monthlyStats[i] = new Statistics();
        }
    }


    // Getters
    // ----------------------------------------------------------
    /**
     * returns the influencer username
     * 
     * @return influencer username
     */
    public String getUsername()
    {
        return username;
    }


    // ----------------------------------------------------------
    /**
     * Getter method for influencer channel name
     * 
     * @return influencer channel name
     */
    public String getChannelName()
    {
        return channelName;
    }


    // ----------------------------------------------------------
    /**
     * Returns the influencer's country
     * 
     * @return influencer's country
     */
    public String getCountry()
    {
        return country;
    }


    // ----------------------------------------------------------
    /**
     * returns the topics of the influencer object
     * 
     * @return topics of influencer
     */
    public String getTopics()
    {
        return topics;
    }


    // ----------------------------------------------------------
    /**
     * Returns likes of influencer
     * 
     * @param month
     *            of likes to fetch
     * @return number of influencer likes
     */
    public int getLikes(int month)
    {
        return monthlyStats[month].getLikes();
    }


    // ----------------------------------------------------------
    /**
     * returns the influencer's post numbers
     * 
     * @param month
     *            of posts to grab
     * @return post numbers
     */
    public int getPosts(int month)
    {
        return monthlyStats[month].getPosts();
    }


    // ----------------------------------------------------------
    /**
     * Returns influencer's follower count
     * 
     * @param month
     *            of followers to grab
     * @return influencer's followers
     */
    public int getFollowers(int month)
    {
        return monthlyStats[month].getFollowers();
    }


    // ----------------------------------------------------------
    /**
     * Getter method for influencer comments
     * 
     * @param month
     *            in which to grab comments
     * @return influencer's comments
     */
    public int getComments(int month)
    {
        return monthlyStats[month].getComments();
    }


    // ----------------------------------------------------------
    /**
     * Getter method for influencer views
     * 
     * @param month
     *            of views to grab
     * @return influencer views
     */
    public int getViews(int month)
    {
        return monthlyStats[month].getViews();
    }


    // Setters
    // ----------------------------------------------------------
    /**
     * change the influencer's username
     * 
     * @param username
     *            - new username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }


    // ----------------------------------------------------------
    /**
     * Set a new channel name
     * 
     * @param channelName
     *            - new channel name
     */
    public void setChannelName(String channelName)
    {
        this.channelName = channelName;
    }


    // ----------------------------------------------------------
    /**
     * Assign a new country to the influencer
     * 
     * @param country
     *            - country to be assigned
     */
    public void setCountry(String country)
    {
        this.country = country;
    }


    // ----------------------------------------------------------
    /**
     * Set the topics covered by influencer
     * 
     * @param topics
     *            - topics covered by influencer
     */
    public void setTopics(String topics)
    {
        this.topics = topics;
    }


    // ----------------------------------------------------------
    /**
     * set the likes of the influencer
     * 
     * @param month
     *            to set statistic for
     * @param likes
     *            - new like count
     */
    public void setLikes(int likes, int month)
    {
        this.monthlyStats[month].setLikes(likes);
    }


    // ----------------------------------------------------------
    /**
     * set the post count of influencer
     * 
     * @param month
     *            to set statistic for
     * @param posts
     *            - post count
     */
    public void setPosts(int posts, int month)
    {
        this.monthlyStats[month].setPosts(posts);
    }


    // ----------------------------------------------------------
    /**
     * Set follower count of influencer
     * 
     * @param month
     *            to set statistic for
     * @param followers
     *            - follower count
     */
    public void setFollowers(int followers, int month)
    {
        this.monthlyStats[month].setFollowers(followers);
    }


    // ----------------------------------------------------------
    /**
     * Set comments count of influencer
     * 
     * @param month
     *            to set statistic for
     * @param comments
     *            - comments count
     */
    public void setComments(int comments, int month)
    {
        this.monthlyStats[month].setComments(comments);
    }


    // ----------------------------------------------------------
    /**
     * Set the number of views obtained by influencer
     * 
     * @param month
     *            to set statistic for
     * @param views
     *            - new number of views
     */
    public void setViews(int views, int month)
    {
        this.monthlyStats[month].setViews(views);
    }


    // ----------------------------------------------------------
    /**
     * Method to calculate the reach rate
     * 
     * @return reach rate of Q1
     */
    public double calculateReachRateQ1()
    {
        double totalLikes = 0;
        double totalComments = 0;
        double totalViews = 0;

        for (int i = 0; i < 3; i++)
        {
            totalLikes += monthlyStats[i].getLikes();
            totalComments += monthlyStats[i].getComments();
            totalViews += monthlyStats[i].getViews();
        }

        if (totalViews == 0)
        {
            return Double.NaN;
        }

        return (totalLikes + totalComments) / totalViews * 100;
    }


    // ----------------------------------------------------------
    /**
     * Add all data for a specific month.
     * 
     * @param monthIndex
     *            - what month to add data to
     * @param likes
     *            - likes for month
     * @param posts
     *            - post for month
     * @param followers
     *            - followers for month
     * @param comments
     *            - comments for month
     * @param views
     *            - views for month
     */
    public void addDataForMonth(
        int monthIndex,
        int likes,
        int posts,
        int followers,
        int comments,
        int views)
    {
        monthlyStats[monthIndex].setLikes(likes);
        monthlyStats[monthIndex].setPosts(posts);
        monthlyStats[monthIndex].setFollowers(followers);
        monthlyStats[monthIndex].setComments(comments);
        monthlyStats[monthIndex].setViews(views);

    }


    // ----------------------------------------------------------
    /**
     * Calculates traditional reach for Q1
     * 
     * @return traditional reach for Q1
     */
    public double calculateTraditionalRateQ1()
    {

        double totalLikes = 0;
        double totalComments = 0;

        for (int i = 0; i < 3; i++)
        {
            totalLikes += monthlyStats[i].getLikes();
            totalComments += monthlyStats[i].getComments();
        }

        double marchFollowers = monthlyStats[2].getFollowers();

        if (marchFollowers == 0)
        {
            return Double.NaN;
        }

        return (totalLikes + totalComments) / marchFollowers * 100;
    }


    /**
     * Determines if this object is equal to passed object
     * 
     * @param influencer
     *            - influencer to be passed
     * @return boolean value describing equality.
     */
    public boolean equals(Object influencer)
    {
        if (this == influencer)
        {
            return true;
        }
        if (influencer == null || influencer.getClass() != this.getClass())
        {
            return false;
        }

        Influencer other = (Influencer)influencer;

        if (!this.channelName.equals(other.channelName)
            || !this.username.equals(other.username)
            || !this.country.equals(other.country)
            || !this.topics.equals(other.topics))
        {
            return false;
        }

        for (int i = 0; i < 12; i++)
        {
            Statistics a = this.monthlyStats[i];
            Statistics b = other.monthlyStats[i];

            if (a.getLikes() != b.getLikes()
                || a.getComments() != b.getComments()
                || a.getViews() != b.getViews() || a.getPosts() != b.getPosts()
                || a.getFollowers() != b.getFollowers())
            {
                return false;
            }
        }

        return true;
    }

}
