package com.yetanotherx.reddit.api.data;

import java.math.BigDecimal;
import com.yetanotherx.reddit.util.MapNode;

/**
 * Stores data about users.
 * 
 * @author yetanotherx
 */
public class AccountData {

    protected Boolean hasMail;
    protected String username;
    protected Integer created;
    protected String modhash;
    protected Integer linkKarma;
    protected Integer commentKarma;
    protected Boolean gold;
    protected Boolean mod;
    protected String id;
    protected Boolean hasModMail;

    public static AccountData newInstance(MapNode node) {
        AccountData account = new AccountData();
        account.commentKarma = node.getInteger("comment_karma");
        account.created = new BigDecimal(node.getString("created_utc")).intValue();
        account.gold = node.getBoolean("is_gold");
        account.hasMail = node.getBoolean("has_mail");
        account.hasModMail = node.getBoolean("has_mod_mail");
        account.id = node.getString("id");
        account.linkKarma = node.getInteger("link_karma");
        account.mod = node.getBoolean("is_mod");
        account.modhash = node.getString("modhash");
        account.username = node.getString("name");

        return account;
    }

    public Integer getCommentKarma() {
        return commentKarma;
    }

    public void setCommentKarma(Integer commentKarma) {
        this.commentKarma = commentKarma;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Boolean isGold() {
        return gold;
    }

    public void setGold(Boolean gold) {
        this.gold = gold;
    }

    public Boolean hasMail() {
        return hasMail;
    }

    public void setHasMail(Boolean hasMail) {
        this.hasMail = hasMail;
    }

    public Boolean hasModMail() {
        return hasModMail;
    }

    public void setHasModMail(Boolean hasModMail) {
        this.hasModMail = hasModMail;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public Integer getLinkKarma() {
        return linkKarma;
    }

    public void setLinkKarma(Integer linkKarma) {
        this.linkKarma = linkKarma;
    }

    public Boolean isMod() {
        return mod;
    }

    public void setMod(Boolean mod) {
        this.mod = mod;
    }

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
