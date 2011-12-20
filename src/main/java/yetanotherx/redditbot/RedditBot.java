package yetanotherx.redditbot;

public class RedditBot {
    
    protected static String VERSION;
    
    public static void startBot(RedditPlugin plugin) {
        
    }

    public static String getVersion() {
        if (VERSION != null) {
            return VERSION;
        }

        Package p = RedditBot.class.getPackage();

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

    public static void setVersion(String VERSION) {
        RedditBot.VERSION = VERSION;
    }
    
    static {
        getVersion();
    }
    
}
