package yetanotherx.redditbot.api.types;

import java.math.BigDecimal;
import yetanotherx.redditbot.util.MapNode;

public class Link {
    
    protected String domain;
    protected String subreddit;
    protected String selftext_html;
    protected String selftext;
    protected Boolean my_vote;
    protected Boolean saved;
    protected String id;
    protected String author;
    protected Integer score;
    protected Boolean nsfw;
    protected Boolean hidden;
    protected String thumbnail;
    protected Integer upvotes;
    protected Integer downvotes;
    protected Boolean self;
    protected String permalink;
    protected String fullname;
    protected Integer created_utc;
    protected String url;
    protected String title;
    protected Integer comments;

    public static Link newInstance(MapNode node) {
        Link link = new Link();
        link.author = node.getString("author");
        link.comments = node.getInteger("num_comments");
        link.created_utc = new BigDecimal(node.getString("created_utc")).intValue();
        link.domain = node.getString("domain");
        link.downvotes = node.getInteger("downs");
        link.fullname = node.getString("name");
        link.hidden = node.getBoolean("hidden");
        link.id = node.getString("id");
        link.my_vote = node.getBoolean("likes");
        link.nsfw = node.getBoolean("over_18");
        link.permalink = node.getString("permalink");
        link.saved = node.getBoolean("saved");
        link.score = node.getInteger("score");
        link.self = node.getBoolean("is_self");
        link.selftext = node.getString("selftext");
        link.selftext_html = node.getString("selftext_html");
        link.subreddit = node.getString("subreddit");
        link.thumbnail = node.getString("thumbnail");
        link.title = node.getString("title");
        link.upvotes = node.getInteger("ups");
        link.url = node.getString("url");
        
        return link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getCreated() {
        return created_utc;
    }

    public void setCreated(Integer created_utc) {
        this.created_utc = created_utc;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public Boolean getMyVote() {
        return my_vote;
    }

    public void setMyVote(Boolean my_vote) {
        this.my_vote = my_vote;
    }

    public Boolean isNSFW() {
        return nsfw;
    }

    public void setNSFW(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Boolean isSaved() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean isSelf() {
        return self;
    }

    public void setSelf(Boolean self) {
        this.self = self;
    }

    public String getSelfText() {
        return selftext;
    }

    public void setSelfText(String selftext) {
        this.selftext = selftext;
    }

    public String getSelfTextHTML() {
        return selftext_html;
    }

    public void setSelfTextHTML(String selftext_html) {
        this.selftext_html = selftext_html;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }
    
    
    
}
