package yetanotherx.redditbot.http.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import yetanotherx.redditbot.RedditPlugin;

/**
 * Core Request class. Stores the URL, the request method, parameters to include,
 * HTTP headers to send, and anything else important to the request.
 * 
 * @author yetanotherx
 */
public abstract class Request {

    protected RedditPlugin plugin;
    protected String url = "";
    protected RequestType method = RequestType.GET;
    protected Map<String, String> parameters;
    protected List<Header> headers;

    public Request(RedditPlugin plugin, Map<String, String> parameters, List<Header> headers) {
        this.plugin = plugin;
        this.parameters = parameters;
        this.headers = headers;
    }

    public Request(RedditPlugin plugin, Map<String, String> parameters) {
        this.plugin = plugin;
        this.parameters = parameters;
        this.headers = new ArrayList<Header>();
    }

    public Request(RedditPlugin plugin) {
        this.plugin = plugin;
        this.parameters = new HashMap<String, String>();
        this.headers = new ArrayList<Header>();
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParameters() {
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
