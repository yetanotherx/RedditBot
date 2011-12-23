package com.yetanotherx.reddit.api.data;

import java.math.BigDecimal;
import com.yetanotherx.reddit.util.MapNode;
import com.yetanotherx.reddit.util.VoteType;

/**
 * Stores data about comments.
 * 
 * @author yetanotherx
 */
public class CommentData {
    
    protected String text;
    protected String html;
    protected String subreddit_id;
    protected String subreddit;
    protected String flair_class;
    protected String flair_text;
    protected Integer created;
    protected Integer ups;
    protected Integer downs;
    protected String author;
    protected String link_id;
    protected String parent_id;
    protected VoteType my_vote;
    protected String id;
    protected String fullname;
    
    public static CommentData newInstance(MapNode node) {
        CommentData com = new CommentData();
        com.author = node.getString("author");
        com.created = new BigDecimal(node.getString("created_utc")).intValue();
        com.downs = node.getInteger("downs");
        com.flair_class = node.getString("author_flair_css_class");
        com.flair_text = node.getString("author_flair_text");
        com.fullname = node.getString("name");
        com.html = node.getString("body_html");
        com.id = node.getString("id");
        com.link_id = node.getString("link_id");
        com.my_vote = VoteType.newFromBoolean(node.getBoolean("likes"));
        com.parent_id = node.getString("parent_id");
        com.subreddit = node.getString("subreddit");
        com.subreddit_id = node.getString("subreddit_id");
        com.text = node.getString("body");
        com.ups = node.getInteger("ups");
        
        return com;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getDownvotes() {
        return downs;
    }

    public void setDownvotes(Integer downs) {
        this.downs = downs;
    }

    public String getFlairCSSClass() {
        return flair_class;
    }

    public void setFlairCSSClass(String flair_class) {
        this.flair_class = flair_class;
    }

    public String getFlairText() {
        return flair_text;
    }

    public void setFlairText(String flair_text) {
        this.flair_text = flair_text;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getHTML() {
        return html;
    }

    public void setHTML(String html) {
        this.html = html;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getLinkID() {
        return link_id;
    }

    public void setLinkID(String link_id) {
        this.link_id = link_id;
    }

    public VoteType getMyVote() {
        return my_vote;
    }

    public void setMyVote(VoteType my_vote) {
        this.my_vote = my_vote;
    }

    public String getParentID() {
        return parent_id;
    }

    public void setParentID(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getSubredditID() {
        return subreddit_id;
    }

    public void setSubredditID(String subreddit_id) {
        this.subreddit_id = subreddit_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUpvotes() {
        return ups;
    }

    public void setUpvotes(Integer ups) {
        this.ups = ups;
    }
    
}
