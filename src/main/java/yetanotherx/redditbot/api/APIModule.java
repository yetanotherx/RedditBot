package yetanotherx.redditbot.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.exception.NetworkException;
import yetanotherx.redditbot.exception.ParserException;

public abstract class APIModule {
    
    protected RedditPlugin plugin;
    protected static final Pattern errorPattern = Pattern.compile("\\[\\\"\\.error\\.(.*)\\\"\\]");

    public APIModule(RedditPlugin plugin) {
        this.plugin = plugin;
    }
    
    public abstract void execute() throws NetworkException, ParserException;
    
    public static String getError(String content) {
        Matcher matcher = errorPattern.matcher(content);
        String result = matcher.group(1);
        if( result != null ) {
            return result;
        }
        
        return null;
    }
    
}
