package com.yetanotherx.reddit;

import org.apache.http.cookie.Cookie;
import com.yetanotherx.reddit.redditbot.http.ApacheTransport;
import com.yetanotherx.reddit.redditbot.http.Transport;
import net.lahwran.fevents.EventDispatcher;

/**
 * Main plugin controller.
 * 
 * TODO: Events
 * TODO: Tests
 * 
 * @author yetanotherx
 */
public abstract class RedditPlugin {

    protected EventDispatcher dispatcher;
    protected static String VERSION;
    protected Cookie cookie;
    protected String modHash;

    /**
     * User agent to send to the server. Override this to change.
     * 
     * @return String
     */
    public String getUserAgent() {
        return getName() + " " + getVersion();
    }

    /**
     * URL of reddit website. Ideally, this shouldn't need to be changed.
     * 
     * @return 
     */
    public String getRedditURL() {
        return "http://www.reddit.com";
    }

    /**
     * Name of the bot/plugin. Used in default user agent.
     * 
     * @return 
     */
    public abstract String getName();

    /**
     * Version of the bot/plugin. Used in default user agent.
     * 
     * @return 
     */
    public abstract String getVersion();

    /**
     * Returns the event manager
     * 
     * @return 
     */
    public EventDispatcher getDispatcher() {
        if (dispatcher == null) {
            dispatcher = new EventDispatcher();
        }
        return dispatcher;
    }

    /**
     * Creates a new transport instance and returns it.
     * 
     * @return 
     */
    public Transport getTransport() {
        Transport trans = new ApacheTransport(this);
        if (this.cookie != null) {
            trans.setCookie(this.cookie);
        }
        return trans;
    }

    /**
     * Gets the reddit_session cookie
     * 
     * @return 
     */
    public Cookie getCookie() {
        return cookie;
    }

    /**
     * Sets the reddit_session cookie
     * 
     * @param cookie 
     */
    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    /**
     * Gets the last known modhash value
     * 
     * @return 
     */
    public String getModHash() {
        return modHash;
    }

    /**
     * Sets the modhash value
     * 
     * @param modHash 
     */
    public void setModHash(String modHash) {
        this.modHash = modHash;
    }

    /**
     * Gets the package version of RedditBot itself
     * 
     * @return 
     */
    public static String getRedditBotVersion() {
        if (VERSION != null) {
            return VERSION;
        }

        Package p = RedditPlugin.class.getPackage();

        if (p == null) {
            p = Package.getPackage("yetanotherx.redditbot");
        }

        if (p == null) {
            VERSION = "(unknown)";
        } else {
            VERSION = p.getImplementationVersion();

            if (VERSION == null) {
                VERSION = "(unknown)";
            }
        }

        return VERSION;
    }

    /**
     * Manually sets the version of RedditBot
     * 
     * @param VERSION 
     */
    public static void setRedditBotVersion(String VERSION) {
        RedditPlugin.VERSION = VERSION;
    }

    static {
        getRedditBotVersion();
    }
}
