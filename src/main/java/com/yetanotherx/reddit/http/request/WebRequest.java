package com.yetanotherx.reddit.http.request;

import java.util.HashMap;
import com.yetanotherx.reddit.RedditPlugin;

/**
 * Implements Request in a usable format
 * 
 * @author yetanotherx
 */
public class WebRequest extends Request {

    public WebRequest(RedditPlugin plugin) {
        super(plugin);
    }

    public WebRequest(RedditPlugin plugin, HashMap<String, String> parameters) {
        super(plugin, parameters);
    }

    
    
}
