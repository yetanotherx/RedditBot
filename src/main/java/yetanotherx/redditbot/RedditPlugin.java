package yetanotherx.redditbot;

import yetanotherx.redditbot.http.Transport;
import yetanotherx.redditbot.http.TransportType;
import yetanotherx.redditbot.lahwran.fevents.EventDispatcher;

/**
 * Main plugin controller.
 * 
 * TODO: Events
 * TODO: Tests
 * 
 * @author yetanotherx
 */
public abstract class RedditPlugin {

    private EventDispatcher dispatcher;
    private static String VERSION;
    
    public EventDispatcher getDispatcher() {
        if( dispatcher == null ) {
            dispatcher = new EventDispatcher();
        }
        return dispatcher;
    }
    
    public Transport getTransport() {
        return Transport.createTransport(this);
    }

    public TransportType getTransportType() {
        return TransportType.JSOUP;
    }
    
    public String getUserAgent() {
        return getName() + getVersion();
    }
    
    public String getRedditURL() {
        return "http://reddit.com";
    }
    
    public abstract String getName();
    public abstract String getVersion();
    public abstract void run();
    
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

    public static void setRedditBotVersion(String VERSION) {
        RedditPlugin.VERSION = VERSION;
    }
    
    static {
        getRedditBotVersion();
    }
    
}
