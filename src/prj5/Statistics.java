package prj5;

// -------------------------------------------------------------------------
/**
 * Statistics "struct" used to help organize data in the influencer class.
 * 
 * @author ryanjeronimus
 * @version Nov 19, 2025
 */
public class Statistics
{
    /**
     * Likes for a month
     */
    private int likes;
    /**
     * Monthly comments
     */
    private int comments;
    /**
     * monthly views
     */
    private int views;
    /**
     * Monthyl posts
     */
    private int posts;
    /**
     * Monthly followers
     */
    private int followers;

    // ----------------------------------------------------------
    /**
     * Create a new Statistics object.
     * 
     * @param likes
     *            - influencer likes
     * @param comments
     *            - influencer comments
     * @param views
     *            - influencer views
     * @param posts
     *            - influencer posts
     * @param followers
     *            - influencer followers.
     */
    public Statistics(
        int likes,
        int comments,
        int views,
        int posts,
        int followers)
    {
        this.likes = likes;
        this.comments = comments;
        this.views = views;
        this.posts = posts;
        this.followers = followers;
    }


    // ----------------------------------------------------------
    /**
     * Create a new Statistics object.
     */
    public Statistics()
    {
        this(0, 0, 0, 0, 0);
    }


    // ----------------------------------------------------------
    /**
     * Return the likes of the influencer
     * 
     * @return influencer likes
     */
    public int getLikes()
    {
        return this.likes;
    }


    // ----------------------------------------------------------
    /**
     * return comments of influencer
     * 
     * @return influencer comments
     */
    public int getComments()
    {
        return this.comments;
    }


    /**
     * return views
     * 
     * @return views of influencer
     */
    public int getViews()
    {
        return this.views;
    }


    // ----------------------------------------------------------
    /**
     * return number of posts
     * 
     * @return number of posts
     */
    public int getPosts()
    {
        return this.posts;
    }


    // ----------------------------------------------------------
    /**
     * return follower count
     * 
     * @return follower count
     */
    public int getFollowers()
    {
        return this.followers;
    }


    // ----------------------------------------------------------
    /**
     * Set the likes field
     * 
     * @param likes
     *            - new likes value
     */
    public void setLikes(int likes)
    {
        this.likes = likes;
    }


    // ----------------------------------------------------------
    /**
     * Set the comments field
     * 
     * @param comments
     *            - new comments value
     */
    public void setComments(int comments)
    {
        this.comments = comments;
    }


    // ----------------------------------------------------------
    /**
     * Set views field
     * 
     * @param views
     *            - new views value
     */
    public void setViews(int views)
    {
        this.views = views;
    }


    // ----------------------------------------------------------
    /**
     * set posts field
     * 
     * @param posts
     *            - new posts field
     */
    public void setPosts(int posts)
    {
        this.posts = posts;
    }


    // ----------------------------------------------------------
    /**
     * set followers field
     * 
     * @param followers
     *            - new followers value
     */
    public void setFollowers(int followers)
    {
        this.followers = followers;
    }
}
