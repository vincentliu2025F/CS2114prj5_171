package prj5;

// -------------------------------------------------------------------------
/**
 *  Statistics "struct" used to help organize data in the influencer class.
 * 
 *  @author ryanjeronimus
 *  @version Nov 19, 2025
 */
public class Statistics {
    /**
     * Likes for a month
     */
    public int likes;
    /**
     * Monthly comments
     */
    public int comments;
    /**
     * monthly views
     */
    public int views;
    /**
     * Monthyl posts
     */
    public int posts;
    /**
     * Monthly followers
     */
    public int followers;

    // ----------------------------------------------------------
    /**
     * Create a new Statistics object.
     * @param likes - influencer likes
     * @param comments - influencer comments
     * @param views - influencer views
     * @param posts - influencer posts
     * @param followers - influencer followers.
     */
    public Statistics(int likes, int comments, int views, int posts, int followers) {
        this.likes = likes;
        this.comments = comments;
        this.views = views;
        this.posts = posts;
        this.followers = followers;
    }

    public Statistics() {
        // empty stats (defaults to 0)
    }
}