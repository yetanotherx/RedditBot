package yetanotherx.redditbot.http.request;

import java.util.HashMap;
import yetanotherx.redditbot.RedditPlugin;

public class WebRequest extends Request {

    public WebRequest(RedditPlugin plugin) {
        super(plugin);
    }

    public WebRequest(RedditPlugin plugin, HashMap<String, String> parameters) {
        super(plugin, parameters);
    }

    
    
}
