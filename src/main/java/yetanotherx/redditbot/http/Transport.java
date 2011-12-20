package yetanotherx.redditbot.http;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import yetanotherx.redditbot.RedditPlugin;
import yetanotherx.redditbot.exception.NetworkException;
import yetanotherx.redditbot.http.request.Request;
import yetanotherx.redditbot.http.response.Response;

public abstract class Transport {

    protected Request request;
    protected RedditPlugin plugin;

    public Transport(RedditPlugin plugin) {
        this.plugin = plugin;
    }

    public abstract Response sendURL(Request request) throws NetworkException;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public static Transport createTransport(RedditPlugin plugin) {
        TransportType type = plugin.getTransportType();

        switch (type) {
            case APACHE:
                return new ApacheTransport(plugin);
            case JSOUP:
                return new JSoupTransport(plugin);
        }
        
        return null;
    }

    public static String parseParameters(Request request) {
        String url = request.getURL();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (String key : request.getParameters().keySet()) {
            params.add(new BasicNameValuePair(key, request.getParameters().get(key)));
        }

        if (params.size() > 0) {
            url += "?" + URLEncodedUtils.format(params, "UTF-8");
        }
        
        return url;
    }
}
