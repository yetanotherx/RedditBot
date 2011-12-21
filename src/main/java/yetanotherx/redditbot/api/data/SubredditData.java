package yetanotherx.redditbot.api.data;

import java.math.BigDecimal;
import yetanotherx.redditbot.util.MapNode;

/**
 * Stores data about subreddits.
 * 
 * @author yetanotherx
 */
public class SubredditData {

    protected String displayName;
    protected String name;
    protected String title;
    protected String url;
    protected Integer created;
    protected Boolean nsfw;
    protected Integer subscribers;
    protected String id;
    protected String description;

    public static SubredditData newInstance(MapNode node) {
        SubredditData sr = new SubredditData();
        sr.created = new BigDecimal(node.getString("created_utc")).intValue();
        sr.description = node.getString("description");
        sr.displayName = node.getString("display_name");
        sr.id = node.getString("id");
        sr.name = node.getString("name");
        sr.nsfw = node.getBoolean("over18");
        sr.subscribers = node.getInteger("subscribers");
        sr.title = node.getString("title");
        sr.url = node.getString("url");

        return sr;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getFullname() {
        return name;
    }

    public void setFullname(String name) {
        this.name = name;
    }

    public Boolean getNSFW() {
        return nsfw;
    }

    public void setNSFW(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    public Integer getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Integer subscribers) {
        this.subscribers = subscribers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }
}
