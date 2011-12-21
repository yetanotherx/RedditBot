package yetanotherx.redditbot.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import yetanotherx.redditbot.RedditPlugin;

/**
 * Base module for API modules. Stores the plugin and 
 * an error pattern to search for.
 * 
 * @author yetanotherx
 */
public abstract class APIModule {

    protected RedditPlugin plugin;
    protected static final Pattern errorPattern = Pattern.compile("\\.error\\.(.*?)(\\.|\")"); //TODO: Broken

    public APIModule(RedditPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Checks for an error message in the result. If it's not
     * found, it returns null. If it is, returns the error.
     * 
     * @param content
     * @return 
     */
    public static String getError(String content) {
        Matcher matcher = errorPattern.matcher(content);
        try {
            matcher.find();
            String result = matcher.group(1);
            if (result != null) {
                return result;
            }
        } catch (Exception e) {
        }

        return null;
    }
}
