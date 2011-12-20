package yetanotherx.redditbot.http.request;

import java.util.HashMap;
import yetanotherx.redditbot.RedditPlugin;

public abstract class Request {

    protected RedditPlugin plugin;
    protected String url = "";
    protected RequestType method = RequestType.GET;
    protected HashMap<String, String> parameters;

    public Request(RedditPlugin plugin, HashMap<String, String> parameters) {
        this.plugin = plugin;
        this.parameters = parameters;
    }

    public Request(RedditPlugin plugin) {
        this.plugin = plugin;
        this.parameters = new HashMap<String, String>();
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public RequestType getMethod() {
        return method;
    }

    public void setMethod(RequestType method) {
        this.method = method;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public RedditPlugin getPlugin() {
        return plugin;
    }
    
}
