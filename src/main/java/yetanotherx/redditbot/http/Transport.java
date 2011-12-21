package yetanotherx.redditbot.http;

import org.apache.http.cookie.Cookie;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.response.Response;

/**
 * Core Transport class. Stores cookies, the request class, and an
 * abstract method for executing the Request.
 * 
 * @author yetanotherx
 */
public abstract class Transport {

    protected Request request;
    protected RedditPlugin plugin;
    protected Cookie cookie;

    public Transport(RedditPlugin plugin) {
        this.plugin = plugin;
    }

    public abstract Response sendURL();

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

}
